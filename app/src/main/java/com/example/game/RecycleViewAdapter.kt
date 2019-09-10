package com.example.game

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row.view.*

class RecycleViewAdapter(val numRow : List<Num>) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row,parent, false))
    }

    override fun getItemCount()= numRow.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var rowNum = numRow[position]
        holder.view.rowNum1.text = rowNum.num1
        holder.view.rowNum2.text = rowNum.num2
        holder.view.rowNum3.text = rowNum.num3
        holder.view.rowTOF.text = rowNum.ToF.toString()

        //change text color when true and false
        when(rowNum.ToF) {
            true -> holder.view.rowTOF.setTextColor(Color.parseColor("#00EE00"))
            false -> holder.view.rowTOF.setTextColor(Color.parseColor("#FF0000"))
        }
    }

    class ViewHolder (val view: View): RecyclerView.ViewHolder(view)
}