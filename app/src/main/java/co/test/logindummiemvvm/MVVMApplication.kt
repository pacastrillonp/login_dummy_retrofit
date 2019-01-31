package co.test.logindummiemvvm

import android.app.Application
import co.test.logindummiemvvm.network.SessionAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MVVMApplication: Application() {

    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var sessionAPI: SessionAPI

        fun injectSessionAPI() = sessionAPI
    }

    private val BASE_URL = "https://apilab.arkbox.co:443"

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        sessionAPI = retrofit.create(SessionAPI::class.java)
    }

}