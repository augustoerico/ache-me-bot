package com.potatobots.facebook.webhook_integration.handlers

import com.potatobots.facebook.config.Env
import io.vertx.ext.web.RoutingContext
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class GetHandler {

    static final Logger LOGGER = LogManager.getLogger GetHandler

    static handle = { RoutingContext context ->
        def hubChallenge = context.request().getParam('hub_challenge')
        def hubVerifyToken = context.request().getParam('hub_verify_token')
        LOGGER.info "params=($hubChallenge, $hubVerifyToken)"
        def response = context.response()
        if (hubVerifyToken == Env.facebookWebhookToken()) {
            response.putHeader('content-type', 'application/json')
                    .end(Env.facebookWebhookToken())
        } else {
            response.putHeader('content-type', 'application/json')
                    .setStatusCode(400)
                    .end()
        }
    }

}
