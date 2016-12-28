package cn.lemene.BookTrace.module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WZZ on 2016/12/27.
 */

public class Comments {
	@SerializedName("username")
	private String username;

	@SerializedName("content")
	private String content;

	public String getUsername() {
		return this.username;
	}

	public String getContent() {
		return this.content;
	}
}
