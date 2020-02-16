package revolut.android.test.repository;

import android.content.Context;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import revolut.android.test.api.ApiHttpClient;
import revolut.android.test.api.ApiService;
import revolut.android.test.api.converter.ApiConverterFactory;
import revolut.android.test.models.CurrencyRate;
import revolut.android.test.utils.NetworkUtils;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class CurrencyRepositoryTest {

    private String resposneString;

    private NetworkUtils networkUtils;
    private MockWebServer mockWebServer = new MockWebServer();
    private ApiService apiService;

    @Before
    public void setUp() throws Exception {
        Context appContext = RuntimeEnvironment.systemContext;
        resposneString = "{\"base\":\"EUR\",\"date\":\"2018-09-06\",\"rates\":{\"AUD\":1.6183,\"BGN\":1.9581,\"BRL\":4.7975,\"CAD\":1.5356,\"CHF\":1.1288,\"CNY\":7.9545,\"CZK\":25.746,\"DKK\":7.4656,\"GBP\":0.89931,\"HKD\":9.1433,\"HRK\":7.4429,\"HUF\":326.88,\"IDR\":17344.0,\"ILS\":4.1756,\"INR\":83.817,\"ISK\":127.95,\"JPY\":129.7,\"KRW\":1306.3,\"MXN\":22.392,\"MYR\":4.8177,\"NOK\":9.7876,\"NZD\":1.7654,\"PHP\":62.666,\"PLN\":4.3234,\"RON\":4.644,\"RUB\":79.669,\"SEK\":10.603,\"SGD\":1.6019,\"THB\":38.175,\"TRY\":7.6373,\"USD\":1.1648,\"ZAR\":17.844}}";
        networkUtils = new NetworkUtils(appContext);
        mockWebServer.start();
        apiService = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(new ApiConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new ApiHttpClient().getHTTPClient(new HashMap<String, String>()))
                .build()
                .create(ApiService.class);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void testApiServiceResponse() {
        if (networkUtils.isNetworkAvailable()) {

            MockResponse response = new MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(resposneString);
            mockWebServer.enqueue(response);

            try {
                Response<CurrencyRate> currencyRate = apiService.getCurrenyData(ApiService.Companion.getCurrencyName()).execute();
                CurrencyRate currencyRateDefined = new Gson().fromJson(resposneString, CurrencyRate.class);
                assertEquals(currencyRateDefined, currencyRate.body());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}