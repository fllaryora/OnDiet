package com.intecanar.ondiet.ui.input.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.domain.InsertWeightUseCase
import com.intecanar.ondiet.ui.extension.hideKeyboard
import com.intecanar.ondiet.ui.util.BaseFragment
import com.intecanar.ondiet.ui.util.ViewMvcFactory

class WeightInputFragment : BaseFragment(), WeightInputViewMvc.Listener, InsertWeightUseCase.Listener {

    private  lateinit var mViewMvcFactory: ViewMvcFactory
    private lateinit var mViewMVC: WeightInputViewMvc

    private lateinit var mInsertWeightUseCase: InsertWeightUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvcFactory = compositionRoot.viewMvcFactory
        mInsertWeightUseCase = compositionRoot.insertWeightUseCase
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        mViewMVC = mViewMvcFactory.newWeightInputViewMvc(container, activity)
        return mViewMVC.getRootView()
    }

    override fun onStart() {
        super.onStart()
        mViewMVC.registerListener(this)
        mInsertWeightUseCase.registerListener(this)
    }


    override fun onStop() {
        super.onStop()
        mViewMVC.unregisterListener(this)
        mInsertWeightUseCase.unregisterListener(this)
        //I hope the equivalent of this
        //cancelButton.setOnClickListener(null)
        //acceptButton.setOnClickListener(null)
        //dateButton.setOnClickListener(null)
        // is called
        hideKeyboard()
    }

    override fun onWeightSaved(newKey : Long) {
        // TOAST SUCCESS !
        mViewMVC.backToWeightScreen()
    }

    /**
     * Callback function which will be used by our controller
     * to do its things when something happen
     */
    override fun onNavigateUp() {
        findNavController().navigateUp()
    }

    override fun onWeightInserted(weight: Weight) {
        mInsertWeightUseCase.insertWeight(weight)
    }
}