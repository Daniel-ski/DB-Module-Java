package UserSystem.annotations.email;

import UserSystem.constants.Constants;
import jakarta.validation.Constraint;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message () default Constants.INVALID_EMAIL_FORMAT;
    int minUserNameLength() default 1;

    int maxUserNameLength() default 50;

    int maxHostNameLength() default 50;

    String regex() default Constants.EMAIL_VALIDATION_REGEX;
}
