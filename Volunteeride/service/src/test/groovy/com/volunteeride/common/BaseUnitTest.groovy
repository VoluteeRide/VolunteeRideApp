package com.volunteeride.common

import com.volunteeride.exception.BaseVolunteerideRuntimeException
import com.volunteeride.exception.ValidationException
import spock.lang.Specification

/**
 * Created by ayazlakdawala on 9/7/15.
 */
class BaseUnitTest extends Specification {

    void assertExpectedException(BaseVolunteerideRuntimeException vExcp, String expectedMsg, String expectedCustomCause,
                                 String expectedResolution, String expectedErrCode) {

        assert vExcp.message == expectedMsg
        assert vExcp.errorCode == expectedErrCode
        assert vExcp.customCause == expectedCustomCause
        assert vExcp.resolution == expectedResolution
    }

    void testForEmptyOrNull(ValidationException vExcp, String nullObjName){
        StringBuilder expectedMsg = new StringBuilder("Required Data is missing : ");
        expectedMsg.append(nullObjName);
        def expectedErrCode = "VR1-01"
        def expectedCustomCause = null
        def expectedResolution = "Please provide the required " + nullObjName + " data."
        this.assertExpectedException(vExcp, expectedMsg.toString(), expectedCustomCause, expectedResolution, expectedErrCode)
    }


}
