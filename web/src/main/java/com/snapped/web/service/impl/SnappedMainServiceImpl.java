package com.snapped.web.service.impl;

import com.snapped.web.model.GoogleResponse;
import com.snapped.web.model.SnappedResponse;
import com.snapped.web.persistance.entity.PhotoEntity;
import com.snapped.web.persistance.repository.PhotoRepository;
import com.snapped.web.service.contract.SnappedMainService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class SnappedMainServiceImpl implements SnappedMainService {

    Logger logger = LoggerFactory.getLogger(SnappedMainServiceImpl.class);

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    PhotoRepository photoRepository;

    private final RestTemplate restTemplate;
    public SnappedMainServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    public SnappedResponse getHomePage() {


        List responseList = new ArrayList<>();

        responseList.add(
               new SnappedResponse.PhotoDetails(
                       0L,
                       "Title_1",
                       "Category_1",
                       "Description_1",
                       "Link_1",
                       "orientation"
               )
        );
        responseList.add(
                new SnappedResponse.PhotoDetails(
                        1L,
                        "Title_2",
                        "Category_2",
                        "Description_2",
                        "Link_2",
                        "orientation"
                )
        );

       return new SnappedResponse(
               responseList
       );
    }

    @Override
    public SnappedResponse getAllCategoryPhotos(String category) throws Exception {
        List responseList = new ArrayList<>();
        List<GoogleResponse>googleResponse = sendRequestToGoogle(category);

        logger.info("Response from bucket - {}", googleResponse);

        for (GoogleResponse res : googleResponse){

            String cloudId = res.getName();
            PhotoEntity photoEntity = photoRepository.findOneByCloudId(cloudId);

            logger.info("*** cloudId - {} ***", cloudId);

            if (photoEntity != null){
                logger.info("Photo Entity - {}", photoEntity.toString());
                responseList.add(
                        new SnappedResponse.PhotoDetails(
                                photoEntity.getId(),
                                photoEntity.getTitle(),
                                photoEntity.getCategory(),
                                photoEntity.getDescription(),
                                res.getUrl(),
                                photoEntity.getOrientation()
                        )
                );
            }
            else {
                logger.info("Photo Entity Not Found.");
            }

        }
        return new SnappedResponse(responseList);
    }


    public List<GoogleResponse> sendRequestToGoogle(String category) throws Exception {
        String url = baseUrl.concat("/snapped/all-files/").concat(category);

        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        String authorizationHeader = "Basic " + base64Credentials;

        // Create the headers and add the "Authorization" header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        // Create the HTTP request entity with the headers
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<GoogleResponse>> responseType = new ParameterizedTypeReference<List<GoogleResponse>>() {};

        try{
            // Send the GET request
            ResponseEntity<List<GoogleResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
            return responseEntity.getBody();
        }
        catch(HttpClientErrorException ex){
            logger.error("Error while connecting to cloud - {}", ex.getMessage());
            throw new AuthenticationException();
        }
    }
}
