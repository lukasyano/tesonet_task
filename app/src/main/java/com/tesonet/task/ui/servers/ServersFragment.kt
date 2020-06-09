package com.tesonet.task.ui.servers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesonet.task.R
import com.tesonet.task.snack
import com.tesonet.task.ui.ServersAdapter
import kotlinx.android.synthetic.main.fragment_servers.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ServersFragment : Fragment() {

    private val serversViewModel: ServersViewModel by viewModel()
    private lateinit var serversAdapter: ServersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_servers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serversAdapter = ServersAdapter()
        recycleView.adapter = serversAdapter
        recycleView.layoutManager = LinearLayoutManager(context)

        observeLiveData()
    }

    private fun observeLiveData() {
        serversViewModel.liveData.observe(
            viewLifecycleOwner, Observer {
                when (it) {
                    is ServersUiState.Success -> {
                        serversAdapter.updateData(it.servers)
                        spinner.hide()
                    }
                    is ServersUiState.ErrorMsg -> {
                        recycleView.snack(it.errorText)
                    }
                    is ServersUiState.NoTokenError -> {
                        //todo navigate
                    }
                }
            }
        )
    }
}