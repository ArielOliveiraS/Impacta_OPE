package com.opedarkgroup.features.comandaeletronica.home.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.removefuncionario.RemoveFuncionarioBody
import com.opedarkgroup.data.models.admin.removemesa.RemoveMesaBody
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import com.opedarkgroup.features.admin.configuracoes.ConfiguracoesActivity
import com.opedarkgroup.features.admin.funcionarios.viewmodel.RemoveFuncionarioViewModel
import com.opedarkgroup.features.admin.mesas.viewmodel.RemoveMesaViewModel
import com.opedarkgroup.features.comandaeletronica.mesasocupadas.view.ConsultaMesasAbertasActivity
import com.opedarkgroup.features.comandaeletronica.mesaslivres.view.MesasActivity
import com.opedarkgroup.features.login.view.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSair.setOnClickListener {
            deslogarDialog()
        }

        btnFazerPedido.setOnClickListener {
            it.isClickable = false
            startActivity(Intent(this, MesasActivity::class.java))
        }

        btnConsultarPedido.setOnClickListener {
            it.isClickable = false
            startActivity(Intent(this, ConsultaMesasAbertasActivity::class.java))
        }

        btnConfiguracoes.setOnClickListener {
            it.isClickable = false
            startActivity(Intent(this, ConfiguracoesActivity::class.java))
        }
    }

    fun deslogarDialog() {
        val alert: AlertDialog
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Deseja realmente sair?")
            .setPositiveButton("Sair",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })

        alert = builder.create()
        alert.show()
    }

    override fun onResume() {
        super.onResume()
        btnFazerPedido.isClickable = true
        btnConsultarPedido.isClickable = true
        btnConfiguracoes.isClickable = true
    }

    override fun onBackPressed() {
        deslogarDialog()
    }
}