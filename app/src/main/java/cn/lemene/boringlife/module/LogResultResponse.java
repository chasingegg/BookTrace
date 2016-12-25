package cn.lemene.boringlife.module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dimon on 16/12/25.
 */

public class LogResultResponse {
    @SerializedName("result")
    private boolean mResult;

    public boolean getmResult() {
        return this.mResult;
    }

    public void setmResult(boolean mResult) {
        this.mResult = mResult;
    }
}
