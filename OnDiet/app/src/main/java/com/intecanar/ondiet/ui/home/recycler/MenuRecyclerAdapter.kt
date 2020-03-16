package com.intecanar.ondiet.ui.home.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.intecanar.ondiet.R

class MenuRecyclerAdapter (var itemList:List<AppSectionItem>):
    RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder>() {

    private lateinit var listener: OnItemClickListener

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.textViewName)
        private val subTitle: TextView = itemView.findViewById(R.id.textViewVersion)
        private val icon: ImageView = itemView.findViewById(R.id.item_icon)
        private val fab: FloatingActionButton = itemView.findViewById(R.id.fab)

        fun bind(appSectionItem :AppSectionItem, listener: OnItemClickListener){
            title.text = appSectionItem.title
            subTitle.text = appSectionItem.subTitle
            icon.setImageDrawable(appSectionItem.drawable)
            fab.setOnClickListener {
                listener.onClick(it, appSectionItem)
            }
        }
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ViewHolder {
        setOnItemClickListener(listener)
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appSectionItem :AppSectionItem = this.itemList[position]
        holder.bind(appSectionItem, this.listener)

    }

    interface OnItemClickListener {
        fun onClick(view: View, mainItem: AppSectionItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_main_fragment
    }
}