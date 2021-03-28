package com.thanhxv.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.thanhxv.validation.constraint.CheckSizeConstraint;

public class CheckSizeValidator implements ConstraintValidator<CheckSizeConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.length() < 2)
			return false;
		return true;
	}

}
