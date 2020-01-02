package com.mungziapp.traveltogether.model;

import com.mungziapp.traveltogether.model.item.CountryItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Countries {
	private static List<CountryItem> countryList = new ArrayList<>();
	private static HashMap<String, CountryItem> countryMap = new HashMap<>();
	public static List<CountryItem> getCountryList() { return countryList; }
	public static HashMap<String, CountryItem> getCountryMap() { return countryMap; }

	public static void setCountryListAndHash() {
		countryList.add(new CountryItem(Arrays.asList("안도라", "ANDORRA", "AD", "\uD83C\uDDE6\uD83C\uDDE9")));
		countryList.add(new CountryItem(Arrays.asList("아랍에미리트", "UNITED ARAB EMIRATES", "AE", "\uD83C\uDDE6\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("아프가니스탄", "AFGHANISTAN", "AF", "\uD83C\uDDE6\uD83C\uDDEB")));
		countryList.add(new CountryItem(Arrays.asList("안티구아 바부다", "ANTIGUA AND BARBUDA", "AG", "\uD83C\uDDE6\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("앵귈라", "ANGUILLA", "AI", "\uD83C\uDDE6\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("알바니아", "ALBANIA", "AL", "\uD83C\uDDE6\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("아르메니아", "ARMENIA", "AM", "\uD83C\uDDE6\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("앙골라", "ANGOLA", "AO", "\uD83C\uDDE6\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("아르헨티나", "ARGENTINA", "AR", "\uD83C\uDDE6\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("아메리칸 사모아", "AMERICAN SAMOA", "AS", "\uD83C\uDDE6\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("오스트리아", "AUSTRIA", "AT", "\uD83C\uDDE6\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("호주", "AUSTRALIA", "AU", "\uD83C\uDDE6\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("아루바", "ARUBA", "AW", "\uD83C\uDDE6\uD83C\uDDFC")));
		countryList.add(new CountryItem(Arrays.asList("알랜드 제도", "ALAND ISLANDS", "AX", "\uD83C\uDDE6\uD83C\uDDFD")));
		countryList.add(new CountryItem(Arrays.asList("아제르바이잔", "AZERBAIJAN", "AZ", "\uD83C\uDDE6\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("보스니아 헤르체고비나", "BOSNIA HERCEGOVINA", "BA", "\uD83C\uDDE7\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("바베이도스", "BARBADOS", "BB", "\uD83C\uDDE7\uD83C\uDDE7")));
		countryList.add(new CountryItem(Arrays.asList("방글라데시", "BANGLADESH", "BD", "\uD83C\uDDE7\uD83C\uDDE9")));
		countryList.add(new CountryItem(Arrays.asList("벨기에", "BELGIUM", "BE", "\uD83C\uDDE7\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("부르키나파소", "BURKINA FASO", "BF", "\uD83C\uDDE7\uD83C\uDDEB")));
		countryList.add(new CountryItem(Arrays.asList("불가리아", "BULGARIA", "BG", "\uD83C\uDDE7\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("바레인", "BAHRAIN", "BH", "\uD83C\uDDE7\uD83C\uDDED")));
		countryList.add(new CountryItem(Arrays.asList("브룬디", "BURUNDI", "BI", "\uD83C\uDDE7\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("베냉", "BENIN", "BJ", "\uD83C\uDDE7\uD83C\uDDEF")));
		countryList.add(new CountryItem(Arrays.asList("버뮤다", "BERMUDA", "BM", "\uD83C\uDDE7\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("브루나이", "BRUNEI DARUSSALAM", "BN", "\uD83C\uDDE7\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("볼리비아", "BOLIVIA", "BO", "\uD83C\uDDE7\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("브라질", "BRAZIL", "BR", "\uD83C\uDDE7\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("바하마", "BAHAMAS", "BS", "\uD83C\uDDE7\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("부탄", "BHUTAN", "BT", "\uD83C\uDDE7\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("보츠와나", "BOTSWANA", "BW", "\uD83C\uDDE7\uD83C\uDDFC")));
		countryList.add(new CountryItem(Arrays.asList("벨라루스", "BELARUS", "BY", "\uD83C\uDDE7\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("벨리즈", "BELIZE", "BZ", "\uD83C\uDDE7\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("캐나다", "CANADA", "CA", "\uD83C\uDDE8\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("코코스 제도", "COCOS (KEELING) ISLANDS", "CC", "\uD83C\uDDE8\uD83C\uDDE8")));
		countryList.add(new CountryItem(Arrays.asList("콩고민주공화국", "DEMOCRATIC REPUBLIC OF THE CONGO", "CD", "\uD83C\uDDE8\uD83C\uDDE9")));
		countryList.add(new CountryItem(Arrays.asList("중앙아프리카공화국", "CENTRAL AFRICAN REPUBLIC", "CF", "\uD83C\uDDE8\uD83C\uDDEB")));
		countryList.add(new CountryItem(Arrays.asList("콩고", "CONGO", "CG", "\uD83C\uDDE8\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("스위스", "SWITZERLAND", "CH", "\uD83C\uDDE8\uD83C\uDDED")));
		countryList.add(new CountryItem(Arrays.asList("코트디부아르", "COTE DIVOIRE", "CI", "\uD83C\uDDE8\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("쿡 제도", "COOK ISLANDS", "CK", "\uD83C\uDDE8\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("칠레", "CHILE", "CL", "\uD83C\uDDE8\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("카메룬", "CAMEROON", "CM", "\uD83C\uDDE8\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("중국", "CHINA", "CN", "\uD83C\uDDE8\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("콜롬비아", "COLOMBIA", "CO", "\uD83C\uDDE8\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("코스타리카", "COSTA RICA", "CR", "\uD83C\uDDE8\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("쿠바", "CUBA", "CU", "\uD83C\uDDE8\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("카보베르데", "CAPE VERDE", "CV", "\uD83C\uDDE8\uD83C\uDDFB")));
		countryList.add(new CountryItem(Arrays.asList("크리스마스섬", "CHRISTMAS ISLAND", "CX", "\uD83C\uDDE8\uD83C\uDDFD")));
		countryList.add(new CountryItem(Arrays.asList("사이프러스", "CYPRUS", "CY", "\uD83C\uDDE8\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("체코공화국", "CZECH REPUBLIC", "CZ", "\uD83C\uDDE8\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("독일", "GERMANY", "DE", "\uD83C\uDDE9\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("지부티", "DJIBOUTI", "DJ", "\uD83C\uDDE9\uD83C\uDDEF")));
		countryList.add(new CountryItem(Arrays.asList("덴마크", "DENMARK", "DK", "\uD83C\uDDE9\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("도미니카", "DOMINICA", "DM", "\uD83C\uDDE9\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("도미니카 공화국", "DOMINICAN REPUBLIC", "DO", "\uD83C\uDDE9\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("알제리", "ALGERIA", "DZ", "\uD83C\uDDE9\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("에쿠아도르", "ECUADOR", "EC", "\uD83C\uDDEA\uD83C\uDDE8")));
		countryList.add(new CountryItem(Arrays.asList("에스토니아", "ESTONIA", "EE", "\uD83C\uDDEA\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("이집트", "EGYPT", "EG", "\uD83C\uDDEA\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("서사하라", "WESTERN SAHARA", "EH", "\uD83C\uDDEA\uD83C\uDDED")));
		countryList.add(new CountryItem(Arrays.asList("에리트레아", "ERITREA", "ER", "\uD83C\uDDEA\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("스페인", "SPAIN", "ES", "\uD83C\uDDEA\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("이디오피아", "ETHIOPIA", "ET", "\uD83C\uDDEA\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("핀란드", "FINLAND", "FI", "\uD83C\uDDEB\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("피지", "FIJI", "FJ", "\uD83C\uDDEB\uD83C\uDDEF")));
		countryList.add(new CountryItem(Arrays.asList("포클랜드섬", "FALKLAND ISLANDS", "FK", "\uD83C\uDDEB\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("미크로네시아", "MICRONESIA", "FM", "\uD83C\uDDEB\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("페로 제도", "FAROE ISLANDS", "FO", "\uD83C\uDDEB\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("프랑스", "FRANCE", "FR", "\uD83C\uDDEB\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("가봉", "GABON", "GA", "\uD83C\uDDEC\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("영국", "UNITED KINGDOM", "GB", "\uD83C\uDDEC\uD83C\uDDE7")));
		countryList.add(new CountryItem(Arrays.asList("그레나다", "GRENADA", "GD", "\uD83C\uDDEC\uD83C\uDDE9")));
		countryList.add(new CountryItem(Arrays.asList("조지아", "GEORGIA", "GE", "\uD83C\uDDEC\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("프랑스령 기아나", "FRENCH GUIANA", "GF", "\uD83C\uDDEC\uD83C\uDDEB")));
		countryList.add(new CountryItem(Arrays.asList("건지", "GUERNSEY", "GG", "\uD83C\uDDEC\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("가나", "GHANA", "GH", "\uD83C\uDDEC\uD83C\uDDED")));
		countryList.add(new CountryItem(Arrays.asList("지브랄타", "GIBRALTAR", "GI", "\uD83C\uDDEC\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("그린랜드", "GREENLAND", "GL", "\uD83C\uDDEC\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("감비아", "GAMBIA", "GM", "\uD83C\uDDEC\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("기니", "GUINEA", "GN", "\uD83C\uDDEC\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("과들루프", "GUADELOUPE", "GP", "\uD83C\uDDEC\uD83C\uDDF5")));
		countryList.add(new CountryItem(Arrays.asList("적도 기니", "EQUATORIAL GUINEA", "GQ", "\uD83C\uDDEC\uD83C\uDDF6")));
		countryList.add(new CountryItem(Arrays.asList("그리스", "GREECE", "GR", "\uD83C\uDDEC\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("과테말라", "GUATEMALA", "GT", "\uD83C\uDDEC\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("괌", "GUAM", "GU", "\uD83C\uDDEC\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("기네비사우", "GUINEA-BISSAU", "GW", "\uD83C\uDDEC\uD83C\uDDFC")));
		countryList.add(new CountryItem(Arrays.asList("가이아나", "GUYANA", "GY", "\uD83C\uDDEC\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("홍콩", "HONG KONG", "HK", "\uD83C\uDDED\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("허드 맥도날드 제도", "HEARD AND MCDONALD ISLANDS", "HM", "\uD83C\uDDED\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("온두라스", "HONDURAS", "HN", "\uD83C\uDDED\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("크로아티아", "CROATIA", "HR", "\uD83C\uDDED\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("아이티", "HAITI", "HT", "\uD83C\uDDED\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("헝가리", "HUNGARY", "HU", "\uD83C\uDDED\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("인도네시아", "INDONESIA", "ID", "\uD83C\uDDEE\uD83C\uDDE9")));
		countryList.add(new CountryItem(Arrays.asList("아일랜드", "IRELAND", "IE", "\uD83C\uDDEE\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("이스라엘", "ISRAEL", "IL", "\uD83C\uDDEE\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("맨섬", "ISLE OF MAN", "IM", "\uD83C\uDDEE\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("인도", "INDIA", "IN", "\uD83C\uDDEE\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("영국령 인도 제도", "BRITISH INDIAN OCEAN TERRITORY", "IO", "\uD83C\uDDEE\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("이라크", "IRAQ", "IQ", "\uD83C\uDDEE\uD83C\uDDF6")));
		countryList.add(new CountryItem(Arrays.asList("이란", "IRAN", "IR", "\uD83C\uDDEE\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("아이슬랜드", "ICELAND", "IS", "\uD83C\uDDEE\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("이탈리아", "ITALY", "IT", "\uD83C\uDDEE\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("저지", "JERSEY", "JE", "\uD83C\uDDEF\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("자메이카", "JAMAICA", "JM", "\uD83C\uDDEF\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("요르단", "JORDAN", "JO", "\uD83C\uDDEF\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("일본", "JAPAN", "JP", "\uD83C\uDDEF\uD83C\uDDF5")));
		countryList.add(new CountryItem(Arrays.asList("케냐", "KENYA", "KE", "\uD83C\uDDF0\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("키르기스스탄", "KYRGYZSTAN", "KG", "\uD83C\uDDF0\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("캄보디아", "CAMBODIA", "KH", "\uD83C\uDDF0\uD83C\uDDED")));
		countryList.add(new CountryItem(Arrays.asList("키리바시", "KIRIBATI", "KI", "\uD83C\uDDF0\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("코모르", "COMOROS", "KM", "\uD83C\uDDF0\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("세인트 키츠 네비스", "SAINT KITTS AND NEVIS", "KN", "\uD83C\uDDF0\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("북한", "NORTH KOREA", "KP", "\uD83C\uDDF0\uD83C\uDDF5")));
		countryList.add(new CountryItem(Arrays.asList("대한민국", "KOREA", "KR", "\uD83C\uDDF0\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("쿠웨이트", "KUWAIT", "KW", "\uD83C\uDDF0\uD83C\uDDFC")));
		countryList.add(new CountryItem(Arrays.asList("케이맨섬", "CAYMAN ISLANDS", "KY", "\uD83C\uDDF0\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("카자흐스탄", "KAZAKHSTAN", "KZ", "\uD83C\uDDF0\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("라오스", "LAOS", "LA", "\uD83C\uDDF1\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("레바논", "LEBANON", "LB", "\uD83C\uDDF1\uD83C\uDDE7")));
		countryList.add(new CountryItem(Arrays.asList("세인트 루시아", "SAINT LUCIA", "LC", "\uD83C\uDDF1\uD83C\uDDE8")));
		countryList.add(new CountryItem(Arrays.asList("리히텐슈타인", "LIECHTENSTEIN", "LI", "\uD83C\uDDF1\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("스리랑카", "SRI LANKA", "LK", "\uD83C\uDDF1\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("라이베리아", "LIBERIA", "LR", "\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("레소토", "LESOTHO", "LS", "\uD83C\uDDF1\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("리투아니아", "LITHUANIA", "LT", "\uD83C\uDDF1\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("룩셈부르크", "LUXEMBOURG", "LU", "\uD83C\uDDF1\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("라트비아", "LATVIA", "LV", "\uD83C\uDDF1\uD83C\uDDFB")));
		countryList.add(new CountryItem(Arrays.asList("리비아", "LIBYAN ARAB JAMAHIRIYA", "LY", "\uD83C\uDDF1\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("모로코", "MOROCCO", "MA", "\uD83C\uDDF2\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("모나코", "MONACO", "MC", "\uD83C\uDDF2\uD83C\uDDE8")));
		countryList.add(new CountryItem(Arrays.asList("몰도바", "MOLDOVA", "MD", "\uD83C\uDDF2\uD83C\uDDE9")));
		countryList.add(new CountryItem(Arrays.asList("세인트 마틴", "SAINT MARTIN (FRENCH PART)", "MF", "\uD83C\uDDF2\uD83C\uDDEB")));
		countryList.add(new CountryItem(Arrays.asList("마다가스카르", "MADAGASCAR", "MG", "\uD83C\uDDF2\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("마샬제도", "MARSHALL ISLANDS", "MH", "\uD83C\uDDF2\uD83C\uDDED")));
		countryList.add(new CountryItem(Arrays.asList("마케도니아", "MACEDONIA", "MK", "\uD83C\uDDF2\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("말리", "MALI", "ML", "\uD83C\uDDF2\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("미얀마", "MYANMAR", "MM", "\uD83C\uDDF2\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("몽골", "MONGOLIA", "MN", "\uD83C\uDDF2\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("마카오", "MACAU", "MO", "\uD83C\uDDF2\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("북마리아나 제도", "NORTHERN MARIANA ISLANDS", "MP", "\uD83C\uDDF2\uD83C\uDDF5")));
		countryList.add(new CountryItem(Arrays.asList("마르티니크", "MARTINIQUE", "MQ", "\uD83C\uDDF2\uD83C\uDDF6")));
		countryList.add(new CountryItem(Arrays.asList("모리타니", "MAURITANIA", "MR", "\uD83C\uDDF2\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("몬트세라트", "MONTSERRAT", "MS", "\uD83C\uDDF2\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("말타", "MALTA", "MT", "\uD83C\uDDF2\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("모리셔스", "MAURITIUS", "MU", "\uD83C\uDDF2\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("몰디브", "MALDIVES", "MV", "\uD83C\uDDF2\uD83C\uDDFB")));
		countryList.add(new CountryItem(Arrays.asList("말라위", "MALAWI", "MW", "\uD83C\uDDF2\uD83C\uDDFC")));
		countryList.add(new CountryItem(Arrays.asList("멕시코", "MEXICO", "MX", "\uD83C\uDDF2\uD83C\uDDFD")));
		countryList.add(new CountryItem(Arrays.asList("말레이시아", "MALAYSIA", "MY", "\uD83C\uDDF2\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("모잠비크", "MOZAMBIQUE", "MZ", "\uD83C\uDDF2\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("나미비아", "NAMIBIA", "NA", "\uD83C\uDDF3\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("뉴칼레도니아", "NEW CALEDONIA", "NC", "\uD83C\uDDF3\uD83C\uDDE8")));
		countryList.add(new CountryItem(Arrays.asList("니제르", "NIGER", "NE", "\uD83C\uDDF3\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("노퍽섬", "NORFOLK ISLAND", "NF", "\uD83C\uDDF3\uD83C\uDDEB")));
		countryList.add(new CountryItem(Arrays.asList("나이지리아", "NIGERIA", "NG", "\uD83C\uDDF3\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("니카라과", "NICARAGUA", "NI", "\uD83C\uDDF3\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("네덜란드", "NETHERLANDS", "NL", "\uD83C\uDDF3\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("노르웨이", "NORWAY", "NO", "\uD83C\uDDF3\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("네팔", "NEPAL", "NP", "\uD83C\uDDF3\uD83C\uDDF5")));
		countryList.add(new CountryItem(Arrays.asList("나우루", "NAURU", "NR", "\uD83C\uDDF3\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("니우에", "NIUE", "NU", "\uD83C\uDDF3\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("뉴질랜드", "NEW ZEALAND", "NZ", "\uD83C\uDDF3\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("오만", "OMAN", "OM", "\uD83C\uDDF4\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("파나마", "PANAMA", "PA", "\uD83C\uDDF5\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("페루", "PERU", "PE", "\uD83C\uDDF5\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("프랑스령 폴리네시아", "FRENCH POLYNESIA", "PF", "\uD83C\uDDF5\uD83C\uDDEB")));
		countryList.add(new CountryItem(Arrays.asList("파푸아뉴기니", "PAPUA NEW GUINEA", "PG", "\uD83C\uDDF5\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("필리핀", "PHILIPPINES", "PH", "\uD83C\uDDF5\uD83C\uDDED")));
		countryList.add(new CountryItem(Arrays.asList("파키스탄", "PAKISTAN", "PK", "\uD83C\uDDF5\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("폴란드", "POLAND", "PL", "\uD83C\uDDF5\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("핏케언 제도", "PITCAIRN", "PN", "\uD83C\uDDF5\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("팔레스타인", "PALESTINE", "PS", "\uD83C\uDDF5\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("포르투갈", "PORTUGAL", "PT", "\uD83C\uDDF5\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("팔라우", "PALAU", "PW", "\uD83C\uDDF5\uD83C\uDDFC")));
		countryList.add(new CountryItem(Arrays.asList("파라과이", "PARAGUAY", "PY", "\uD83C\uDDF5\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("카타르", "QATAR", "QA", "\uD83C\uDDF6\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("리유니언", "REUNION", "RE", "\uD83C\uDDF7\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("루마니아", "ROMANIA", "RO", "\uD83C\uDDF7\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("세르비아", "SERBIA", "RS", "\uD83C\uDDF7\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("러시아", "RUSSIAN FEDERATION", "RU", "\uD83C\uDDF7\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("르완다", "RWANDA", "RW", "\uD83C\uDDF7\uD83C\uDDFC")));
		countryList.add(new CountryItem(Arrays.asList("사우디아라비아", "SAUDI ARABIA", "SA", "\uD83C\uDDF8\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("솔로몬 제도", "SOLOMON ISLANDS", "SB", "\uD83C\uDDF8\uD83C\uDDE7")));
		countryList.add(new CountryItem(Arrays.asList("세이셸", "SEYCHELLES", "SC", "\uD83C\uDDF8\uD83C\uDDE8")));
		countryList.add(new CountryItem(Arrays.asList("수단", "SUDAN", "SD", "\uD83C\uDDF8\uD83C\uDDE9")));
		countryList.add(new CountryItem(Arrays.asList("스웨덴", "SWEDEN", "SE", "\uD83C\uDDF8\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("싱가포르", "SINGAPORE", "SG", "\uD83C\uDDF8\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("슬로베니아", "SLOVENIA", "SI", "\uD83C\uDDF8\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("슬로바키아", "SLOVAKIA", "SK", "\uD83C\uDDF8\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("시에라리온", "SIERRA LEONE", "SL", "\uD83C\uDDF8\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("세네갈", "SENEGAL", "SN", "\uD83C\uDDF8\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("소말리아", "SOMALIA", "SO", "\uD83C\uDDF8\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("수리남", "SURINAME", "SR", "\uD83C\uDDF8\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("남수단", "SOUTH SUDAN", "SS", "\uD83C\uDDF8\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("엘살바도르", "EL SALVADOR", "SV", "\uD83C\uDDF8\uD83C\uDDFB")));
		countryList.add(new CountryItem(Arrays.asList("시리아", "SYRIAN ARAB REPUBLIC", "SY", "\uD83C\uDDF8\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("스와질랜드", "SWAZILAND", "SZ", "\uD83C\uDDF8\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("터크스 카이코스 제도", "TURKS AND CAICOS ISLANDS", "TC", "\uD83C\uDDF9\uD83C\uDDE8")));
		countryList.add(new CountryItem(Arrays.asList("차드", "CHAD", "TD", "\uD83C\uDDF9\uD83C\uDDE9")));
		countryList.add(new CountryItem(Arrays.asList("프랑스 남부지역", "FRENCH SOUTHERN TERRITORIES", "TF", "\uD83C\uDDF9\uD83C\uDDEB")));
		countryList.add(new CountryItem(Arrays.asList("토고", "TOGO", "TG", "\uD83C\uDDF9\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("태국", "THAILAND", "TH", "\uD83C\uDDF9\uD83C\uDDED")));
		countryList.add(new CountryItem(Arrays.asList("타지키스탄", "TAJIKISTAN", "TJ", "\uD83C\uDDF9\uD83C\uDDEF")));
		countryList.add(new CountryItem(Arrays.asList("토켈라우", "TOKELAU", "TK", "\uD83C\uDDF9\uD83C\uDDF0")));
		countryList.add(new CountryItem(Arrays.asList("동티모르", "EAST TIMOR", "TL", "\uD83C\uDDF9\uD83C\uDDF1")));
		countryList.add(new CountryItem(Arrays.asList("투르크메니스탄", "TURKMENISTAN", "TM", "\uD83C\uDDF9\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("튀니지", "TUNISIA", "TN", "\uD83C\uDDF9\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("통가", "TONGA", "TO", "\uD83C\uDDF9\uD83C\uDDF4")));
		countryList.add(new CountryItem(Arrays.asList("터키", "TURKEY", "TR", "\uD83C\uDDF9\uD83C\uDDF7")));
		countryList.add(new CountryItem(Arrays.asList("트리니다드토바고", "TRINIDAD AND TOBAGO", "TT", "\uD83C\uDDF9\uD83C\uDDF9")));
		countryList.add(new CountryItem(Arrays.asList("투발루", "TUVALU", "TV", "\uD83C\uDDF9\uD83C\uDDFB")));
		countryList.add(new CountryItem(Arrays.asList("대만", "TAIWAN", "TW", "\uD83C\uDDF9\uD83C\uDDFC")));
		countryList.add(new CountryItem(Arrays.asList("탄자니아", "TANZANIA", "TZ", "\uD83C\uDDF9\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("우크라이나", "UKRAINE", "UA", "\uD83C\uDDFA\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("우간다", "UGANDA", "UG", "\uD83C\uDDFA\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("미국령 군소제도", "UNITED STATES MINOR OUTLYING ISLANDS", "UM", "\uD83C\uDDFA\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("미국", "UNITED STATES", "US", "\uD83C\uDDFA\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("우루과이", "URUGUAY", "UY", "\uD83C\uDDFA\uD83C\uDDFE")));
		countryList.add(new CountryItem(Arrays.asList("우즈베키스탄", "UZBEKISTAN", "UZ", "\uD83C\uDDFA\uD83C\uDDFF")));
		countryList.add(new CountryItem(Arrays.asList("바티칸", "VATICAN CITY STATE", "VA", "\uD83C\uDDFB\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("베네수엘라", "VENEZUELA", "VE", "\uD83C\uDDFB\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("영국령 버진아일랜드", "VIRGIN ISLANDS (BRITISH)", "VG", "\uD83C\uDDFB\uD83C\uDDEC")));
		countryList.add(new CountryItem(Arrays.asList("미국령 버진아일랜드", "VIRGIN ISLANDS (U.S.)", "VI", "\uD83C\uDDFB\uD83C\uDDEE")));
		countryList.add(new CountryItem(Arrays.asList("베트남", "VIET NAM", "VN", "\uD83C\uDDFB\uD83C\uDDF3")));
		countryList.add(new CountryItem(Arrays.asList("바누아투", "VANUATU", "VU", "\uD83C\uDDFB\uD83C\uDDFA")));
		countryList.add(new CountryItem(Arrays.asList("사모아", "SAMOA", "WS", "\uD83C\uDDFC\uD83C\uDDF8")));
		countryList.add(new CountryItem(Arrays.asList("예멘", "YEMEN", "YE", "\uD83C\uDDFE\uD83C\uDDEA")));
		countryList.add(new CountryItem(Arrays.asList("남아프리카공화국", "SOUTH AFRICA", "ZA", "\uD83C\uDDFF\uD83C\uDDE6")));
		countryList.add(new CountryItem(Arrays.asList("잠비아", "ZAMBIA", "ZM", "\uD83C\uDDFF\uD83C\uDDF2")));
		countryList.add(new CountryItem(Arrays.asList("짐바브웨", "ZIMBABWE", "ZW", "\uD83C\uDDFF\uD83C\uDDFC")));

		for (CountryItem countryItem : countryList)
			countryMap.put(countryItem.getCountryCode(), countryItem);
	}
}
