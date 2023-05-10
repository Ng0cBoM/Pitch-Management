package com.example.pitch_management.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitch_management.MyApplication;
import com.example.pitch_management.R;
import com.example.pitch_management.model.MyTime;

import java.util.List;

public class CaThiDauAdapter extends RecyclerView.Adapter<CaThiDauAdapter.ViewHolder> {

    private Context context;
    private List<MyTime> times;

    public CaThiDauAdapter(Context context, List<MyTime> times) {
        this.context = context;
        this.times = times;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cathidau,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(times.get(position).getName());
        holder.tvTime.setText(times.get(position).getStartTime() + "h - " + times.get(position).getEndTime()+"h");
        holder.tvMoney.setText(MyApplication.convertMoneyToString(times.get(position).getMoney())+"VNĐ");

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_down_to_up));
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public void setData(List<MyTime> list){
        times = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvTime,tvMoney;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_ca_item_cathidau);
            tvTime = itemView.findViewById(R.id.tv_time_item_cathidau);
            tvMoney = itemView.findViewById(R.id.tv_money_item_cathidau);
            cardView = itemView.findViewById(R.id.cardView_cathidau);
        }
    }
}
