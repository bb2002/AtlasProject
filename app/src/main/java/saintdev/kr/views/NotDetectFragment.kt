package saintdev.kr.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import saintdev.kr.R

class NotDetectFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater.inflate(R.layout.fm_not_detect, container, false)
        return this.rootView
    }
}