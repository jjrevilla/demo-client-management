package com.client.management.dto.requests;

import com.client.management.enums.DocumentType;
import com.client.management.validators.EnumValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.client.management.utils.Constants.DOCUMENT_NUMBER_INVALID_MESSAGE;
import static com.client.management.utils.Constants.DOCUMENT_NUMBER_PATTERN;
import static com.client.management.utils.Constants.DOCUMENT_NUMBER_REQUIRED_MESSAGE;
import static com.client.management.utils.Constants.DOCUMENT_TYPE_INVALID_MESSAGE;
import static com.client.management.utils.Constants.DOCUMENT_TYPE_REQUIRED_MESSAGE;
import static com.client.management.utils.Constants.FATHER_LAST_NAME_REQUIRED_MESSAGE;
import static com.client.management.utils.Constants.MOTHER_LAST_NAME_REQUIRED_MESSAGE;
import static com.client.management.utils.Constants.NAME_INVALID_MESSAGE;

@RequiredArgsConstructor
@Getter
@Setter
public class ClientRequest {

    @NotBlank(message = NAME_INVALID_MESSAGE)
    private String name;

    @NotBlank(message = FATHER_LAST_NAME_REQUIRED_MESSAGE)
    private String fatherLastName;

    @NotNull(message = MOTHER_LAST_NAME_REQUIRED_MESSAGE)
    private String motherLastName;

    @NotNull(message = DOCUMENT_TYPE_REQUIRED_MESSAGE)
    @EnumValidator(enumClass = DocumentType.class, message = DOCUMENT_TYPE_INVALID_MESSAGE)
    private String documentType;

    @NotBlank(message = DOCUMENT_NUMBER_REQUIRED_MESSAGE)
    @Pattern(regexp = DOCUMENT_NUMBER_PATTERN, message = DOCUMENT_NUMBER_INVALID_MESSAGE)
    private String documentNumber;
}