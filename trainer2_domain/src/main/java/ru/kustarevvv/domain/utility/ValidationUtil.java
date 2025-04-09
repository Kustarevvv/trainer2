package ru.kustarevvv.domain.utility;

import java.util.Objects;

public final class ValidationUtil {
    private ValidationUtil() {}

    public static void validateNotBlank(String variableUnderValidation, String errorMessage) {
        if (Objects.isNull(variableUnderValidation) || variableUnderValidation.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}