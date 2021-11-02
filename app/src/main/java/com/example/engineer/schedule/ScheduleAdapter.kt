package com.example.engineer.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineer.R
import com.example.engineer.dto.ScheduleData

class ScheduleAdapter(private val dataset: ArrayList<ScheduleData>)
    : RecyclerView.Adapter<ScheduleAdapter.ScheduleDataViewHolder>(){

//    interface OnItemClickListener{
//        fun onItemClick(view: View, data: ScheduleData, pos: Int)
//    }
//
//    private var listener: OnItemClickListener? = null
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        this.listener = listener
//    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
    private lateinit var mOnItemClickListener: OnItemClickListener

    fun setOnItemClickListenr(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    inner  class ScheduleDataViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val dateTime = view.findViewById<TextView>(R.id.confirm_product)
        val product = view.findViewById<TextView>(R.id.confirm_datetime)
        val address = view.findViewById<TextView>(R.id.confirm_content)
        val process = view.findViewById<TextView>(R.id.confirm_grade)
        val phoneNum = view.findViewById<TextView>(R.id.schedule_phoneNum)


        init {
            view.setOnClickListener{
                val pos = adapterPosition
                if(pos != null && mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(view, pos)
                }

            }
        }
//        fun bind(item: ScheduleData){
//            val pos = adapterPosition
//            if(pos != RecyclerView.NO_POSITION){
//                itemView.setOnClickListener{
//                    listener?.onItemClick(itemView,item,pos)
//                }
//            }
//        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scheduel_viewgroup,parent,false)
        return ScheduleDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ScheduleDataViewHolder, position: Int) {
        holder.dateTime.text  = dataset[position].dateTime
        holder.product.text  = dataset[position].dateTime
        holder.address.text  = dataset[position].dateTime
        holder.process.text  = dataset[position].dateTime
        holder.phoneNum.text  = dataset[position].dateTime

    }

    override fun getItemCount(): Int = dataset.size
}