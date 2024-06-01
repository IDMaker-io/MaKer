package IDMaker.idmaker;

/**
 * The enum Exception code.
 * @author yongjunhong
 * @since 2024. 6. 1.
 */
public enum ExceptionCode {

	/**
	 * The Failed to access field.
	 */
	FAILED_TO_ACCESS_FIELD("Failed to access field with IDMaker annotation."),
	/**
	 * The Idmaker annotation on non string.
	 */
	IDMAKER_ANNOTATION_ON_NON_STRING("IDMaker annotation is only allowed on fields of type String.");

	private final String message;

	ExceptionCode(String message) {
		this.message = message;
	}

	/**
	 * Gets message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}