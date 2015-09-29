package com.volunteeride.rest.exceptionmapper;

import com.volunteeride.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This class catches all the validation exceptions in Volunteeride App
 * <p>
 * Created by ayazlakdawala on 9/26/15.
 */

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    private static final Logger logger = LoggerFactory.getLogger(ValidationExceptionMapper.class);



    /**
     * Map an exception to a {@link Response}. Returning
     * {@code null} results in a {@link Response.Status#NO_CONTENT}
     * response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     *
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(ValidationException vExcp) {

        // Log validation Exception.
        logger.error("Validation Exception: ", vExcp);

        ErrorMessage errorMessage = new ErrorMessage(vExcp.getMessage(), vExcp.getCustomCause(), vExcp.getResolution(),
                vExcp.getErrorCode());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
