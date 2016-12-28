package cn.lemene.BookTrace.interfaces;

import cn.lemene.BookTrace.module.CommonResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by WZZ on 2016/12/27.
 */

public class MarkService {
	public static final String GET_MARK_INFORMATION = "mark";

	public interface markService {
		@Headers({"Content-type:application/json;charset=UTF-8"})
		@POST(GET_MARK_INFORMATION)
		Call<CommonResponse> getMarkResult(@Body RequestBody route);
	}
}
