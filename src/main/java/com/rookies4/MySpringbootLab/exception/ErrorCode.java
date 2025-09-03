package com.rookies4.MySpringbootLab.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common errors
    RESOURCE_NOT_FOUND("%s not found with %s: %s", HttpStatus.NOT_FOUND),
    RESOURCE_DUPLICATE("%s already exists with %s: %s", HttpStatus.CONFLICT),
    RESOURCE_ALREADY_EXISTS("%s already exists: %s", HttpStatus.CONFLICT),

    // Student errors
    STUDENT_NUMBER_DUPLICATE("Student already exists with student number: %s", HttpStatus.CONFLICT),

    // StudentDetail errors
    EMAIL_DUPLICATE("Student detail already exists with email: %s", HttpStatus.CONFLICT),
    PHONE_NUMBER_DUPLICATE("Student detail already exists with phone number: %s", HttpStatus.CONFLICT),

    // Book errors
    ISBN_DUPLICATE("Book already exists with ISBN: %s", HttpStatus.CONFLICT),

    // Department errors
    DEPARTMENT_CODE_DUPLICATE("Department already exists with code: %s", HttpStatus.CONFLICT),
    DEPARTMENT_NAME_DUPLICATE("Department already exists with name: %s", HttpStatus.CONFLICT),
    DEPARTMENT_HAS_STUDENTS("Cannot delete department with id: %s. It has %s students", HttpStatus.CONFLICT),

    // Publisher errors (추가)
    PUBLISHER_NOT_FOUND("Publisher not found with id: %s", HttpStatus.NOT_FOUND),
    DUPLICATE_PUBLISHER_NAME("Publisher already exists with name: %s", HttpStatus.CONFLICT),
    PUBLISHER_DELETE_FAIL_HAS_BOOKS("Cannot delete publisher with id: %s. It has %s books", HttpStatus.CONFLICT);

    private final String messageTemplate;
    private final HttpStatus httpStatus;

    public String formatMessage(Object... args) {
        return String.format(messageTemplate, args);
    }
}
