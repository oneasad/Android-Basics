package com.example.tiptime

import java.text.NumberFormat
import org.junit.Assert.assertEquals
import org.junit.Test

class TipCalculatorTest {

    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)

    }
}