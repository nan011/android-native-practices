package id.ui.ac.cs.mobileprogramming.nandhikaprayoga.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.R
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.fragments.InfoFragment
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.fragments.InputFragment
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exitButton.setOnClickListener {
            super.onBackPressed()
        }

        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(
            application
        )).get(MainViewModel::class.java)

        setFirstFragment()

    }

    private fun setFirstFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragment, InputFragment.newInstance {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragment, InfoFragment())
                    .commit()
            })
            .commit()
    }

    override fun onBackPressed() {
        setFirstFragment()
    }
}