package cn.sits.rjb.config.authentication;

/**
 * 令牌异常
 *
 * @author louis.lu
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String message) {
		super(message);
	}
}
