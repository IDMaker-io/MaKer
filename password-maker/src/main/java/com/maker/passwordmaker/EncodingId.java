package com.maker.passwordmaker;

/**
 * The enum Encoding id.
 * @author yongjunhong
 * @since 2024. 6. 3.
 */
public enum EncodingId {
	/**
	 *Bcrypt encoding id.
	 */
	BCRYPT("bcrypt"),
	/**
	 *Pbkdf encoding id.
	 */
	PBKDF("pbkdf2@SpringSecurity_v5_8"),
	/**
	 *Scrypt encoding id.
	 */
	SCRYPT("scrypt@SpringSecurity_v5_8"),
	/**
	 *Argon encoding id.
	 */
	ARGON("argon2@SpringSecurity_v5_8");

	private String encodingId;

	EncodingId(String encodingId) {
		this.encodingId = encodingId;
	}

	/**
	 * Gets encoding id.
	 *
	 * @return the encoding id
	 */
	public String getEncodingId() {
		return encodingId;
	}
}
