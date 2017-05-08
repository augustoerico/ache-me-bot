package com.potatobots.facebook.config

class Env {

    /**
     * Infrastructure configuration
     */
    static port() {
        def port = System.getenv().PORT ?: '3000'
        Integer.parseInt(port)
    }

    static host() {
        System.getenv().HOST ?: 'localhost'
    }

    /**
     * App configuration
     */
    static poolingInterval() {
        def interval = System.getenv().POOLING_INTERVAL ?: '300000'
        Long.parseLong(interval)
    }

    static facebookGroupId() {
        System.getenv().FACEBOOK_GROUP_ID ?: 'facebook.group.id'
    }

    static facebookWebhookToken() {
        System.getenv().FACEBOOK_WEBHOOK_TOKEN ?: 'facebook.webhook.token'
    }

    /**
     * Integration
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
