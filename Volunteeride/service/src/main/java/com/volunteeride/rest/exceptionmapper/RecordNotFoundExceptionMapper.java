package com.volunteeride.rest.exceptionmapper;

import com.volunteeride.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by ayazlakdawala on 9/29/15.
 */
@Provider
public class RecordNotFoundExceptionMapper implements ExceptionMapper<RecordNotFoundException> {

    private static final Logger logger = LoggerFactory.getLogger(RecordNotFoundExceptionMapper.class);

    /**
     * Map an exception to a {@link Response}. Returning
     * {@code null} results in a {@link Response.Status#NO_CONTENT}
     * response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     *
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(RecordNotFoundException rnfe) {
        // Log validation Exception.
        logger.error("Record Not Found Exception: ", rnfe);

        ErrorMessage errorMessage = new ErrorMessage(rnfe.getMessage(), rnfe.getCustomCause(), rnfe.getResolution(),
                rnfe.getErrorCode());

        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
