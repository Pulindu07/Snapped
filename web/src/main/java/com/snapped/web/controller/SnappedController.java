package com.snapped.web.controller;

import com.snapped.web.service.contract.SnappedMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snapped/v1")
public class SnappedController {

    @Autowired
    private SnappedMainService snappedMainService;

    @GetMapping(path = "/web/home", produces = "application/json; charset=utf-8")
    public String getHomePage(){
        return snappedMainService.getHomePage();
    }

    @GetMapping(path = "/web/categories", produces = "application/json; charset=utf-8")
    public String getCategories(){
        return snappedMainService.getCategories();
    }

}
