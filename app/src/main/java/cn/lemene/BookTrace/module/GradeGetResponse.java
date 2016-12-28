package cn.lemene.BookTrace.module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WZZ on 2016/12/27.
 */

public class GradeGetResponse {
	@SerializedName("result")
	private boolean result;

	@SerializedName("averageGrade")
	private float averageGrade;

	@SerializedName("myGrade")
	private float myGrade;

	public boolean getResult() {
		return  this.result;
	}

	public float getAverageGrade() {
		return this.averageGrade;
	}

	public float getMyGrade() {
		return this.myGrade;
	}
}
