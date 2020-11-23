package com.symb.task.demux.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import com.symb.task.demux.DataModel.Question
import com.symb.task.demux.R
import kotlinx.android.synthetic.main.item_loading.view.*
import kotlinx.android.synthetic.main.question_container.view.*
import java.util.*
import kotlin.collections.ArrayList

class QuestionAdapter(private var datasetquestion:List<Question?>):RecyclerView.Adapter<RecyclerView.ViewHolder>(),Filterable {
    companion object{
        const val VIEW_TYPE_ITEM=0
        const val VIEW_TYPE_LOADING=1
    }
    var dataset=datasetquestion
    var datasetFiltered:List<Question?>?=null

    override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch=constraint.toString()
                if(charSearch.isNullOrEmpty())
                datasetFiltered=datasetquestion
                else{
                    val resultList=ArrayList<Question>()
                    for(row in datasetquestion){
                        if (row != null) {
                            if(row.college.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))
                                || row.company.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))
                                || row.job_nature.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))
                                || row.interview_type.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))
                                || row.frequency.toString().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                                resultList.add(row)
                            }
                        }
                    }

                    datasetFiltered=resultList
                }
                var filterResults=FilterResults()
                filterResults.values=datasetFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataset= results?.values as List<Question>
                    notifyDataSetChanged()
            }
        }
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView_frequency=view.frequency_title
        val textView_college=view.college_name
        val textView_title=view.question_title
        val textureView_description=view.question_description
        val imageview_company=view.companyImageView
        val chipGroup=view.chip_group
        val inter_view_answer=view.interview_type_answer
        val job_nature_answer=view.job_nature_answer
    }
    class LoadingViewHolder(view: View):RecyclerView.ViewHolder(view){
        val progressBar=view.progressBar


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType== VIEW_TYPE_ITEM)
        {
            var v=LayoutInflater.from(parent.context).inflate(R.layout.question_container,parent,false)
            return ViewHolder(v)
        }
        else{
            var v=LayoutInflater.from(parent.context).inflate(R.layout.item_loading,parent,false)
            return LoadingViewHolder(
                v
            )
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun getItemViewType(position: Int): Int {
        if (dataset[position]==null)
            return VIEW_TYPE_LOADING
        else
            return VIEW_TYPE_ITEM
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if(holder is ViewHolder)
            {
                holder.textView_college.text=dataset[position]?.college
                holder.textView_frequency.text=dataset[position]?.frequency.toString()
                holder.textView_title.text=dataset[position]?.title
                holder.textureView_description.text=dataset[position]?.description
                holder.inter_view_answer.text=dataset[position]?.interview_type
                holder.job_nature_answer.text=dataset[position]?.job_nature
                Picasso.get().load(dataset.get(position)?.image!!).placeholder(dataset.get(position)!!.image).into(holder.imageview_company)
                holder.chipGroup.removeAllViews()
                for (h in 0 until dataset[position]?.tags!!.length())
                {
                    dataset[position]?.tags!!.getString(h)
                    var chip=Chip(holder.chipGroup.context)
                    chip.setText(dataset[position]?.tags!!.getString(h))
                    chip.setChipBackgroundColorResource(R.color.colorSecondary)
                    chip.setTextAppearanceResource(R.style.ChipTextStyleSelected)
                    holder.chipGroup.addView(chip)
                }
            }
    }
}