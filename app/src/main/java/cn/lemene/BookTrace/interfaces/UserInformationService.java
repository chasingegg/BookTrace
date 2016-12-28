package cn.lemene.BookTrace.interfaces;

import cn.lemene.BookTrace.module.UserInformationResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WZZ on 2016/12/27.
 */

public class UserInformationService {
	public static final String GET_USER_INFORMATION = "user";

	public interface userInformationService {
		@GET(GET_USER_INFORMATION)
		Call<UserInformationResponse> getUserInformation(@Query("username") String username);
	}
}
