package com.potatobots.facebook.api.health

import com.potatobots.facebook.api.ApiSpec

class HealthApiSpec extends ApiSpec {

    def 'Should get application health status'() {

        when:
        def response = restClient.get(path: '/health')

        and:
        def data = response.responseData
        def status = response.status

        then:
        status == 200
        data && data.status == 'OK'
    }

}
