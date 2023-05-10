package com.example.pitch_management.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pitch_management.MyApplication;
import com.example.pitch_management.R;
import com.example.pitch_management.activity.admin.FormActivity;

public class SelectTypeActivity extends AppCompatActivity {

    LinearLayout linearLayoutAdmin, linearLayoutUser;
    TextView tvAdmin, tvUser;
    Button btnConfirm;

    boolean isUserRoom = false;
    boolean isAdminRoom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        btnConfirm = findViewById(R.id.btn_confirn_select_type);
        linearLayoutAdmin = findViewById(R.id.linear_select_type_admin);
        linearLayoutUser = findViewById(R.id.linear_select_type_user);
        tvAdmin = findViewById(R.id.text_view_select_type_admin);
        tvUser = findViewById(R.id.text_view_select_type_user);


        linearLayoutAdmin.setOnClickListener(v -> {
            if (MyApplication.CURRENT_TYPE != MyApplication.TYPE_ADMIN) {
                btnConfirm.setEnabled(true);
                btnConfirm.setBackground(AppCompatResources.getDrawable(this, R.drawable.btn_background));
                if(!isAdminRoom) {
                    linearLayoutAdmin.setBackground(AppCompatResources.getDrawable(this, R.drawable.btn_background));
                    isAdminRoom = true;
                }
                if(isUserRoom) {
                    linearLayoutUser.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_item_naptien));
                    isUserRoom = false;
                }
                tvAdmin.setTextSize(16);
                tvUser.setTextSize(13);
                tvAdmin.setTextColor(getResources().getColor(R.color.white));
                tvUser.setTextColor(getResources().getColor(R.color.dark_gray));

                MyApplication.CURRENT_TYPE = MyApplication.TYPE_ADMIN;
            }
        });

        linearLayoutUser.setOnClickListener(v -> {
            if (MyApplication.CURRENT_TYPE != MyApplication.TYPE_USER) {
                btnConfirm.setEnabled(true);
                btnConfirm.setBackground(AppCompatResources.getDrawable(this, R.drawable.btn_background));

                if(isAdminRoom) {
                    linearLayoutAdmin.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_item_naptien));
                    isAdminRoom = false;
                }
                if(!isUserRoom) {
                    linearLayoutUser.setBackground(AppCompatResources.getDrawable(this, R.drawable.btn_background));
                    isUserRoom = true;
                }
                tvAdmin.setTextSize(13);
                tvUser.setTextSize(16);
                tvAdmin.setTextColor(getResources().getColor(R.color.dark_gray));
                tvUser.setTextColor(getResources().getColor(R.color.white));

                MyApplication.CURRENT_TYPE = MyApplication.TYPE_USER;
            }
        });

        btnConfirm.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
            finish();
        });
    }

}