package id.ui.ac.cs.mobileprogramming.nandhikaprayoga.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.R
import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_input.*

class InputFragment : Fragment() {
    private lateinit var onClick: () -> Unit
    private var mainViewModel: MainViewModel? = null

    companion object {
        fun newInstance(onClick: (() -> Unit)): InputFragment {
            val instance = InputFragment()
            instance.onClick = onClick
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mainViewModel = ViewModelProviders.of(it)[MainViewModel::class.java]
            val valueExist: Boolean = mainViewModel!!.input.value != null
            if (valueExist == true) {
                textInput.setText(mainViewModel!!.input.value)
            }
        } ?: throw Exception("Activity is null")

        submitButton.setOnClickListener {
            mainViewModel?.setInput(textInput.text.toString())
            onClick()
        }
    }
}