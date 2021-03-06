package com.opedarkgroup.features.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.features.comandaeletronica.home.view.MainActivity
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.resetsenha.ResetSenhaBody
import com.opedarkgroup.data.models.login.LoginBody
import com.opedarkgroup.features.login.viewmodel.LoginViewModel
import com.opedarkgroup.features.login.viewmodel.ResetSenhaViewModel
import kotlinx.android.synthetic.main.activity_login.*

//alan.esteves -> 1234

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        resetSenhaTextView.setOnClickListener {
//            val viewModel = ViewModelProviders.of(this).get(ResetSenhaViewModel::class.java)
//
////            viewModel.resetSenha(ResetSenhaBody())
//
//            viewModel.resetSenhaResult.observe(this, Observer {
//                if (it) {
//                    Toast.makeText(applicationContext, "Login ou senha incorretos", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(applicationContext, "Login ou senha incorretos", Toast.LENGTH_SHORT).show()
//                }
//            })
//
//        }

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        btnEntrar.setOnClickListener {
            it.isClickable = false
            val user = usuarioEditText.text.toString()
            val password = senhaEditText.text.toString()
            viewModel.getLogin(LoginBody(user, password))

            viewModel.loginResult.observe(this, Observer {
                if (it) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Login ou senha incorretos", Toast.LENGTH_SHORT).show()
                    btnEntrar.isClickable = true
                }
            })
        }
    }
}