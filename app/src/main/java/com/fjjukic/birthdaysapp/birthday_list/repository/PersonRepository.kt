package com.fjjukic.birthdaysapp.birthday_list.repository

import com.fjjukic.birthdaysapp.GetPeopleQuery
import io.reactivex.rxjava3.core.Observable


/**
 * Interface which provides method signature to get list of people
 */
interface PersonRepository {
    fun getPeople(): Observable<GetPeopleQuery.Data>
}