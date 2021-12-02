package com.rhinemann.divinecatsource

import com.rhinemann.divinecatsource.core.util.Util.getScaledSize
import org.junit.Assert.assertEquals
import org.junit.Test

class GetScaledSizeTest {

    @Test
    fun widthMoreThenHeigth() {
        assertEquals(
            992 to 744,
            getScaledSize(
                sourceSize = 4032 to 3024,
                maxSize = 992 to 1510
            )
        )
    }

    @Test
    fun widthEqualsHeigth() {
        assertEquals(
            992 to 992,
            getScaledSize(
                sourceSize = 3024 to 3024,
                maxSize = 992 to 1510
            )
        )
    }

    @Test
    fun heigthMoreThenWidth() {
        assertEquals(
            992 to 1322,
            getScaledSize(
                sourceSize = 768 to 1024,
                maxSize = 992 to 1510
            )
        )
    }

}