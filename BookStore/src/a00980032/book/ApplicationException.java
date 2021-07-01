/**
 * Project: Bcmc
 * File: ApplicationException.java
 * Date: February 11, 2021
 * Time: 1:59:25 PM
 */

package a00980032.book;

/**
 * @author Chris Noble, A00980032
 *
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception
{

	/**
	 * Construct an ApplicationException
	 * 
	 * @param message
	 *            the exception message.
	 */
	public ApplicationException(String message)
	{
		super(message);
	}

	/**
	 * Construct an ApplicationException
	 * 
	 * @param throwable
	 *            a Throwable.
	 */
	public ApplicationException(Throwable throwable)
	{
		super(throwable);
	}

}
