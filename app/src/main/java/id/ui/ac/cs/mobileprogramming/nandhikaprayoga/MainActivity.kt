package id.ui.ac.cs.mobileprogramming.nandhikaprayoga

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var countingJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exitButton.setOnClickListener {
            super.onBackPressed()
        }

        countingJob = CoroutineScope(Dispatchers.Default).launch {
            startCounter()
        }
    }

    private suspend fun startCounter() {
        var counter = 0
        while (true) {
            setCounterInUI(counter)
            counter += 1
            delay(1000)
        }
    }

    private suspend fun setCounterInUI(seconds: Int) {
        withContext(Dispatchers.Main) {
            counter.text = "$seconds"
        }
    }

    override fun onBackPressed() {
        Toast.makeText(
            this@MainActivity,
            "Yoo I override this default back button",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroy() {
        countingJob?.cancel()
        super.onDestroy()
    }
}