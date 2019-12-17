package com.mungziapp.traveltogether.app;

import android.content.Context;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.data.Countries;
import com.mungziapp.traveltogether.data.TravelData;

import java.util.ArrayList;
import java.util.List;

public class TravelHelper {
	private static TravelHelper instance = new TravelHelper();

	public static TravelHelper getInstance() { return instance; }
	private TravelHelper() { Countries.setCountryList(); }

	public static void init(Context context) {
		DatabaseManager.openDatabase(context);
		DatabaseManager.dropTables();
		DatabaseManager.createTables();
		DatabaseManager.insertDummyData(makeTravelItems());
	}

	private static List<TravelData> makeTravelItems() {
		String countries = "\uD83C\uDDF0\uD83C\uDDF7,\uD83C\uDDFA\uD83C\uDDF8,\uD83C\uDDED\uD83C\uDDF0,\uD83C\uDDEB\uD83C\uDDF7" +
				",\uD83C\uDDEC\uD83C\uDDFA,\uD83C\uDDFB\uD83C\uDDF3,\uD83C\uDDF2\uD83C\uDDF4,\uD83C\uDDF3\uD83C\uDDF5" +
				",\uD83C\uDDEC\uD83C\uDDF9,\uD83C\uDDEC\uD83C\uDDE9,\uD83C\uDDEC\uD83C\uDDF7,\uD83C\uDDEC\uD83C\uDDF1";
		String countries2 = "";
		String countries3 = "\uD83C\uDDF7\uD83C\uDDFA";

		int members = 9;
		int members2 = 1;
		int members3 = 2;

		List<TravelData> travelData = new ArrayList<>();
		travelData.add(new TravelData(1, "엄마와 함께하는 4박 5일 홍콩여행", "2020.1.12", "2020.1.16", countries, R.drawable.travel_sample_01, members));
		travelData.add(new TravelData(2, "친구들과 처음가는 배낭 여행", "2019.12.9", "2019.12.29", countries2, R.drawable.travel_sample_02, members2));
		travelData.add(new TravelData(3, "마카오로 호캉스~~!!", "2019.10.11", "2019.10.15", countries3, R.drawable.travel_sample_03, members3));
		travelData.add(new TravelData(4, "앗싸 퇴직여행 ✈️", "2019.8.15", "2019.8.18", countries, R.drawable.travel_sample_04, members));
		travelData.add(new TravelData(5, "혼자가는 러시아 일주 \uD83C\uDFA1", "2019.7.12", "2019.7.16", countries2, R.drawable.travel_sample_01, members2));
		travelData.add(new TravelData(6, "찐친들 - 미국 횡단 일주", "2019.6.9", "2019.6.29", countries3, R.drawable.travel_sample_02, members3));
		travelData.add(new TravelData(7, "여행 갈까 말까 던질까 말까", null, null, null, 0, 0));

		travelData.add(new TravelData(8, "가치 같이 여행", "2018.10.12", "2018.10.16", countries2, R.drawable.travel_sample_05, members2));
		travelData.add(new TravelData(9, "일주일 제주 여행", "2018.6.9", "2019.6.29", countries3, R.drawable.travel_sample_06, members3));
		travelData.add(new TravelData(10, "내일로 전국 일주~~", "2018.2.11", "2018.2.15", countries, R.drawable.travel_sample_07, members));
		travelData.add(new TravelData(11, "가자 파리로~!", "2017.12.15", "2017.12.28", countries2, R.drawable.travel_sample_01, members2));
		travelData.add(new TravelData(12, "얄리얄리얄라셩 얄라리얄라", "2017.10.12", "2017.10.16", countries3, R.drawable.travel_sample_05, members3));
		travelData.add(new TravelData(13, "일주일 제주 여행", "2017.6.9", "2017.6.29", countries, R.drawable.travel_sample_06, members));
		travelData.add(new TravelData(14, "내일로 전국 일주~~", "2017.2.11", "2017.2.15", countries2, R.drawable.travel_sample_07, members2));
		travelData.add(new TravelData(15, "가자 파리로~!", "2016.8.19", "2016.9.2", countries3, R.drawable.travel_sample_01, members3));

		return travelData;
	}
}
