package com.symb.task.demux.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.symb.task.demux.DataModel.Category
import com.symb.task.demux.R
import kotlinx.android.synthetic.main.category_container.view.*
class CategoryAdapter(private val context: Context, private val dataset:ArrayList<Category>, private val questionAdapter: QuestionAdapter): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val btn_category=view.menu_button
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.category_container,parent,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(dataset[position].name=="all")
        {
            holder.btn_category.text=dataset[position].name
            holder.btn_category.setOnClickListener {
                questionAdapter.filter.filter("")
            }
        }
        else{
            holder.btn_category.text= dataset[position].name
            val popup=PopupMenu(context,holder.btn_category)
            holder.btn_category.setOnClickListener {
                popup.menu.clear()
                for (h in 0 until dataset[position].catogeries.length())
                {
                    popup.menu.add(0,h,0,dataset[position].catogeries.getString(h))
                }
                popup.setOnMenuItemClickListener { item: MenuItem? ->
                    questionAdapter.filter.filter(item?.title)

                    return@setOnMenuItemClickListener true
                }
                popup.show()
            }
        }
    }
}