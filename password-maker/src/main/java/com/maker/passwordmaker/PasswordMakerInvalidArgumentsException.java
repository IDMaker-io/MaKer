package com.maker.passwordmaker;

/**
 * The type Password maker invalid arguments exception.
 * @author yongjunhong
 * @since 2024. 6. 4.
 */
public non-sealed class PasswordMakerInvalidArgumentsException extends PasswordException {
	/**
	 * Instantiates a new Password maker invalid arguments exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public PasswordMakerInvalidArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Password maker invalid arguments exception.
	 *
	 * @param message the message
	 */
	public PasswordMakerInvalidArgumentsException(String message) {
		super(message);
	}
}
