package org.stt.module.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stt.module.constants.FrameConstants;
import org.stt.module.util.base.BusinessContext;

/**
 * 
 * 日志记录
 *
 */
public class LogRecord {

    static Logger LOGGER = LoggerFactory.getLogger(LogRecord.class);

    public static void logInfoRecord(final String msg, final Class clazz) {

        LOGGER = LoggerFactory.getLogger(clazz);
        final String callId = "callId:" + BusinessContext.getProperty(FrameConstants.CALL_ID);
        LOGGER.info(callId + msg);
    }

    public static void logErrorRecord(final String msg, final Class clazz, final Exception e) {

        LOGGER = LoggerFactory.getLogger(clazz);
        final String callId = "callId:" + BusinessContext.getProperty(FrameConstants.CALL_ID);
        LOGGER.error(callId + msg, e);
    }
}
