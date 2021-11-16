package com.example.engineer.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineer.R
import com.example.engineer.dto.EngineerBrieflySchedule

class ScheduleAdapter(private val dataset: ArrayList<EngineerBrieflySchedule>)
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
//        val address = view.findViewById<TextView>(R.id.schedule_address)
        val state = view.findViewById<TextView>(R.id.schedule_process)
//        val phoneNum = view.findViewById<TextView>(R.id.schedule_phoneNum)


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
        var state  = dataset[position].state.toString()
        holder.dateTime.text  = dataset[position].startTime
        holder.product.text  = dataset[position].productName


        when(state){
            "0" -> {
                holder.state.setBackgroundResource(R.drawable.label_red)
                holder.state.text = "방문예정"
            }
            "1" -> {
                holder.state.setBackgroundResource(R.drawable.label_blue)
                holder.state.text ="처리완료"
            }
            "2" -> {
                holder.state.setBackgroundResource(R.drawable.label_yellow)
                holder.state.text ="수령"
            }
            "3" -> {
                holder.state.setBackgroundResource(R.drawable.label_green)
                holder.state.text ="수리완료"
            }
            "4" -> {
                holder.state.setBackgroundResource(R.drawable.label_gray)
                holder.state.text ="반납예약완료"
            }
            "5" -> {
                holder.state.setBackgroundResource(R.drawable.label_black)
                holder.state.text ="평가완료"
            }
        }
    }

    override fun getItemCount(): Int = dataset.size
}