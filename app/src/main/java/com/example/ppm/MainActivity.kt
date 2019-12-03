package com.example.ppm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.textfield.TextInputEditText
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var bLogup: Button
    private lateinit var bLogin: Button
    private lateinit var txtUser: TextInputEditText
    private lateinit var txtPass: TextInputEditText
    private lateinit var frgtPass :TextView
    private lateinit var baboutUs: ImageView

    private lateinit var userText: String

    companion object {
        const val CHANNEL_ID = "AndroidCourse"
    }


    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        createNotificationChannel()

        //Click on about us
        baboutUs = find(R.id.activity_main_aboutus)
        baboutUs.setOnClickListener { startActivity<AboutUs>() }

        txtUser = find(R.id.activity_main_tf_username_tx)
        txtPass = find(R.id.activity_main_tf_password_tx)


        //Boton logup
        bLogup = find(R.id.activity_main_btn_logup)
        bLogup.setOnClickListener {
          startActivity<ActivityLogup>()
        }

        //Boton login
        bLogin = find(R.id.activity_main__btn_login)
        val sharedPref = getSharedPreferences("session",Context.MODE_PRIVATE)

        bLogin.setOnClickListener {
            Log.d("brn","login works")

            val query = ParseQuery.getQuery<ParseObject>("UserPPM")
            val users = query.find()

            var loginIsTrue = false
            users.forEach { u -> run{
                if (u.get("username") == txtUser.text.toString() && u.get("password") == txtPass.text.toString()) {
                    val editor = sharedPref.edit()
                    editor.putString("user", u.get("username").toString())
                    editor.apply()
                    loginIsTrue = true
                    userText = u.get("username").toString()
                    startActivity<GroupsActivity>()
                } else {
                    Log.d("No jala", "no jala")
                }
            }}

            if(!loginIsTrue){
                Toast.makeText(this, "Datos invalidos", Toast.LENGTH_LONG).show()
            }

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Es hora de jugar!")
                .setContentText("Que gusto tenerte de vuelta $userText :)")
                .setStyle(NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            NotificationManagerCompat.from(this).notify(10, builder.build())
        }
      
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this@MainActivity)

        // Set the alert dialog title
        builder.setTitle(R.string.caution)

        // Display a message on alert dialog
        builder.setMessage(R.string.disclaimer)

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(R.string.continue_str){dialog, which ->

        }


        // Display a negative button on alert dialog
        builder.setNegativeButton(R.string.exit){dialog,which ->
            //Toast.makeText(applicationContext,"You are not agree.",Toast.LENGTH_SHORT).show()
            System.exit(0)
        }


        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()

        frgtPass = find(R.id.activity_main_forgotPassword)
        frgtPass.setOnClickListener{
            // Initialize a new layout inflater instance
            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // Inflate a custom view using layout inflater
            val view = inflater.inflate(R.layout.forgot_password_popup,null)

            // Initialize a new instance of popup window
            val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                LinearLayout.LayoutParams.WRAP_CONTENT // Window height
            )

            // Get the widgets reference from custom view
            val it_email = view.findViewById<TextView>(R.id.popup_frgt_email)
            val btnCancel = view.findViewById<Button>(R.id.popup_forgotpassword_btn_cancel)
            val btnRestore = view.findViewById<Button>(R.id.popup_forgotpassword_btn_restore)

            // Set click listener for popup window's text view
            it_email.setOnClickListener{

            }

            // Set a click listener for popup's button widget
            btnCancel.setOnClickListener{
                popupWindow.dismiss()
            }

            // Set a click listener for popup's button widget
            btnRestore.setOnClickListener{
                popupWindow.dismiss()
            }

            // Finally, show the popup window on app
            TransitionManager.beginDelayedTransition(root_layout)
            popupWindow.showAtLocation(
                root_layout, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                0 // Y offset
            )
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "mx.iteso.ANDROID",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManger.createNotificationChannel(notificationChannel)
        }
    }
}
