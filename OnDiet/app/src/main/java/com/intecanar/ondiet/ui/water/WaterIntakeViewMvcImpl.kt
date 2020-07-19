package com.intecanar.ondiet.ui.water

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.intecanar.ondiet.R
import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.ui.util.BaseViewMvc
import com.intecanar.ondiet.ui.util.VisibilityScreenStatus
import com.intecanar.ondiet.ui.water.recycler.chart.WaterAreaChartAdapter
import com.intecanar.ondiet.ui.water.recycler.timeline.TimeLineAdapter
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.view.LineChartView

class WaterIntakeViewMvcImpl(layoutInflater: LayoutInflater, container: ViewGroup?, activity: FragmentActivity?)
    : BaseViewMvc<WaterIntakeViewMvc.Listener>(), WaterIntakeViewMvc {

    private var waterScaleButton: FloatingActionButton
    private var timeLine: RecyclerView
    private var timeLineAdapter: TimeLineAdapter
    private var  areaChartView: LineChartView
    private var emptyListImage: ImageView
    private var emptyListMessage: TextView

    init {
        //here will go all the widgets of onCreateView
        setRootView(layoutInflater.inflate(R.layout.fragment_water_intake, container, false))
        waterScaleButton = getRootView().findViewById(R.id.fab)
        timeLine = getRootView().findViewById(R.id.recycler_view)
        areaChartView = getRootView().findViewById(R.id.chart)
        emptyListImage = getRootView().findViewById(R.id.no_data_image)
        emptyListMessage = getRootView().findViewById(R.id.empty_time_line_text_view)

        timeLine.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        timeLineAdapter = TimeLineAdapter()
        timeLine.adapter = timeLineAdapter

        waterScaleButton.setOnClickListener {
            for(listener in listeners) {
                listener.onNavigateWaterScaleClicked(R.id.nav_weight_input)
            }
        }
        configureVisibilityByStatus (VisibilityScreenStatus.EMPTY_LIST)

    }

    override fun bindWaterList(waterList: List<Water>) {
        val status: VisibilityScreenStatus = if (waterList.isEmpty())
            VisibilityScreenStatus.EMPTY_LIST else VisibilityScreenStatus.AVAILABLE_LIST
        configureVisibilityByStatus (status)
        configureRecyclers(waterList)
    }

    private fun configureRecyclers(waterList: List<Water>) {
        configureAreaChart(getAreaChartList(waterList))
        configureTimeLine(waterList)
    }

    private fun configureTimeLine(waterList: List<Water>) {
        timeLineAdapter.setTimeLine(waterList)
        timeLineAdapter.setOnItemClickListener(object : TimeLineAdapter.OnItemClickListener {
            override fun onClick(view: View, water : Water) {
                for(listener in listeners) {
                    listener.onWaterSelectedToDelete(water)
                }
            }
        })

    }

    private fun configureVisibilityByStatus (status: VisibilityScreenStatus){
        when (status) {
            VisibilityScreenStatus.EMPTY_LIST -> {
                timeLine.visibility = View.GONE
                areaChartView.visibility = View.GONE


                emptyListImage.visibility = View.VISIBLE
                emptyListMessage.visibility = View.VISIBLE
            }
            VisibilityScreenStatus.AVAILABLE_LIST -> {
                emptyListImage.visibility = View.GONE
                emptyListMessage.visibility = View.GONE

                timeLine.visibility = View.VISIBLE
                areaChartView.visibility = View.VISIBLE

            }
        }
    }

    private fun configureAreaChart( lineChartData: LineChartData) {
        areaChartView.isZoomEnabled = true
        areaChartView.isScrollEnabled = false
        areaChartView.lineChartData = lineChartData
    }

    fun getAreaChartList(waterList: List<Water>): LineChartData
            = WaterAreaChartAdapter.configureWholeData(waterList)

    override fun getDeleteMessage(success: Boolean): String {
        return if(success) {
            this.getString(R.string.delete_water_successful)
        } else {
            this.getString(R.string.delete_water_error)
        }
    }

}