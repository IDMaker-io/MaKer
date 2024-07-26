package com.maker.passwordmaker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify the encoding type for a password.
 * The default encoding type is BCRYPT.
 *
 * @author yongjunhong
 * @since 2024. 6. 3.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PasswordMaker {
	/**
	 * Specifies the encoding type for a password.
	 *
	 * @return the encoding type
	 */
	EncodingId encodingType() default EncodingId.BCRYPT;
}
