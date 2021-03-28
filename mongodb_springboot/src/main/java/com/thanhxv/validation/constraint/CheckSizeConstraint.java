package com.thanhxv.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.thanhxv.validation.validator.CheckSizeValidator;

@Documented
@Constraint(validatedBy = CheckSizeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSizeConstraint {
	String message() default "Not allow size < 2";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
