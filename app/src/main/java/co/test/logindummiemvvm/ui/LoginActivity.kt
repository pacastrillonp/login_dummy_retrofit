package co.test.logindummiemvvm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import co.test.logindummiemvvm.R
import co.test.logindummiemvvm.adapter.AccountsAdapter
import co.test.logindummiemvvm.model.AccountsRequest
import co.test.logindummiemvvm.network.LoginWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    private var accountsListAdapter: AccountsAdapter? = null
    private var accountsList: ArrayList<AccountsRequest>? = null
    private var loginWebService: LoginWebService? = null


    private var mCompositeDisposable: CompositeDisposable? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.
        loginWebService = LoginWebService()
        initAccountsRecyclerView()
        mCompositeDisposable = CompositeDisposable()
        initControllers()
    }


    private fun initAccountsRecyclerView() {
        rv_accounts_list.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_accounts_list.layoutManager = layoutManager
    }

    private fun initControllers() {
        bt_sign_in.setOnClickListener {
            val requestInterface =   loginWebService?.getAccounts("pablo.castrillon@tekus.co")

            if (requestInterface != null) {
                mCompositeDisposable?.add(requestInterface.getAccounts()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError))
            }

            Toast.makeText(this@LoginActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }

    }


    private fun handleResponse(androidList: List<AccountsRequest>) {
        accountsList = ArrayList(androidList)
        println(androidList)


    }

    private fun handleError(error: Throwable) {

        Log.d("", error.localizedMessage)
    }



}




