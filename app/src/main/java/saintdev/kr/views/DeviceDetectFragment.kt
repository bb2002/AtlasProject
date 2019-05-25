package saintdev.kr.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

    private lateinit var companyView: TextView  // 회사 이름
    private lateinit var trustView: TextView    // 패기물 이름
    private lateinit var priceView: TextView  // 폐기 가격
    private lateinit var uuidView: TextView     // UUID

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater.inflate(R.layout.fm_device_detect, container, false)
        val arg = arguments

        // generate data
        if(arg != null) {
            this.beaconUUID = arg.getString("uuid")!!
            when {
                this.beaconUUID.equals(Trust.getTrust1().UUID, true) -> this.trustData = Trust.getTrust1()
                this.beaconUUID.equals(Trust.getTrust2().UUID, true) -> this.trustData = Trust.getTrust2()
                else -> this.trustData = Trust.getTrust3()
            }
        }

        // find view
        this.companyView = this.rootView.findViewById(R.id.company_id)
        this.trustView = this.rootView.findViewById(R.id.trash_id)
        this.priceView = this.rootView.findViewById(R.id.coin_id)
        this.uuidView = this.rootView.findViewById(R.id.device_id)

        this.companyView.text = this.trustData.LOCATION
        this.trustView.text = this.trustData.SOURCE
        this.priceView.text = "${this.trustData.PRICE}원"
        this.uuidView.text = this.beaconUUID


        this.rootView.findViewById<Button>(R.id.pay_button).setOnClickListener {
            startActivity(Intent(context, BillingActivity::class.java))
            activity?.finish()
        }

        return this.rootView
    }
}