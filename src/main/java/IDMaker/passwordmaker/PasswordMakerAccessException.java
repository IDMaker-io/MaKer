package IDMaker.passwordmaker;

/**
 *
 * @author yongjunhong
 * @since 2024. 6. 4.
 */
public non-sealed class PasswordMakerAccessException extends PasswordException {

	public PasswordMakerAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordMakerAccessException(String message) {
		super(message);
	}
}
