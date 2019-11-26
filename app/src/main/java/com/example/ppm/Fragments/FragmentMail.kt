package iteso.mx.fragments.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ppm.R
import com.google.android.material.textfield.TextInputEditText
import com.parse.ParseObject
import com.parse.ParseQuery

class FragmentMail : Fragment(){

    lateinit var back: ImageView
    lateinit var textView: EditText
    lateinit var cancel: Button
    lateinit var save: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mail, container, false)

        back = view.findViewById(R.id.back_arrow)
        textView = view.findViewById(R.id.feedback_et)
        cancel = view.findViewById(R.id.cancel_btn)
        save = view.findViewById(R.id.save_btn)

        return view
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)

        back.setOnClickListener(View.OnClickListener {
            this.getActivity()?.onBackPressed()
        })

        cancel.setOnClickListener(View.OnClickListener {
            textView.text.clear()
        })

        save.setOnClickListener(View.OnClickListener {
            val sharePref = this.activity?.getSharedPreferences("session", Context.MODE_PRIVATE)
            var user = sharePref!!.getString("user","no jala")
            Log.d("Save", user)

            //Parse
            val feed = ParseObject("Feedback")
            feed.put("username", user!!)
            feed.put("feedback", textView.text.toString())

            feed.saveInBackground{e -> if (e == null) {
                Toast.makeText(this.activity, "Gracias! Hemos recibido tu feedback", Toast.LENGTH_SHORT)
                textView.text.clear()
            } else {
                Log.d("No jala", "No hala")
            }}

        })


    }

}