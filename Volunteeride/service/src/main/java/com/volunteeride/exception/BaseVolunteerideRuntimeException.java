package com.volunteeride.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionKeyConstants.*;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionResourceConstants.exceptionResourceBundle;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.exceptionArgumentBundle;

/**
 * This class acts as a base class for runtime exceptions appearing within volunteeride application.
 * <p>
 * Created by ayazlakdawala on 9/3/15.
 */
public class BaseVolunteerideRuntimeException extends RuntimeException {

    protected static ResourceBundle excpRsrcBundle = exceptionResourceBundle;
    protected static ResourceBundle excpArgBundle = exceptionArgumentBundle;

    protected String errorCode;

    protected String resolution;

    protected String customCause;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BaseVolunteerideRuntimeException() {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param messageKey the key to retrieve detailed message for exception from.
     */
    public BaseVolunteerideRuntimeException(String messageKey) {
        super(excpRsrcBundle.getString(messageKey));

        if(excpRsrcBundle.containsKey(messageKey + CAUSE_KEY_SUFFIX)){
            this.customCause = excpRsrcBundle.getString(messageKey + CAUSE_KEY_SUFFIX);
        }

        if(excpRsrcBundle.containsKey(messageKey + RESOLUTION_KEY_SUFFIX)){
            this.resolution = excpRsrcBundle.getString(messageKey + RESOLUTION_KEY_SUFFIX);
        }

        if(excpRsrcBundle.containsKey(messageKey + ERROR_CODE_KEY_SUFFIX)){
            this.errorCode = excpRsrcBundle.getString(messageKey + ERROR_CODE_KEY_SUFFIX);
        }
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public BaseVolunteerideRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message     the detail message (which is saved for later retrieval
     *                    by the {@link #getMessage()} method).
     * @param cause       the cause (which is saved for later retrieval by the
     *                    {@link #getCause()} method).  (A <tt>null</tt> value is
     *                    permitted, and indicates that the cause is nonexistent or
     *                    unknown.)
     * @param errorCode   represents errorcode related to exception
     * @param resolution  represents possible solution for the exception.
     * @param customCause represents custom configured cause of the exception.
     * @since 1.4
     */
    public BaseVolunteerideRuntimeException(String message, Throwable cause, String customCause, String resolution, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.resolution = resolution;
        this.customCause = customCause;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message     the detail message. The detail message is saved for
     *                    later retrieval by the {@link #getMessage()} method.
     * @param errorCode   represents errorcode related to exception
     * @param resolution  represents possible solution for the exception.
     * @param customCause represents custom configured cause of the exception.
     */
    public BaseVolunteerideRuntimeException(String message, String customCause, String resolution, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.resolution = resolution;
        this.customCause = customCause;
    }

    public BaseVolunteerideRuntimeException(String messageKey, Object[] args) {
        super(MessageFormat.format(excpRsrcBundle.getString(messageKey), args));

        if(excpRsrcBundle.containsKey(messageKey + CAUSE_KEY_SUFFIX)){
            this.customCause = MessageFormat.format(excpRsrcBundle.getString(messageKey + CAUSE_KEY_SUFFIX), args);
        }

        if(excpRsrcBundle.containsKey(messageKey + RESOLUTION_KEY_SUFFIX)){
            this.resolution = MessageFormat.format(excpRsrcBundle.getString(messageKey + RESOLUTION_KEY_SUFFIX), args);
        }

        if(excpRsrcBundle.containsKey(messageKey + ERROR_CODE_KEY_SUFFIX)){
            this.errorCode = MessageFormat.format(excpRsrcBundle.getString(messageKey + ERROR_CODE_KEY_SUFFIX), args);
        }

    }
    
    public String getErrorCode() {
        return errorCode;
    }

    public String getResolution() {
        return resolution;
    }

    public String getCustomCause() {
        return customCause;
    }
}
