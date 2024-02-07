package com.example.project_api_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_api_kotlin.data.ReqresService
import com.example.project_api_kotlin.databinding.ActivityMainBinding
import com.example.project_api_kotlin.ui.adapter.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private lateinit var adapter:UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        getRetrofit()
        initRecyclerView()
        initAPI()
    }

    private fun initAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val res = getRetrofit().getAllUsers()
                if (res.isSuccessful){
                    val body = res.body()
                    body?.let {
                        println(it.data)
                        adapter.updateList(it.data)
                    }
                }
            }catch (e:Exception){
                println(e.message)
            }
        }

    }

    private fun initRecyclerView() {
        adapter = UserAdapter(emptyList()){

        }
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
    }


    private fun getRetrofit():ReqresService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ReqresService::class.java)
    }

}