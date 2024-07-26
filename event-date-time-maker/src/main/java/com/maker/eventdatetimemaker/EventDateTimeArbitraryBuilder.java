package com.maker.eventdatetimemaker;

import java.time.LocalDateTime;

import net.jqwik.time.api.DateTimes;
import net.jqwik.time.api.arbitraries.LocalDateTimeArbitrary;

/**
 * Builder class for configuring {@link EventDateTimeArbitrary}.
 *
 * @author yongjunhong
 * @since 2024. 7. 6.
 */
public class EventDateTimeArbitraryBuilder {

	private LocalDateTimeArbitrary localDateTimeArbitrary;

	/**
	 * Constructs an {@code EventDateTimeArbitraryBuilder} with default settings.
	 */
	public EventDateTimeArbitraryBuilder() {
		this.localDateTimeArbitrary = DateTimes.dateTimes();
	}

	/**
	 * Sets the range of {@link LocalDateTime} values to generate.
	 *
	 * @param min The minimum {@link LocalDateTime}.
	 * @param max The maximum {@link LocalDateTime}.
	 * @return This builder instance for method chaining.
	 */
	public EventDateTimeArbitraryBuilder between(LocalDateTime min, LocalDateTime max) {
		this.localDateTimeArbitrary = this.localDateTimeArbitrary.between(min, max);
		return this;
	}

	/**
	 * Sets the year for generated {@link LocalDateTime}.
	 *
	 * @param year The year value.
	 * @return This builder instance for method chaining.
	 */
	public EventDateTimeArbitraryBuilder setYear(int year) {
		this.localDateTimeArbitrary = this.localDateTimeArbitrary.yearBetween(year, year);
		return this;
	}

	/**
	 * Sets the month for generated {@link LocalDateTime}.
	 *
	 * @param month The month value (1 for January, 2 for February, etc.).
	 * @return This builder instance for method chaining.
	 */
	public EventDateTimeArbitraryBuilder setMonth(int month) {
		this.localDateTimeArbitrary = this.localDateTimeArbitrary.monthBetween(month, month);
		return this;
	}

	/**
	 * Sets the day of month for generated {@link LocalDateTime}.
	 *
	 * @param day The day of the month (1-31).
	 * @return This builder instance for method chaining.
	 */
	public EventDateTimeArbitraryBuilder setDay(int day) {
		this.localDateTimeArbitrary = this.localDateTimeArbitrary.dayOfMonthBetween(day, day);
		return this;
	}

	/**
	 * Sets the hour of day for generated {@link LocalDateTime}.
	 *
	 * @param hour The hour of the day (0-23).
	 * @return This builder instance for method chaining.
	 */
	public EventDateTimeArbitraryBuilder setHour(int hour) {
		this.localDateTimeArbitrary = this.localDateTimeArbitrary.hourBetween(hour, hour);
		return this;
	}

	/**
	 * Sets the minute of hour for generated {@link LocalDateTime}.
	 *
	 * @param minute The minute of the hour (0-59).
	 * @return This builder instance for method chaining.
	 */
	public EventDateTimeArbitraryBuilder setMinute(int minute) {
		this.localDateTimeArbitrary = this.localDateTimeArbitrary.minuteBetween(minute, minute);
		return this;
	}

	/**
	 * Constructs an {@link EventDateTimeArbitrary} based on the configured settings.
	 *
	 * @return An {@link EventDateTimeArbitrary} instance.
	 */
	public EventDateTimeArbitrary build() {
		System.out.println("localDateTimeArbitrary: " + localDateTimeArbitrary.sample());
		return new EventDateTimeArbitrary(localDateTimeArbitrary);
	}
}
