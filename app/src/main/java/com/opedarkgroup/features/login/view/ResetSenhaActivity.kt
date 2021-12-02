package com.opedarkgroup.features.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.resetsenha.ResetSenhaBody
import com.opedarkgroup.features.login.viewmodel.ResetSenhaViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_reset_senha.*

class ResetSenhaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_senha)

        btnAlterarSenhaCancelar.setOnClickListener {
            it.isClickable = false
            finish()
        }

        btnAlterarSenha.setOnClickListener {
            it.isClickable = false
            val viewModel = ViewModelProviders.of(this).get(ResetSenhaViewModel::class.java)
            val email = emailResetSenha.text.toString()

            if (!email.isNullOrEmpty()) {
                viewModel.resetSenha(ResetSenhaBody(email))

                viewModel.resetSenhaResult.observe(this, Observer {
                    if (it) {
                        Toast.makeText(applicationContext, "Senha enviada para seu email", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Erro ao solicitar nova senha", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(applicationContext, "Coloque seu email para receber uma nova senha", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        btnAlterarSenhaCancelar.isClickable = true
        btnAlterarSenha.isClickable = true
    }
}