package com.audhil.medium.gweatherapp.basic_mockito_samples

class SomeClass {

    fun multiply(x: Int, y: Int): Int {
        if (x > 100)
            throw IllegalArgumentException("Number should be less or equal to 100")
        return x * y
    }
}