package com.maker.passwordmaker;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The type Password factory.
 * @author yongjunhong
 * @since 2024. 6. 3.
 */
public class PasswordFactory {
	private PasswordFactory() {
	}
	/**
	 * Create b crypt password encoder password encoder.
	 *
	 * @return the password encoder
	 */
	public static PasswordEncoder createBCryptPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder(EncodingId.BCRYPT.getEncodingId());
	}

	/**
	 * Create pbkdf 2 password encoder password encoder.
	 *
	 * @return the password encoder
	 */
	public static PasswordEncoder createPbkdf2PasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder(EncodingId.PBKDF.getEncodingId());
	}

	/**
	 * Create s crypt password encoder password encoder.
	 *
	 * @return the password encoder
	 */
	public static PasswordEncoder createSCryptPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder(EncodingId.SCRYPT.getEncodingId());
	}

	/**
	 * Create argon 2 password encoder password encoder.
	 *
	 * @return the password encoder
	 */
	public static PasswordEncoder createArgon2PasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder(EncodingId.ARGON.getEncodingId());
	}
}
