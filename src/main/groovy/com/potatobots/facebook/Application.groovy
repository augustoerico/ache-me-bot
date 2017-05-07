package com.potatobots.facebook

import io.vertx.core.http.RequestOptions
import io.vertx.core.Vertx
import io.vertx.core.http.HttpClient

class Application {

    // FIXME this token expires
    static final String ACCESS_TOKEN = 'EAACEdEose0cBAEyRXvZAAngNRXv80YWZB6MmULLVQUoZCuv1QvdUh58Tprpzuh1KsN1bB3RKJQfrZAkrZCGHjonMRI1p4tx2RI756T0mvihbQwpskMhp5VStDs3BArcsk3tQ0Vj7ONr7PXZAFNdbfEAlIOSDmC3VqeZC87SvGc1gHTW8jRKb8n6nJt7To36o8wZD'

    static main(args) {
        println 'Hello, world'

        def vertx = Vertx.vertx()

        HttpClient client = vertx.createHttpClient()

        def requestOptions = new RequestOptions([
                host: 'graph.facebook.com',
                port: 443,
                uri: "/v2.9/802438716587415?access_token=$ACCESS_TOKEN\"",
                ssl: true
        ])
        client.getNow(requestOptions, { response ->
            response.bodyHandler {
                println it
            }
        })
    }
}
