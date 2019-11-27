package com.example.ppm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import com.example.ppm.Fragments.AboutUsFragment
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


    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

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
        bLogin.setOnClickListener{


            Log.d("brn","login works")
            //startActivity<GroupsActivity>()

            val query = ParseQuery.getQuery<ParseObject>("UserPPM")
            query.findInBackground{objects, e ->
                if (e == null) {
                    for (armor in objects) {
                        //Log.d("Datos:", "User: " + txtUser.text.toString() + " Pass: " + txtPass.text.toString())
                        if (armor.get("username") == txtUser.text.toString() && armor.get("password") == txtPass.text.toString()) {
                            // Log.d("Jala", "Jala")
                            val editor = sharedPref.edit()
                            editor.putString("user", armor.get("username").toString())
                            editor.apply()
                            startActivity<GroupsActivity>()
                        } else {
                            Log.d("No jala", "no jala")
                        }
                    }
                } else {
                    Log.d("Errorsazo", "Error: " + e!!.message)
                }
            }                    //Log.d("Parse:", "User: " + armor.get("username") + " Pass: " + armor.get("password"))

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

   /* fun showAboutUs(view: View){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_main, AboutUsFragment())
            .commit()
    }*/
}
