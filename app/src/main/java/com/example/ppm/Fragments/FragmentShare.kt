package iteso.mx.fragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ppm.R
import com.mikhaellopez.circularimageview.CircularImageView

class FragmentShare : Fragment() {
    private lateinit var photo : CircularImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_share, container, false)
        photo = view.findViewById(R.id.fragment_profle_profilePhoto)
        return view
    }
}