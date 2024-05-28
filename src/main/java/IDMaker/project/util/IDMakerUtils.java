package IDMaker.project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for generating IDs.
 * The ID consists of a timestamp and a random string.
 * The timestamp is in the format "yyyyMMddHHmmssSSS".
 * The length of the random string is specified when calling the method.
 */

public class IDMakerUtils {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	private IDMakerUtils() {
	}

	/**
	 * Generates an ID consisting of a timestamp and a random string.
	 * @param length the length of the random string
	 * @return the generated ID
	 */
	public static String createTimestampedRandomID(int length) {
		return LocalDateTime.now().format(formatter)
			+ RandomValueUtil.createRandomString(length);
	}
}
