package com.volunteeride.common

import com.volunteeride.exception.BaseVolunteerideRuntimeException
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


}
