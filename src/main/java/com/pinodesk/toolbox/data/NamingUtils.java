package com.pinodesk.toolbox.data;

import org.apache.commons.lang3.StringUtils;

public class NamingUtils {

    /**
     * Converts a name in camelCase to an underscored name in lower case. Any upper
     * case letters are converted to lower case with a preceding underscore.
     * 
     * @param name the original name
     * 
     * @return the converted name
     */
    public static String toSnakeCase(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(name.charAt(0)));
        for (int i = 1; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append('_').append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

}
