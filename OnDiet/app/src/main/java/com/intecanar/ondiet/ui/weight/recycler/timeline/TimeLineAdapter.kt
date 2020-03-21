package com.intecanar.ondiet.ui.weight.recycler.timeline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.intecanar.ondiet.R
import java.time.format.DateTimeFormatter

class TimeLineAdapter ( var itemList: List<TimeLineModel> ):
    RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>() {

    class TimeLineViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.text_timeline_title)
        private val date: TextView = itemView.findViewById(R.id.text_timeline_date)
        private val timeLine: TimelineView = itemView.findViewById(R.id.timeline)
        private val trashBin : ImageButton = itemView.findViewById(R.id.trash_bin)

        init{
            timeLine.initLine(viewType)
        }
        companion object {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a, dd-MMM-yyyy")
        }

        fun bind(timeLineModel : TimeLineModel, listener: OnItemClickListener){
            title.text = timeLineModel.message
            date.text = timeLineModel.date.format(formatter)
            trashBin.setOnClickListener {
                listener.onClick(it, timeLineModel)
            }
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    /**
     * This is necessary in order to draw well the lines between the dots
     */
    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {
        val timeItem :TimeLineModel = this.itemList[position]
        holder.bind(timeItem, this.listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        //in this case getItemViewType will add the subtime of our item
        val view = layoutInflater.inflate(R.layout.item_timeline, parent, false)
        return TimeLineViewHolder(view, viewType)
    }


    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(view: View, timeLineModel: TimeLineModel)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}