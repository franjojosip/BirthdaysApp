package com.fjjukic.birthdaysapp.birthday_list.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx3.Rx3Apollo
import com.fjjukic.birthdaysapp.GetPeopleQuery
import io.reactivex.rxjava3.core.Observable

/**
 * Repository for retrieving list of people with birthdays
 *
 * @param apolloClient - used for making API calls with ApolloClient
 * Parameter is retrieved through Koin DI
 */
class PersonRepositoryImpl(private val apolloClient: ApolloClient) : PersonRepository {

    /**
     * Rx3Apollo is used to transform ApolloCall to RxJava
     * Data is mapped so caller can get only response data
     *
     * @return GetPeopleQuery.Data - result from ApolloClient contains list of people
     */
    override fun getPeople(): Observable<GetPeopleQuery.Data> {
        return Rx3Apollo.from(apolloClient.query(GetPeopleQuery.builder().build())).map { it.data }
    }
}