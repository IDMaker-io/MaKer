package com.maker.idmaker;

/**
 * The type Id maker access exception.
 * @author yongjunhong
 * @since 2024. 6. 1.
 */
public non-sealed class IDMakerAccessException extends IDMakerException {
	/**
	 * Instantiates a new Id maker access exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public IDMakerAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
