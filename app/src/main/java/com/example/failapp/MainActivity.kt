package com.example.failapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.failapp.models.Stores
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//http://128.199.215.102:4040/api/stores?page=1

const val  BASE_URL="http://128.199.215.102:4040"
var page=1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrofitCall()
        val next=findViewById<Button>(R.id.nextbutton)
        val prev=findViewById<Button>(R.id.prevbutton)

        prev.setOnClickListener {
            page--
            if(page<1){
                page=1
                //Toast.makeText(this,"No More Back",Toast.LENGTH_LONG).show()
            }
            retrofitCall()
        }

        next.setOnClickListener{
            page++
            if(page>55){
                page=55
                //Toast.makeText(this,"No More Left",Toast.LENGTH_LONG).show()
            }
            retrofitCall()
        }
    }

    private fun retrofitCall() {
        lateinit var  adapter: ShoppingAdapter
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService=retrofit.create(ApiInterface::class.java)
        val response=apiService.getStores(page)

        response.enqueue(object : Callback<Stores>{
            override fun onResponse(call: Call<Stores>, response: Response<Stores>) {
                val body=response.body()!!
                val showpageno=findViewById<TextView>(R.id.pageno)
                showpageno.text="Page No $page of 55"
                adapter= ShoppingAdapter(this@MainActivity, body.data)
                val recyclerview=findViewById<RecyclerView>(R.id.shoppingrecycler)
                recyclerview.adapter= adapter
                adapter.setOnItemClickListener(object : ShoppingAdapter.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent=Intent(this@MainActivity,DetailedActivity::class.java)
                        intent.putExtra("ID",body.data[position].id)
                        intent.putExtra("NAME",body.data[position].name)
                        intent.putExtra("ADDR",body.data[position].address)

                        startActivity(intent)
                    }
                })
            }
            override fun onFailure(call: Call<Stores>, t: Throwable) {
                val message = "Something is wrong. Check your internet connection!"
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}


