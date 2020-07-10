package com.intecanar.ondiet

import android.app.Application
import com.intecanar.ondiet.data.database.ObjectBox
import com.intecanar.ondiet.ui.util.ApplicationCompositionRoot

class OnDietApplication: Application() {

    private var mApplicationCompositionRoot: ApplicationCompositionRoot? = null

    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }

    fun getApplicationCompositionRoot(): ApplicationCompositionRoot {
        if (mApplicationCompositionRoot == null) {
            mApplicationCompositionRoot = ApplicationCompositionRoot(this)
        }
        return mApplicationCompositionRoot!!
    }
}