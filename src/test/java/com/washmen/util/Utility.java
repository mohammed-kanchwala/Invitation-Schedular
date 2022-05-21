package com.washmen.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.washmen.entity.Partner;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Utility {

    public static String createURL(int port, String urlEndpoint) {
        return ApiConstants.HTTP_LOCALHOST + port + ApiConstants.REQUEST_MAPPING_API + urlEndpoint;
    }

    public static List<Partner> fetchPartnersFromResponse(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Partner.class));
    }

    public static void addQueryParametersToBuilder(Map<String, String> queryParams, UriComponentsBuilder builder) {
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
    }
}
