package cn.lemene.BookTrace.module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dimon on 16/12/25.
 */

public class LogResultResponse {
    @SerializedName("result")
    private boolean result;

    @SerializedName("user")
    private User user;

    public boolean getResult() {
        return this.result;
    }

    public void setmResult(boolean mResult) {
        this.result = mResult;
    }
    public User getUser() {return this.user;}
    public void setUser(User user) {this.user=user;}


    public class User {
        @SerializedName("id")
        private String id;

        @SerializedName("username")
        private String username;

        @SerializedName("password")
        private String password;

        @SerializedName("book")     //待修改
        private String book;

        public String getId() {return this.id;}
        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return this.username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }
        public void setPassword(String password) {
            this.password = password;
        }

        public String getBook() {
            return this.book;
        }
        public void setBook(String book) {
            this.book = book;
        }
    }
}
