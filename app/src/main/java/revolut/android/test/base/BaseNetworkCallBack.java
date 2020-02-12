package revolut.android.test.base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import revolut.android.test.R;
import revolut.android.test.enums.ViewModelEventsEnum;
import revolut.android.test.utils.NetworkExceptionUtils;


public class BaseNetworkCallBack<T> implements Callback<T> {
    private final BaseViewModel viewModel;

    public BaseNetworkCallBack(BaseViewModel viewModel) {
        this.viewModel = viewModel;
        init();
    }

    private void init() {
        notifyObserver(ViewModelEventsEnum.ON_API_CALL_START, null);
    }

    public void notifyObserver(ViewModelEventsEnum viewModelEvent, Object message) {
        viewModel.notifyObserver(viewModelEvent, message);

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        notifyObserver(ViewModelEventsEnum.ON_API_CALL_STOP, null);
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        if (throwable.getMessage() == null) {
            notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
            notifyObserver(ViewModelEventsEnum.ON_API_CALL_STOP, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
            return;
        }

        NetworkExceptionUtils.ExceptionViewModel exceptionViewModel = NetworkExceptionUtils.getNetworkException(viewModel, throwable);
        notifyObserver(exceptionViewModel.getViewModelEventsEnum(), exceptionViewModel.getMessage());

        notifyObserver(ViewModelEventsEnum.ON_API_CALL_STOP, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));

    }
}
