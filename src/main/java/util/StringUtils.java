package util;

import java.util.List;

public class StringUtils {

    public static String join(List<String> strList, String delimiter){
        StringBuilder joinBuilder = new StringBuilder();
        for(String str : strList){
            joinBuilder.append(str);
            joinBuilder.append(delimiter);
        }

        return joinBuilder.toString();
    }
}
