package com.volunteeride.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class represents the custom implementation for Log out success handler which
 * just returns back the status on successful log out instead of redirection.
 *
 * Created by ayazlakdawala on 11/2/2015.
 */
//Get ride of this class when updated to Spring security 4.0.2+
public class CustomHttpStatusReturningLogoutSuccessHandler implements LogoutSuccessHandler {

    private final HttpStatus httpStatusToReturn;

    /**
     * Initialize the {@code HttpStatusLogoutSuccessHandler} with a user-defined
     * {@link HttpStatus}.
     *
     * @param httpStatusToReturn Must not be {@code null}.
     */
    public CustomHttpStatusReturningLogoutSuccessHandler(HttpStatus httpStatusToReturn) {
        Assert.notNull(httpStatusToReturn, "The provided HttpStatus must not be null.");
        this.httpStatusToReturn = httpStatusToReturn;
    }

    /**
     * Initialize the {@code HttpStatusLogoutSuccessHandler} with the default
     * {@link HttpStatus#OK}.
     */
    public CustomHttpStatusReturningLogoutSuccessHandler() {
        this.httpStatusToReturn = HttpStatus.OK;
    }

    /**
     * Implementation of {@link LogoutSuccessHandler#onLogoutSuccess(HttpServletRequest, HttpServletResponse, Authentication)}.
     * Sets the status on the {@link HttpServletResponse}.
     */
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.setStatus(httpStatusToReturn.value());
        response.getWriter().flush();
    }
}
