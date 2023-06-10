package com.snapped.web.service.contract;

import com.snapped.web.model.SnappedResponse;

import java.io.IOException;

public interface SnappedMainService {

    public SnappedResponse getHomePage();

    SnappedResponse getAllCategoryPhotos(String category) throws Exception;
}
