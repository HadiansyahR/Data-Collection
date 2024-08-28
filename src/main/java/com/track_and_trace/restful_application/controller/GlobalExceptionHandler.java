package com.track_and_trace.restful_application.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.track_and_trace.restful_application.exception.DatabaseException;
import com.track_and_trace.restful_application.model.WebResponse;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<String> handleDatabaseException(DatabaseException ex) {

        return new ResponseEntity<>("Database error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<WebResponse<String>> handleInvalidFormatException(InvalidFormatException ex) {
        String fieldName = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .reduce((first, second) -> second)
                .orElse("Unknown field");

        String targetType = ex.getTargetType().getSimpleName();
        String invalidValue = ex.getValue().toString();

        String errorMessage = String.format("Invalid value '%s' for field '%s'. Expected type: %s.",
                invalidValue, fieldName, targetType);

        HttpStatus status = HttpStatus.BAD_REQUEST; // Or another status based on the exception details

        WebResponse<String> response = new WebResponse<>(
                null, errorMessage, 0
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<WebResponse<String>> handleMismatchedInputException(MismatchedInputException ex) {
        String fieldName = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .reduce((first, second) -> second)
                .orElse("Unknown field");

        String targetType = ex.getTargetType().getSimpleName();

        String errorMessage = String.format("Invalid value for field '%s'. Expected type: %s.",
                fieldName, targetType);

        HttpStatus status = HttpStatus.BAD_REQUEST; // Or another status based on the exception details

        WebResponse<String> response = new WebResponse<>(
                null, errorMessage, 0
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<WebResponse<String>> handlePSQLException(PSQLException ex) {
        HttpStatus status;
        String errorMessage;

        // Determine the error based on SQL error codes or SQLState
        switch (ex.getSQLState()) {
            case "23505": // Unique violation
                status = HttpStatus.CONFLICT;
                errorMessage = "A record with the same key already exists.";
                break;
            case "23503": // Foreign key violation
                status = HttpStatus.BAD_REQUEST;
                errorMessage = "A referenced record does not exist.";
                break;
            case "23514": // Check constraint violation
                status = HttpStatus.BAD_REQUEST;
                errorMessage = "Input data does not meet the required constraints.";
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                errorMessage = extractRelevantDetail(ex.getMessage());
                break;
        }

        WebResponse<String> response = new WebResponse<>(
                null, errorMessage, 0
        );
        return new ResponseEntity<>(response, status);
    }

    private String extractRelevantDetail(String message) {
        // Adjust this regex pattern to match the detail part of the message
        String pattern = "Detail: (.+)";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(message);

        if (matcher.find()) {
            return matcher.group(1); // Extract the detail part
        }

        return "An unexpected database error occurred."; // Default message if pattern not found
    }

//    @ExceptionHandler(PSQLException.class)
//    public ResponseEntity<WebResponse<String>> handlePSQLException(PSQLException ex) {
//        // You can map the PSQLException to a specific HttpStatus
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Or another status based on the exception details
//
//        WebResponse<String> response = new WebResponse<>(
//                null, ex.getMessage(), 0
//        );
//        return new ResponseEntity<>(response, status);
//    }
}
