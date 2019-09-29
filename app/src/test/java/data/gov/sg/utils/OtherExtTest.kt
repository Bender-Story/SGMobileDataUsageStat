package data.gov.sg.utils

import data.gov.sg.BaseTest
import org.junit.Assert
import org.junit.Test

class OtherExtTest :BaseTest(){
    @Test
    fun `format a value to 2 decimal point`(){
        val input=1.123
        val expected="1.12"
        Assert.assertEquals(expected,input.toFormatDouble(2))
    }
    @Test
    fun `format a value to 6 decimal point`(){
        val input=1.01234567
        val expected="1.012346"
        Assert.assertEquals(expected,input.toFormatDouble(6))
    }
    @Test
    fun `format a value to 1 decimal point`(){
        val input=1.123
        val expected="1.1"
        Assert.assertEquals(expected,input.toFormatDouble(1))
    }

    @Test
    fun `format a value to 3 decimal point`(){
        val input=0.0
        val expected="0.000"
        Assert.assertEquals(expected,input.toFormatDouble(3))
    }

}