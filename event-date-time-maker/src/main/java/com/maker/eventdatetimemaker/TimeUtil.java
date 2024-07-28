package com.maker.eventdatetimemaker;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;

/**
 * Utility class for converting {@link LocalDateTime} to {@link EventDateTime}.
 *
 * @author yongjunhong
 * @since 2024. 7. 6.
 */
public final class TimeUtil {

	static final String DATE_TIME_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

	private TimeUtil() {
		// Private constructor to prevent instantiation.
	}

	/**
	 * Converts a {@link LocalDateTime} to {@link EventDateTime}.
	 *
	 * @param localDateTime The {@link LocalDateTime} to convert.
	 * @return The corresponding {@link EventDateTime}.
	 */
	public static EventDateTime getEventDateTimeFromLocalDateTime(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

		if (localDateTime.getHour() == 0 && localDateTime.getMinute() == 0) {
			// zonedDateTime = zonedDateTime.plusMinutes(1);
		}
		return new EventDateTime()
			.setDateTime(new DateTime(zonedDateTime.toInstant().toEpochMilli()))
			.set("timeZone", zoneId.getId());
	}
	/**
	 * Converts a {@link DateTime} to {@link LocalDateTime}.
	 *
	 * @param eventDateTime The {@link DateTime} to convert.
	 * @return The corresponding {@link LocalDateTime}.
	 */

	public static LocalDateTime getLocalDateTimeFromDateTime(EventDateTime eventDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
		return LocalDateTime.parse(eventDateTime.getDateTime().toStringRfc3339(), formatter);
	}
}
