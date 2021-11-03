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

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

//    private var
    private lateinit var mOnItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    inner  class ScheduleDataViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val dateTime = view.findViewById<TextView>(R.id.schedule_datetime)
        val product = view.findViewById<TextView>(R.id.schedule_product)
        val address = view.findViewById<TextView>(R.id.schedule_address)
        val process = view.findViewById<TextView>(R.id.schedule_process)
        val phoneNum = view.findViewById<TextView>(R.id.schedule_phoneNum)


        init {
            view.setOnClickListener{
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION && mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(view, pos)
                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_viewgroup,parent,false)
        return ScheduleDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ScheduleDataViewHolder, position: Int) {
        holder.dateTime.text  = dataset[position].dateTime
        holder.product.text  = dataset[position].product
        holder.address.text  = dataset[position].address
        holder.process.text  = dataset[position].process
        holder.phoneNum.text  = dataset[position].phoneNum

    }

    override fun getItemCount(): Int = dataset.size
}