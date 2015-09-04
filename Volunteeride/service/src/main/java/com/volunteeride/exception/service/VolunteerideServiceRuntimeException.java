package com.volunteeride.exception.service;

import com.volunteeride.exception.BaseVolunteerideRuntimeException;

/**
 *
 * This class represents all the business layer runtime exceptions for Volunteeride Application.
 *
 * Created by ayazlakdawala on 9/3/15.
 */
public class VolunteerideServiceRuntimeException extends BaseVolunteerideRuntimeException {

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public VolunteerideServiceRuntimeException() {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public VolunteerideServiceRuntimeException(String message) {
        super(message);
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
    public VolunteerideServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message    the detail message (which is saved for later retrieval
     *                   by the {@link #getMessage()} method).
     * @param cause      the cause (which is saved for later retrieval by the
     *                   {@link #getCause()} method).  (A <tt>null</tt> value is
     *                   permitted, and indicates that the cause is nonexistent or
     *                   unknown.)
     * @param errorCode  represents errorcode related to exception
     * @param resolution represents possible solution for the exception.
     * @since 1.4
     */
    public VolunteerideServiceRuntimeException(String message, Throwable cause, String errorCode, String resolution) {
        super(message, cause, errorCode, resolution);
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message    the detail message. The detail message is saved for
     *                   later retrieval by the {@link #getMessage()} method.
     * @param errorCode  represents errorcode related to exception
     * @param resolution represents possible solution for the exception.
     */
    public VolunteerideServiceRuntimeException(String message, String errorCode, String resolution) {
        super(message, errorCode, resolution);
    }
}
