package com.fjjukic.birthdaysapp.birthday_list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fjjukic.birthdaysapp.birthday_list.adapter.BirthdayListRecyclerAdapter
import com.fjjukic.birthdaysapp.birthday_list.listener.OnItemClickedListener
import com.fjjukic.birthdaysapp.birthday_list.model.PersonUI
import com.fjjukic.birthdaysapp.birthday_list.view_model.BirthdayListVM
import com.fjjukic.birthdaysapp.databinding.FragmentBirthdayListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * View for birthday list
 *
 * @property binding - access to layout through data binding
 * @property adapter - adapter used on RecyclerView to show data
 * @property viewModel - viewModel class from which data is observed, used with Koin DI
 */
class BirthdayListFragment : Fragment() {
    private var binding: FragmentBirthdayListBinding? = null
    private lateinit var adapter: BirthdayListRecyclerAdapter
    private val viewModel: BirthdayListVM by viewModel()

    /**
     * Called once when Activity is created
     * SetUp click event for items and create BirthdayListRecyclerAdapter
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clickListener = object : OnItemClickedListener {
            override fun onClicked(data: PersonUI) {
                viewModel.handleItemClicked(data)
            }
        }
        adapter = BirthdayListRecyclerAdapter(mutableListOf(), clickListener)
    }

    /**
     * Create data binding
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBirthdayListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    /**
     * Initialize data
     */
    override fun onResume() {
        super.onResume()
        viewModel.initData()
    }

    /**
     * SetUp RecyclerView layout manager and adapter
     */
    private fun setUpRecyclerView() {
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.recyclerView?.adapter = adapter
    }

    /**
     * SetUp observers for data changes and if navigation event occur
     */
    private fun setUpObservers() {
        viewModel.personList.observeNotNull {
            adapter.setData(it.toMutableList())
        }
        viewModel.navDirections.observeNotNull {
            findNavController().navigate(it)
        }
    }

    /**
     * Destroy binding to avoid memory leaks
     */
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    /**
     * Extension used for filtering null values from LiveData
     */
    private fun <T> LiveData<T>.observeNotNull(observer: (t: T) -> Unit) {
        this.observe(viewLifecycleOwner) {
            it.let(observer)
        }
    }
}