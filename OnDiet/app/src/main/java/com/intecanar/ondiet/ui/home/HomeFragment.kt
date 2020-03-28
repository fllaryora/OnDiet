package com.intecanar.ondiet.ui.home

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intecanar.ondiet.R
import com.intecanar.ondiet.ui.home.recycler.AppSectionItem
import com.intecanar.ondiet.ui.home.recycler.MenuRecyclerAdapter

class HomeFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: MenuRecyclerAdapter
        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
         recyclerView = root.findViewById(R.id.recycler_view)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        val titles: Array<String> = resources.getStringArray(R.array.main_title_list)
        val subTitles: Array<String> = resources.getStringArray(R.array.main_sub_title_list)
        val icons: TypedArray = resources.obtainTypedArray(R.array.main_icon_list)

        val appLicationSections = mutableListOf<AppSectionItem>()
        for ( index in titles.indices) {
            val icon: Drawable? = icons.getDrawable(index)
            appLicationSections.add(AppSectionItem(titles[index], subTitles[index], icon) )
        }
        icons.recycle()
        recyclerAdapter = MenuRecyclerAdapter(appLicationSections)
        recyclerAdapter.setOnItemClickListener(object : MenuRecyclerAdapter.OnItemClickListener {
            override fun onClick(view: View, mainItem: AppSectionItem) {
                when (mainItem.title) {
                    resources.getString(R.string.menu_weighing) -> {
                        findNavController().navigate(R.id.nav_weighing)
                    }
                    resources.getString(R.string.menu_water_intake) -> {
                        findNavController().navigate(R.id.nav_water_intake)
                    }
                    resources.getString(R.string.menu_pill_remainder) -> {
                        findNavController().navigate(R.id.nav_pill_remainder)
                    }
                }
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = recyclerAdapter

    }
}