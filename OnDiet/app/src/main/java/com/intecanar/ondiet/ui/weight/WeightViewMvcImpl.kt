package com.intecanar.ondiet.ui.weight

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
import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.ui.util.BaseViewMvc
import com.intecanar.ondiet.ui.util.VisibilityScreenStatus
import com.intecanar.ondiet.ui.weight.recycler.chart.PreviewAreaChartAdapter
import com.intecanar.ondiet.ui.weight.recycler.chart.WholeDataAreaChartAdapter
import com.intecanar.ondiet.ui.weight.recycler.timeline.TimeLineAdapter
import lecho.lib.hellocharts.gesture.ZoomType
import lecho.lib.hellocharts.listener.ViewportChangeListener
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.Viewport
import lecho.lib.hellocharts.view.LineChartView
import lecho.lib.hellocharts.view.PreviewLineChartView

class WeightViewMvcImpl(layoutInflater: LayoutInflater,container: ViewGroup?, activity: FragmentActivity?)
    : BaseViewMvc<WeightViewMvc.Listener>(), WeightViewMvc {

    private var weightScaleButton: FloatingActionButton
    private var timeLine: RecyclerView
    private var timeLineAdapter: TimeLineAdapter
    private var  areaChartView: LineChartView
    private var  previewAreaChartView: PreviewLineChartView
    private var  innerLineSeparator : View
    private var emptyListImage: ImageView
    private var emptyListMessage: TextView

    init {
        //here will go all the widgets of onCreateView
        setRootView(layoutInflater.inflate(R.layout.fragment_weight, container, false))
        weightScaleButton = getRootView().findViewById(R.id.fab)
        timeLine = getRootView().findViewById(R.id.recycler_view)
        areaChartView = getRootView().findViewById(R.id.chart)
        previewAreaChartView = getRootView().findViewById(R.id.chart_preview)
        innerLineSeparator = getRootView().findViewById(R.id.inner_separator)
        emptyListImage = getRootView().findViewById(R.id.no_data_image)
        emptyListMessage = getRootView().findViewById(R.id.empty_time_line_text_view)

        timeLine.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        timeLineAdapter = TimeLineAdapter()
        timeLine.adapter = timeLineAdapter

        weightScaleButton.setOnClickListener {
            for(listener in listeners) {
                listener.onNavigateWeightScaleClicked(R.id.nav_weight_input)
            }
        }
        configureVisibilityByStatus (VisibilityScreenStatus.EMPTY_LIST)

    }

    override fun bindWeightList(weightList: List<Weight>) {
        val status: VisibilityScreenStatus = if (weightList.isEmpty())
            VisibilityScreenStatus.EMPTY_LIST else VisibilityScreenStatus.AVAILABLE_LIST
        configureVisibilityByStatus (status)
        configureRecyclers(weightList)
    }

    private fun configureRecyclers(weightList: List<Weight>) {
        configureAreaChart(getAreaChartList(weightList))
        configurePreviewChart(getPreviewChartList(weightList))
        configureTimeLine(weightList)
    }

    private fun configureTimeLine(weightList: List<Weight>) {
        timeLineAdapter.setTimeLine(weightList)
        timeLineAdapter.setOnItemClickListener(object : TimeLineAdapter.OnItemClickListener {
            override fun onClick(view: View, weight : Weight) {
                for(listener in listeners) {
                    listener.onWeightSelectedToDelete(weight)
                }
            }
        })

    }

    private fun configureVisibilityByStatus (status: VisibilityScreenStatus){
        when (status) {
            VisibilityScreenStatus.EMPTY_LIST -> {
                timeLine.visibility = View.GONE
                areaChartView.visibility = View.GONE
                innerLineSeparator.visibility = View.GONE
                previewAreaChartView.visibility = View.GONE

                emptyListImage.visibility = View.VISIBLE
                emptyListMessage.visibility = View.VISIBLE
            }
            VisibilityScreenStatus.AVAILABLE_LIST -> {
                emptyListImage.visibility = View.GONE
                emptyListMessage.visibility = View.GONE

                timeLine.visibility = View.VISIBLE
                areaChartView.visibility = View.VISIBLE
                innerLineSeparator.visibility = View.VISIBLE
                previewAreaChartView.visibility = View.VISIBLE
            }
        }
    }

    private fun configureAreaChart( lineChartData: LineChartData) {
        areaChartView.isZoomEnabled = true
        areaChartView.isScrollEnabled = false
        areaChartView.lineChartData = lineChartData
    }

    private fun configurePreviewChart( lineChartData: LineChartData) {
        previewAreaChartView.lineChartData = lineChartData
        previewAreaChartView.setViewportChangeListener(ViewportListener())
        val tempViewport = Viewport(previewAreaChartView.maximumViewport)
        val dx = tempViewport.width() / 3f
        tempViewport.inset(dx, 0f)
        previewAreaChartView.setCurrentViewportWithAnimation(tempViewport)
        previewAreaChartView.zoomType = ZoomType.HORIZONTAL
    }

    inner class  ViewportListener : ViewportChangeListener {
        override fun onViewportChanged(newViewport: Viewport?) {
            // don't use animation, it is unnecessary when using preview chart.
            areaChartView.currentViewport = newViewport
        }
    }

    fun getAreaChartList(weightList: List<Weight>): LineChartData
            = WholeDataAreaChartAdapter.configureWholeData(weightList)

    fun getPreviewChartList(weightList: List<Weight>): LineChartData
            = PreviewAreaChartAdapter.configurePreviewChart(weightList)
}