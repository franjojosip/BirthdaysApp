package com.fjjukic.birthdaysapp

import com.apollographql.apollo.ApolloClient
import com.fjjukic.birthdaysapp.base.di.ApolloConnector
import com.fjjukic.birthdaysapp.birthday_list.repository.PersonRepository
import com.fjjukic.birthdaysapp.birthday_list.repository.PersonRepositoryImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * JUnit test for testing PersonRepository api call
 */
@RunWith(JUnit4::class)
class PersonRepositoryTest {

    /**
     * This part can be refactored with KoinTestRule
     * To avoid manually creation of ApolloClient and repository
     */

    private lateinit var apolloClient: ApolloClient
    private lateinit var repository: PersonRepository

    /**
     * SetUp required client and repository
     */
    @Before
    fun setUp() {
        apolloClient = ApolloConnector.provideClient()
        repository = PersonRepositoryImpl(apolloClient)
    }

    /**
     * Test if API call success returning either empty or full list
     */
    @Test
    fun `Check getPeople API call and result value`() {
        val data = repository.getPeople().test().await().assertNoErrors().assertComplete().values()
        Assert.assertNotEquals(null, data)
    }

}