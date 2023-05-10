package com.example.pitch_management.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.pitch_management.MyApplication;
import com.example.pitch_management.R;
import com.example.pitch_management.adapter.admin.AdapterForm;
import com.example.pitch_management.database.MyDatabase;
import com.example.pitch_management.fragment.adminfragment.LoginFragment;
import com.example.pitch_management.model.MyTime;
import com.example.pitch_management.model.PithCategory;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class
FormActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    public LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        createData();

        loginFragment = new LoginFragment();

        AdapterForm adapter = new AdapterForm(this);
        viewPager2 = findViewById(R.id.viewpager_form);
        viewPager2.setAdapter(adapter);
        tabLayout = findViewById(R.id.tablayout_form);

        if(MyApplication.CURRENT_TYPE == MyApplication.TYPE_USER) {
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
                if (position == 0) tab.setText("Log In");
                else if (position == 1) tab.setText("Sign Up");
            }).attach();
        }else{
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
                if (position == 0) tab.setText("Log In");
            }).attach();
        }
    }

    public void createData(){
        createPitchCategoryDataIfNotExists();
        addTimeDataIfNotExit();
    }


    public void createPitchCategoryDataIfNotExists(){
        if(MyDatabase.getInstance(this).pitchCategoryDAO().getAll().size()==0){
            PithCategory category = new PithCategory(MyApplication.ID_CATEGORY_PITCH_5,"Sân 5 người",80000);
            MyDatabase.getInstance(this).pitchCategoryDAO().insert(category);
            category = new PithCategory(MyApplication.ID_CATEGORY_PITCH_7,"Sân 7 người",100000);
            MyDatabase.getInstance(this).pitchCategoryDAO().insert(category);
            category = new PithCategory(MyApplication.ID_CATEGORY_PITCH_11,"Sân 11 người",140000);
            MyDatabase.getInstance(this).pitchCategoryDAO().insert(category);
        }
    }

    public void registerSuccess(String s1, String s2) {
        viewPager2.setCurrentItem(0);
        loginFragment.edtStk.setText(s1);
        loginFragment.edtPassword.setText(s2);
    }

    public void addTimeDataIfNotExit(){
        if(MyDatabase.getInstance(this).timeDAO().getAll().size() == 0){
            for (int i = 1;i<=12;i++){
                MyTime myTime = new MyTime();
                myTime.setName("Ca "+i);
                myTime.setId(i);
                myTime.setStartTime((i-1)*2);
                myTime.setEndTime(i*2);
                MyDatabase.getInstance(this).timeDAO().insert(myTime);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}