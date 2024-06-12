package com.fjjukic.birthdaysapp.single_birthday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fjjukic.birthdaysapp.base.fragment.AppFragment
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
class SingleBirthdayFragment : AppFragment<FragmentSingleBirthdayBinding>() {
    private val navArgs by navArgs<SingleBirthdayFragmentArgs>()
    private val viewModel: SingleBirthdayVM by viewModel()

    /**
     * Create view binding
     */
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSingleBirthdayBinding {
        return FragmentSingleBirthdayBinding.inflate(inflater, container, false)
    }

    /**
     * Call methods to set UI components, observers and clickEvents
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setUpObservers()
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
        binding.btnGoBack.setOnClickListener {
            viewModel.handleBackClicked()
        }
    }

    /**
     * SetUp UI with data received from navigation arguments
     */
    private fun setUpUI() {
        binding.tvInitials.text = navArgs.initials
        binding.tvName.text = navArgs.name
        binding.tvYears.text =
            DateConverter.convertToAgeString(navArgs.years, requireContext().resources)
    }
}