package com.client.management.validators.impl;

import com.client.management.enums.DeviceType;
import com.client.management.enums.DocumentType;
import com.client.management.utils.ValidatorUtil;
import com.client.management.validators.EnumValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(final EnumValidator constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
        if (enumClass == DocumentType.class) {
            return ValidatorUtil.checkDocumentType(value);
        }
        if (enumClass == DeviceType.class) {
            return ValidatorUtil.checkDeviceType(value);
        }
        return false;
    }
}