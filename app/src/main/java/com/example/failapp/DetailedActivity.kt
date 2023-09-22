package com.example.failapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val shopnumberdetails=findViewById<TextView>(R.id.shopnoD)
        val shopnamedetails=findViewById<TextView>(R.id.shopnameD)
        val shopaddressdetails=findViewById<TextView>(R.id.shopaddrD)

        val bundle : Bundle? =intent.extras

        val id=bundle?.get("ID")
        shopnumberdetails.text="Shop No: ${id.toString()}"
        val name=bundle?.get("NAME")
        shopnamedetails.text="Shop Name: ${name.toString()}"
        val address=bundle?.get("ADDR")
        shopaddressdetails.text="Shop Address: ${address.toString()}"
    }
}