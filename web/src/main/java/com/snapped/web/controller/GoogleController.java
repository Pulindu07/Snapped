package com.snapped.web.controller;


import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.snapped.web.Constants;
import com.snapped.web.model.GoogleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/snapped/")
public class GoogleController {

    private final Storage storage;

    @Autowired
    public GoogleController(Storage storage) {
        this.storage = storage;
    }

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        Bucket bucket = storage.get(Constants.BUCKET_NAME);
        String filename = file.getOriginalFilename();
        Blob blob = bucket.create(filename, file.getBytes(), file.getContentType());
        return blob.getMediaLink();
    }

    @GetMapping("{category}/{filename}")
    public List<GoogleResponse> downloadPhoto(@PathVariable String category,@PathVariable String filename) {
        List<GoogleResponse> fileNames = new ArrayList<>();

        BlobInfo blobInfo = BlobInfo.newBuilder(Constants.BUCKET_NAME+"/"+category, filename).build();
        long duration = 1; // URL duration in minutes
        URL signedUrl = storage.signUrl(blobInfo, duration, TimeUnit.MINUTES);
        fileNames.add(
                new GoogleResponse(
                        blobInfo.getName(),
                        signedUrl.toString()
                )
        );

        return fileNames;
    }

    @GetMapping("/all-files/{category}")
    public List<GoogleResponse> getAllFiles(@PathVariable String category) {
        // Replace with your actual bucket name
        List<GoogleResponse> fileNames = new ArrayList<>();

        Bucket bucket = storage.get(Constants.BUCKET_NAME);
        String prefix = category + "/";
        Page<Blob> blobs = bucket.list(Storage.BlobListOption.prefix(prefix));

        for (Blob blob : blobs.iterateAll()) {
            BlobInfo blobInfo = BlobInfo.newBuilder(Constants.BUCKET_NAME, blob.getName()).build();
            long duration = 1; // URL duration in minutes
            URL signedUrl = storage.signUrl(blobInfo, duration, TimeUnit.MINUTES);

            if (!blob.getName().equals(category.concat("/"))){
                fileNames.add(
                        new GoogleResponse(
                                blob.getName(),
                                signedUrl.toString()
                        )
                );
            }
        }

        return fileNames;
    }
}

