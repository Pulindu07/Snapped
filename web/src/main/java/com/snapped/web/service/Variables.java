package com.snapped.web.service;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class Variables {

    private final String hello = "Hello";
}
