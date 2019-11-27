package iteso.mx.fragments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.ppm.R
import com.google.android.material.textfield.TextInputEditText
import com.mikhaellopez.circularimageview.CircularImageView

class FragmentShare : Fragment() {
    private lateinit var photo: CircularImageView
    private lateinit var username: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var birthday: TextInputEditText
    private lateinit var addPicture: ImageButton
    private lateinit var share: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_share, container, false)
        photo = view.findViewById(R.id.fragment_profle_profilePhoto)
        username = view.findViewById(R.id.fragment_profle_ti_username_tx)
        email = view.findViewById(R.id.fragment_profle_tf_email_tx)
        birthday = view.findViewById(R.id.fragment_profile_tf_birthday_tx)
        addPicture = view.findViewById(R.id.fragment_profle_tf_getPhoto)
        share = view.findViewById(R.id.fragment_profile_tf_shareProfile)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //Add photo
        addPicture.setOnClickListener(View.OnClickListener {
            Log.d("Listener", "Add photo")
        })

        //Share
        share.setOnClickListener(View.OnClickListener {
            Log.d("Listener", "Share")
        })
    }
}