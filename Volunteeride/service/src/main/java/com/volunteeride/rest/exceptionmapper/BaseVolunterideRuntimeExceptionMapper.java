package com.volunteeride.rest.exceptionmapper;

import com.volunteeride.exception.BaseVolunteerideRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by ayazlakdawala on 11/4/15.
 */
@Provider
public class BaseVolunterideRuntimeExceptionMapper implements ExceptionMapper<BaseVolunteerideRuntimeException>{

    private static final Logger logger = LoggerFactory.getLogger(BaseVolunterideRuntimeExceptionMapper.class);

    /**
     * Map an exception to a {@link Response}. Returning
     * {@code null} results in a {@link Response.Status#NO_CONTENT}
     * response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     *
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(BaseVolunteerideRuntimeException bvrExcp) {

        logger.error("Base Volunteeride Runtime Exception: ", bvrExcp);

        ErrorMessage errorMessage = new ErrorMessage(bvrExcp.getMessage(), bvrExcp.getCustomCause(),
                bvrExcp.getResolution(), bvrExcp.getErrorCode());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
