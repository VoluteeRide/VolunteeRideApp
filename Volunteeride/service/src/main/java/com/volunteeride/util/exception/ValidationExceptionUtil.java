package com.volunteeride.util.exception;

import com.volunteeride.common.constants.VolunteerideApplicationConstants;
import com.volunteeride.exception.ValidationException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.ResourceBundle;

import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionArgumentConstants.exceptionArgumentBundle;
import static com.volunteeride.common.constants.VolunteerideApplicationConstants.ExceptionResourceConstants.REQUIRED_DATA_MISSING_EXCEPTION_KEY;

/**
 * Created by ayazlakdawala on 9/7/15.
 */
public class ValidationExceptionUtil {

    protected static ResourceBundle excpArgBundle = exceptionArgumentBundle;

    public static void validateForEmptyOrNull(Object objToValidate, Object[] argKeys){

        boolean validationFailed = false;

        if(objToValidate instanceof Collection){
           if(CollectionUtils.isEmpty((Collection)objToValidate)){
               validationFailed = true;
           }
        } else if(null == objToValidate){
            validationFailed = true;
        }

        if(validationFailed){
            throw new ValidationException(REQUIRED_DATA_MISSING_EXCEPTION_KEY, resolveExceptionArguments(argKeys));
        }

    }


    /**
     * This function takes in array of argument keys and resolves to
     * proper String arguments defined in Property file.
     *
     * @param args
     * @return
     */
    private static Object[] resolveExceptionArguments(Object[] args){

        if(null != args){
            for(int i=0; i< args.length; i++){
                args[i] = excpArgBundle.getString((String)args[i]);
            }
        }

        return args;
    }

}
