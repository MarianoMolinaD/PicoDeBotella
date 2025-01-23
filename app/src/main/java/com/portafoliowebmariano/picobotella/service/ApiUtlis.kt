package com.portafoliowebmariano.picobotella.service

class ApiUtlis {
    companion object{
        fun getApiServices(): ApiService{
            return RetrofitClient.getRetrofit().create(ApiService::class.java)
        }
    }
}