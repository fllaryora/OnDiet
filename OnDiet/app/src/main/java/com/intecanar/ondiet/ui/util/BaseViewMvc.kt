package com.intecanar.ondiet.ui.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.Date
import androidx.annotation.ColorRes
import androidx.annotation.IdRes

abstract class BaseViewMvc<LISTENER> : BaseObservable<LISTENER>(), ViewMvc {

    private lateinit var mRootView: View

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
        return mRootView!!.context.resources.getColor(colorId)
    }

    companion object {
        fun fromUnixTimestampToHumanReadableFormat(timestamp: String): String {
            val fmtOut = SimpleDateFormat("yyyy-MM-dd HH:mm")
            return fmtOut.format(Date(java.lang.Long.valueOf(timestamp)))
        }
    }
}