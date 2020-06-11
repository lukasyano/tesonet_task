package com.tesonet.task.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tesonet.task.R
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
        //hide default action bar
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()

        buttonLogin.setOnClickListener {
            login()
            hideKeyboard()
            spinner.show()
        }
    }

    private fun login() {
        username = editTextUsername.text.toString()
        password = editTextPassword.text.toString()

        if (username.isNotEmpty() || password.isNotEmpty()) {
            loginViewModel.login(username, password)
        }
    }

    private fun observeLiveData() {
        loginViewModel.liveData.observe(
            viewLifecycleOwner, Observer {
                when (it) {
                    is LoginUiState.Success -> {
                        hideKeyboard()
                        view?.let {
                            findNavController()
                                .navigate(
                                    LoginFragmentDirections.actionLoginFragmentToServersFragment()
                                )
                        }
                    }
                    is LoginUiState.Error -> {
                        spinner.hide()
                        buttonLogin.snack(it.error)
                    }
                }
            }
        )
    }
    private fun hideKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}
