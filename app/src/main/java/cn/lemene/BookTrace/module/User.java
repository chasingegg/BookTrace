package cn.lemene.BookTrace.module;

/**
 * Created by xu on 2016/11/25.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户实体类
 * @author snail 2016/11/25 17:55
 * @version v1.0
 */

public class User implements Serializable {
    /** ID */
    @SerializedName("id")
    private String mId;

    /** 用户名 */
    @SerializedName("username")
    private String mUsername;

    /** 想读的书 */
    @SerializedName("bookWant")
    private List<String> mBookWant;

    /** 在读的书 */
    @SerializedName("bookReading")
    static List<String> mBookReading;

    /** 读过的书 */
    @SerializedName("bookRead")
    static List<String> mBookRead;

    public User() {
        mUsername = UserContainer.username;
        mId =UserContainer.userID;
        mBookWant = UserContainer.wantReadList;
        mBookReading = UserContainer.readingList;
        mBookRead = UserContainer.hasReadList;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public List<String> getBookWant() {
        return mBookWant;
    }

    public void setBookWant(List<String> bookWant) {
        mBookWant = bookWant;
    }

    public List<String> getBookReading() {
        return mBookReading;
    }

    public void setBookReading(List<String> bookReading) {
        mBookReading = bookReading;
    }

    public List<String> getBookRead() {
        return mBookRead;
    }

    public void setBookRead(List<String> bookRead) {
        mBookRead = bookRead;
    }

    public String getBookWantString() {
        String tmp="Please login in.";
        String tmp2 = "No book.";
        if(UserContainer.username.equals("DefaultUser")) {
            return tmp;
        }
        if(mBookWant!=null) {
            return list2String(mBookWant);
        }
        else{return tmp2;}
    }

    public String getBookReadingString() {
        String tmp = "Please login in.";
        String tmp2 = "No book.";
        if(UserContainer.username.equals("DefaultUser")) {
            return tmp;
        }
        if(mBookReading!=null) {
            return list2String(mBookReading);
        }
        else{return tmp2;}
    }

    public String getBookReadString() {
        String tmp="Please login in.";
        String tmp2 = "No book.";
        if(UserContainer.username.equals("DefaultUser")) {
            return tmp;
        }
        if(mBookRead!=null) {
            return list2String(mBookRead);
        }
        else{return tmp2;}
    }

    /**
     * 字符串列表转换为字符串
     * @param list
     * @return
     */
    public String list2String(List<?> list) {
        StringBuilder builder = new StringBuilder();
        if (list != null && list.size() > 0) {
            int i;
            for (i = 0; i < list.size() - 2; i++) {
                builder.append(list.get(i));
                builder.append('\n');
            }
            builder.append(list.get(i));
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return mId != null ? mId.equals(user.mId) : user.mId == null;

    }

    @Override
    public int hashCode() {
        return mId != null ? mId.hashCode() : 0;
    }

    /*public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        setId("ZHEER_XU!"); //初始化全局变量
        List<String> list_book = null;
        list_book.add("Ocean");
        setBookWant(list_book);
        setBookReading(list_book);
        setBookRead(list_book);
    }*/

    /*@Override
    /*public String toString() {
        return "DBBook{" +
                "mId='" + mId + '\'' +
                ", mRating=" + mRating +
                ", mTitle='" + mTitle + '\'' +
                ", mSubtile='" + mSubtile + '\'' +
                ", mAuthors=" + mAuthors +
                ", mPUbDate='" + mPUbDate + '\'' +
                ", mTagses=" + mTagses +
                ", mOriginTitle='" + mOriginTitle + '\'' +
                ", mCover='" + mCover + '\'' +
                ", mBinding='" + mBinding + '\'' +
                ", mTranslators=" + mTranslators +
                ", mCatalog='" + mCatalog + '\'' +
                ", mPages='" + mPages + '\'' +
                ", mCoveres=" + mCoveres +
                ", mWebLink='" + mWebLink + '\'' +
                ", mWebTitle='" + mWebTitle + '\'' +
                ", mApiUrl='" + mApiUrl + '\'' +
                ", mPUblisher='" + mPUblisher + '\'' +
                ", mIsbn10='" + mIsbn10 + '\'' +
                ", mIsbn13='" + mIsbn13 + '\'' +
                ", mAuthorIntro='" + mAuthorIntro + '\'' +
                ", mSummary='" + mSummary + '\'' +
                ", mPrice='" + mPrice + '\'' +
                ", mComments='" + mComments + '\'' +
                '}';
    }*/
}