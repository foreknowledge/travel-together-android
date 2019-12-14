package com.mungziapp.traveltogether.customWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MaxHeightRecyclerView extends RecyclerView {
	private static final int MAX_HEIGHT = 200;

	public MaxHeightRecyclerView(Context context) {
		super(context);
	}

	public MaxHeightRecyclerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MaxHeightRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthSpec, int heightSpec) {
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, MAX_HEIGHT, getResources().getDisplayMetrics());
		heightSpec = MeasureSpec.makeMeasureSpec(px, MeasureSpec.AT_MOST);
		super.onMeasure(widthSpec, heightSpec);
	}
}
