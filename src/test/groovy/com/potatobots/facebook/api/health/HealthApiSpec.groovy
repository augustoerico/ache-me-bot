package com.potatobots.facebook.api

import com.potatobots.facebook.config.Env
import spock.util.concurrent.AsyncConditions

class HealthApiSpec extends ApiSpec {

    static final URI = '/health'

    def 'Should get application health status'() {
        def async = new AsyncConditions()

        when:
        httpClient.getNow(URI) { response ->
            if (response.statusCode() == 200) {
                async.evaluate { true }
            }
        }

        then:
        async.await Env.testWaitTime()
    }

}
