package com.fjjukic.birthdaysapp.single_birthday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fjjukic.birthdaysapp.base.utils.DateConverter
import com.fjjukic.birthdaysapp.base.utils.observeNotNull
import com.fjjukic.birthdaysapp.databinding.FragmentSingleBirthdayBinding
import com.fjjukic.birthdaysapp.single_birthday.view_model.SingleBirthdayVM
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * View for showing single birthday
 *
 * @property binding - access to layout through data binding
 * @property navArgs - contains navigation arguments which are required when navigating to this fragment
 * @property viewModel - viewModel class from which data is observed, used with Koin DI
 */
class SingleBirthdayFragment : Fragment() {
    private var binding: FragmentSingleBirthdayBinding? = null
    private val navArgs by navArgs<SingleBirthdayFragmentArgs>()
    private val viewModel: SingleBirthdayVM by viewModel()

    /**
     * Create data binding
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleBirthdayBinding.inflate(inflater, container, false)
        return binding?.root
    }

    /**
     * Call methods to set UI components, observers and clickEvents
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpUI()
        setUpClickEvents()
    }

    /**
     * Observe back navigation event
     */
    private fun setUpObservers() {
        viewModel.navigateUp.observeNotNull(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    /**
     * Handle click events from layout
     */
    private fun setUpClickEvents() {
        binding?.btnGoBack?.setOnClickListener {
            viewModel.handleBackClicked()
        }
    }

    /**
     * SetUp UI with data received from navigation arguments
     */
    private fun setUpUI() {
        binding?.tvInitials?.text = navArgs.initials
        binding?.tvName?.text = navArgs.name
        binding?.tvYears?.text = DateConverter.convertToAgeString(navArgs.years, requireContext().resources)
    }

    /**
     * Destroy binding to avoid memory leaks
     */
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}