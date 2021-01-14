package com.danieljrodrigues.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDay ->
                val selected = "$selectedDay/${selectedMonth+1}/$selectedYear"
                selectedDate.setText(selected)

                var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val parsedDate = sdf.parse(selected)
                val selectedInMinutes = parsedDate!!.time / 60000
                val current = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currInMinutes = current!!.time / 60000
                val diff = currInMinutes - selectedInMinutes

                result.setText(diff.toString())

            }, year, month, day).show()
    }
}