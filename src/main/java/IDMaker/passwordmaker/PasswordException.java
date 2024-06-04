package IDMaker.passwordmaker;

/**
 * A sealed class that represents exceptions related to password handling.
 * This class is extended by PasswordMakerAccessException and PasswordMakerInvalidArgumentsException.
 *
 * @author yongjunhong
 * @since 2024. 6. 4.
 */
public sealed class PasswordException extends RuntimeException permits PasswordMakerAccessException, PasswordMakerInvalidArgumentsException{

	/**
	 * Constructs a new password exception with the specified detail message and cause.
	 *
	 * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method)
	 * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method)
	 */
	public PasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new password exception with the specified detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method)
	 */
	public PasswordException(String message) {
		super(message);
	}
}