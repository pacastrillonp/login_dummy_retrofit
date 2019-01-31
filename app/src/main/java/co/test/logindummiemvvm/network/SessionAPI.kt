package co.test.logindummiemvvm.network

import co.test.logindummiemvvm.model.Account
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SessionAPI {

    @GET("/Manager/Account")
    fun getAccounts(@Query("email") email: String): Observable<List<Account>>


}