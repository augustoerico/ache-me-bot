package com.potatobots.facebook.config

class Env {

    /**
     * Infrastructure configuration
     */
    static port() {
        def port = System.getenv().PORT ?: '3000'
        Integer.parseInt(port)
    }

    static address() {
        System.getenv().ADDRESS ?: 'localhost'
    }

    /**
     * App
     */
    static facebookAppId() {
        System.getenv().FACEBOOK_APP_ID ?: 'facebook.app.id'
    }

    static facebookAppSecret() {
        System.getenv().FACEBOOK_APP_SECRET ?: 'facebook.app.secret'
    }


    /**
     * Tests
     */
    static Double testWaitTime() {
        def time = System.getenv().TEST_WAIT_TIME ?: '5.0'
        Double.parseDouble(time)
    }

}
