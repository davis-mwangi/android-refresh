package com.david.mobilemonettest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var  sf: SharedPreferences;
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var enteredName:String;
    private lateinit var greetingTextView:TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MY_TAG", "MainActivity:onCreate")

        sf = getSharedPreferences("my_sf", MODE_PRIVATE);
        editor = sf.edit();

        greetingTextView =  findViewById<TextView>(R.id.greetingTextView);
        val inputField =  findViewById<EditText>(R.id.tvName);
        val submitButton  = findViewById<Button>(R.id.btnSubmit);
        val offersButton =  findViewById<Button>(R.id.btnOffers);


        submitButton.setOnClickListener{

             enteredName =  inputField.text.toString();
            if(enteredName == ""){
                greetingTextView.text = "";
                offersButton.visibility = INVISIBLE;
                Toast.makeText(
                    this@MainActivity,
                    "Please Enter your name",
                    Toast.LENGTH_SHORT
                ).show();
            }else {
                val message = "Welcome $enteredName";
                greetingTextView.text = message;
                inputField.text.clear();

                offersButton.visibility = VISIBLE;

            }
        }

        offersButton.setOnClickListener {
            val intent  =  Intent(this, OffersActivity::class.java);
            intent.putExtra("USER", enteredName);
            startActivity(intent);
        }

    }

    override fun onPause() {
        super.onPause()
        val name = enteredName

        editor.apply{
            putString("sf_name", name);
            commit();
        };
    }

    override fun onResume() {
        super.onResume()

        val name = sf.getString("sf_name", null);
        val message = "Welcome $name";
        greetingTextView.text =  message;

    }
}