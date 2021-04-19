package com.example.calculator.base

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.calculator.R
import com.example.calculator.view.calculator.CalculatorFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    private var isBack = false

    private val fragmentManager by lazy {
        supportFragmentManager
    }

    var onBackPressedListener: OnBackPressedListener? = null

    override fun onCreatedView() {

        replaceFragment(CalculatorFragment())

        fragmentManager.addOnBackStackChangedListener {
            if (isBack) {
                val backStackEntryCount: Int = fragmentManager.backStackEntryCount
                if (backStackEntryCount > 0 && backStackEntryCount <= fragmentManager.fragments.size) {
                    try {
                        val fragment: Fragment = fragmentManager.fragments[backStackEntryCount - 1]
                        fragment.onResume()
                    } catch (e: Exception) {
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener?.doBack()
        }
    }

    fun addFragment(fragment: Fragment, tag: String? = "") {

        fragmentManager.beginTransaction()
            .add(R.id.frameMain, fragment, tag)
            .addToBackStack(tag).commit()

        val backStackEntryCount: Int = fragmentManager.backStackEntryCount
        if (backStackEntryCount > 0) {
            val fragmentStop: Fragment = fragmentManager.fragments[backStackEntryCount - 1]
            fragmentStop.onStop()
            fragmentStop.onDestroy()
            fragmentStop.onDetach()
        }
    }

    fun replaceFragment(fragment: Fragment, tag: String? = "", isBackStack: Boolean = false) {
        if (isBackStack) {
            fragmentManager.beginTransaction()
                .replace(R.id.frameMain, fragment, tag)
                .addToBackStack(tag).commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(R.id.frameMain, fragment, tag).commit()
        }

        val backStackEntryCount: Int = fragmentManager.backStackEntryCount
        if (backStackEntryCount > 0) {
            val fragmentStop: Fragment = fragmentManager.fragments[backStackEntryCount - 1]
            fragmentStop.onStop()
            fragmentStop.onDestroy()
            fragmentStop.onDetach()
        }
    }

    fun backFragment(tag: String? = "") {
        if (fragmentManager.backStackEntryCount > 1) {
            isBack = true
            if (tag.isNullOrEmpty()) {
                fragmentManager.popBackStack()
            } else {
                fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        } else {
            finish()
        }
    }

    interface OnBackPressedListener {
        fun doBack()
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
