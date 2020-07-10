package com.intecanar.ondiet.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.navigation.fragment.findNavController
import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.domain.FetchAllWeightsUseCase
import com.intecanar.ondiet.ui.util.BaseFragment
import com.intecanar.ondiet.ui.util.ViewMvcFactory

///https://github.com/techyourchance/android_mvc_tutorial/blob/master/app/src/main/java/com/techyourchance/android_mvc_tutorial/screens/smsall/SmsAllFragment.java
class WeightFragment : BaseFragment(), WeightViewMvc.Listener, FetchAllWeightsUseCase.Listener {

    private lateinit var mViewMVC: WeightViewMvc
    private  lateinit var mViewMvcFactory: ViewMvcFactory
    private lateinit var mFetchAllWeightsUseCase: FetchAllWeightsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvcFactory = compositionRoot.viewMvcFactory
        mFetchAllWeightsUseCase = compositionRoot.fetchAllWeightsUseCase
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
    override fun onWeightClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigateWeightScaleClicked(@IdRes rIdNavWeightInput: Int) {
        findNavController().navigate(rIdNavWeightInput)
    }

}