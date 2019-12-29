package com.mungziapp.traveltogether.app.helper;

import android.content.Context;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.model.Countries;
import com.mungziapp.traveltogether.model.data.ScheduleData;
import com.mungziapp.traveltogether.model.data.TravelData;

import java.util.ArrayList;
import java.util.List;

public class TravelHelper {
	private static TravelHelper instance = new TravelHelper();

	public static TravelHelper getInstance() { return instance; }
	private TravelHelper() { Countries.setCountryListAndHash(); }

	public static void init(Context context) {
		DatabaseHelper.openDatabase(context);
		DatabaseHelper.dropTables();
		DatabaseHelper.createTables();
		DatabaseHelper.insertDummyData(makeTravelItems());
		DatabaseHelper.insertScheduleDummyData(makeScheduleItems());
	}

	private static List<TravelData> makeTravelItems() {
		String countries = "KR,US,HK,FR,GU,VN,MO,NP,GT,GD,GR,GL";
		String countries2 = "";
		String countries3 = "RU";

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
		travelData.add(new TravelData(7, "여행 갈까 말까 던질까 말까", null, null, null, 0, 1));

		travelData.add(new TravelData(8, "가치 같이 여행", "2018.10.12", "2018.10.16", countries2, R.drawable.travel_sample_05, members2));
		travelData.add(new TravelData(9, "일주일 제주 여행", "2018.6.9", "2018.6.29", countries3, R.drawable.travel_sample_06, members3));
		travelData.add(new TravelData(10, "내일로 전국 일주~~", "2018.2.11", "2018.2.15", countries, R.drawable.travel_sample_07, members));
		travelData.add(new TravelData(11, "가자 파리로~!", "2017.12.15", "2017.12.28", countries2, R.drawable.travel_sample_01, members2));
		travelData.add(new TravelData(12, "얄리얄리얄라셩 얄라리얄라", "2017.10.12", "2017.10.16", countries3, R.drawable.travel_sample_05, members3));
		travelData.add(new TravelData(13, "일주일 제주 여행", "2017.6.9", "2017.6.29", countries, R.drawable.travel_sample_06, members));
		travelData.add(new TravelData(14, "내일로 전국 일주~~", "2017.2.11", "2017.2.15", countries2, R.drawable.travel_sample_07, members2));
		travelData.add(new TravelData(15, "가자 파리로~!", "2016.8.19", "2016.9.2", countries3, R.drawable.travel_sample_01, members3));

		return travelData;
	}

	private static List<ScheduleData> makeScheduleItems() {
		List<ScheduleData> scheduleData = new ArrayList<>();

		scheduleData.add(new ScheduleData(1, 1, 2, "호텔 조식 냠", "8:00", null, null, null));
		scheduleData.add(new ScheduleData(2, 1, 2, "준비하고 출발!", "10:00", null, "준비물 - 물병, 셀카봉, 지갑, 핸드폰, 보조배터리", null));
		scheduleData.add(new ScheduleData(3, 1, 2, 1, "260번 버스 타고 센트럴역으로 이동", null, null, null, null));
		scheduleData.add(new ScheduleData(4, 1, 2, "센트럴 마켓 구경 & 점심 냠", null, null, null, null));
		scheduleData.add(new ScheduleData(5, 1, 2, "소호", null, null, null, null));
		scheduleData.add(new ScheduleData(6, 1, 2, "란콰이풍", null, null, null, null));
		scheduleData.add(new ScheduleData(7, 1, 2, "성요한 대성당", null, null, null, null));
		scheduleData.add(new ScheduleData(8, 1, 2, "피크트램", null, null, null, null));
		scheduleData.add(new ScheduleData(9, 1, 2, 1, "스타페리 타고 침사추이로 이동", null, null, "센트럴역에서 스타페리 표 구매. (평일 2.7홍콩달러 /  주말…", null));
		scheduleData.add(new ScheduleData(10, 1, 2, 1, "택시 타고 호텔로 이동", null, null, null, null));
		scheduleData.add(new ScheduleData(11, 1, 2, "저녁 먹고 호텔에서 휴식", null, null, null, null));

		scheduleData.add(new ScheduleData(12, 1, 5, "호텔 체크아웃 하기", "10:30", null, "빠뜨린거 없나 준비물 다시 체크하기", null));
		scheduleData.add(new ScheduleData(13, 1, 5, 1, "호텔 픽업차량으로 공항 이동", null, null, null, null));
		scheduleData.add(new ScheduleData(14, 1, 5, "홍콩 공항 도착", "11:30", null, null, null));
		scheduleData.add(new ScheduleData(15, 1, 5, 1, "아시아나 항공 A380 출발", "12:55", null, null, null));
		scheduleData.add(new ScheduleData(16, 1, 5, "인천공항 도착!", "16:30", null, "뭐 하기.", null));
		scheduleData.add(new ScheduleData(17, 1, 5, 1,"집으로...", null, null, "아빠가 데리러 오기로 함.", null));
		scheduleData.add(new ScheduleData(18, 1, 5, "집 도착 후 짐풀기", null, null, null, null));

		return scheduleData;
	}
}
