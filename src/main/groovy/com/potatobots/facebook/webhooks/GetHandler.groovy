package com.potatobots.facebook.webhooks

import com.potatobots.facebook.config.Env
import com.potatobots.facebook.utils.QueryUrl
import io.vertx.ext.web.RoutingContext
import org.apache.logging.log4j.LogManager

class GetHandler {

    static LOGGER = LogManager.getLogger GetHandler

    static handle = { RoutingContext context ->
        LOGGER.info "[GET ] ${context.normalisedPath()}"

        def params = QueryUrl.verification(context)
        def response = context.response()

        params.verifyToken == Env.facebookWebhookToken() ?
                response.end(params.challenge as String) :
                response.setStatusCode(400).end()
    }

}
