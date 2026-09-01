package com.pinodesk.toolbox.data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.commons.lang3.ArrayUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DateTimeUtils {

    public static final DateTimeFormatter[] DEFAULT_FORMATTERS = new DateTimeFormatter[] {
            DateTimeFormatter.ISO_INSTANT,
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_DATE,
            DateTimeFormatter.ISO_TIME };

    public static final ZoneOffset DEFAULT_ZONE_OFFSET = OffsetDateTime.now().getOffset();

    private DateTimeUtils() {
    }

    public static Instant toInstant(ZonedDateTime zdt) {
        return zdt.toInstant();
    }

    public static Instant toInstant(OffsetDateTime odt) {
        return odt.toInstant();
    }

    public static Instant toInstant(OffsetTime ot) {
        return toInstant(ot.atDate(LocalDate.now()));
    }

    public static Instant toInstant(LocalDateTime ldt, ZoneOffset zo) {
        return toInstant(ldt.atOffset(zo));
    }

    public static Instant toInstant(LocalDateTime ldt, ZoneId zoneId) {
        return toInstant(ldt.atZone(zoneId));
    }

    public static Instant toInstant(LocalDateTime ldt) {
        return toInstant(ldt.atZone(ZoneId.systemDefault()));
    }

    public static Instant toInstant(LocalDate ld, LocalTime lt) {
        return toInstant(ld.atTime(lt));
    }

    public static Instant toInstant(LocalDate ld, OffsetTime ot) {
        return toInstant(ld.atTime(ot));
    }

    public static Instant toInstant(LocalDate ld) {
        return toInstant(ld.atStartOfDay(ZoneId.systemDefault()));
    }

    public static Instant toInstant(LocalDate ld, LocalTime lt, ZoneOffset zo) {
        return toInstant(ld.atTime(lt), zo);
    }

    public static Instant toInstant(LocalDate ld, LocalTime lt, ZoneId zoneId) {
        return toInstant(ld.atTime(lt), zoneId);
    }

    public static Instant toInstant(LocalTime lt, ZoneOffset zo) {
        return toInstant(lt.atDate(LocalDate.now()), zo);
    }

    public static Instant toInstant(LocalTime lt, ZoneId zoneId) {
        return toInstant(lt.atDate(LocalDate.now()), zoneId);
    }

    public static Instant toInstant(LocalTime lt) {
        return toInstant(lt, ZoneId.systemDefault());
    }

    public static ZonedDateTime toZonedDateTime(Instant instant, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(instant, zoneId);
    }

    public static ZonedDateTime toZonedDateTime(Instant instant) {
        return toZonedDateTime(instant, ZoneId.systemDefault());
    }

    public static ZonedDateTime toZonedDateTime(OffsetDateTime odt) {
        return odt.toZonedDateTime();
    }

    public static ZonedDateTime toZonedDateTime(LocalDateTime ldt, ZoneOffset zo) {
        return toZonedDateTime(ldt.atOffset(zo));
    }

    public static ZonedDateTime toZonedDateTime(LocalDate ld, LocalTime lt, ZoneOffset zo) {
        return toZonedDateTime(ld.atTime(lt), zo);
    }

    public static ZonedDateTime toZonedDateTime(LocalDateTime ldt, ZoneId zoneId) {
        return ZonedDateTime.of(ldt, zoneId);
    }

    public static ZonedDateTime toZonedDateTime(LocalDateTime ldt) {
        return ZonedDateTime.of(ldt, ZoneId.systemDefault());
    }

    public static ZonedDateTime toZonedDateTime(LocalDate ld, LocalTime lt, ZoneId zoneId) {
        return toZonedDateTime(ld.atTime(lt), zoneId);
    }

    public static ZonedDateTime toZonedDateTime(LocalDate ld, LocalTime lt) {
        return toZonedDateTime(ld.atTime(lt));
    }

    public static OffsetDateTime toOffsetDateTime(Instant instant, ZoneId zoneId) {
        return OffsetDateTime.ofInstant(instant, zoneId);
    }

    public static OffsetDateTime toOffsetDateTime(Instant instant) {
        return toOffsetDateTime(instant, ZoneId.systemDefault());
    }

    public static OffsetDateTime toOffsetDateTime(ZonedDateTime zdt) {
        return zdt.toOffsetDateTime();
    }

    public static OffsetDateTime toOffsetDateTime(LocalDateTime ldt, ZoneOffset zo) {
        return OffsetDateTime.of(ldt, zo);
    }

    public static OffsetDateTime toOffsetDateTime(LocalDateTime ldt) {
        return OffsetDateTime.of(ldt, DEFAULT_ZONE_OFFSET);
    }

    public static OffsetDateTime toOffsetDateTime(LocalDate ld, LocalTime lt, ZoneOffset zo) {
        return toOffsetDateTime(ld.atTime(lt), zo);
    }

    public static OffsetDateTime toOffsetDateTime(LocalDate ld, LocalTime lt) {
        return toOffsetDateTime(ld.atTime(lt));
    }

    public static ZoneOffset parseZoneOffset(Calendar calendar) {
        // The same as datetime.getTimezoneOffset() (deprecated)
        int offsetMinutes = (calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / (60 * 1000);
        return ZoneOffset.ofTotalSeconds(offsetMinutes * 60);
    }

    public static ZoneId parseZoneId(Calendar calendar) {
        return calendar.getTimeZone().toZoneId();
    }

    public static DateTimeFormatter[] toFormatter(String... patterns) {
        return Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).toArray(DateTimeFormatter[]::new);
    }

    public static Instant parseInstantQuietly(String datetime, DateTimeFormatter... formatters) {
        if (ArrayUtils.isEmpty(formatters)) {
            return Instant.parse(datetime);
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                return Instant.from(formatter.parse(datetime));
            } catch (Exception e) {
                // Ignore
            }
        }
        return null;
    }

    public static Instant parseInstantQuietly(String datetime, String... patterns) {
        return parseInstantQuietly(datetime, toFormatter(patterns));
    }

    public static ZonedDateTime parseZonedDateTimeQuietly(String datetime, DateTimeFormatter... formatters) {
        if (ArrayUtils.isEmpty(formatters)) {
            return ZonedDateTime.parse(datetime);
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                return ZonedDateTime.from(formatter.parse(datetime));
            } catch (Exception e) {
                // Ignore
            }
        }
        return null;
    }

    public static ZonedDateTime parseZonedDateTimeQuietly(String datetime, String... patterns) {
        return parseZonedDateTimeQuietly(datetime, toFormatter(patterns));
    }

    public static OffsetDateTime parseOffsetDateTimeQuietly(String datetime, DateTimeFormatter... formatters) {
        if (ArrayUtils.isEmpty(formatters)) {
            return OffsetDateTime.parse(datetime);
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                return OffsetDateTime.from(formatter.parse(datetime));
            } catch (Exception e) {
                // Ignore
            }
        }
        return null;
    }

    public static OffsetDateTime parseOffsetDateTimeQuietly(String datetime, String... patterns) {
        return parseOffsetDateTimeQuietly(datetime, toFormatter(patterns));
    }

    public static OffsetTime parseOffsetTimeQuietly(String time, DateTimeFormatter... formatters) {
        if (ArrayUtils.isEmpty(formatters)) {
            return OffsetTime.parse(time);
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                return OffsetTime.from(formatter.parse(time));
            } catch (Exception e) {
                // Ignore
            }
        }
        return null;
    }

    public static OffsetTime parseOffsetTimeQuietly(String datetime, String... patterns) {
        return parseOffsetTimeQuietly(datetime, toFormatter(patterns));
    }

    public static LocalDateTime parseLocalDateTimeQuietly(String datetime, DateTimeFormatter... formatters) {
        if (ArrayUtils.isEmpty(formatters)) {
            return LocalDateTime.parse(datetime);
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(datetime, formatter);
            } catch (Exception e) {
                // Ignore
            }
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTimeQuietly(String datetime, String... patterns) {
        return parseLocalDateTimeQuietly(datetime, toFormatter(patterns));
    }

    public static LocalDate parseLocalDateQuietly(String date, DateTimeFormatter... formatters) {
        if (ArrayUtils.isEmpty(formatters)) {
            return LocalDate.parse(date);
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (Exception e) {
                // Ignore
            }
        }
        return null;
    }

    public static LocalDate parseLocalDateQuietly(String datetime, String... patterns) {
        return parseLocalDateQuietly(datetime, toFormatter(patterns));
    }

    public static LocalTime parseLocalTimeQuietly(String time, DateTimeFormatter... formatters) {
        if (ArrayUtils.isEmpty(formatters)) {
            return LocalTime.parse(time);
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalTime.parse(time, formatter);
            } catch (Exception e) {
                // Ignore
            }
        }
        return null;
    }

    public static LocalTime parseLocalTimeQuietly(String datetime, String... patterns) {
        return parseLocalTimeQuietly(datetime, toFormatter(patterns));
    }

    public static Instant parseInstantWithDefaultFormatters(String datetime) {
        for (DateTimeFormatter formatter : DEFAULT_FORMATTERS) {
            try {
                TemporalAccessor parsed = formatter.parse(datetime);
                if (DateTimeFormatter.ISO_INSTANT.equals(formatter)) {
                    log.info("ISO_INSTANT");
                    return Instant.from(parsed);
                }
                if (DateTimeFormatter.ISO_DATE.equals(formatter)) {
                    LocalDateTime ldt = LocalDate.from(parsed).atStartOfDay();
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        log.info("ISO_DATE + OFFSET_SECONDS");
                        ZoneOffset zo = ZoneOffset.ofTotalSeconds(parsed.get(ChronoField.OFFSET_SECONDS));
                        return toInstant(ldt, zo);
                    }
                    log.info("ISO_DATE");
                    return toInstant(ldt);
                }
                if (DateTimeFormatter.ISO_DATE_TIME.equals(formatter)) {
                    log.info("ISO_DATE_TIME");
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        return toInstant(ZonedDateTime.from(parsed));
                    }
                    return toInstant(LocalDateTime.from(parsed));
                }
                if (DateTimeFormatter.ISO_TIME.equals(formatter)) {
                    LocalDate ld = LocalDate.now();
                    LocalTime lt = LocalTime.from(parsed);
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        log.info("ISO_TIME + OFFSET_SECONDS");
                        ZoneOffset zo = ZoneOffset.ofTotalSeconds(parsed.get(ChronoField.OFFSET_SECONDS));
                        return toInstant(ld, lt.atOffset(zo));
                    }
                    log.info("ISO_TIME");
                    return toInstant(ld, lt);
                }
            } catch (DateTimeParseException e) {
                if (log.isTraceEnabled()) {
                    log.trace("Unable to parse datetime to Instant: \"{}\"", e.getMessage());
                }
            }
        }
        throw new DateTimeParseException(
                String.format("Unable to parse datetime to Instant: \"%s\"", datetime),
                datetime,
                datetime.length());
    }

    public static ZonedDateTime parseZonedDateTimeWithDefaultFormatters(String datetime) {
        for (DateTimeFormatter formatter : DEFAULT_FORMATTERS) {
            try {
                TemporalAccessor parsed = formatter.parse(datetime);
                if (DateTimeFormatter.ISO_INSTANT.equals(formatter)) {
                    return toZonedDateTime(Instant.from(parsed));
                }
                if (DateTimeFormatter.ISO_DATE.equals(formatter)) {
                    LocalDateTime ldt = LocalDate.from(parsed).atStartOfDay();
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        ZoneOffset zo = ZoneOffset.ofTotalSeconds(parsed.get(ChronoField.OFFSET_SECONDS));
                        return toZonedDateTime(ldt.atOffset(zo));
                    }
                    return toZonedDateTime(ldt);
                }
                if (DateTimeFormatter.ISO_DATE_TIME.equals(formatter)) {
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        return ZonedDateTime.from(parsed);
                    }
                    return toZonedDateTime(LocalDateTime.from(parsed));
                }
                if (DateTimeFormatter.ISO_TIME.equals(formatter)) {
                    LocalDate ld = LocalDate.now();
                    LocalTime lt = LocalTime.from(parsed);
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        ZoneOffset zo = ZoneOffset.ofTotalSeconds(parsed.get(ChronoField.OFFSET_SECONDS));
                        return toZonedDateTime(ld, lt, zo);
                    }
                    return toZonedDateTime(ld, lt);
                }
            } catch (DateTimeParseException e) {
                if (log.isTraceEnabled()) {
                    log.trace("Unable to parse datetime to ZonedDateTime: \"{}\"", e.getMessage());
                }
            }
        }
        throw new DateTimeParseException(
                String.format("Unable to parse datetime to ZonedDateTime: \"%s\"", datetime),
                datetime,
                datetime.length());
    }

    public static OffsetDateTime parseOffsetDateTimeWithDefaultFormatters(String datetime) {
        for (DateTimeFormatter formatter : DEFAULT_FORMATTERS) {
            try {
                TemporalAccessor parsed = formatter.parse(datetime);
                if (DateTimeFormatter.ISO_INSTANT.equals(formatter)) {
                    return toOffsetDateTime(Instant.from(parsed));
                }
                if (DateTimeFormatter.ISO_DATE.equals(formatter)) {
                    LocalDateTime ldt = LocalDate.from(parsed).atStartOfDay();
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        ZoneOffset zo = ZoneOffset.ofTotalSeconds(parsed.get(ChronoField.OFFSET_SECONDS));
                        return toOffsetDateTime(ldt, zo);
                    }
                    return toOffsetDateTime(ldt);
                }
                if (DateTimeFormatter.ISO_DATE_TIME.equals(formatter)) {
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        return OffsetDateTime.from(parsed);
                    }
                    return toOffsetDateTime(LocalDateTime.from(parsed));
                }
                if (DateTimeFormatter.ISO_TIME.equals(formatter)) {
                    LocalDate ld = LocalDate.now();
                    LocalTime lt = LocalTime.from(parsed);
                    if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                        ZoneOffset zo = ZoneOffset.ofTotalSeconds(parsed.get(ChronoField.OFFSET_SECONDS));
                        return toOffsetDateTime(ld, lt, zo);
                    }
                    return toOffsetDateTime(ld, lt);
                }
            } catch (DateTimeParseException e) {
                if (log.isTraceEnabled()) {
                    log.trace("Unable to parse datetime to OffsetDateTime: \"{}\"", e.getMessage());
                }
            }
        }
        throw new DateTimeParseException(
                String.format("Unable to parse datetime to OffsetDateTime: \"%s\"", datetime),
                datetime,
                datetime.length());
    }

}
