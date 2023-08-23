package com.bbrustol.support.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class MainCoroutinesRule : TestRule {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    override fun apply(base: Statement, description: Description) =
        object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(testDispatcher)
                base.evaluate()
                Dispatchers.resetMain()
            }
        }

    fun runTest(block: suspend TestScope.() -> Unit) = testScope.runTest { block }
}
