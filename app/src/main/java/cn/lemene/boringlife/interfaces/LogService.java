package cn.lemene.boringlife.interfaces;


import android.app.DownloadManager;

import cn.lemene.boringlife.module.LogResultResponse;
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

    public interface logService {
        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(RES_GET_LOG_INFORMATION)
        Call<LogResultResponse> getLogResult(@Body RequestBody route);
    }
}
