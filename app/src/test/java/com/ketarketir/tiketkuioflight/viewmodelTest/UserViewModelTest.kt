package com.ketarketir.tiketkuioflight.viewmodelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.networking.ApiService
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var userManager: UserManager

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var viewModel: UserViewModel

    // Coroutine testing variables
    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        viewModel = UserViewModel(apiService, userManager)
    }
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        @Suppress("DEPRECATION")
        testCoroutineScope.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetToken() = testDispatcher.runBlockingTest {
        // Mock data
        val expectedToken = "123456"
        Mockito.`when`(userManager.getToken()).thenReturn(expectedToken)

        // Run the function
        viewModel.getToken()

        // Advance the dispatcher to execute coroutines
        testDispatcher.advanceUntilIdle()

        // Verify the result
        val actualToken = viewModel.token.value
        TestCase.assertEquals(expectedToken, actualToken)
    }
}