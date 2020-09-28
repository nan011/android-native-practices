package id.ui.ac.cs.mobileprogramming.nandhikaprayoga.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lab2Button.setOnClickListener {
            val openLab2Activity = Intent(this, Lab2Activity::class.java)
            startActivity(openLab2Activity)
        }
    }
}