package IDMaker.passwordmaker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Utility class for password encoding and matching.
 *
 * @author yongjunhong
 * @since 2024. 6. 3.
 */
public class PasswordMakerUtil {
	private PasswordMakerUtil() {
	}

	/**
	 * Encodes the given password using the specified encoding ID.
	 *
	 * @param password the password to encode
	 * @param encodingId the encoding ID to use for encoding the password
	 * @return the encoded password
	 */
	public static String encodingPassword(String password, EncodingId encodingId) {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(
			encodingId.getEncodingId());

		return passwordEncoder.encode(password);
	}

	/**
	 * Matches the given password with the encoded password.
	 *
	 * @param password the password to match
	 * @param encodedPassword the encoded password to match with
	 * @return true if the password matches the encoded password, false otherwise
	 */
	public static boolean matchPasswordMaker(String password, String encodedPassword) {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(
			extractValueInsideBraces(encodedPassword));

		return passwordEncoder.matches(password, encodedPassword);
	}

	/**
	 * Extracts the value inside braces from the given input string.
	 *
	 * @param input the input string to extract the value from
	 * @return the extracted value if found, null otherwise
	 */
	private static String extractValueInsideBraces(String input) {
		Pattern pattern = Pattern.compile("\\{([^}]*)\\}");
		Matcher matcher = pattern.matcher(input);

		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}
}