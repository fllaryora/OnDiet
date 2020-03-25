package com.intecanar.ondiet.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.RecyclerView
import lecho.lib.hellocharts.view.LineChartView
import lecho.lib.hellocharts.view.PreviewLineChartView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.intecanar.ondiet.R
import com.intecanar.ondiet.data.entity.Weight
import com.intecanar.ondiet.ui.weight.recycler.timeline.TimeLineAdapter
import lecho.lib.hellocharts.gesture.ZoomType
import lecho.lib.hellocharts.listener.ViewportChangeListener
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.Viewport

class WeightFragment : Fragment() {

    private lateinit var weightViewModelViewModel: WeightViewModel

    private lateinit var weightScaleButton: FloatingActionButton
    private  lateinit var timeLine: RecyclerView
    private  lateinit var timeLineAdapter: TimeLineAdapter
    private lateinit var  areaChartView: LineChartView
    private lateinit var  previewAreaChartView: PreviewLineChartView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_weight, container, false)
        weightScaleButton = root.findViewById(R.id.fab)
        timeLine = root.findViewById(R.id.recycler_view)
        areaChartView = root.findViewById(R.id.chart)
        previewAreaChartView = root.findViewById(R.id.chart_preview)
        timeLine.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        timeLineAdapter = TimeLineAdapter()
        timeLine.adapter = timeLineAdapter

        weightViewModelViewModel =
            ViewModelProvider(this).get(WeightViewModel::class.java)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //observe here to avoid manual update.
        weightViewModelViewModel.weightList.observe(viewLifecycleOwner, Observer{
            it?.let{
                //TODO change show or not here
                configureRecyclers(it)
            }
        })

    }

    private fun configureRecyclers(weightList: List<Weight>) {
        configureAreaChart(weightViewModelViewModel.getAreaChartList(weightList))
        configurePreviewChart(weightViewModelViewModel.getPreviewChartList(weightList))
        configureTimeLine(weightList)
    }

    private fun configureTimeLine(weightList: List<Weight>) {
        timeLineAdapter.setTimeLine(weightList)
        timeLineAdapter.setOnItemClickListener(object : TimeLineAdapter.OnItemClickListener {
            override fun onClick(view: View, weight : Weight) {
                weightViewModelViewModel.delete(weight)
            }
        })

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

    override fun onStart() {
        super.onStart()
        weightScaleButton.setOnClickListener {
            findNavController().navigate(R.id.nav_weight_input)
        }
    }

    override fun onStop() {
        super.onStop()
        weightScaleButton.setOnClickListener(null)
    }
}