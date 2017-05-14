package com.potatobots.facebook.utils

class QueryUrl {

    static CHALLENGE_KEY = 'hub.challenge'
    static VERIFY_TOKEN_KEY = 'hub.verify_token'

    static verification(context) {
        [
                challenge  : parameter(context, CHALLENGE_KEY),
                verifyToken: parameter(context, VERIFY_TOKEN_KEY)
        ]
    }

    static parameter(context, parameterName) {
        context.request().getParam parameterName
    }

}
