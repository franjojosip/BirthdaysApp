package com.fjjukic.birthdaysapp.birthday_list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.fjjukic.birthdaysapp.GetPeopleQuery
import com.fjjukic.birthdaysapp.base.live_data.SingleLiveData
import com.fjjukic.birthdaysapp.base.utils.DateConverter
import com.fjjukic.birthdaysapp.base.view_model.AppVM
import com.fjjukic.birthdaysapp.birthday_list.model.PersonUI
import com.fjjukic.birthdaysapp.birthday_list.repository.PersonRepository
import com.fjjukic.birthdaysapp.birthday_list.view.BirthdayListFragmentDirections
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * BirthdayListVM class provides data retrieved from API call
 * Inherits AppVM for error handling and disposables
 *
 * @param repository - used for retrieving list of people from server
 *
 * @property navDirections - SingleLiveData which emit a navigation event only once so View can handle navigation with given NavDirections
 * @property personList - LiveData which represents list of people retrieved from API and mapped for UI
 */

class BirthdayListVM(private val repository: PersonRepository) : AppVM() {

    private val _navDirections = SingleLiveData<NavDirections>()
    val navDirections: LiveData<NavDirections> = _navDirections

    private val _personList = MutableLiveData<List<PersonUI>>()
    val personList: LiveData<List<PersonUI>> = _personList

    /**
     * Initialize data with API call if there is no data saved in ViewModel
     * Error is handled with extension and added to disposable
     *
     * @param subscribeOnScheduler - set thread used for IO (network call)
     * @param subscribeOnWorkScheduler - set thread used for mapping, work, etc.
     * @param observeOnScheduler - set thread used to emit data to subscribers
     */
    fun initData(
        subscribeOnScheduler: Scheduler = Schedulers.io(),
        subscribeOnWorkScheduler: Scheduler = Schedulers.computation(),
        observeOnScheduler: Scheduler = AndroidSchedulers.mainThread()
    ) {
        if (personList.value != null) return

        repository.getPeople()
            .subscribeOn(subscribeOnScheduler)
            .map {
                getPersonList(it.person())
            }
            .subscribeOn(subscribeOnWorkScheduler)
            .observeOn(observeOnScheduler)
            .subscribeWithErrorHandling {
                _personList.value = it
            }
            .addToDisposable()
    }

    /**
     * Prepare data from server for UI
     * Handles empty data received from server
     * Sort by date descending
     *
     * @param data - data received from server
     */
    private fun getPersonList(data: MutableList<GetPeopleQuery.Person>): List<PersonUI> {
        return when {
            data.isNullOrEmpty() -> mutableListOf()
            else -> {
                data.map { person ->
                    PersonUI(
                        initials = getInitials(person.name()),
                        name = person.name(),
                        dateOfBirth = DateConverter.formatString(person.date_of_birth().toString())
                    )
                }.sortedByDescending {
                    DateConverter.convertStringToDate(it.dateOfBirth)
                }.apply {
                    first().isFirstRow = true
                }
            }
        }
    }

    /**
     * @return - return initials from given string (name)
     * @param name
     */
    private fun getInitials(name: String): String {
        return name.split(" ", limit = 2)
            .filter { it.isNotEmpty() }
            .map { it.first() }
            .joinToString("")
            .uppercase()
    }

    /**
     * Handle item clicked and create NavDirections for navigation to BirthdaySingle
     * @param data - represents clicked person from UI
     */
    fun handleItemClicked(data: PersonUI) {
        _navDirections.setValue(
            BirthdayListFragmentDirections.actionBirthdayListToSingle(
                initials = data.initials,
                name = data.name,
                years = data.dateOfBirth
            )
        )
    }
}