package com.xiaofengzi.wxapi.util;

public class NestedExceptionUtil {
	
	/**
	 * Build a message for the given base message and root cause.
	 * @param message the base message
	 * @param cause the root cause
	 * @return the full exception message
	 */
	public static String buildMessage(String message, Throwable cause) {
		if (cause != null) {
			StringBuffer buf = new StringBuffer();
			if (message != null) {
				buf.append(message).append("; ");
			}
			buf.append("嵌套的异常为: ").append(cause);
			return buf.toString();
		}
		else {
			return message;
		}
	}
}
