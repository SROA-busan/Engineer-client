package com.example.engineer.confirm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineer.R
import com.example.engineer.dto.ResponseEngineerEvaluation

class ConfirmEvaluationAdapter(private val dataset: ArrayList<ResponseEngineerEvaluation>)
    : RecyclerView.Adapter<ConfirmEvaluationAdapter.ConfirmMainViewHolder>() {
    // 커스텀 리스너
    interface OnItemClickListener {
        fun onItemclick(view: View, position: Int)
    }

    // 객체 저장 변수
    private lateinit var mOnItemClickListener: OnItemClickListener

    // 객체 전달변수
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    inner class ConfirmMainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val classifyName = view.findViewById<TextView>(R.id.evaluation_classifyName)
        val writeDate = view.findViewById<TextView>(R.id.evaluation_writeDate)
        val content = view.findViewById<TextView>(R.id.evaluation_content)
        val score = view.findViewById<TextView>(R.id.evaluation_score)

        init {
            view.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION && mOnItemClickListener != null) {
                    mOnItemClickListener.onItemclick(view, pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmMainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.confirm_evaluation_viewgroup, parent, false)
        return ConfirmMainViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConfirmEvaluationAdapter.ConfirmMainViewHolder, position: Int) {
//        var score = dataset[position].score.toString()
        holder.writeDate.text = dataset[position].writeDate
        holder.content.text = dataset[position].content
        holder.classifyName.text=dataset[position].classifyName
        holder.score.text = dataset[position].score.toString()
    }



    override fun getItemCount(): Int = dataset.size
}