package com.example.engineer.confirm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineer.R
import com.example.engineer.dto.EvaluationData

class ConfirmEvaluationAdapter(private val dataset: ArrayList<EvaluationData>) : RecyclerView.Adapter<ConfirmEvaluationAdapter.ConfirmMainViewHolder>() {
    // 커스텀 리스너
    interface onItemClickListener {
        fun onItemclick(view: View, position: Int)
    }

    // 객체 저장 변수
    private lateinit var mOnItemClickListener: onItemClickListener

    // 객체 전달변수
    fun setOnItemClickListener(onItemClickListener: onItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    inner class ConfirmMainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val product = view.findViewById<TextView>(R.id.evaluation_product)
        val dateTime = view.findViewById<TextView>(R.id.evaluation_datetime)
        val grade = view.findViewById<TextView>(R.id.evaluation_grade)
        val content = view.findViewById<TextView>(R.id.evaluation_content)

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.confirm_viewgroup, parent, false)
        return ConfirmMainViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConfirmMainViewHolder, position: Int) {
        var grade = dataset[position].grade
        holder.product.text = dataset[position].product
        holder.dateTime.text = dataset[position].dateTime
        holder.dateTime.text = dataset[position].content
        holder.grade.text = grade

        // 평점상황별 색 변경
        when (grade) {
            "한개" -> holder.grade.setBackgroundResource(R.drawable.label_blue)
            "두개" -> holder.grade.setBackgroundResource(R.drawable.label_green)
            "세개" -> holder.grade.setBackgroundResource(R.drawable.label_red)
        }
    }

    override fun getItemCount(): Int = dataset.size
}