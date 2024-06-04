package IDMaker.passwordmaker;

/**
 *
 * @author yongjunhong
 * @since 2024. 6. 4.
 */
public non-sealed class PasswordMakerInvalidArgumentsException extends PasswordException {
	public PasswordMakerInvalidArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordMakerInvalidArgumentsException(String message) {
		super(message);
	}
}
