package com.novko.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        private String INCORRECT_REQUEST = "Image size exceeds limit";

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public final ResponseEntity<String> handleImageSizeException(MaxUploadSizeExceededException ex, WebRequest request) {
        return new ResponseEntity<>(INCORRECT_REQUEST, HttpStatus.PAYLOAD_TOO_LARGE); //413
    }

}
//
//    private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
////    private String BAD_REQUEST = "BAD_REQUEST";
////    private String SQL_REQUEST = "SQL_REQUEST";
////    private String CONSTRAINT_REQUEST = "CONSTRAINT_REQUEST";
////
////
////    public GlobalExceptionHandler() {
////        super();
////    }
////
////    @ExceptionHandler(CustomResourceNotFoundException.class)
////    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
////            (CustomResourceNotFoundException ex, WebRequest request) {
////        List<String> details = new ArrayList<>();
////        details.add(ex.getLocalizedMessage());
////        ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
////        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
////    }
////
////    @ExceptionHandler(CustomMissingHeaderInfoException.class)
////    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException
////            (CustomMissingHeaderInfoException ex, WebRequest request) {
////        List<String> details = new ArrayList<>();
////        details.add(ex.getLocalizedMessage());
////        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
////        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
////    }
////
////    @ExceptionHandler(ConstraintViolationException.class)
////    public final ResponseEntity<ErrorResponse> handleConstraintException
////            (ConstraintViolationException ex, WebRequest request) {
////        List<String> details = new ArrayList<>();
////        details.add(ex.getLocalizedMessage());
////        ErrorResponse error = new ErrorResponse(CONSTRAINT_REQUEST, details);
////        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
////    }
////
////    @ExceptionHandler(DataIntegrityViolationException.class)
////    public final ResponseEntity<ErrorResponse> handleIntegrityViolationException
////            (DataIntegrityViolationException ex, WebRequest request) {
////        List<String> details = new ArrayList<>();
////        details.add(ex.getLocalizedMessage());
////        ErrorResponse error = new ErrorResponse(SQL_REQUEST, details);
////        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
////    }
//
//
//    //    @ExceptionHandler({ConstraintViolationException.class})
////    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
////        final String bodyOfResponse = "ConstraintViolationException";
////        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
////    }
////
////    @ExceptionHandler({DataIntegrityViolationException.class})
////    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
////        final String bodyOfResponse = "DataIntegrityViolationException";
////        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
////    }
//
//
//    //================================
//
//    // API
//
//    // 400
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
//        final String bodyOfResponse = "ConstraintViolationException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//
//    @ExceptionHandler({DataIntegrityViolationException.class})
//    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
//        final String bodyOfResponse = "DataIntegrityViolationException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//        final String bodyOfResponse = "This should be application specific";
//        // ex.getCause() instanceof JsonMappingException, JsonParseException // for additional information later on
//        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//        final String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(value = {CustomMissingHeaderInfoException.class})
//    protected ResponseEntity<Object> handleMissingHeader(final CustomMissingHeaderInfoException ex, final WebRequest request) {
//        final String bodyOfResponse = "CustomMissingHeaderInfoException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//
//    @ExceptionHandler(value = {CustomResourceNotFoundException.class})
//    protected ResponseEntity<Object> handleResourceNotFound(final CustomResourceNotFoundException ex, final WebRequest request) {
//        final String bodyOfResponse = "CustomResourceNotFoundException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
//    }
//
//    @ExceptionHandler(value = {CustomIllegalArgumentException.class})
//    protected ResponseEntity<Object> handleIllegalArgumentException(final CustomIllegalArgumentException ex, final WebRequest request) {
//        final String bodyOfResponse = "IllegalArgumentException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
//    }
//
//    // 404
//
//    @ExceptionHandler(value = {EntityNotFoundException.class})
//    protected ResponseEntity<Object> handleNotFound(final EntityNotFoundException ex, final WebRequest request) {
//        final String bodyOfResponse = "EntityNotFoundException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
//    }
//
//    // 409
//
//    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
//    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
//        final String bodyOfResponse = "DataAccessException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//
//    // 412
//
//    // 500
//
//    @ExceptionHandler(value = {IllegalStateException.class})
//    public ResponseEntity<Object> handleIllegalState(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//        final String bodyOfResponse = "IllegalStateException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }
//
//    @ExceptionHandler(value = {IllegalArgumentException.class})
//    public ResponseEntity<Object> handleIllegalArgument(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//        final String bodyOfResponse = "IllegalArgumentException framework";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//
//    @ExceptionHandler(value = {NullPointerException.class})
//    public ResponseEntity<Object> handleNullPointer(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//        final String bodyOfResponse = "NullPointerException";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }
//
//
////    @Override
////    protected ResponseEntity<Object> handleMethodArgumentNotValid(
////            MethodArgumentNotValidException ex,
////            HttpHeaders headers,
////            HttpStatus status,
////            WebRequest request) {
////        List<String> errors = new ArrayList<>();
////        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
////            errors.add(error.getField() + ": " + error.getDefaultMessage());
////        }
////        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
////            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
////        }
////
////        ApiError apiError =
////                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
////        return handleExceptionInternal(
////                ex, apiError, headers, apiError.getStatus(), request);
////    }
////
////    @Override
////    protected ResponseEntity<Object> handleMissingServletRequestParameter(
////            MissingServletRequestParameterException ex, HttpHeaders headers,
////            HttpStatus status, WebRequest request) {
////        String error = ex.getParameterName() + " parameter is missing";
////
////        ApiError apiError =
////                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
////        return new ResponseEntity<Object>(
////                apiError, new HttpHeaders(), apiError.getStatus());
////    }
////
////
////    @ExceptionHandler({ ConstraintViolationException.class })
////    public ResponseEntity<Object> handleConstraintViolation(
////            ConstraintViolationException ex, WebRequest request) {
////        List<String> errors = new ArrayList<String>();
////        for (ConstraintViolationException<?> violation : ex.getConstraintViolations()) {
////            errors.add(violation.getRootBeanClass().getName() + " " +
////                    violation.getPropertyPath() + ": " + violation.getMessage());
////        }
////
////        ApiError apiError =
////                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
////        return new ResponseEntity<Object>(
////                apiError, new HttpHeaders(), apiError.getStatus());
////    }
//
//    // -----------------
////
////
////    @ExceptionHandler(value
////            = {IllegalArgumentException.class, IllegalStateException.class})
////    protected ResponseEntity<Object> handleConflict(
////            RuntimeException ex, WebRequest request) {
////        String bodyOfResponse = "This should be application specific";
////        return handleExceptionInternal(ex, bodyOfResponse,
////                new HttpHeaders(), HttpStatus.CONFLICT, request);
////    }
////
////
////    @ExceptionHandler({AccessDeniedException.class})
////    public ResponseEntity<Object> handleAccessDeniedException(
////            Exception ex, WebRequest request) {
////        return new ResponseEntity<Object>(
////                "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
////    }
////    //baeldung
////
////    @ResponseStatus(value = HttpStatus.CONFLICT,
////            reason = "Data integrity violation")  // 409
////    @ExceptionHandler({DataIntegrityViolationException.class})
////    public void conflict() {
////    }
////
////
////    @ExceptionHandler({SQLException.class, DataAccessException.class})
////    public ResponseEntity<String> databaseError() {
////        return new ResponseEntity<>("Database error", HttpStatus.CONFLICT);
////    }
////
////    @ExceptionHandler(Exception.class)
////    public ResponseEntity<String> handleError(HttpServletRequest req, Exception ex) {
////        return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
////    }
//
//
//}
