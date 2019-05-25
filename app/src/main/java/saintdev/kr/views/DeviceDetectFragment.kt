package saintdev.kr.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import saintdev.kr.R

class DeviceDetectFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater.inflate(R.layout.fm_device_detect, container, false)
        val arg = arguments

        if(arg != null) {
            val beaconUUID = arg.getString("uuid")
            this.rootView.findViewById<TextView>(R.id.device_id).text = beaconUUID
        }

        return this.rootView
    }
}