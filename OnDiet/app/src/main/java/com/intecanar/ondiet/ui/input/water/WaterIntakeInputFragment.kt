package com.intecanar.ondiet.ui.input.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.domain.water.InsertWaterUseCase
import com.intecanar.ondiet.ui.extension.hideKeyboard
import com.intecanar.ondiet.ui.util.BaseFragment
import com.intecanar.ondiet.ui.util.ViewMvcFactory

class WaterIntakeInputFragment : BaseFragment(), WaterIntakeInputViewMvc.Listener, InsertWaterUseCase.Listener {

    private  lateinit var mViewMvcFactory: ViewMvcFactory
    private lateinit var mViewMVC: WaterIntakeInputViewMvc

    private lateinit var mInsertWaterUseCase: InsertWaterUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvcFactory = compositionRoot.viewMvcFactory
        mInsertWaterUseCase = compositionRoot.insertWaterUseCase
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        mViewMVC = mViewMvcFactory.newWaterInputViewMvc(container)
        return mViewMVC.getRootView()
    }

    override fun onStart() {
        super.onStart()
        mViewMVC.registerListener(this)
        mInsertWaterUseCase.registerListener(this)
    }


    override fun onStop() {
        super.onStop()
        mViewMVC.unregisterListener(this)
        mInsertWaterUseCase.unregisterListener(this)
        //I hope the equivalent of this
        //cancelButton.setOnClickListener(null)
        //acceptButton.setOnClickListener(null)
        //dateButton.setOnClickListener(null)
        // is called
        hideKeyboard()
    }

    override fun onWaterSaved(newKey: Long) {
        // TOAST SUCCESS !
        mViewMVC.backToWaterScreen()
    }

    /**
     * Callback function which will be used by our controller
     * to do its things when something happen
     */
    override fun onNavigateUp() {
        findNavController().navigateUp()
    }

    override fun onWaterInserted(water: Water) {
        mInsertWaterUseCase.insertWaterIntake(water)
    }
}