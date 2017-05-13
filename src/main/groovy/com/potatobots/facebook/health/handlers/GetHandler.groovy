package com.potatobots.facebook.health.handlers

import groovy.json.JsonOutput
import io.vertx.ext.web.RoutingContext
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class GetHandler {

    static final Logger LOGGER = LogManager.getLogger GetHandler

    static handle = { RoutingContext context ->
        LOGGER.info "[GET ] ${context.normalisedPath()}"

        def responseData = JsonOutput.toJson(status: 'OK', checkedAt: new Date())

        def response = context.response()
        response.putHeader('content-type', 'application/json')
                .end JsonOutput.prettyPrint(responseData)
    }
}
