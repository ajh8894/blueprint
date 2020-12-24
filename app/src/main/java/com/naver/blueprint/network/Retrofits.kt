package com.naver.blueprint.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


/**
 * @author junghwan.ahn@webtoonscorp.com
 */
object Retrofits {

    private val retrofitMap = EnumMap<Type, Retrofit>(Type::class.java)

    private fun get(type: Type): Retrofit = retrofitMap.getOrPut(type, { create(type.baseUrl) })

    private fun create(baseUrl: String): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    enum class Type(val baseUrl: String) {

        GITHUB("https://github.com/")

    }

}