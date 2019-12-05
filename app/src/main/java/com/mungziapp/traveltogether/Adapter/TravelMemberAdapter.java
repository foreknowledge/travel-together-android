package com.mungziapp.traveltogether.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;

import java.util.ArrayList;

public class TravelMemberAdapter extends RecyclerView.Adapter<TravelMemberAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Integer> items = new ArrayList<>();

    public TravelMemberAdapter(Context context) { this.context = context; }
    public void addItem(int item) { items.add(item); }

    @Override   // 아이템의 개수 리턴
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override   // 뷰홀더가 만들어지는 시점에 호출되는 메서드
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_member, parent, false);

        return new ViewHolder(itemView);
    }

    @Override   // 데이터와 뷰가 결합되는 시점에 호출되는 메서드
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(items.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        // 각각의 item에 대한 뷰가 뷰홀더의 파라미터로 전달 됨.
        ViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.member_img);
        }

        void setItem(int item) {
            imageView.setImageResource(item);
        }
    }
}
