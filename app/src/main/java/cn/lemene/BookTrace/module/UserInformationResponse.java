package cn.lemene.BookTrace.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WZZ on 2016/12/27.
 */

public class UserInformationResponse {
	@SerializedName("books")
	private List<Books> books;

	public List<Books> getBooks() {
		return this.books;
	}
}
