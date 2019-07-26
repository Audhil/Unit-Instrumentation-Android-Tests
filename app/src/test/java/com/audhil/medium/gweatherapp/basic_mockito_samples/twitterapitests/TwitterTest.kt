package com.audhil.medium.gweatherapp.basic_mockito_samples.twitterapitests

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TwitterTest {

    @Mock
    lateinit var iTweet: ITweet

    @Mock
    lateinit var twitterClient: TwitterClient

    @Test
    fun `test sending tweet`() {
        `when`(iTweet.getTweet()).thenReturn("jack and jill")
        val value = twitterClient.sendTweet(iTweet)
        verify(iTweet, atLeastOnce()).getTweet()
        assertEquals("here's the result", "jack and jill", value)
    }
}