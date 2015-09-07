package com.volunteeride.util.exception;

import com.volunteeride.exception.ValidationException;

/**
 * Created by ayazlakdawala on 9/7/15.
 */
public class ValidationExceptionUtil {

    public static void validateForNull(Object objToValidate, String arg){

        StringBuilder excpMsg = new StringBuilder("Required Data is missing : ");
        excpMsg.append(arg);

        String excpResolution = "Please provide the required data.";

        String excpErrorCode = "VR1-01";

        if(null == objToValidate){
            throw new ValidationException(excpMsg.toString(), null, excpResolution, excpErrorCode);
        }

    }
}
