package org.javaguru.travel.insurance.rest.v1;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
 abstract public class TravelCalculatePremiumControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    private String response(String request) throws Exception {

        return mockMvc.perform(post("/insurance/travel/api/v1/")
                        .content(request)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    protected void freeJsonAssert(String request, String responseOk) throws Exception {
        assertJson(response(request))
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(responseOk);
    }
}
