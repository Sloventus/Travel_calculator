package org.javaguru.travel.insurance.rest.v2;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.V2.DtoV2Converter;
import org.javaguru.travel.insurance.dto.V2.TravelCalculatePremiumRequestV2;
import org.javaguru.travel.insurance.dto.V2.TravelCalculatePremiumResponseV2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v2")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumControllerV2 {

	private final TravelCalculatePremiumService calculatePremiumService;
	private final DtoV2Converter converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
		TravelCalculatePremiumCoreCommand command = converter.buildCommand(request);
		TravelCalculatePremiumCoreResult travelCalculatePremiumCoreResult
				= calculatePremiumService.calculatePremium(command);
		return converter.buildResponseV2(travelCalculatePremiumCoreResult);
	}

}