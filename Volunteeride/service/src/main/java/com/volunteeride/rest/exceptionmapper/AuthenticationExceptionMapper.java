package com.volunteeride.rest.exceptionmapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by ayazlakdawala on 11/4/15.
 */
@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException>{

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationExceptionMapper.class);

    /**
     * Map an exception to a {@link Response}. Returning
     * {@code null} results in a {@link Response.Status#NO_CONTENT}
     * response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     *
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(AuthenticationException aExcp) {

        // Log Authentication Exception.
        logger.error("Authentication Exception: ", aExcp);

        ErrorMessage errorMessage = new ErrorMessage(aExcp.getMessage(), null, null, null);

        return Response.status(Response.Status.UNAUTHORIZED).entity(errorMessage).build();    }
}
