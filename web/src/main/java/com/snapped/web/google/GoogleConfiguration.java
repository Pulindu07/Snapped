package com.snapped.web.google;


import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class GoogleConfiguration {

    @Bean
    public Storage googleCloudStorage() throws IOException {
        // Replace "path/to/service-account-key.json" with the actual path to your service account key file
        Credentials credentials = GoogleCredentials
                .fromStream(
                        new ClassPathResource("google/snapped-389316-a7a27dfe682a.json").getInputStream()
                );
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}

