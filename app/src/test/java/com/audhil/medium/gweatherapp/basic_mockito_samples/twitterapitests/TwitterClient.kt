package com.audhil.medium.gweatherapp.basic_mockito_samples.twitterapitests

open class TwitterClient {
    fun sendTweet(iTweet: ITweet): String {
        val tweet = iTweet.getTweet()
        //  do the sending logic
        return tweet
    }
}