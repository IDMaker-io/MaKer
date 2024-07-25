package com.maker.eventdatetimemaker;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;

/**
 * Utility class for converting {@link LocalDateTime} to {@link EventDateTime}.
 *
 * @author yongjunhong
 * @since 2024. 7. 6.
 */
public final class TimeUtil {

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
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

		return new EventDateTime()
			.setDateTime(new DateTime(zonedDateTime.toInstant().toEpochMilli()))
			.setTimeZone(ZoneId.systemDefault().getId());
	}
}
