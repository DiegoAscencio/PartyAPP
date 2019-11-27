package com.example.ppm

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.find
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputEditText
import com.parse.ParseObject
import java.time.MonthDay
import java.util.*

class ActivityLogup: AppCompatActivity() {
    private lateinit var bBack: TextView
    private lateinit var etDate: TextInputEditText
    private lateinit var bDate: ImageButton

    private lateinit var tUsername: TextInputEditText
    private lateinit var tEmail: TextInputEditText
    private lateinit var tPassword: TextInputEditText
    private lateinit var tPassword1: TextInputEditText
    private lateinit var tBirthday: TextInputEditText


    private lateinit var fullDate: String


    @RequiresApi(Build.VERSION_CODES.N)
    //private var c = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logup)

        //Mostrar fecha picker
        etDate =find(R.id.activity_logup_tf_birthday)
        bDate = find(R.id.activity_logup_btn_getDate)

        tUsername = find(R.id.activity_logup_tf_username_tx)
        tEmail = find(R.id.activity_logup_tf_email_tx)
        tPassword = find(R.id.activity_logup_tf_password_tx)
        tPassword1 = find(R.id.activity_logup_tf_confpassword_tx)
        tBirthday = find(R.id.activity_logup_tf_birthday)


        //Hide action bar
        if (supportActionBar != null)
            supportActionBar?.hide()

        bBack = find(R.id.activity_logup_toMain)
        bBack.setOnClickListener {
            this.onBackPressed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun clicklup(view: View) {
        //validaciones

        if (tUsername.text!!.isNotBlank() &&
            tPassword.text!!.isNotBlank() &&
            tEmail.text!!.isNotBlank() &&
            //fullDate.isEmpty() &&
            tPassword.text.toString().equals(tPassword1.text.toString())
        ) {

            val gameScore = ParseObject("UserPPM")
            gameScore.put("username", tUsername.text.toString())
            gameScore.put("password", tPassword.text.toString())
            gameScore.put("email", tEmail.text.toString())
            gameScore.put("birthday",fullDate)

            gameScore.saveInBackground{e -> if (e == null) {
                // Hooray! Let them use the app now.
                Log.d("Si jala", "Si hala")
            } else {
                Log.d("No jala", "No hala")
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
            }
            }

        } else {
            Toast.makeText(this, "Las contraseñas deben de coincidir y ningun campo debe estar vacío", Toast.LENGTH_LONG).show()
        }
    }


    fun clickDataPicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            //Toast.makeText(this, """$dayOfMonth - ${monthOfYear + 1} - $year""", Toast.LENGTH_LONG).show()
            fullDate = """$dayOfMonth / ${monthOfYear + 1} / $year"""
            // tBirthday.text = fullDate.toEditable()
        }, year, month, day)
        dpd.show()
    }

}
