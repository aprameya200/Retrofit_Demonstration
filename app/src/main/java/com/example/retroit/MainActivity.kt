package com.example.retroit

import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retroit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter
    private var listOfNews = ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()

    }

    //sends API request to get the news headlines  
    fun getNews()  {
        val news: Call<News> = NewsService.newsInstance.getHeadLines("us", 1)


        // enqueue() send the request and notifies of its response or if error occured in communicating with the server
            news.enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news: News? = response.body()

                    recyclerView = findViewById(R.id.newsList)


                    if (news != null){
                        Log.d("Success", news.toString())
                        adapter = NewsAdapter(this@MainActivity, news.articles)
                        recyclerView.adapter = adapter

                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                    }


                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("FAILURE", "Error in fetching news", t)
                }
            })


    }
}