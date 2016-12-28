package cn.lemene.BookTrace.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.adapter.CommentAdapter;
import cn.lemene.BookTrace.interfaces.CommentGetService;
import cn.lemene.BookTrace.interfaces.CommentSendService;
import cn.lemene.BookTrace.module.Comment;
import cn.lemene.BookTrace.module.CommentGetResponse;
import cn.lemene.BookTrace.module.Comments;
import cn.lemene.BookTrace.module.CommonResponse;
import cn.lemene.BookTrace.module.UserContainer;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView comment;
    private TextView hide_down;
    private EditText comment_content;
    private Button comment_send;

    private LinearLayout rl_enroll;
    private RelativeLayout rl_comment;

    private ListView comment_list;
    private CommentAdapter adapterComment;
    private List<Comment> data;
    private List<Comments> getData;
    private String id;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent = getIntent();
        id = intent.getStringExtra("bookId");

        initView();
    }

    private void initView()
    {
        comment_list = (ListView) findViewById(R.id.comment_list);
        data = new ArrayList<>();

        adapterComment = new CommentAdapter(getApplicationContext(), data);
        comment_list.setAdapter(adapterComment);


        //Get Comment from server
        final ProgressDialog progressDialog = new ProgressDialog(CommentActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Comments");
        progressDialog.show();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(UserContainer.BASE_IP_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        CommentGetService.commentGetService comGetSer = retrofit.create(CommentGetService.commentGetService.class);
        Call<CommentGetResponse> call = comGetSer.getComment(id);
        call.enqueue(new Callback<CommentGetResponse>() {
            @Override
            public void onResponse(Call<CommentGetResponse> call, Response<CommentGetResponse> response) {
                getData = response.body().getComments();
                for (Comments tmp:getData) {
                    Comment mCom = new Comment();
                    System.out.println("评论 "+tmp.getUsername()+":"+tmp.getContent());
                    mCom.setName(tmp.getUsername());
                    mCom.setContent(tmp.getContent());
                    adapterComment.addComment(mCom);
                }
            }

            @Override
            public void onFailure(Call<CommentGetResponse> call, Throwable t) {

            }
        });

        progressDialog.dismiss();


        comment = (ImageView) findViewById(R.id.comment);
        hide_down = (TextView) findViewById(R.id.hide_down);
        comment_content = (EditText) findViewById(R.id.comment_content);
        comment_send = (Button) findViewById(R.id.comment_send);

        rl_enroll = (LinearLayout) findViewById(R.id.rl_enroll);
        rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);
        setListener();
    }

    public void setListener(){
        comment.setOnClickListener(this);

        hide_down.setOnClickListener(this);
        comment_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment:
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // 显示评论框
                rl_enroll.setVisibility(View.GONE);
                rl_comment.setVisibility(View.VISIBLE);
                break;
            case R.id.hide_down:
                // 隐藏评论框
                rl_enroll.setVisibility(View.VISIBLE);
                rl_comment.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                break;
            case R.id.comment_send:
                sendComment();
                break;
            default:
                break;
        }
    }

    /**
     * 发送评论
     */
    public void sendComment(){
        if(UserContainer.username.equals("DefaultUser")){
            Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();}
        else if(comment_content.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();}
        else{

            // 生成评论数据
            final Comment comment = new Comment();
            comment.setName(UserContainer.username+"：");
            comment.setContent(comment_content.getText().toString());

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(UserContainer.BASE_IP_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            CommentSendService.commentSendService comSer = retrofit.create(CommentSendService.commentSendService.class);

            Gson gson = new Gson();
            HashMap<String,String> paramsMap = new HashMap<>();
            paramsMap.put("username",comment.getName());
            paramsMap.put("bookId",id);
            paramsMap.put("content",comment.getContent());
            String strEntity = gson.toJson(paramsMap);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
            Call<CommonResponse> call = comSer.getCommentSendResult(body);
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    adapterComment.addComment(comment);
                    // 发送完，清空输入框
                    comment_content.setText("");
                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {

                }
            });

            Toast.makeText(getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }

}
