package IDMaker.passwordmaker;

/**
 * The type Password maker access exception.
 * @author yongjunhong
 * @since 2024. 6. 4.
 */
public non-sealed class PasswordMakerAccessException extends PasswordException {

	/**
	 * Instantiates a new Password maker access exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public PasswordMakerAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Password maker access exception.
	 *
	 * @param message the message
	 */
	public PasswordMakerAccessException(String message) {
		super(message);
	}
}
