package com.andrewdanilin.homework6

import com.andrewdanilin.homework6.chatmessage.ChatMessage
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.*

interface MessagesAPI {

    @GET("1ch")
    suspend fun getMessages(
        @Query("limit") limit: Int,
        @Query("lastKnownId") lastKnownId: Int,
        @Query("reverse") reverse: Boolean
    ): List<ChatMessage>

    @POST("1ch")
    @Headers("Content-Type: application/json")
    suspend fun sendTextMessage(
        @Body requestBody: RequestBody
    ): ResponseBody

    @POST("1ch")
    @Multipart
    suspend fun sendImageMessage(
        @Part("msg") msg: RequestBody,
        @Part picture: MultipartBody.Part
    ): ResponseBody
}