package id.ac.ui.cs.mobileprogramming.nandhikaprayoga

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 1
        private val PERMISSIONS: Array<String> = arrayOf(
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    }

    private val wifiManager: WifiManager by lazy {
        applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    private val list = ArrayList<String>()

    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!wifiManager.isWifiEnabled) {
            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG)
                .show();
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        wifiListView.adapter = adapter

        if (!hasPermission()) {
            requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS_CODE)
        } else {
            scanWifi()
        }
    }

    private fun scanWifi() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        registerReceiver(adapter?.let { AccessPointReceiver(wifiManager, list, it) }, intentFilter)
        Toast.makeText(this, "Scanning...", Toast.LENGTH_LONG).show()
        val success = wifiManager.startScan()

        if (!success) {
            Toast.makeText(this, "Unable to scan wifi", Toast.LENGTH_LONG).show()
        }
    }

    private fun hasPermission(): Boolean {
        return PERMISSIONS.fold(
            true,
            { allPermissions, permission ->
                allPermissions && this.let {
                    ActivityCompat.checkSelfPermission(
                        it, permission
                    )
                } == PackageManager.PERMISSION_GRANTED
            }
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PERMISSIONS_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            scanWifi()
        }
    }
}