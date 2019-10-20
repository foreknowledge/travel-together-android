package com.mungziapp.traveltogether;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NoticeItem> items = new ArrayList<>();

    NoticeAdapter(Context context) { this.context = context; }
    void addItem(NoticeItem item) { items.add(item); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.notice_item, parent, false);

        return new ViewHolder(itemView, items, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;

        ViewHolder(@NonNull final View itemView, final ArrayList<NoticeItem> items, final Context context) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            contents = itemView.findViewById(R.id.item_contents);
        }

        void setItem(NoticeItem item) {
            title.setText(item.getTitle());
            contents.setText(item.getContents());
        }
    }
}
