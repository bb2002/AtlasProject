package saintdev.kr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.RemoteException
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.altbeacon.beacon.*
import java.util.*
import java.util.Arrays.asList
import org.altbeacon.beacon.Beacon
import java.nio.file.Files.size
import org.altbeacon.beacon.RangeNotifier
import saintdev.kr.views.DeviceDetectFragment
import saintdev.kr.views.NotDetectFragment


class MainActivity : AppCompatActivity(), BeaconConsumer {
    private lateinit var beaconManager: BeaconManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"))
        beaconManager.bind(this)

        val beacon = Beacon.Builder()
            .setId1("2f234454-cf6d-4a0f-adf2-f4911ba9ffa6")
            .setId2("1")
            .setId3("2")
            .setManufacturer(0x0118)
            .setTxPower(-59)
            .setDataFields(arrayListOf(0L))
            .build()
        val beaconParser = BeaconParser().setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25")
        val beaconTrans = BeaconTransmitter(this, beaconParser)
        beaconTrans.startAdvertising(beacon)

        // Set Not Detect fragment
        val fm = supportFragmentManager
        val fragmentTrans = fm.beginTransaction()
        fragmentTrans.add(R.id.content_layout, NotDetectFragment())
        fragmentTrans.commit()
    }

    /**
     * @Date 05.25 2019
     * When beacon detect call.
     */
    private fun onBeaconDetected(uuid: String) {
        val fragmn = DeviceDetectFragment()
        val bundle = Bundle()
        bundle.putString("uuid", uuid)
        fragmn.arguments = bundle


        val fm = supportFragmentManager
        val fragmentTrans = fm.beginTransaction()
        fragmentTrans.replace(R.id.content_layout, fragmn)
        fragmentTrans.commit()
    }

    override fun onBeaconServiceConnect() {
        beaconManager.removeAllRangeNotifiers()
        beaconManager.addRangeNotifier { beacons, _ ->
            if (beacons.isNotEmpty()) { onBeaconDetected(beacons.iterator().next().id1.toString())
                this.beaconManager.unbind(this)
            }
        }

        try {
            beaconManager.startRangingBeaconsInRegion(Region("myRangingUniqueId", null, null, null))
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.beaconManager.unbind(this)
    }
}
