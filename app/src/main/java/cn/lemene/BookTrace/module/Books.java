package cn.lemene.BookTrace.module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WZZ on 2016/12/27.
 */

public class Books {
		@SerializedName("bookname")
		private String bookname;

		@SerializedName("status")
		private String status;

		public String getBookname() {
			return this.bookname;
		}

		public String getStatus() {
			return this.status;
		}

	}
