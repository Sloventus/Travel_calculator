package org.javaguru.travel.insurance.rest.v2;

import org.javaguru.travel.insurance.rest.JsonFileReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelCalculatePremiumControllerV2TestCaseAll extends TravelCalculatePremiumControllerV2Test {

    @Autowired
    private JsonFileReader jsonFileReader;


    @ParameterizedTest
    @CsvSource(value = {
             "Ok",
            "SingleInvalidSelectRisk",
            "MultipleInvalidSelectRisk",
            "CountryNull",
            "CountryEmpty"
    })
    public void compareAll(String path) throws Exception {
        String request = jsonFileReader.readJsonFromFile("rest/v2/requests/" + path + ".json");
        String responseOk = jsonFileReader.readJsonFromFile("rest/v2/responses/" + path + ".json");
        freeJsonAssert(request, responseOk);
    }
}
