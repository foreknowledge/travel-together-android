package com.mungziapp.traveltogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.interfaces.OnItemClickListener;
import com.mungziapp.traveltogether.item.DetailScheduleItem;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
	private Context context;
	private List<List<String>> dates = new ArrayList<>();

	private OnItemClickListener listener;

	public ScheduleAdapter(Context context) {
		this.context = context;
	}

	public void addDate(List<String> date) { dates.add(date); }
	public List<String> getDate(int position) {
		return dates.get(position);
	}
	public void setClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_schedule, parent, false);

		return new ViewHolder(itemView, context, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.setItem(dates.get(position));
	}

	@Override
	public int getItemCount() {
		return dates.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView textDayN;
		private TextView textDate;
		private TextView scheduleNotice;

		ViewHolder(@NonNull final View itemView, final Context context, final OnItemClickListener listener) {
			super(itemView);

			textDayN = itemView.findViewById(R.id.text_day_n);
			textDate = itemView.findViewById(R.id.text_date);
			scheduleNotice = itemView.findViewById(R.id.schedule_notice);

			TextView addSchedule = itemView.findViewById(R.id.btn_add_schedule);
			addSchedule.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null) listener.onItemClick(ViewHolder.this, itemView, getAdapterPosition());
				}
			});

			RecyclerView detailRecycler = itemView.findViewById(R.id.detail_schedule_recycler);
			detailRecycler.setLayoutManager(new LinearLayoutManager(context));

			DetailScheduleAdapter detailRecyclerAdapter =  new DetailScheduleAdapter(context);
//			detailRecyclerAdapter.addItem(new DetailScheduleItem("호텔 조식 냠", "8:00", null, null, null));
//			detailRecyclerAdapter.addItem(new DetailScheduleItem("준비하고 출발!", "10:00", "준비물 - 물병, 셀카봉, 지갑, 핸드폰, 보조배터리", null, null));
//			detailRecyclerAdapter.addItem(new DetailScheduleItem(true, "260번 버스 타고 센트럴역으로 이동", null, null, null, null));
			detailRecyclerAdapter.setClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
					Toast.makeText(context, "item 클릭함.", Toast.LENGTH_SHORT).show();
				}

				@Override
				public Boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
					return null;
				}
			});

			detailRecycler.setAdapter(detailRecyclerAdapter);
		}

		void setItem(List<String> date) {
			String strDayN = "DAY " + date.get(0);
			textDayN.setText(strDayN);
			String strDate = "(" + date.get(1) + ")";
			textDate.setText(strDate);
		}
	}
}
