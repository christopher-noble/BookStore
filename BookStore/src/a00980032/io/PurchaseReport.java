package a00980032.io;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00980032.book.ApplicationException;
import a00980032.data.Purchase;

/**
 * @author Chris Noble, A00980032
 *
 */
public class PurchaseReport
{
	public static final String HORIZONTAL_LINE = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

	public static final String HEADER_FORMAT = "%-24s %-80s %1s";
	public static final String PURCHASE_FORMAT = "%-24s %-80s $%.2f";

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private PurchaseReport()
	{
	}

	/**
	 * Print the report.
	 * 
	 * @param customers
	 * @throws ApplicationException
	 * @throws IOException
	 */

	public static void write(List<Purchase> purchases, PrintStream out) throws ApplicationException, IOException
	{
		String text = null;
		System.out.println();
		println("Purchase Report", out);
		println(HORIZONTAL_LINE, out);
		text = String.format(HEADER_FORMAT, "Name", "Title", "Price");
		println(text, out);
		println(HORIZONTAL_LINE, out);

		for (Purchase purchase : purchases)
		{
			text = String.format(PURCHASE_FORMAT, purchase.getFullName(), purchase.getTitle(), purchase.getPrice());
			println(text, out);

			LOG.debug(String.format(PURCHASE_FORMAT, purchase.getFullName(), purchase.getTitle(), purchase.getPrice()));
		}
	}

	private static void println(String text, PrintStream out)
	{
		out.println(text);
		LOG.info(text);
	}
}
