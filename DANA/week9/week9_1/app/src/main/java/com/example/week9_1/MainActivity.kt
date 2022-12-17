package com.example.week9_1

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit= Retrofit.Builder()
            .baseUrl("https://openapi.gg.go.kr")
            .addConverterFactory(GsonConverterFactory.create()) // Json데이터를 사용자가 정의한 Java 객채로 변환해주는 라이브러리
            .build() //레트로핏 구현체 완성!
        val server_1: BookService = retrofit.create(BookService::class.java)

        server_1.getBookList("c6287d767dd74db39390763f6f733423","json",1,10).enqueue(object : Callback<BookResponseDTO>{
            override fun onFailure(call: Call<BookResponseDTO>?, t: Throwable?) {
                Log.e("BookRanking", t.toString())
            }

            override fun onResponse(call: Call<BookResponseDTO>?, response: Response<BookResponseDTO>?) {
                if(response?.isSuccessful?.not() == true){
                    return
                }
                response?.body()?.let{
                    //body가 있다면 그안에는 bestSellerDto가 들어있을것
                    Log.d(TAG,it.toString())

                    it.Poplitloanbook.forEach{ book->
                        Log.d(TAG,book.toString())
                    }
                Log.d("Book Ranking", response?.body().toString())
            }
        }
    })
        val server_2: GGSPSTService = retrofit.create(GGSPSTService::class.java)
        server_2.getGGSPSTList("69dfb85c1b3a49eb8232c45dab448e86","json",1,10).enqueue(object : Callback<GGSPSTResponseDTO>{
            override fun onFailure(call: Call<GGSPSTResponseDTO>, t: Throwable) {
                Log.e("Specialties", t.toString())
            }

            override fun onResponse(
                call: Call<GGSPSTResponseDTO>,
                response: Response<GGSPSTResponseDTO>
            ) {
                if(response?.isSuccessful?.not() == true){
                    return
                }
                response?.body()?.let{
                    //body가 있다면 그안에는 bestSellerDto가 들어있을것
                    Log.d(TAG,it.toString())

                    it.GGSPST.forEach{ book->
                        Log.d(TAG,book.toString())
                    }
                    Log.d("Region Specialties", response?.body().toString())
                }
            }
        })

        val server_3: RegionMnySTService = retrofit.create(RegionMnySTService::class.java)
        server_3.getStoreList("70cf4e06f74142ab945f3cbe85b0dcd3","json",1,10).enqueue(object : Callback<RegionMnySTResponseDTO>{
            override fun onFailure(call: Call<RegionMnySTResponseDTO>, t: Throwable) {
                Log.e("RegionMoney_Store", t.toString())
            }

            override fun onResponse(
                call: Call<RegionMnySTResponseDTO>,
                response: Response<RegionMnySTResponseDTO>
            ) {
                if(response?.isSuccessful?.not() == true){
                    return
                }
                response?.body()?.let{
                    //body가 있다면 그안에는 bestSellerDto가 들어있을것
                    Log.d(TAG,it.toString())

                    it.RegionMnyFacltStus.forEach{ book->
                        Log.d(TAG,book.toString())
                    }
                    Log.d("Region Money Store", response?.body().toString())
                }
            }
        })
}
}