package com.intecanar.ondiet.ui.water.recycler.timeline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.intecanar.ondiet.R
import com.intecanar.ondiet.data.entity.Water
import com.intecanar.ondiet.ui.util.TimeHelper

class TimeLineAdapter :
    RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>() {

    var itemList: MutableList<Water> = mutableListOf()

    class TimeLineViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.text_timeline_title)
        private val date: TextView = itemView.findViewById(R.id.text_timeline_date)
        private val timeLine: TimelineView = itemView.findViewById(R.id.timeline)
        private val trashBin : ImageButton = itemView.findViewById(R.id.trash_bin)

        init{
            timeLine.initLine(viewType)
        }

        fun bind(water : Water, listener: OnItemClickListener){
            title.text = itemView.context.resources.getString(
                R.string.water_timeline_label, water.volume.toString())
            date.text = water.date.format(TimeHelper.formatter)
            trashBin.setOnClickListener {
                listener.onClick(it, water)
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
        val timeItem :Water = this.itemList[position]
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
        fun onClick(view: View, water: Water)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    public fun setTimeLine( itemList: List<Water> ){
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }
}