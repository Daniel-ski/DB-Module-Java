package annotations;

import jakarta.validation.ConstraintValidatorContext;

public enum AnnotationUtils {
    ;

    public static void setErrorMessage(final ConstraintValidatorContext context,final String errorMessage){

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}
