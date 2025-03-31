package org.javaguru.travel.insurance.rest.v1;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.v1.DtoV1Converter;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v1")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumControllerV1 {

	private final TravelCalculatePremiumService calculatePremiumService;
	private final DtoV1Converter dtoV1Converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		TravelCalculatePremiumCoreCommand command = dtoV1Converter.buildCommand(request);
		TravelCalculatePremiumCoreResult travelCalculatePremiumCoreResult
				= calculatePremiumService.calculatePremium(command);

		return dtoV1Converter.buildResponseV1(travelCalculatePremiumCoreResult);
	}

}