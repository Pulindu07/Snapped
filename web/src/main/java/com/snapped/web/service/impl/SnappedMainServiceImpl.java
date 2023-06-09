package com.snapped.web.service.impl;

import com.snapped.web.service.contract.SnappedMainService;
import org.springframework.stereotype.Service;

@Service
public class SnappedMainServiceImpl implements SnappedMainService {
    @Override
    public String getHomePage() {
        return "Snapped HomePage";
    }

    @Override
    public String getCategories() {
        return "Snapped Category Page";
    }
}
