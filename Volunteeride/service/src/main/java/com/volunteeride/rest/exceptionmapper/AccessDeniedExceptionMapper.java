package com.volunteeride.rest.exceptionmapper;

import com.volunteeride.exception.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by ayazlakdawala on 11/4/15.
 */
@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedExceptionMapper.class);

    /**
     * Map an exception to a {@link Response}. Returning
     * {@code null} results in a {@link Response.Status#NO_CONTENT}
     * response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     *
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(AccessDeniedException adExcp) {

        // Log Access denied Exception.
        logger.error("Access Denied Exception: ", adExcp);

        ErrorMessage errorMessage = new ErrorMessage(adExcp.getMessage(), adExcp.getCustomCause(),
                adExcp.getResolution(), adExcp.getErrorCode());

        return Response.status(Response.Status.FORBIDDEN).entity(errorMessage).build();
    }
}
