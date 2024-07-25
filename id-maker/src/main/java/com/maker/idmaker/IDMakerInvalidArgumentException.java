package com.maker.idmaker;

/**
 * The type Id maker invalid argument exception.
 * @author yongjunhong
 * @since 2024. 6. 1.
 */
public non-sealed class IDMakerInvalidArgumentException extends IDMakerException {
	/**
	 * Instantiates a new Id maker invalid argument exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public IDMakerInvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Id maker invalid argument exception.
	 *
	 * @param message the message
	 */
	public IDMakerInvalidArgumentException(String message) {
		super(message);
	}
}
