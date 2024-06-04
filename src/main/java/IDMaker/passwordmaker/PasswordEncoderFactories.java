package IDMaker.passwordmaker;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * A utility class that provides a factory method for creating a delegating password encoder.
 * The delegating password encoder can encode passwords using various encoding strategies.
 * The encoding strategy used is determined by the encodingId parameter.
 *
 * @author yongjunhong
 * @since 2024. 6. 3.
 */
public final class PasswordEncoderFactories {
	private PasswordEncoderFactories() {
	}

	/**
	 * Creates a delegating password encoder with the encoding strategy specified by the encodingId parameter.
	 * The created encoder can handle passwords encoded with various strategies,
	 * including "bcrypt", "pbkdf2@SpringSecurity_v5_8", "scrypt@SpringSecurity_v5_8", and "argon2@SpringSecurity_v5_8".
	 *
	 * @param encodingId the encoding strategy to use for the created password encoder
	 * @return a delegating password encoder that uses the specified encoding strategy
	 */
	public static PasswordEncoder createDelegatingPasswordEncoder(String encodingId) {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		return new DelegatingPasswordEncoder(encodingId, encoders);
	}
}