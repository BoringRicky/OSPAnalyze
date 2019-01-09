package me.ricky.ospanalyze.utils;

public class Arrays {

    public static int contains(String[] strings, String string) {
        int position = 0;
        for (String str : strings) {
            if (str.equals(string)) {
                return position;
            }

            position++;
        }
        return -1;
    }

}
