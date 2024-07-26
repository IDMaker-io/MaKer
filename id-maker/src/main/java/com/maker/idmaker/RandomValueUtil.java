package com.maker.idmaker;

import java.util.Random;

/**
 * Utility class for creating random strings.
 * @author yongjunhong
 * @since  2024. 5. 31.
 */
public class RandomValueUtil {
	private static final String KOREAN_CHARACTERS = "가나다라마바사아자차카타파하";
	private static final String ENGLISH_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String NUMBER_CHARACTERS = "0123456789";
	private static final String MIXED_CHARACTERS = KOREAN_CHARACTERS + ENGLISH_CHARACTERS + NUMBER_CHARACTERS;

	private static final Random RANDOM = new Random();

	private RandomValueUtil() {
	}

	/**
	 * Creates a random Korean string of the specified length.
	 *
	 * @param length the length of the string to generate
	 * @return a random Korean string
	 */
	public static String createRandomKoreanString(int length) {
		return generateRandomString(KOREAN_CHARACTERS, length);
	}

	/**
	 * Creates a random English string of the specified length.
	 *
	 * @param length the length of the string to generate
	 * @return a random English string
	 */
	public static String createRandomEnglishString(int length) {
		return generateRandomString(ENGLISH_CHARACTERS, length);
	}

	/**
	 * Creates a random number string of the specified length.
	 *
	 * @param length the length of the string to generate
	 * @return a random number string
	 */
	public static String createRandomNumberString(int length) {
		return generateRandomString(NUMBER_CHARACTERS, length);
	}

	/**
	 * Creates a random mixed string of the specified length.
	 *
	 * @param length the length of the string to generate
	 * @return a random mixed string
	 */
	public static String createRandomMixedString(int length) {
		return generateRandomString(MIXED_CHARACTERS, length);
	}

	/**
	 * Generates a random string of the specified length using the provided characters.
	 *
	 * @param characters the characters to use for generating the string
	 * @param length the length of the string to generate
	 * @return a random string
	 */
	private static String generateRandomString(String characters, int length) {
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			builder.append(characters.charAt(RANDOM.nextInt(characters.length())));
		}
		return builder.toString();
	}
}