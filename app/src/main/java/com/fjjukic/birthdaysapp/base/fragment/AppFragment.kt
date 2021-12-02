package com.fjjukic.birthdaysapp.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Abstract class used to reduce boilerplate for creating and managing ViewBinding
 * @property binding - created ViewBinding for layout
 */
abstract class AppFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    /**
     * Create view binding
     */
    abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater, container)
        return binding.root
    }

    /**
     * Handle potential memory leaks
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}