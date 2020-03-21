package com.intecanar.ondiet.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.RecyclerView
import lecho.lib.hellocharts.view.LineChartView
import lecho.lib.hellocharts.view.PreviewLineChartView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.intecanar.ondiet.R
import com.intecanar.ondiet.ui.weight.recycler.timeline.TimeLineAdapter
import com.intecanar.ondiet.ui.weight.recycler.timeline.TimeLineModel
import lecho.lib.hellocharts.gesture.ZoomType
import lecho.lib.hellocharts.listener.ViewportChangeListener
import lecho.lib.hellocharts.model.Viewport

class WeightFragment : Fragment() {

    private lateinit var weightViewModelViewModel: WeightViewModel

    private lateinit var weightScaleButton: FloatingActionButton
    private  lateinit var timeLine: RecyclerView
    private lateinit var  areaChartView: LineChartView
    private lateinit var  previewAreaChartView: PreviewLineChartView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        weightViewModelViewModel =
            ViewModelProviders.of(this).get(WeightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weight, container, false)
        weightScaleButton = root.findViewById(R.id.fab)
        timeLine = root.findViewById(R.id.recycler_view)
        areaChartView = root.findViewById(R.id.chart)
        previewAreaChartView = root.findViewById(R.id.chart_preview)

        weightViewModelViewModel.wholeData.observe(this, Observer {
            areaChartView.isZoomEnabled = true
            areaChartView.isScrollEnabled = false
            areaChartView.lineChartData = it
        })

        weightViewModelViewModel.previewData.observe(this, Observer {
            previewAreaChartView.lineChartData = it
            previewAreaChartView.setViewportChangeListener(ViewportListener())
            val tempViewport = Viewport(previewAreaChartView.maximumViewport)
            val dx = tempViewport.width() / 3f
            tempViewport.inset(dx, 0f)
            previewAreaChartView.setCurrentViewportWithAnimation(tempViewport)
            previewAreaChartView.zoomType = ZoomType.HORIZONTAL
        })

        weightViewModelViewModel.timeLineList.observe(this, Observer {
            timeLine.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val timeLineAdapter = TimeLineAdapter(it)
            timeLineAdapter.setOnItemClickListener(object : TimeLineAdapter.OnItemClickListener {
                override fun onClick(view: View, timeLineModel : TimeLineModel) {
                        Toast.makeText(context, timeLineModel.message, Toast.LENGTH_LONG).show()
                }
            })
            timeLine.adapter = timeLineAdapter
        })

        return root
    }

    inner class  ViewportListener : ViewportChangeListener {
        override fun onViewportChanged(newViewport: Viewport?) {
            // don't use animation, it is unnecessary when using preview chart.
            areaChartView.currentViewport = newViewport
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weightScaleButton.setOnClickListener {
            findNavController().navigate(R.id.nav_weight_input)
        }

    }
}