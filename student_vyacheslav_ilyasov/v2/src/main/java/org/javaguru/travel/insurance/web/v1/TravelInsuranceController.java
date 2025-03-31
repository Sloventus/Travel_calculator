package org.javaguru.travel.insurance.web.v1;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.v1.DtoV1Converter;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelInsuranceController {

    private final TravelCalculatePremiumService service;
    private final DtoV1Converter converter;

    @GetMapping("/insurance/travel/web/v1/")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV1());
        return "travel-calculate-premium-v1";
    }

    @PostMapping("/insurance/travel/web/v1/")
    public String processForm(@ModelAttribute(value = "request") TravelCalculatePremiumRequestV1 request,
                              ModelMap modelMap) {

        var command = converter.buildCommand(request);
        var response = converter.buildResponseV1(service.calculatePremium(command));
        modelMap.addAttribute("response", response);


        return "travel-calculate-premium-v1";
    }

}
