package com.maker.idmaker;


import static com.maker.idmaker.ExceptionCode.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for creating timestamped random IDs.
 * @author yongjunhong
 * @since  2024. 5. 31.
 */
public class IDMakerUtils {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	private IDMakerUtils() {
	}

	/**
	 * Creates a timestamped random ID.
	 *
	 * @param length the length of the random part of the ID
	 * @param generationType the type of characters to use for the random part of the ID
	 * @return a timestamped random ID
	 * @throws IllegalArgumentException if an invalid GenerationType is provided
	 */
	public static String createTimestampedRandomID(int length, GenerationType generationType) {
		String randomString = switch (generationType) {
			case KO -> RandomValueUtil.createRandomKoreanString(length);
			case EN -> RandomValueUtil.createRandomEnglishString(length);
			case NUMBER -> RandomValueUtil.createRandomNumberString(length);
			case MIX -> RandomValueUtil.createRandomMixedString(length);
			default -> throw new IDMakerInvalidArgumentException(IDMAKER_ANNOTATION_TYPE_INVALID.getMessage());
		};
		return LocalDateTime.now().format(formatter) + randomString;
	}
}
