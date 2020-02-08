package revolut.android.test.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import revolut.android.test.api.ApiService
import revolut.android.test.listener.RepoResponseListener
import revolut.android.test.models.CurrencyRate
import revolut.android.test.utils.NetworkUtils
import javax.inject.Inject

class CurrencyRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var networkUtils: NetworkUtils

    fun getVehicles(repoResponseListener: RepoResponseListener<CurrencyRate>): Disposable {
        return apiService.getCurrenyData()
            .map { response ->
                /*
                    This mapping will be helpful if we want to show error messages from server side as well.
                 */
                when {
                    response.isSuccessful -> response.body()!!
                    else -> response.body()!!
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                repoResponseListener.onSuccess(it)
            }, {
                repoResponseListener.onError(it.localizedMessage!!)
            })
    }
}