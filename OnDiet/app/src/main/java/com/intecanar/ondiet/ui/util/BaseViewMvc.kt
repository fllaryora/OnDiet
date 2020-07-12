package com.intecanar.ondiet.ui.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.Date
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import java.util.regex.Pattern

abstract class BaseViewMvc<LISTENER> : BaseObservable<LISTENER>(), ViewMvc {

    private lateinit var mRootView: View
    private val weightPattern = Pattern.compile("^[1-9][0-9]{0,2}(\\.[0-9]{0,2})?")

    protected fun setRootView(rootView: View) {
        mRootView = rootView
    }

    override fun getRootView(): View {
        return mRootView
    }

    protected fun <T : View> findViewById(@IdRes id: Int): T {
        return getRootView().findViewById(id)
    }

    protected fun getColor(@ColorRes colorId: Int): Int {
        return mRootView.context.resources.getColor(colorId)
    }

    protected fun getString(@StringRes stringId: Int): String {
        return mRootView.context.resources.getString(stringId)
    }

    protected fun String.isValidWeight(): Boolean
            = this.isNotEmpty() && weightPattern.matcher(this).matches()
}