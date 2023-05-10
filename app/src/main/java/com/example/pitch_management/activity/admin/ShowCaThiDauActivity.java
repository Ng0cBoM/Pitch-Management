package com.example.pitch_management.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.pitch_management.R;
import com.example.pitch_management.adapter.admin.CaThiDauAdapter;
import com.example.pitch_management.database.MyDatabase;

public class ShowCaThiDauActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CaThiDauAdapter adapter;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ca_thi_dau);

        recyclerView = findViewById(R.id.recy_show_cathi);
        adapter = new CaThiDauAdapter(this, MyDatabase.getInstance(this).timeDAO().getAll());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        imgBack = findViewById(R.id.img_back_show_cathi);
        imgBack.setOnClickListener(v->{
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}