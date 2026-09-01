package com.pinodesk.toolbox.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeUtilsTest {

    private static final ZoneId UTC = ZoneOffset.UTC;

    @BeforeEach
    void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("Africa/Cairo"));
    }

    @AfterEach
    void tearDown() {
        TimeZone.setDefault(null);
    }

    @Test
    void testParseZoneOffset() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Toronto"));
        ZoneOffset result = DateTimeUtils.parseZoneOffset(cal);
        assertThat(result.getId(), equalTo("-04:00"));

        cal = Calendar.getInstance();
        result = DateTimeUtils.parseZoneOffset(cal);
        // The result was calculated with daylight saving time (DST)
        // It needs to be compared with getOffset() which is also using DST instead of
        // getRawOffset()
        assertThat(
                result.getTotalSeconds(),
                equalTo(TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000));
    }

    @Test
    void testParseZoneId() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        ZoneId result = DateTimeUtils.parseZoneId(cal);
        assertThat(result.getId(), equalTo("Asia/Tokyo"));

        cal = Calendar.getInstance();
        result = DateTimeUtils.parseZoneId(cal);
        assertThat(result.getId(), equalTo(ZoneId.systemDefault().getId()));
    }

    @Test
    void testParseInstantWithDefaultFormatters() {
        ZonedDateTime zdtBase = ZonedDateTime.of(2020, 2, 22, 20, 22, 2, 0, ZoneId.of("Australia/Sydney"));

        // date: 2020-02-22, time: 20:22:02 (+11:00 Australia/Sydney)
        // date: 2020-02-22, time: 09:22:02 (UTC)
        String datetime = zdtBase.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        log.info("Formatted: {}", datetime);
        Instant result = DateTimeUtils.parseInstantWithDefaultFormatters(datetime);
        assertInstant(result, 2020, 2, 22, 9, 22, 2);

        // date: 2020-02-22, time: 20:22:02 (+11:00 Australia/Sydney)
        // date: 2020-02-22, time: 09:22:02 (UTC)
        datetime = zdtBase.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        log.info("Formatted: {}", datetime);
        result = DateTimeUtils.parseInstantWithDefaultFormatters(datetime);
        assertInstant(result, 2020, 2, 22, 9, 22, 2);

        // When ISO LOCAL format is used, it will use the default timezone which is:
        // Africa/Cairo

        // date: 2020-02-22, time: 20:22:02 (+02:00 Africa/Cairo)
        // date: 2020-02-22, time: 18:22:02 UTC
        datetime = zdtBase.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        log.info("Formatted: {}", datetime);
        result = DateTimeUtils.parseInstantWithDefaultFormatters(datetime);
        assertInstant(result, 2020, 2, 22, 18, 22, 2);

        // date: 2020-02-22, time: 00:00:00 (+02:00 Africa/Cairo)
        // date: 2020-02-21, time: 22:00:00 UTC
        datetime = zdtBase.format(DateTimeFormatter.ISO_LOCAL_DATE);
        log.info("Formatted: {}", datetime);
        result = DateTimeUtils.parseInstantWithDefaultFormatters(datetime);
        assertInstant(result, 2020, 2, 21, 22, 0, 0);

        // date: 2020-02-22, time: 00:00:00 (+11:00 Australia/Sydney)
        // date: 2020-02-21, time: 13:00:00 UTC
        datetime = zdtBase.format(DateTimeFormatter.ISO_OFFSET_DATE);
        log.info("Formatted: {}", datetime);
        result = DateTimeUtils.parseInstantWithDefaultFormatters(datetime);
        assertInstant(result, 2020, 2, 21, 13, 0, 0);

        // date: 2020-02-22, time: 20:22:02 (+11:00 Australia/Sydney)
        // date: 2020-02-22, time: 20:22:02 UTC
        datetime = zdtBase.format(DateTimeFormatter.ISO_INSTANT);
        log.info("Formatted: {}", datetime);
        result = DateTimeUtils.parseInstantWithDefaultFormatters(datetime);
        assertInstant(result, 2020, 2, 22, 9, 22, 2);

        // Parsing "time only" with default formatters will use now as the date
        LocalDate today = LocalDate.now();

        // The hour will be 17 instead of 18 due to daylight saving

        // date: 2020-02-22, time: 20:22:02 (+02:00 Africa/Cairo)
        // date: 2021-02-22, time: 18:22:02 UTC
        // datetime = zdtBase.format(DateTimeFormatter.ISO_LOCAL_TIME);
        // Duration daylightSavings = TimeZone.getDefault().toZoneId().getRules()
        // .getDaylightSavings(DateTimeUtils.toInstant(today.atTime(zdtBase.toLocalTime())));
        // log.info("daylightSavings: {}", daylightSavings);
        // log.info("Formatted: {}", datetime);
        // result = DateTimeUtils.parseInstantWithDefaultFormatters(datetime);
        // assertInstant(
        // result,
        // today.getYear(),
        // today.getMonthValue(),
        // today.getDayOfMonth(),
        // 18 + (int) daylightSavings.toHours(),
        // 22 + (int) daylightSavings.toMinutes(),
        // 2);

        // date: 2020-02-22, time: 20:22:02 (+11:00 Australia/Sydney)
        // date: 2021-02-22, time: 09:22:02 UTC
        datetime = zdtBase.format(DateTimeFormatter.ISO_OFFSET_TIME);
        log.info("Formatted: {}", datetime);
        result = DateTimeUtils.parseInstantWithDefaultFormatters(datetime);
        assertInstant(result, today.getYear(), today.getMonthValue(), today.getDayOfMonth(), 9, 22, 2);
    }

    @Test
    void testToInstant_withZonedDateTime() {
        ZonedDateTime zdt = ZonedDateTime.of(2020, 2, 22, 20, 22, 2, 0, ZoneId.systemDefault());
        Instant result = DateTimeUtils.toInstant(zdt);
        assertInstant(result, 2020, 2, 22, 18, 22, 2);
    }

    @Test
    void testToInstant_withOffsetDateTime() {
        OffsetDateTime odt = OffsetDateTime.of(2020, 2, 22, 20, 22, 2, 0, ZoneOffset.ofHours(3));
        Instant result = DateTimeUtils.toInstant(odt);
        assertInstant(result, 2020, 2, 22, 17, 22, 2);
    }

    @Test
    void testToInstant_withOffsetTime() {
        OffsetTime ot = OffsetTime.of(20, 22, 2, 0, ZoneOffset.UTC);
        Instant result = DateTimeUtils.toInstant(ot);
        LocalDate today = LocalDate.now();
        assertInstant(result, today.getYear(), today.getMonthValue(), today.getDayOfMonth(), 20, 22, 2);
    }

    private void assertInstant(Instant instant, int year, int month, int date, int hour, int minute, int second) {
        ZonedDateTime zdt = instant.atZone(UTC);
        assertThat(zdt.get(ChronoField.YEAR), equalTo(year));
        assertThat(zdt.get(ChronoField.MONTH_OF_YEAR), equalTo(month));
        assertThat(zdt.get(ChronoField.DAY_OF_MONTH), equalTo(date));
        assertThat(zdt.get(ChronoField.HOUR_OF_DAY), equalTo(hour));
        assertThat(zdt.get(ChronoField.MINUTE_OF_HOUR), equalTo(minute));
        assertThat(zdt.get(ChronoField.SECOND_OF_MINUTE), equalTo(second));
    }

}
