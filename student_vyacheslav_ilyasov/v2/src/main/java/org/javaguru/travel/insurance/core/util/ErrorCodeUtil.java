package org.javaguru.travel.insurance.core.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Properties;


@Component
public class ErrorCodeUtil {

    private Properties props;

    ErrorCodeUtil() throws IOException {
        Resource resource = new ClassPathResource("errorCodes.properties");
        props = PropertiesLoaderUtils.loadProperties(resource);
    }

    public String getErrorDescription(String code) {
        return props.getProperty(code,"Wrong ERROR_CODE number");
    }

    public String getErrorDescription(String errorCode, List<Placeholder> placeholders) {
        String description = props.getProperty(errorCode,"Wrong ERROR_CODE number");

        //value or name of placeholder
        return placeholders.stream()
                .map(placeholder ->
                        description.replaceFirst("placeholder",placeholder.getPlaceholderValue()))
                .toList().toString();
    }
}
