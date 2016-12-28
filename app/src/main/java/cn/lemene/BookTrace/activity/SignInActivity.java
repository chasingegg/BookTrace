package cn.lemene.BookTrace.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.interfaces.LogService;
import cn.lemene.BookTrace.module.Books;
import cn.lemene.BookTrace.module.LogResultResponse;
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

public class SignInActivity extends AppCompatActivity {


    private static final String TAG = "SignInActivity";
    private static final int REQUEST_SIGNUP = 0;
    private boolean LogFlag;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login()
    {
        Log.d(TAG, "SignIn");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

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

        LogService.logService logSer = retrofit.create(LogService.logService.class);

        Gson gson = new Gson();
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("username",email);
        paramsMap.put("password",password);
        String strEntity = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        Call<LogResultResponse>call = logSer.getLogResult(body);
        call.enqueue(new Callback<LogResultResponse>() {
            @Override
            public void onResponse(Call<LogResultResponse> call, Response<LogResultResponse> response) {
                Logger.d("query book done");
                UserContainer.isLogFlag = response.body().getResult();
                if (UserContainer.isLogFlag == true) {
                    UserContainer.username = response.body().getUser().getUsername();
                    UserContainer.userID = response.body().getUser().getId();
                    List<Books> tmp = response.body().getUser().getBooks();

                    UserContainer.wantReadList = new ArrayList<String>();
                    UserContainer.readingList = new ArrayList<String>();
                    UserContainer.hasReadList = new ArrayList<String>();



                    for (Books instanceBooks:tmp) {

                        String result = instanceBooks.getBookname();

                        if (instanceBooks.getStatus().equals("正在读")) {
                            UserContainer.readingList.add(result);
                        }

                        else if (instanceBooks.getStatus().equals("准备读")) {
                            UserContainer.wantReadList.add(result);
                        }

                        else if (instanceBooks.getStatus().equals("已经读")) {
                            UserContainer.hasReadList.add(result);
                        }
                    }

                    Toast.makeText(getBaseContext(), "登录成功！", Toast.LENGTH_SHORT).show();

                }

                else {
                    Toast.makeText(getBaseContext(), "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                }

                }

            @Override
            public void onFailure(Call<LogResultResponse> call, Throwable t) {
                String msg = t.getLocalizedMessage();
                Logger.e(t, "query book error " + msg);

                Toast.makeText(getBaseContext(), "无法与服务器连接", Toast.LENGTH_SHORT).show();
            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登录失败", Toast.LENGTH_SHORT).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _emailText.setError("用户名不能为空");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError("密码不能为空");
            valid = false;
        }

        else if ( password.length() < 4 || password.length() > 10) {
            _passwordText.setError("密码长度必须为4到10位");
            valid = false;
        }

        else {
            _passwordText.setError(null);
        }

        return valid;
    }

}