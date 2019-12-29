package com.mungziapp.traveltogether.model;

import com.mungziapp.traveltogether.R;

public enum FragmentType {
	NOTICE(0, R.id.notification),
	SUPPLIES(1, R.id.supplies),
	SCHEDULE(2, R.id.schedule),
	ACCOUNT(3, R.id.account_book),
	DIARY(4, R.id.diary);

	private int index, menuItemId;
	FragmentType(int index, int menuItemId) { this.index = index; this.menuItemId = menuItemId; }
	public int getIndex() { return index; }
	public int getMenuItemId() { return menuItemId; }
}
