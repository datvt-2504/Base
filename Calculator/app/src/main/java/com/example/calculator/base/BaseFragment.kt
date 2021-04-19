package com.example.calculator.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment(private val layout: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreatedView()
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            (it as MainActivity).onBackPressedListener =
                object : MainActivity.OnBackPressedListener {
                    override fun doBack() {
                        if (onBackPress()) {
                            onBackTask()
                        }
                    }
                }
        }
    }

    protected abstract fun onCreatedView()
    protected abstract fun onBackPress(): Boolean

    fun startFragment(fragment: Fragment, tag: String? = "") {
        (activity as MainActivity).addFragment(fragment, tag)
    }

    fun replaceFragment(fragment: Fragment, tag: String? = "", isBackStack: Boolean = false) {
        (activity as MainActivity).replaceFragment(fragment, tag, isBackStack)
    }

    fun onBackTask(tag: String? = "") {
        activity?.let {
            (it as MainActivity).backFragment(tag)
        }
    }
}
