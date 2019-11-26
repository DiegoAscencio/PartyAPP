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
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputEditText
import com.parse.ParseObject

class ActivityLogup: AppCompatActivity() {
    private lateinit var bBack: TextView
    private lateinit var etDate: TextInputEditText
    private lateinit var bDate: ImageButton

    private lateinit var tUsername: TextInputEditText
    private lateinit var tEmail: TextInputEditText
    private lateinit var tPassword: TextInputEditText
    private lateinit var date: DatePickerDialog

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
        //aqui va el parse

        val gameScore = ParseObject("UserPPM")
        gameScore.put("username", tUsername.text.toString())
        gameScore.put("password", tPassword.text.toString())
        //gameScore.put("email", tEmail.text.toString())
        //gameScore.put("birthday","")

        gameScore.saveInBackground{e -> if (e == null) {
            // Hooray! Let them use the app now.
            Log.d("Si jala", "Si hala")
        } else {
            Log.d("No jala", "No hala")
            // Sign up didn't succeed. Look at the ParseException
            // to figure out what went wrong
        }}


    }

    /*
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPicker(view: View) {

        val mes = c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)
        val anio = c.get(Calendar.YEAR)

         date = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val mesActual = month + 1
                val diaFormateado =
                    if (dayOfMonth < 10) "0" + dayOfMonth.toString() else dayOfMonth.toString()
                val mesFormateado =
                    if (mesActual < 10) "0" + mesActual.toString() else mesActual.toString()
                etDate.setText(diaFormateado + "/" + mesFormateado + "/" + year)
            },
            anio, mes, dia
        )
        date.show()
    }
    */
}