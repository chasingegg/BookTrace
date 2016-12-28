package cn.lemene.BookTrace.interfaces;

import cn.lemene.BookTrace.module.CommentGetResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WZZ on 2016/12/27.
 */

public class CommentGetService {
	public static final String GET_COMMENT_INFORMATION = "book";

	public interface commentGetService {
		@GET(GET_COMMENT_INFORMATION)
		Call<CommentGetResponse> getComment(@Query("bookid") String bookid);
	}
}
