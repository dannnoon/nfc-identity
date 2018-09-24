package com.krysinski.dawid.nfcidentity.util

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable

class NfcIdentityManager(private val activity: Activity) {

    private val nfcAdapter = NfcAdapter.getDefaultAdapter(activity)
    private val pendingIntent: PendingIntent
    private val intentFilters = arrayOf<IntentFilter>()
    private val techList: Array<Array<String>> = arrayOf(
            arrayOf(Ndef::class.java.name),
            arrayOf(NdefFormatable::class.java.name)
    )

    init {
        val intent = Intent(activity, activity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        pendingIntent = PendingIntent.getActivity(activity, 0, intent, 0)
    }

    fun isActive(): Boolean = nfcAdapter.isEnabled

    fun enable() {
        nfcAdapter.enableForegroundDispatch(
                activity,
                pendingIntent,
                intentFilters,
                techList
        )
    }

    fun disable() {
        nfcAdapter.disableForegroundDispatch(activity)
    }
}