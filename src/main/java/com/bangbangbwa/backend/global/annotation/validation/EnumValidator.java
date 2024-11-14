package com.bangbangbwa.backend.global.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {

  private ValidEnum annotation;

  @Override
  public void initialize(ValidEnum constraintAnnotation) {
    this.annotation = constraintAnnotation;
  }

  @Override
  public boolean isValid(Enum value, ConstraintValidatorContext context) {
    boolean result = false;
    Object[] enumValues = this.annotation.enumClass().getEnumConstants();
    if (Objects.nonNull(enumValues)) {
      for (Object enumValue : enumValues) {
        if (Objects.equals(enumValue, value)) {
          result = true;
          break;
        }
      }
    }
    return result;
  }
}
