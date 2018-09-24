package com.krysinski.dawid.nfcidentity.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.krysinski.dawid.nfcidentity.R

class CreateIdentityActivity : AppCompatActivity() {

    companion object {

        fun newIntent(context: Context) = Intent(context, CreateIdentityActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_identity)
        title = "Create identity"
    }
}