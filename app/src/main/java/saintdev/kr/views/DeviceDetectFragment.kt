package saintdev.kr.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fm_device_detect.*
import saintdev.kr.R
import saintdev.kr.datas.Trust
import saintdev.kr.datas.TrustData

class DeviceDetectFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var beaconUUID: String
    private lateinit var trustData: TrustData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater.inflate(R.layout.fm_device_detect, container, false)
        val arg = arguments

        if(arg != null) {
            this.beaconUUID = arg.getString("uuid") ?: "AAAAAAAA-BBBB-BBBB-CCCC-CCCCDDDDDDDD"
            if(this.beaconUUID == Trust.getTrust1().UUID) {
                // this is trust 1
                this.trustData = Trust.getTrust1()
            } else {
                // this is trust 2
                this.trustData = Trust.getTrust2()
            }

            // debug
            this.rootView.findViewById<TextView>(R.id.device_id).text = this.beaconUUID
        }

        this.rootView.findViewById<TextView>(R.id.device_id).setOnClickListener {
                v ->
            startActivity(Intent(context, BillingActivity::class.java))
            activity?.finish()
        }

        return this.rootView
    }


}