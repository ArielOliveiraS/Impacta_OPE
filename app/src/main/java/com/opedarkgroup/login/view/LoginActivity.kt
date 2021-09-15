package com.opedarkgroup.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.consultapedido.view.MainActivity
import com.opedarkgroup.R
import com.opedarkgroup.login.model.models.LoginResponse
import com.opedarkgroup.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

//alan.esteves -> 1234

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        btnEntrar.setOnClickListener {
            val user = usuarioEditText.text.toString()
            val password = senhaEditText.text.toString()
            viewModel.getLogin(LoginResponse(user, password))

            viewModel.loginResult.observe(this, Observer {
                if (it) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "Login ou senha incorretos", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}