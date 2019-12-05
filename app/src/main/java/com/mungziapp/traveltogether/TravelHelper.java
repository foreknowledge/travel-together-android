package com.mungziapp.traveltogether;

import java.util.HashMap;

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
}
