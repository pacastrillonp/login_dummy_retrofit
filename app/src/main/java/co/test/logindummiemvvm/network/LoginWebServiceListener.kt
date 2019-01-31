package co.test.logindummiemvvm.network

import co.test.logindummiemvvm.model.AccountsRequest
import io.reactivex.Observable
import retrofit2.http.GET

interface LoginWebServiceListener {

    @GET("api")
    fun getAccounts (): Observable<List<AccountsRequest>>
}