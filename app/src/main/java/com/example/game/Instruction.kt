package com.example.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.instruction.view.*

class Instruction : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.instruction, container, false)

        rootView.goToPlayButton.setOnClickListener { (activity as MainActivity).setViewPager(1) }

        return rootView
    }
}
