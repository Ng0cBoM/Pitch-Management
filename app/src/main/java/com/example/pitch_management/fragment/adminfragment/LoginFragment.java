package com.example.pitch_management.fragment.adminfragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pitch_management.MyApplication;
import com.example.pitch_management.R;
import com.example.pitch_management.activity.admin.MainActivity;
import com.example.pitch_management.activity.user.UserMainActivity;
import com.example.pitch_management.database.MyDatabase;
import com.example.pitch_management.model.Customer;

import java.util.List;

public class LoginFragment extends Fragment {

    public EditText edtStk;
    public EditText edtPassword;
    private TextView tvCheckAccount;
    private CheckBox checkBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_login = view.findViewById(R.id.btn_login);
        edtPassword = view.findViewById(R.id.edt_password_fragment_login);
        edtStk = view.findViewById(R.id.edt_account_fragment_login);
        tvCheckAccount = view.findViewById(R.id.tv_check_account);
        checkBox = view.findViewById(R.id.cbx_remember);

        String account,pass;
        SharedPreferences sharedPreferences;
        if(MyApplication.CURRENT_TYPE == MyApplication.TYPE_ADMIN){
            sharedPreferences = getContext().getSharedPreferences("ADMIN_ACCOUNT", Context.MODE_PRIVATE);
        }else{
            sharedPreferences = getContext().getSharedPreferences("USER_ACCOUNT", Context.MODE_PRIVATE);
        }
        account = sharedPreferences.getString("ACCOUNT","");
        pass = sharedPreferences.getString("PASSWORD","");
        checkBox.setChecked(sharedPreferences.getBoolean("REMEMBER",false));
        edtStk.setText(account);
        edtPassword.setText(pass);

        btn_login.setOnClickListener(v -> {
            if (getContext() != null) {
                String stk = edtStk.getText().toString();
                String password = edtPassword.getText().toString();
                if(MyApplication.CURRENT_TYPE == MyApplication.TYPE_USER){
                    List<Customer> customers = MyDatabase.getInstance(getContext()).customerDAO().getCustomerWithPhone(stk, -1);
                    if(customers.size() > 0){
                        Customer customer = customers.get(0);
                        if(password.equals(customer.getPassword())){
                            tvCheckAccount.setVisibility(View.INVISIBLE);
                            remember(stk,password,checkBox.isChecked(),sharedPreferences);
                            Intent intent = new Intent(getContext(), UserMainActivity.class);
                            intent.putExtra("account",stk);
                            getContext().startActivity(intent);
                        }else{
                            tvCheckAccount.setVisibility(View.VISIBLE);
                        }
                    }else {
                        tvCheckAccount.setVisibility(View.VISIBLE);
                    }
                }else{
                    if (stk.equals("Admin") && password.equals("123456")){
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.putExtra("account",stk);
                        getContext().startActivity(intent);
                    } else {
                        tvCheckAccount.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void remember(String account,String pass,boolean remember,SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(remember){
            editor.putString("ACCOUNT",account);
            editor.putString("PASSWORD",pass);
            editor.putBoolean("REMEMBER",true);
        }else{
            editor.clear();
        }
        editor.apply();
    }
}
