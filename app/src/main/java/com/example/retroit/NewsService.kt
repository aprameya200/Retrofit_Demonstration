package com.example.retroit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


//https://newsapi.org/v2/top-headlines?country=us&apiKey=59d8c9a75ac34e1da733cfa639ecf231


const val BASE_URL = "https://newsapi.org/"
const val API_Key = "59d8c9a75ac34e1da733cfa639ecf231"

interface NewsInterface {

    // calling this method creates a url
    // base url + value + + api_KEY + country + page
    @GET("v2/top-headlines?apiKey=$API_Key")
    fun getHeadLines(@Query("country") country: String, @Query("page") page: Int): Call<News> //deserialized into News Object
    // https://newsapi.org/ + v2/top-headlines?apiKey=$API_Key + & country + & page

}

//singleton
object NewsService {

    //not instansiated
    val newsInstance: NewsInterface // singleton object of NewsService

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        //Builder is inner class of retrofit


        //implements same
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}