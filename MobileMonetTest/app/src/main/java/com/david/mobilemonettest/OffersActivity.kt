package com.david.mobilemonettest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OffersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)

        val userName = intent.getStringExtra("USER");
        val textView = findViewById<TextView>(R.id.textOffers);
        val message  = "$userName, you will get free access to all the Offers available";

        textView.text =  message;
    }
}