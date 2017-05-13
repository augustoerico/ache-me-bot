package com.potatobots.facebook.api

import com.potatobots.facebook.Application
import com.potatobots.facebook.config.Env
import io.vertx.core.Vertx
import io.vertx.core.http.HttpClient
import io.vertx.core.http.HttpClientOptions
import spock.lang.Shared
import spock.lang.Specification

class ApiSpec extends Specification {

    @Shared
    HttpClient httpClient

    def setupSpec() {
        Vertx vertx = Vertx.vertx()

        Map options = [defaultHost: Env.host(), defaultPort: Env.port()]
        httpClient = vertx.createHttpClient(new HttpClientOptions(options))

        Application.main()
        sleep 1000
    }

}
