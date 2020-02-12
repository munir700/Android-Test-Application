package revolut.android.test.base;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import revolut.android.test.enums.ViewModelEventsEnum;
import revolut.android.test.interfaces.ViewModelCallBackObserver;
import revolut.android.test.managers.AppManager;

/**
 * Created by Munir Ahmad.
 */

public class BaseViewModel extends ObservableViewModel {

    CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    protected AppManager appManager;

    private ArrayList<ViewModelCallBackObserver> callBacksObservers = new ArrayList<>();

    public void addObserver(ViewModelCallBackObserver callBackObserver) {
        this.callBacksObservers.add(callBackObserver);
    }

    public void notifyObserver(ViewModelEventsEnum eventType, Object message) {
        for (ViewModelCallBackObserver callBackObserver : callBacksObservers) {
            callBackObserver.onObserve(eventType, message);
        }
    }

    public void removeCallBacks() {
        callBacksObservers.clear();
    }

    public AppManager getAppManager() {
        return appManager;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        removeCallBacks();
    }

}
