package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.ViewContract
import com.geekbrains.tests.view.details.ViewDetailsContract
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.lang.reflect.Field

class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter()
        presenter.onAttach(viewContract)
    }

    @Test
    fun onDetach_test() {
        presenter.onDetach()

        // use reflection to get access to private fields. shame on me
        val privateField: Field = DetailsPresenter::class.java.getDeclaredField("viewContract")
        privateField.isAccessible = true
        val fieldValue = privateField.get(presenter) as ViewContract?

        assertNull(fieldValue)
    }

    @Test
    fun onAttach_test() {
        presenter.onDetach()
        presenter.onAttach(viewContract)

        // use reflection to get access to private fields. shame on me
        val privateField: Field = DetailsPresenter::class.java.getDeclaredField("viewContract")
        privateField.isAccessible = true
        val fieldValue = privateField.get(presenter) as ViewContract?

        assertNotNull(fieldValue)
    }

    @Test
    fun setCounter_test() {
        // use reflection to get access to private fields. shame on me
        val privateField: Field = DetailsPresenter::class.java.getDeclaredField("count")
        privateField.isAccessible = true

        val startCounter = privateField.get(presenter) as Int
        val offset = 10
        presenter.setCounter(startCounter + offset)

        assertEquals(startCounter + offset, privateField.get(presenter) as Int)
    }

    @Test
    fun onIncrement_test() {
        presenter.onIncrement()
        verify(viewContract, times(1)).setCount(anyInt())
    }

    @Test
    fun onDecrement_test() {
        presenter.onDecrement()
        verify(viewContract, times(1)).setCount(anyInt())
    }
}
