package cn.lemene.boringlife.interfaces;


import cn.lemene.boringlife.module.LogResultResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;;
import retrofit2.Call;
/**
 * Created by dimon on 16/12/25.
 */

public class LogService {
    public static final String RES_GET_LOG_INFORMATION = "signup";

    public interface logService {
        @POST(RES_GET_LOG_INFORMATION)
        @FormUrlEncoded
        Call<LogResultResponse> getLogResult(@Field("userName")String userName,@Field("password")String password);
    }
}
