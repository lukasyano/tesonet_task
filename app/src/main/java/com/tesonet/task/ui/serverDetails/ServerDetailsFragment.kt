package com.tesonet.task.ui.serverDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tesonet.task.R
import kotlinx.android.synthetic.main.fragment_server_details.*

class ServerDetailsFragment : Fragment() {

    private val distance by lazy {
      navArgs<ServerDetailsFragmentArgs>().value.distance
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_server_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        server_distance_text.text = distance.toString()
    }
}