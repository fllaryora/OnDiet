package com.intecanar.ondiet.ui.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes

import androidx.navigation.fragment.findNavController
import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.domain.water.DeleteWaterUseCase
import com.intecanar.ondiet.domain.water.FetchAllWaterUseCase

import com.intecanar.ondiet.ui.util.BaseFragment
import com.intecanar.ondiet.ui.util.ViewMvcFactory


class WaterIntakeFragment : BaseFragment(), WaterIntakeViewMvc.Listener,
    FetchAllWaterUseCase.Listener,
    DeleteWaterUseCase.Listener {

    private lateinit var mViewMVC: WaterIntakeViewMvc
    private  lateinit var mViewMvcFactory: ViewMvcFactory
    private lateinit var mFetchAllWaterUseCase: FetchAllWaterUseCase
    private lateinit var mDeleteWaterUseCase: DeleteWaterUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvcFactory = compositionRoot.viewMvcFactory
        mFetchAllWaterUseCase = compositionRoot.fetchAllWaterUseCase
        mDeleteWaterUseCase = compositionRoot.deleteWaterUseCase
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        mViewMVC = mViewMvcFactory.newWaterIntakeViewMvc(container, activity)
        return mViewMVC.getRootView()
    }

    override fun onStart() {
        super.onStart()
        mViewMVC.registerListener(this)
        mFetchAllWaterUseCase.registerListener(this)
        mDeleteWaterUseCase.registerListener(this)
        mFetchAllWaterUseCase.fetchAllWaterIntakesForToday()
    }

    override fun onStop() {
        super.onStop()
        mViewMVC.unregisterListener(this)
        mFetchAllWaterUseCase.unregisterListener(this)
        mDeleteWaterUseCase.unregisterListener(this)
    }

    /**
     * Callback function which will be used by our controller
     * to do its things when something happen
     */
    override fun onNavigateWaterScaleClicked(@IdRes rIdNavWaterInput: Int) {
        findNavController().navigate(rIdNavWaterInput)
    }

    override fun onWaterSelectedToDelete(water: Water) {
        mDeleteWaterUseCase.deleteWeight(water)
    }

    override fun onAllWaterFetched(waterList: List<Water>) {
        mViewMVC.bindWaterList(waterList)
    }

    override fun onWaterDeleted(success: Boolean) {
        val message : String = mViewMVC.getDeleteMessage(success)
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
        // refresh
        mFetchAllWaterUseCase.fetchAllWaterIntakesForToday()
    }

}