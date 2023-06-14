package com.example.cookbook.fragments


import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cookbook.R
import com.google.android.material.textfield.TextInputEditText


class StoperFragment : Fragment(), View.OnClickListener {
    private var seconds = 15
    private var running = false
    private var wasRunning = false
    private val minutesMaxValue = 999
    private val secondsMaxValue = 59
    private lateinit var minutesField: TextInputEditText
    private lateinit var secondsField: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_stoper, container, false)
        runStoper(layout)
        layout.findViewById<View>(R.id.start_button).setOnClickListener(this)
        layout.findViewById<View>(R.id.stop_button).setOnClickListener(this)
        layout.findViewById<View>(R.id.reset_button).setOnClickListener(this)

        minutesField = layout.findViewById<View>(R.id.minutes_field) as TextInputEditText
        secondsField = layout.findViewById<View>(R.id.seconds_field) as TextInputEditText
        minutesField.setText("0")
        secondsField.setText("15")
        minutesField.addTextChangedListener(minutesTextWatcher)
        secondsField.addTextChangedListener(secondsTextWatcher)

        return layout
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    private fun onClickStart() {
        running = true
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = minutesField.text.toString().toInt() * 60 + secondsField.text.toString().toInt()
        minutesField.visibility = View.VISIBLE
        secondsField.visibility = View.VISIBLE
        val timeView = view?.findViewById<View>(R.id.time_view) as TextView
        timeView.visibility = View.INVISIBLE
    }

    private fun runStoper(view: View) {
        val timeView = view.findViewById<View>(R.id.time_view) as TextView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {

                val minutes = seconds / 60
                val secs = seconds % 60
                val time = String.format("%02d:%02d", minutes, secs)
                timeView.text = time
                if (running) {
                    timeView.visibility = View.VISIBLE
                    minutesField.visibility = View.INVISIBLE
                    secondsField.visibility = View.INVISIBLE
                    seconds--

                    if (seconds == 0 && running){
                        Thread.sleep(1000)
                        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                        val r = RingtoneManager.getRingtone(requireContext(), notification)
                        r.play()
                        Toast.makeText(requireContext(), "COUNTDOWN HAS FINISHED", Toast.LENGTH_LONG).show()
                        timeView.visibility = View.INVISIBLE
                        onClickReset()
                    }
                }
                handler.postDelayed(this, 1000)

            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
        }
    }


    private val minutesTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            if (s.isNullOrBlank()) {
                s?.replace(0, 0, "0")
                return
            }
            if (s.length == 2 && s.toString() == "00"){
                s.replace(1, s.length, "")
            }
            if (s.length > 1 && s[0] == '0'){
                s.replace(0, 1, "")
            }
            val minutesValue = s.toString().toIntOrNull() ?: 0
            if (minutesValue > minutesMaxValue) {
                s.replace(0, s.length, minutesMaxValue.toString())
            }
            seconds = minutesField.text.toString().toInt() * 60 + secondsField.text.toString().toInt()
        }
    }

    private val secondsTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            if (s.isNullOrBlank()) {
                s?.replace(0, 0, "0")
                return
            }
            if (s.length > 1 && s[0] == '0'){
                s.replace(0, 1, "")
            }
            val secondsValue = s.toString().toIntOrNull() ?: 0
            if (secondsValue > secondsMaxValue) {
                s.replace(0, s.length, secondsMaxValue.toString())
            }
            seconds = minutesField.text.toString().toInt() * 60 + secondsField.text.toString().toInt()
        }
    }


}