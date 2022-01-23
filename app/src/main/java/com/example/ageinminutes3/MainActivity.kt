package com.example.ageinminutes3

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        textView5.setOnClickListener { view ->
            showDatePicker(view)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDatePicker(view: View) {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selecteMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selecteMonth + 1}/$selectedYear"
                my_textView.setText(selectedDate)

                val sdf=SimpleDateFormat("dd/mm/yyyy" , Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val dateInMinutes = theDate.time/60000
                val current = sdf.format(System.currentTimeMillis())
                val currentDate = sdf.parse(current)
                val currentDateInMinutes = currentDate.time/60000

                val totalMinutes = currentDateInMinutes - dateInMinutes
                minutes_textView.setText(totalMinutes.toString())

            },
            year,
            month,
            day
        )
            .show()
    }
}