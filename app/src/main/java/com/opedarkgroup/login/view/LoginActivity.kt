package com.opedarkgroup.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.consultapedido.view.MainActivity
import com.opedarkgroup.R
import com.opedarkgroup.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        viewModel.getLogin()

        viewModel.loginResult.observe(this, Observer {
            textoTeste.text = it.email
        })

        btnEntrar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}