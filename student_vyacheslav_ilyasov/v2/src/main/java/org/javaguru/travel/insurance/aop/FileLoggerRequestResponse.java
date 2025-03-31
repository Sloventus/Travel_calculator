package org.javaguru.travel.insurance.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.javaguru.travel.insurance.dto.V2.TravelCalculatePremiumRequestV2;
import org.javaguru.travel.insurance.dto.V2.TravelCalculatePremiumResponseV2;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FileLoggerRequestResponse {

    private final ObjectMapper objectMapper = new ObjectMapper();


    public void logging(TravelCalculatePremiumRequestV1 request) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            log.info("REQUEST{}", objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            log.info("Не удается отобразить лог запроса");
        }
    }

    public void logging(TravelCalculatePremiumResponseV1 response) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            log.info("RESPONSE{}", objectMapper.writeValueAsString(response));
        } catch (JsonProcessingException e) {
            log.info("Не удается отобразить лог ответа");
        }
    }

    public void logging(TravelCalculatePremiumRequestV2 request) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            log.info("REQUEST{}", objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            log.info("Не удается отобразить лог запроса");
        }
    }

    public void logging(TravelCalculatePremiumResponseV2 response) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            log.info("RESPONSE{}", objectMapper.writeValueAsString(response));
        } catch (JsonProcessingException e) {
            log.info("Не удается отобразить лог ответа");
        }
    }
}
