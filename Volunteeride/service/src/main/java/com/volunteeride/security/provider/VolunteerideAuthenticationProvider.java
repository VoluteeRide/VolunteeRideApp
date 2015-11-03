package com.volunteeride.security.provider;

import com.volunteeride.dao.UserDAO;
import com.volunteeride.model.VolunteerideUser;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 *
 * Reference <a href="https://github.com/jthoms/spring-security-mongodb/blob/master/src/main/java/com/sustia/service/LocalAuthenticationProvider.java" />
 *
 * Created by ayazlakdawala on 11/3/2015.
 */
@Named
public class VolunteerideAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(VolunteerideAuthenticationProvider.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserDAO userDAO;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    //TODO Ayaz Externalize exception messages
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        String password = (String) authentication.getCredentials();

        if (!StringUtils.hasText(password)) {
            logger.warn("Username {}: no password provided", username);
            throw new BadCredentialsException("Please enter password");
        }

        VolunteerideUser loggedInUser = userDAO.findByUsername(username);

        if (loggedInUser == null) {
            logger.warn("Username {} password {}: user not found", username, password);
            throw new UsernameNotFoundException("Invalid Login");
        }

        if (!passwordEncoder.matches(password, loggedInUser.getPassword())) {
            logger.warn("Username {} password {}: invalid password", username, password);
            throw new BadCredentialsException("Invalid Login");
        }

        final List<GrantedAuthority> auths;
        if (CollectionUtils.isNotEmpty(loggedInUser.getUserRoles())) {
            auths = AuthorityUtils.commaSeparatedStringToAuthorityList(loggedInUser.getCommaSeperatedRoles());
        } else {
            auths = AuthorityUtils.NO_AUTHORITIES;
        }

        return new User(username, password, true, // enabled
                true, // account not expired
                true, // credentials not expired
                true, // account not locked
                auths);
    }
}
