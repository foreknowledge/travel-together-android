package com.mungziapp.traveltogether.app;

import android.content.Context;

import com.mungziapp.traveltogether.R;
import com.mungziapp.traveltogether.data.TravelRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TravelHelper {
    public static HashMap<String, String> countryMap = new HashMap<>();
    private static TravelHelper instance = new TravelHelper();

    public static TravelHelper getInstance() { return instance; }

    private TravelHelper() {
        countryMap.put("한국", "\uD83C\uDDF0\uD83C\uDDF7");
        countryMap.put("홍콩", "\uD83C\uDDED\uD83C\uDDF0");
        countryMap.put("미국", "\uD83C\uDDFA\uD83C\uDDF8");
        countryMap.put("베트남", "\uD83C\uDDFB\uD83C\uDDF3");
        countryMap.put("프랑스", "\uD83C\uDDEB\uD83C\uDDF7");
        countryMap.put("러시아", "\uD83C\uDDF7\uD83C\uDDFA");
        countryMap.put("일본", "\uD83C\uDDEF\uD83C\uDDF5");
        countryMap.put("브라질", "\uD83C\uDDE7\uD83C\uDDF7");
        countryMap.put("아르헨티나", "\uD83C\uDDE6\uD83C\uDDF7");
        countryMap.put("호주", "\uD83C\uDDE6\uD83C\uDDFA");
        countryMap.put("독일", "\uD83C\uDDE9\uD83C\uDDEA");
        countryMap.put("아일랜드", "\uD83C\uDDEE\uD83C\uDDEA");
        countryMap.put("스페인", "\uD83C\uDDEA\uD83C\uDDF8");
        countryMap.put("가나", "\uD83C\uDDEC\uD83C\uDDED");
    }

    public static void init(Context context) {
        DatabaseManager.openDatabase(context);
        DatabaseManager.dropTables();
        DatabaseManager.createTables();
        DatabaseManager.insertDummyData(makeTravelRoom());
    }

    private static List<TravelRoom> makeTravelRoom() {
        String countries = "\uD83C\uDDF0\uD83C\uDDF7,\uD83C\uDDFA\uD83C\uDDF8,\uD83C\uDDED\uD83C\uDDF0,\uD83C\uDDEB\uD83C\uDDF7" +
                ",\uD83C\uDDEC\uD83C\uDDFA,\uD83C\uDDFB\uD83C\uDDF3,\uD83C\uDDF2\uD83C\uDDF4,\uD83C\uDDF3\uD83C\uDDF5" +
                ",\uD83C\uDDEC\uD83C\uDDF9,\uD83C\uDDEC\uD83C\uDDE9,\uD83C\uDDEC\uD83C\uDDF7,\uD83C\uDDEC\uD83C\uDDF1";
        String countries2 = "";
        String countries3 = "\uD83C\uDDF7\uD83C\uDDFA";

        int members = 9;
        int members2 = 1;
        int members3 = 2;

        List<TravelRoom> travelRooms = new ArrayList<>();
        travelRooms.add(new TravelRoom(1, "엄마와 함께하는 4박 5일 홍콩여행", "20.01.12","20.01.16", countries, R.drawable.travel_room_sample_01, members));
        travelRooms.add(new TravelRoom(2, "친구들과 처음가는 배낭 여행", "2019.12.09", "19.12.29", countries2, R.drawable.travel_room_sample_02, members2));
        travelRooms.add(new TravelRoom(3, "마카오로 호캉스~~!!", "19.10.11", "19.10.15", countries3, R.drawable.travel_room_sample_03, members3));
        travelRooms.add(new TravelRoom(4, "앗싸 퇴직여행 ✈️", "19.08.15", "19.08.18", countries, R.drawable.travel_room_sample_04, members));
        travelRooms.add(new TravelRoom(5, "혼자가는 러시아 일주 \uD83C\uDFA1", "19.07.12", "19.07.16", countries2, R.drawable.travel_room_sample_01, members2));
        travelRooms.add(new TravelRoom(6, "찐친들 - 미국 횡단 일주", "19.06.09", "19.06.29", countries3, R.drawable.travel_room_sample_02, members3));

        travelRooms.add(new TravelRoom(7, "가치 같이 여행", "18.10.12", "18.10.16", countries2, R.drawable.travel_room_sample_05, members2));
        travelRooms.add(new TravelRoom(8, "일주일 제주 여행", "18.06.09", "19.06.29", countries3, R.drawable.travel_room_sample_06, members3));
        travelRooms.add(new TravelRoom(9, "내일로 전국 일주~~", "18.02.11", "18.02.15", countries, R.drawable.travel_room_sample_07, members));
        travelRooms.add(new TravelRoom(10, "가자 파리로~!", "17.12.15", "17.12.28", countries2, R.drawable.travel_room_sample_01, members2));
        travelRooms.add(new TravelRoom(11, "얄리얄리얄라셩 얄라리얄라", "17.10.12", "17.10.16", countries3, R.drawable.travel_room_sample_05, members3));
        travelRooms.add(new TravelRoom(12, "일주일 제주 여행", "17.06.09", "17.06.29", countries, R.drawable.travel_room_sample_06, members));
        travelRooms.add(new TravelRoom(13, "내일로 전국 일주~~", "17.02.11", "17.02.15", countries2, R.drawable.travel_room_sample_07, members2));
        travelRooms.add(new TravelRoom(14, "가자 파리로~!", "16.08.19", "16.09.02", countries3, R.drawable.travel_room_sample_01, members3));

        return travelRooms;
    }
}
