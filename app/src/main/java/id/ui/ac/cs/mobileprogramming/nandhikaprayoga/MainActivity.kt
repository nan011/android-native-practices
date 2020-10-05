package id.ui.ac.cs.mobileprogramming.nandhikaprayoga

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exitButton.setOnClickListener {
            super.onBackPressed()
        }

    }

    override fun onBackPressed() {
        Toast.makeText(this@MainActivity, "Yoo I override this default back button", Toast.LENGTH_LONG).show()
    }
}