package com.potatobots.facebook.pooling.handlers

import org.apache.logging.log4j.LogManager

class PoolingHandler {

    static final LOGGER = LogManager.getLogger PoolingHandler

    static handle = {
        LOGGER.info 'Handling interval call'

    }

}
