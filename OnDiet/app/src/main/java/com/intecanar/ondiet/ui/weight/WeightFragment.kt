package com.intecanar.ondiet.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.navigation.fragment.findNavController
import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.domain.DeleteWeightUseCase
import com.intecanar.ondiet.domain.FetchAllWeightsUseCase
import com.intecanar.ondiet.ui.util.BaseFragment
import com.intecanar.ondiet.ui.util.ViewMvcFactory

///https://github.com/techyourchance/android_mvc_tutorial/blob/master/app/src/main/java/com/techyourchance/android_mvc_tutorial/screens/smsall/SmsAllFragment.java
class WeightFragment : BaseFragment(), WeightViewMvc.Listener,
    FetchAllWeightsUseCase.Listener,
    DeleteWeightUseCase.Listener {

    private lateinit var mViewMVC: WeightViewMvc
    private  lateinit var mViewMvcFactory: ViewMvcFactory
    private lateinit var mFetchAllWeightsUseCase: FetchAllWeightsUseCase
    private lateinit var mDeleteWeightUseCase: DeleteWeightUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvcFactory = compositionRoot.viewMvcFactory
        mFetchAllWeightsUseCase = compositionRoot.fetchAllWeightsUseCase
        mDeleteWeightUseCase = compositionRoot.deleteWeightUseCase
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        mViewMVC = mViewMvcFactory.newWeightViewMvc(container, activity)
        return mViewMVC.getRootView()
    }

    override fun onStart() {
        super.onStart()
        mViewMVC.registerListener(this)
        mFetchAllWeightsUseCase.fetchAllWeights()
    }

    override fun onStop() {
        super.onStop()
        mViewMVC.unregisterListener(this)
        //I hope the equivalent of this weightScaleButton.setOnClickListener(null)
        // is called
    }

    override fun onAllWeightsFetched(weightList: List<Weight>) {
        mViewMVC.bindWeightList(weightList)
    }

    /**
     * Callback function which will be used by our controller
     * to do its things when something happen
     */

    override fun onWeightDeleted(success: Boolean) {
        if(success) {
            Toast.makeText(context,"Se elimino con exito", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context,"No Se elimino", Toast.LENGTH_LONG).show()
        }
        // refresh
        mFetchAllWeightsUseCase.fetchAllWeights()
    }

    override fun onNavigateWeightScaleClicked(@IdRes rIdNavWeightInput: Int) {
        findNavController().navigate(rIdNavWeightInput)
    }

    override fun onWeightSelectedToDelete(weight: Weight) {
        mDeleteWeightUseCase.deleteWeight(weight)
    }

}