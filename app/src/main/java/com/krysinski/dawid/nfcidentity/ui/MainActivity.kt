package com.krysinski.dawid.nfcidentity.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.krysinski.dawid.nfcidentity.R
import com.krysinski.dawid.nfcidentity.util.NfcIdentityManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val nfcIdentityManager by lazy {
        NfcIdentityManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityCreateAction.setOnClickListener {
            startActivity(CreateIdentityActivity.newIntent(this))
        }

        Handler().postDelayed({ animateCard() }, 2000)
    }

    private fun animateCard() {
        val deltaX = mainActivityPhone.x - mainActivityCard.width / 2
        val startX = mainActivityCard.x
        val animator = ObjectAnimator.ofFloat(mainActivityCard, "translationX", deltaX)
        animator.duration = 2000
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                Handler().postDelayed({ reverseAnimateCard(startX) }, 1000)
            }
        })
        animator.start()
    }

    private fun reverseAnimateCard(startX: Float) {
        val reverseAnimator = ObjectAnimator.ofFloat(mainActivityCard, "translationX", startX)
        reverseAnimator.duration = 1000
        reverseAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                animateCard()
            }
        })
        reverseAnimator.start()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.let {
            val tag = it.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)

        }
    }

    override fun onResume() {
        super.onResume()
        nfcIdentityManager.enable()
    }

    override fun onPause() {
        nfcIdentityManager.disable()
        super.onPause()
    }
}
