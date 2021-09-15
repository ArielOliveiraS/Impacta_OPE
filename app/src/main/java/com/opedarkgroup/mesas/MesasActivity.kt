package com.opedarkgroup.mesas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opedarkgroup.R
import kotlinx.android.synthetic.main.activity_mesas.*

class MesasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        btnMesaVoltar.setOnClickListener {
            finish()
        }
    }
}