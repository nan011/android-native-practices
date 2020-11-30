package id.ac.ui.cs.mobileprogramming.nandhikaprayoga

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException

class AccessPointReceiver(
    private val wifiManager: WifiManager,
    private val list: ArrayList<String>,
    private val adapter: ArrayAdapter<String>
) : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
        if (success) {
            list.clear()
            val scanResults: List<ScanResult> = wifiManager.scanResults

            for (scanResult in scanResults) {
                list.add(scanResult.SSID)
            }

            adapter.notifyDataSetChanged()

            Toast.makeText(context, "Send to somewhere on internet", Toast.LENGTH_LONG).show()

            CoroutineScope(Dispatchers.Default).launch {
                MainService.sendRequest(
                    object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            CoroutineScope(Dispatchers.Main).launch {
                                Toast.makeText(context, "Fail send to server", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onResponse(call: Call, response: Response) {
                            if (response.isSuccessful) {
                                if (response.code() == 200) {
                                    val body = Utility.parseJSON(response.body()?.string())
                                    println(body)

                                    CoroutineScope(Dispatchers.Main).launch {
                                        Toast.makeText(context, "Success send to server", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }


                        }

                    },
                    MainService.getAPIUri("/post"),
                    MainService.HttpMethod.POST,
                    RequestBody.create(
                        MediaType.parse("application/json"),
                        Utility.stringifyJSON(
                            mapOf(
                                "list" to list
                            )
                        )
                    )
                )
            }
        }
    }

}