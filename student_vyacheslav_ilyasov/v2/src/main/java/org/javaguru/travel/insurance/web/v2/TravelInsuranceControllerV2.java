package org.javaguru.travel.insurance.web.v2;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.V2.DtoV2Converter;
import org.javaguru.travel.insurance.dto.V2.TravelCalculatePremiumRequestV2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelInsuranceControllerV2 {

    private final TravelCalculatePremiumService service;
    private final DtoV2Converter converter;

    @GetMapping("/insurance/travel/web/v2/")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV2());
        return "travel-calculate-premium-v2";
    }

    @PostMapping("/insurance/travel/web/v2/")
    public String processForm(@ModelAttribute(value = "request") TravelCalculatePremiumRequestV2 request,
                              ModelMap modelMap) {

        var command = converter.buildCommand(request);
        var response = converter.buildResponseV2(service.calculatePremium(command));
        modelMap.addAttribute("response", response);


        return "travel-calculate-premium-v2";
    }

}
