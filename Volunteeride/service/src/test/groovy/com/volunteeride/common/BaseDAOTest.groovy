package com.volunteeride.common

import com.volunteeride.springconfig.MongoDBConfig
import com.volunteeride.springconfig.TestDBConfig
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
