package co.test.logindummiemvvm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import co.test.logindummiemvvm.MVVMApplication
import co.test.logindummiemvvm.R
import co.test.logindummiemvvm.adapter.AccountsAdapter
import co.test.logindummiemvvm.model.Account
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    private var accountsListAdapter: AccountsAdapter? = null
    private var accountsList: ArrayList<Account>? = null

    private var mCompositeDisposable: CompositeDisposable? = null

    companion object {
        private val sessionAPI = MVVMApplication.injectSessionAPI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.
        initAccountsRecyclerView()
        initControllers()
    }


    private fun initAccountsRecyclerView() {
        rv_accounts_list.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_accounts_list.layoutManager = layoutManager
    }

    private fun initControllers() {
        bt_sign_in.setOnClickListener {
            sessionAPI.getAccounts("pablo.castrillon@tekus.co")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{
                    Log.d("PABLO", it.first().legalName)
                }
        }

    }

}




