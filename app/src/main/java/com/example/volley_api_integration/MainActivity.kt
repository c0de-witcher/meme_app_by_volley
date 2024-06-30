package com.example.volley_api_integration

import android.app.DownloadManager
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.volley_api_integration.databinding.ActivityMainBinding
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    val url : String = "https://meme-api.com/gimme"
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.next.setOnClickListener {
            getdata()
        }

        getdata()
    }

    fun getdata(){



// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val progessdis  = ProgressDialog(this)
        progessdis.setMessage("Loading")
        progessdis.show()


// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.i("Response is: ","meme data "+ response.toString())

                val responseobject = JSONObject(response)
                binding.tittle.text = responseobject.getString("title")
                binding.author.text = responseobject.getString("author")
             //   binding.image.set
                Glide.with(this@MainActivity)
                    .load(responseobject.getString("url"))
                    .into(binding.image);
                progessdis.dismiss()


              },{ error->
                Toast.makeText(this, error.localizedMessage,Toast.LENGTH_LONG).show()
                progessdis.dismiss()

            })


// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}