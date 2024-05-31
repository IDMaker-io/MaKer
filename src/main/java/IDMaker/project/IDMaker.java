package IDMaker.project;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for fields that should have an ID generated before persisting.
 * @author yongjunhong
 * @since  2024. 5. 31.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IDMaker {

	/**
	 * The length of the random part of the ID.
	 *
	 * @return the length of the random part of the ID
	 */
	int length() default 7;

	/**
	 * The type of characters to use for the random part of the ID.
	 *
	 * @return the type of characters to use for the random part of the ID
	 */
	GenerationType type() default GenerationType.EN;
}