package cn.lemene.BookTrace.interfaces;


import cn.lemene.BookTrace.module.LogResultResponse;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.Call;

/**
 * Created by dimon on 16/12/25.
 */

public class LogService {
    public static final String RES_GET_LOG_INFORMATION = "signin";
    public static final String RES_GET_SIGNUP_INFORMATION = "signup";

    public interface logService {
        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(RES_GET_LOG_INFORMATION)
        Call<LogResultResponse> getLogResult(@Body RequestBody route);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(RES_GET_SIGNUP_INFORMATION)
        Call<LogResultResponse> getSignupResult(@Body RequestBody route);
    }
}
