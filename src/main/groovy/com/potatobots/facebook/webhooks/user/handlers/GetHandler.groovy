package com.potatobots.facebook.webhooks.user.handlers

import com.potatobots.facebook.config.Env
import io.vertx.ext.web.RoutingContext
import org.apache.logging.log4j.LogManager

class GetHandler {

    static LOGGER = LogManager.getLogger GetHandler

    static CHALLENGE_KEY = 'hub.challenge'
    static VERIFY_TOKEN_KEY = 'hub.verify_token'

    static handle = { RoutingContext context ->
        LOGGER.info "[GET ] ${context.normalisedPath()}"

        def challenge = context.request().getParam CHALLENGE_KEY
        def verifyToken = context.request().getParam VERIFY_TOKEN_KEY

        LOGGER.info "params=($challenge, $verifyToken)"
        def response = context.response()
        if (verifyToken == Env.facebookWebhookToken()) {
            response.putHeader('content-type', 'application/json')
                    .end(challenge)
        } else {
            response.putHeader('content-type', 'application/json')
                    .setStatusCode(400)
                    .end()
        }
    }

}
