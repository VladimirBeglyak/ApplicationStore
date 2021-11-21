package by.beglyakdehterenok.store.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailOnUnique.class)
public @interface CheckEmail {

    String message() default "account.form.valid.email";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] allEmails();

}
