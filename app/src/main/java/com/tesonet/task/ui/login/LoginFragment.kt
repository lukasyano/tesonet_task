package com.tesonet.task.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tesonet.task.R
import com.tesonet.task.hideKeyboard
import com.tesonet.task.snack
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var username: String
    private lateinit var password: String

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        username = editTextUsername.text.toString()
        password = editTextPassword.text.toString()
        loginViewModel.login(username, password)
    }

    private fun observeLiveData() {

        loginViewModel.liveData.observe(
            viewLifecycleOwner, Observer {
                loginSpinner.hide()
                view?.hideKeyboard()

                when (it) {
                    is LoginUiState.Success -> {
                        view?.let {
                            findNavController().navigate(
                                LoginFragmentDirections.actionLoginFragmentToServersFragment()
                            )
                        }
                    }
                    is LoginUiState.Error -> {
                        buttonLogin.snack(getString(it.errorMsg))
                    }
                    is LoginUiState.ShowSpinner -> loginSpinner.show()
                }
            }
        )
    }
}
