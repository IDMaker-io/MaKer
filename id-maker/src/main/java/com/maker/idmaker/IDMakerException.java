package com.maker.idmaker;

/**
 * The type Id maker exception.
 * @author yongjunhong
 * @since 2024. 6. 1.
 */
public sealed class IDMakerException extends RuntimeException permits IDMakerAccessException,
	IDMakerInvalidArgumentException {
	/**
	 * Instantiates a new Id maker exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public IDMakerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Id maker exception.
	 *
	 * @param message the message
	 */
	public IDMakerException(String message) {
		super(message);
	}
}
