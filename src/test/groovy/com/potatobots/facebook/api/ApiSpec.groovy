package com.potatobots.facebook.api

import com.potatobots.facebook.Application
import com.potatobots.facebook.config.Env
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class ApiSpec extends Specification {

    @Shared
    RESTClient restClient

    def setupSpec() {
        Application.main()
        sleep 1000
    }

    def setup() {
        restClient = new RESTClient(Env.appUrl())
        restClient.setContentType 'application/json'
    }

}
