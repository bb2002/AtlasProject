package saintdev.kr.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_billing.*
import android.net.Uri
import saintdev.kr.R


class BillingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        test.setOnClickListener {
            v ->
            IntentIntegrator(this).initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IntentIntegrator.REQUEST_CODE) {
            // QR Code result.
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

            if(result != null) {
                // Open web browser
                val url = result.contents
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }
}