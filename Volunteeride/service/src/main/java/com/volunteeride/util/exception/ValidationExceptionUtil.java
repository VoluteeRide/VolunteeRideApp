package com.volunteeride.util.exception;

import com.volunteeride.exception.ValidationException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * Created by ayazlakdawala on 9/7/15.
 */
public class ValidationExceptionUtil {

    public static void validateForEmptyOrNull(Object objToValidate, String arg){

        StringBuilder excpMsg = new StringBuilder("Required Data is missing : ");
        excpMsg.append(arg);

        String excpResolution = "Please provide the required data.";

        String excpErrorCode = "VR1-01";

        boolean validationFailed = false;

        if(objToValidate instanceof Collection){
           if(CollectionUtils.isEmpty((Collection)objToValidate)){
               validationFailed = true;
           }
        } else if(null == objToValidate){
            validationFailed = true;
        }

        if(validationFailed){
            throw new ValidationException(excpMsg.toString(), null, excpResolution, excpErrorCode);
        }

    }

}
