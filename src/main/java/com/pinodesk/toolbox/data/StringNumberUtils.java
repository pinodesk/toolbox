package com.pinodesk.toolbox.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public final class StringNumberUtils {

    private StringNumberUtils() {
    }

    public static String toStringOrDefault(BigDecimal num, String dflt) {
        return toStringOrDefault(num, 0, dflt);
    }

    public static String toStringOrNull(BigDecimal num) {
        return toStringOrDefault(num, null);
    }

    public static String toStringOrEmpty(BigDecimal num) {
        return toStringOrDefault(num, "");
    }

    public static String toStringOrDefault(BigDecimal num, int fractionDigit, String dflt) {
        return num == null ? dflt : num.setScale(fractionDigit, RoundingMode.HALF_EVEN).toString();
    }

    public static String toStringOrNull(BigDecimal num, int fractionDigit) {
        return toStringOrDefault(num, fractionDigit, null);
    }

    public static String toStringOrEmpty(BigDecimal num, int fractionDigit) {
        return toStringOrDefault(num, fractionDigit, "");
    }

    public static String toStringOrDefault(Integer num, String dflt) {
        return num == null ? dflt : num.toString();
    }

    public static String toStringOrNull(Integer num) {
        return toStringOrDefault(num, null);
    }

    public static String toStringOrEmpty(Integer num) {
        return toStringOrDefault(num, "");
    }

    public static String toStringOrDefault(Long num, String dflt) {
        return num == null ? dflt : num.toString();
    }

    public static String toStringOrNull(Long num) {
        return toStringOrDefault(num, null);
    }

    public static String toStringOrEmpty(Long num) {
        return toStringOrDefault(num, "");
    }

    public static String toStringOrDefault(Double num, String dflt) {
        return num == null ? dflt : num.toString();
    }

    public static String toStringOrNull(Double num) {
        return toStringOrDefault(num, null);
    }

    public static String toStringOrEmpty(Double num) {
        return toStringOrDefault(num, "");
    }

    public static String toStringOrDefault(Float num, String dflt) {
        return num == null ? dflt : num.toString();
    }

    public static String toStringOrNull(Float num) {
        return toStringOrDefault(num, null);
    }

    public static String toStringOrEmpty(Float num) {
        return toStringOrDefault(num, "");
    }

    public static BigDecimal toBigDecimalOrDefault(String str, BigDecimal dflt) {
        return StringUtils.isBlank(str) ? dflt : NumberUtils.toScaledBigDecimal(str);
    }

    public static BigDecimal toBigDecimalOrNull(String str) {
        return toBigDecimalOrDefault(str, null);
    }

    public static BigDecimal toBigDecimalOrZero(String str) {
        return toBigDecimalOrDefault(str, BigDecimal.ZERO);
    }

    public static BigDecimal toBigDecimalOrDefault(String str, int scale, BigDecimal dflt) {
        return StringUtils.isBlank(str) ? dflt : NumberUtils.toScaledBigDecimal(str, scale, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toBigDecimalOrNull(String str, int scale) {
        return toBigDecimalOrDefault(str, scale, null);
    }

    public static BigDecimal toBigDecimalOrZero(String str, int scale) {
        return toBigDecimalOrDefault(str, scale, BigDecimal.ZERO);
    }

    public static Integer toIntegerOrDefault(String str, Integer dflt) {
        return StringUtils.isBlank(str) ? dflt : (Integer) Double.valueOf(NumberUtils.toDouble(str)).intValue();
    }

    public static Integer toIntegerOrNull(String str) {
        return toIntegerOrDefault(str, null);
    }

    public static Integer toIntegerOrZero(String str) {
        return toIntegerOrDefault(str, 0);
    }

    public static Long toLongOrDefault(String str, Long dflt) {
        return StringUtils.isBlank(str) ? dflt : (Long) Double.valueOf(NumberUtils.toDouble(str)).longValue();
    }

    public static Long toLongOrNull(String str) {
        return toLongOrDefault(str, null);
    }

    public static Long toLongOrZero(String str) {
        return toLongOrDefault(str, 0l);
    }

    public static Double toDoubleOrDefault(String str, Double dflt) {
        return StringUtils.isBlank(str) ? dflt : Double.valueOf(str);
    }

    public static Double toDoubleOrNull(String str) {
        return toDoubleOrDefault(str, null);
    }

    public static Double toDoubleOrZero(String str) {
        return toDoubleOrDefault(str, 0d);
    }

    public static Float toFloatOrDefault(String str, Float dflt) {
        return StringUtils.isBlank(str) ? dflt : Float.valueOf(str);
    }

    public static Float toFloatOrNull(String str) {
        return toFloatOrDefault(str, null);
    }

    public static Float toFloatOrZero(String str) {
        return toFloatOrDefault(str, 0f);
    }

    public static String format(Number num) {
        DecimalFormat df = new DecimalFormat();
        return df.format(num);
    }

    public static String formatOrDefault(Number num, String dflt) {
        if (num == null) {
            return dflt;
        }
        return format(num);
    }

    public static String format(Number num, Locale locale) {
        DecimalFormat df = new DecimalFormat();
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(locale));
        return df.format(num);
    }

    public static String formatOrDefault(Number num, Locale locale, String dflt) {
        if (num == null) {
            return dflt;
        }
        return format(num, locale);
    }

    public static String format(Number num, Locale locale, int fractionDigit) {
        DecimalFormat df = new DecimalFormat();
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(locale));
        df.setMaximumFractionDigits(fractionDigit);
        return df.format(num);
    }

    public static String formatOrDefault(Number num, Locale locale, int fractionDigit, String dflt) {
        if (num == null) {
            return dflt;
        }
        return format(num, locale, fractionDigit);
    }

}
