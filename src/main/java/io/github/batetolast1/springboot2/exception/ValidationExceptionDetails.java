package io.github.batetolast1.springboot2.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationExceptionDetails extends ExceptionDetails {

    private String fields;
    private String fieldsMessages;
}
