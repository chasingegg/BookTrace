package cn.lemene.BookTrace.interfaces;

import cn.lemene.BookTrace.module.GradeGetResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by WZZ on 2016/12/27.
 */

public class GradeGetService {

	public interface  gradeGetService {
		@GET("getgrade/{bookid}/{userid}")
		Call<GradeGetResponse> getGradeResult(@Path("bookid") String bookid, @Path("userid") String userid);
	}

}
