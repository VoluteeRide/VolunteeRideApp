package com.volunteeride.common

import com.volunteeride.springconfig.MongoDBConfig
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by ayazlakdawala on 9/17/2015.
 */
@ContextConfiguration(classes = MongoDBConfig)
class BaseDAOTest extends Specification {
}
