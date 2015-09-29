//
// Built on Sat Sep 05 19:24:19 CEST 2015 by logback-translator
// For more information on configuration files in Groovy
// please see http://logback.qos.ch/manual/groovy.html

// For assistance related to this tool or configuration files
// in general, please contact the logback user mailing list at
//    http://qos.ch/mailman/listinfo/logback-user

// For professional support please see
//   http://www.qos.ch/shop/products/professionalSupport

/**
 * Created by ayazlakdawala on 9/5/15.
 */

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.ERROR

//Specify the location of log file
def LOG_FILE_HOME = "/Users/ayazlakdawala/ayaz/vRLogs"
def LOG_FILE = "${LOG_FILE_HOME}/vRideApp.log"
def ROLLING_LOG_FILE = "${LOG_FILE_HOME}/vRideRolling"
def ENCODER_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
def MAX_FILE_SIZE = "1MB"

//Console Appender
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "$ENCODER_PATTERN"
    }
}

//File Appender
appender("FILE", FileAppender) {
    file = "${LOG_FILE}"
    encoder(PatternLayoutEncoder) {
        pattern = "$ENCODER_PATTERN"
    }
}

//Rolling Log file Appender based on Size and time based archiving
//The follwing appender rolls over daily or when the size reaches max size.
appender("ROLLING_FILE_SIZE_AND_TIME", RollingFileAppender) {
    file = "${ROLLING_LOG_FILE}.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${ROLLING_LOG_FILE}-%d{yyyy-MM-dd}.%i.log"
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "${MAX_FILE_SIZE}"
        }
    }
    encoder(PatternLayoutEncoder) {
        pattern = "$ENCODER_PATTERN"
    }
}

//Rolling Log file Appender based on Fixed Window Rolling Policy
//Following appender deletes last index log file when file index reaches max index
appender("ROLLING_FILE_FIXED_WINDOW", RollingFileAppender) {
    file = "${ROLLING_LOG_FILE}.log"
    rollingPolicy(FixedWindowRollingPolicy) {
        fileNamePattern = "${ROLLING_LOG_FILE}.%i.log"
        minIndex = 1
        maxIndex = 3
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
        maxFileSize = "${MAX_FILE_SIZE}"
    }
    encoder(PatternLayoutEncoder) {
        pattern = "$ENCODER_PATTERN"
    }
}


//logger takes logger name,log level, appender name and additivity as parameters
logger("com.volunteeride", ERROR, ["ROLLING_FILE_FIXED_WINDOW"], false)

root(ERROR, ["STDOUT", "ROLLING_FILE_FIXED_WINDOW"])