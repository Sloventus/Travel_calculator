package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    @Test
    void testBuildError_WithoutPlaceholders() {
        String errorCode = "ERROR_CODE_1";
        String errorDescription = "This is error 1";

        when(errorCodeUtil.getErrorDescription(errorCode)).thenReturn(errorDescription);

        ValidationErrorDTO error = validationErrorFactory.buildError(errorCode);

        assertNotNull(error);
        assertEquals(errorCode, error.getErrorCode());
        assertEquals(errorDescription, error.getDescription());
    }

    @Test
    void testBuildError_WithPlaceholders() {
        String errorCode = "ERROR_CODE_2";
        List<Placeholder> placeholders = List.of(new Placeholder("{placeholder}", "replacementValue"));

        when(errorCodeUtil.getErrorDescription(errorCode, placeholders))
                .thenReturn("This is error 2 with replacementValue");

        ValidationErrorDTO error = validationErrorFactory.buildError(errorCode, placeholders);

        assertNotNull(error);
        assertEquals(errorCode, error.getErrorCode());
        assertEquals("This is error 2 with replacementValue", error.getDescription());
    }
}