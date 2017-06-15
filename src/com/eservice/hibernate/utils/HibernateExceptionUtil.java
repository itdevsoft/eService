package com.eservice.hibernate.utils;

import java.sql.BatchUpdateException;

import org.hibernate.exception.ConstraintViolationException;


public class HibernateExceptionUtil {

	/**
	 * Gets the name of the violated constraint. 
	 * @param ex
	 *            RuntimeException
	 * @return constraint name
	 * 
	 */
	public static String getViolatedConstraintName(Throwable t) {
		Throwable cause = t;
		while (cause != null) {
			if (cause instanceof BatchUpdateException) {
				break;
			}
			cause = cause.getCause();
		}
		// We are now dealing with a ConstraintViolationException
		BatchUpdateException bUpdException = (BatchUpdateException) cause;
		return bUpdException.getNextException().getMessage();

	}

	public static boolean isViolatedConstraint(Throwable t) {
		Throwable cause = t;
		while (cause != null) {
			if (cause instanceof ConstraintViolationException) {
				return true;
			}
			cause = cause.getCause();
		}

		return false;
	}
	
	public static String getTestMessage(){
		
	return "Hello...........";
	}

}
