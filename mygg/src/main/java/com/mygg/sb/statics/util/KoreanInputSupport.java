package com.mygg.sb.statics.util;

public class KoreanInputSupport {
    private static String[] choSung = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ",
            "ㅌ", "ㅍ", "ㅎ" };
    private static String[] jungSung = { "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ",
            "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ" };
    private static String[] jongSung = { "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ",
            "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ" };

    public static int[][] findIndex(String input) {
        boolean[] isKorean = isKorean(input);
        int[][] index = new int[input.length()][3];
        for (int i = 0; i < input.length(); i++) {
            int c = input.charAt(i) - 0xAC00;
            int jongSung = c % 28;
            int jungSung = ((c - jongSung) / 28) % 21;
            int choSung = (((c - jongSung) / 28) - jungSung) / 21;
            index[i][0] = choSung;
            index[i][1] = jungSung;
            index[i][2] = jongSung;
        }
        return index;
    }

    public static String toKorean(String input) {
        int[][] index = findIndex(input);
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            result += "초성 : " + choSung[index[i][0]];
            result += "중성 : " + jungSung[index[i][1]];
            result += "종성 : " + jongSung[index[i][2]];
            result += "\n";
        }
        return result;
    }

    public static boolean[] isKorean(String input) {
        boolean[] result = new boolean[input.length()];
        for (int i = 0; i < input.length(); i++) {
            result[i] = isKorean(input.charAt(i));
        }
        return result;
    }

    public static boolean isKorean(char input) {
        return input >= 0xAC00 && input <= 0xD7A3;
    }

    public static String[] splitKorean(String input) {
        return input.split("[^ㄱ-ㅎㅏ-ㅣ가-힣]+");
    }
}
