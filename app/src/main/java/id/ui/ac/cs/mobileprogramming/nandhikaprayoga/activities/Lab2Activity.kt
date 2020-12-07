package id.ui.ac.cs.mobileprogramming.nandhikaprayoga.activities


import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.R
import kotlinx.android.synthetic.main.activity_lab_2.*
import kotlinx.android.synthetic.main.activity_main.*


class Lab2Activity: AppCompatActivity() {
    companion object {
        init {
            System.loadLibrary("native-lib")
        }

        external fun reverse(content: String): String

        fun reverseString(content: String): String {
            return Lab2Activity.reverse(content)
//            if (content.length <= 1) {
//                return content
//            }
//
//            return content[content.lastIndex] + reverseString(content.substring(1, content.lastIndex)) + content[0]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_2)

        lab2Input.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                reverseTextOnView()
                return@OnEditorActionListener true
            }
            false
        })

        lab2Enter.setOnClickListener {
            reverseTextOnView()
        }

    }

    private fun reverseTextOnView() {
        val content: String = lab2Input.text.toString()
        lab2Output.text = reverseString(content)
    }
}