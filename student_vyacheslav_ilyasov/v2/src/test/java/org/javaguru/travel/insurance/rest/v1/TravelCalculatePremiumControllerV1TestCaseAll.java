package org.javaguru.travel.insurance.rest.v1;

import org.javaguru.travel.insurance.rest.JsonFileReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelCalculatePremiumControllerV1TestCaseAll extends TravelCalculatePremiumControllerV1Test {

    @Autowired
    private JsonFileReader jsonFileReader;


    @ParameterizedTest
    @CsvSource(value = {
            "DateToMoreDoteFrom, dateIncorrectly"
            , "agreementDateToNull, dateIncorrectly"
            , "agreementDateFromNull, dateIncorrectly"
            , "lastNameNull, lastNameNull"
            , "firstNameNull, firstNameNull"
            , "requestOk, responseOk"
    })
    public void compareAll(String requestPath, String responsePath) throws Exception {
        String request = jsonFileReader.readJsonFromFile("rest/v1/requests/" + requestPath + ".json");
        String responseOk = jsonFileReader.readJsonFromFile("rest/v1/responses/" + responsePath + ".json");
        freeJsonAssert(request, responseOk);
    }
}
