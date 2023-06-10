package com.snapped.web.controller;

import com.snapped.web.model.SnappedResponse;
import com.snapped.web.service.contract.SnappedMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snapped/v1")
public class SnappedController {

    @Autowired
    private SnappedMainService snappedMainService;

    @GetMapping(path = "/web/home", produces = "application/json; charset=utf-8")
    public SnappedResponse getHomePage(){
        return snappedMainService.getHomePage();
    }

    @GetMapping(path = "/web/categories/{category}", produces = "application/json; charset=utf-8")
    public SnappedResponse getAllPhotosOfCategory(
            @PathVariable String category
    ) throws Exception {
        return snappedMainService.getAllCategoryPhotos(category);
    }

}
