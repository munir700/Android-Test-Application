package revolut.android.test.api.converter;

import com.bumptech.glide.load.HttpException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class ApiConverter<T> implements Converter<ResponseBody, T> {
    private Converter<ResponseBody, T> delegate;
    private Converter<ResponseBody, T> upperWrapDelegate;

    private ResponseBody updateResponseBody;

    ApiConverter(Converter<ResponseBody, T> delegate) {
        this.delegate = delegate;

        this.upperWrapDelegate = new Converter<ResponseBody, T>() {
            @Override
            public T convert(@NotNull ResponseBody responseBody) throws IOException {
                String response = responseBody.string();
                updateResponseBody = ResponseBody.create(responseBody.contentType(), response);
                Type typeOfObjectsList = new TypeToken<T>() {
                }.getType();
                Gson gson = new GsonBuilder().create();
                return gson.fromJson(response, typeOfObjectsList);
            }
        };

    }

    @Override
    public T convert(@NotNull ResponseBody responseBody) throws IOException {
        try {
            T metaDelegate = this.upperWrapDelegate.convert(responseBody);
            if (metaDelegate != null) {
                return delegate.convert(updateResponseBody);
            }
            return null;
        } catch (HttpException httpEx) {
            throw httpEx;
        } catch (Exception e) {
            //TODO : Handle it more gracefully. Define status and error.
            throw new HttpException(null);
        }

    }


}