package br.senai.sp.jandira.retrofit_reqres_xml

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/users/{id}")
    suspend fun getUserByID(@Path("id") id: String): Response<BaseResponse<UserResponse>>
}
