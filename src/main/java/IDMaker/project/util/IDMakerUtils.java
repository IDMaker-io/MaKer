package IDMaker.project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import IDMaker.project.annotation.GenerationType;

/**
 * Utility class for creating timestamped random IDs.
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
		String randomString;
		switch (generationType) {
			case KO:
				randomString = RandomValueUtil.createRandomKoreanString(length);
				break;
			case EN:
				randomString = RandomValueUtil.createRandomEnglishString(length);
				break;
			case NUMBER:
				randomString = RandomValueUtil.createRandomNumberString(length);
				break;
			case MIX:
				randomString = RandomValueUtil.createRandomMixedString(length);
				break;
			default:
				throw new IllegalArgumentException("Invalid GenerationType: " + generationType);
		}
		return LocalDateTime.now().format(formatter) + randomString;
	}
}