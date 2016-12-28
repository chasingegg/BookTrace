package cn.lemene.BookTrace.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WZZ on 2016/12/27.
 */

public class CommentGetResponse {
	@SerializedName("comments")
	private List<Comments> comments;

	public List<Comments> getComments() {
		return this.comments;
	}

}
