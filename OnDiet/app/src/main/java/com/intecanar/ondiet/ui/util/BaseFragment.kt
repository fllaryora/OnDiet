package com.intecanar.ondiet.ui.util

import androidx.fragment.app.Fragment
import com.intecanar.ondiet.MainActivity

abstract class BaseFragment : Fragment() {

    protected val compositionRoot: ActivityCompositionRoot
        get() = (requireActivity() as MainActivity).getCompositionRoot()

}