package com.maker.eventdatetimemaker;

import java.time.LocalDateTime;

import net.jqwik.time.api.arbitraries.LocalDateTimeArbitrary;

import com.google.api.services.calendar.model.EventDateTime;

import jakarta.annotation.Nonnull;

/**
 * Provides arbitrary {@link EventDateTime} instances based on {@link LocalDateTimeArbitrary}.
 *
 * @author yongjunhong
 * @since 2024. 7. 6.
 */
public final class EventDateTimeArbitrary {

	private final LocalDateTimeArbitrary localDateTimeArbitrary;

	/**
	 * Constructs an {@code EventDateTimeArbitrary} instance.
	 *
	 * @param localDateTimeArbitrary The arbitrary generator for {@link LocalDateTime}.
	 */
	public EventDateTimeArbitrary(@Nonnull LocalDateTimeArbitrary localDateTimeArbitrary) {
		this.localDateTimeArbitrary = localDateTimeArbitrary;
	}

	/**
	 * Generates a random {@link EventDateTime} using the configured {@link LocalDateTimeArbitrary}.
	 *
	 * @return A randomly generated {@link EventDateTime}.
	 */
	public EventDateTime getEventDateTime() {
		return TimeUtil.getEventDateTimeFromLocalDateTime(localDateTimeArbitrary.sample());
	}

	/**
	 * Creates a builder for {@code EventDateTimeArbitrary}.
	 *
	 * @return A new {@link EventDateTimeArbitraryBuilder} instance.
	 */
	public static EventDateTimeArbitraryBuilder builder() {
		return new EventDateTimeArbitraryBuilder();
	}
}
