package com.volunteeride.common

import com.volunteeride.TestDBConfig
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by ayazlakdawala on 9/17/2015.
 */
@ContextConfiguration(classes = TestDBConfig)
@ActiveProfiles("test")
class BaseDAOTest extends Specification {
}
