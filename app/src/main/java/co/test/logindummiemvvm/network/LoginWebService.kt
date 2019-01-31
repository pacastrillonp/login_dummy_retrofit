package co.test.logindummiemvvm.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginWebService {
    private val BASE_URL = "https://apilab.arkbox.co"


    private val loginWebServiceListener: LoginWebServiceListener? = null
    fun getAccounts(email: String): LoginWebServiceListener {
        if (loginWebServiceListener != null) {
            return loginWebServiceListener
        }
//        val retrofit = Retrofit.Builder().baseUrl( "$BASE_URL:443/Manager/Account/?email=$email")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("$BASE_URL:443/Manager/Account/?email=$email")
            .build()
        return retrofit.create(LoginWebServiceListener::class.java)
    }
}

