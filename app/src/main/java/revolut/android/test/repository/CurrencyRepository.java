package revolut.android.test.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;
import revolut.android.test.R;
import revolut.android.test.api.ApiService;
import revolut.android.test.base.BaseNetworkCallBack;
import revolut.android.test.base.BaseViewModel;
import revolut.android.test.enums.ViewModelEventsEnum;
import revolut.android.test.models.CurrencyRate;
import revolut.android.test.utils.NetworkUtils;

public class CurrencyRepository {
    @Inject
    ApiService apiService;

    @Inject
    NetworkUtils networkUtils;

    @Inject
    public CurrencyRepository() {
    }


    public MutableLiveData<CurrencyRate> getCurrenyData(final BaseViewModel viewModel, Call<CurrencyRate> currencyCall) {
        final MutableLiveData<CurrencyRate> liveData = new MutableLiveData<>();
        if (networkUtils.isNetworkAvailable()) {
            if (currencyCall != null) {
                currencyCall.cancel();
            }
            currencyCall = apiService.getCurrenyData(ApiService.Companion.getCurrencyName());
            currencyCall.enqueue(new BaseNetworkCallBack<CurrencyRate>(viewModel) {
                @Override
                public void onResponse(Call<CurrencyRate> call, Response<CurrencyRate> response) {
                    super.onResponse(call, response);
                    if (!call.isCanceled() && response.isSuccessful()){
                        liveData.postValue(response.body());
                    }
                }
            });
        } else {
            viewModel.notifyObserver(ViewModelEventsEnum.NO_INTERNET_CONNECTION, viewModel.getAppManager().getContext().getString(R.string.NO_INTERNET_CONNECTIVITY));
        }
        return liveData;
    }

}
