package com.potatobots.facebook.api.users

import com.potatobots.facebook.api.ApiSpec
import com.potatobots.facebook.config.Env

class UsersApiSpec extends ApiSpec {

    def 'Should validate webhook integration'() {

        given:
        def query = ['hub.challenge': 'challenge', 'hub.verify_token': Env.facebookWebhookToken()]

        when:
        restClient.setContentType('text/html')

        and:
        def response = restClient.get(path: '/webhooks/user', query: query)

        and:
        def status = response.status
        def data = response.responseData

        then:
        status == 200
        data == 'challenge'
    }

}
