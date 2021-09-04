package com.example.retrofit1_lyold_video;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText mEtUserId;
    private Button mBtnApiCall;
    private TextView mtvFirstName;
    private TextView mtvLastName;
    private TextView mtvEmailId;
    private ImageView mIvAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEtUserId = findViewById(R.id.etUserId);
        mBtnApiCall = findViewById(R.id.btnCallApi);
        mtvFirstName =findViewById(R.id.tvfirstName);
        mtvLastName = findViewById(R.id.tvLastName);
        mtvEmailId = findViewById(R.id.tvEmail);
        mIvAvatar = findViewById(R.id.ivAvatar);
        mBtnApiCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**** in the apiService we are getting the list of response from the ApiService Interface ******/
                ApiService apiService = Network.getInstance().create(ApiService.class);
                int userId = Integer.parseInt(mEtUserId.getText().toString());
                apiService.getUser(userId).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        /**** ResponseModel  will give u whole data which is present in postman class****/
                        /***** give internet permission in tne manifest file without given can not access server data****/
                        ResponseModel responseModel = response.body();
                        String firstName= responseModel.getData().getFirstName();
                        String LastName = responseModel.getData().getLastName();
                        String EmailId = responseModel.getData().getEmail();
                        mtvFirstName.setText(firstName);
                        mtvLastName.setText(LastName);
                        mtvEmailId.setText(EmailId);
                        /*** Glide is a library which help u to load the image and cache the image****/
                        Glide.with(mIvAvatar).load(responseModel.getData().getAvatar()).into(mIvAvatar);



                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                    }
                });
            }
        });
    }
}