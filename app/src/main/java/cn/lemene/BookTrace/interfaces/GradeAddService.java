package cn.lemene.BookTrace.interfaces;

import cn.lemene.BookTrace.module.GradeAddResponse;
import retrofit2.Call;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by WZZ on 2016/12/26.
 */

public class GradeAddService {
	public static final String GET_GRADE_ADD_INFORMATION = "grade";

	public interface gradeAddService {
		@Headers({"Content-type:application/json;charset=UTF-8"})
		@POST(GET_GRADE_ADD_INFORMATION)
		Call<GradeAddResponse> getGradeAddResult(@Body RequestBody route);
	}
}
