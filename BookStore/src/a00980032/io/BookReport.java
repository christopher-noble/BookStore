package a00980032.io;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00980032.data.Book;

/**
 * @author Chris Noble, A00980032
 *
 */
public class BookReport
{
	public static final String HORIZONTAL_LINE = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

	public static final String HEADER_FORMAT = "%8s %-12s %-40s %-40s %4s %6.3s %13s %-60s";
	public static final String BOOK_FORMAT = "%08d %-12s %-40s %-40s %4d %6.3f %13d %-60s";

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private BookReport()
	{
	}

	/**
	 * Print the report.
	 * 
	 * @param customers
	 * @throws IOException
	 */

	public static void write(List<Book> books, PrintStream out)
	{
		String text = null;
		System.out.println();
		println("Book Report", out);
		println(HORIZONTAL_LINE, out);
		text = String.format(HEADER_FORMAT, "ID", "ISBN", "Authors", "Title", "Year", "Average Rating", "Ratings Count", "Image URL");
		println(text, out);
		println(HORIZONTAL_LINE, out);

		int i = 0;
		for (Book book : books)
		{
			if (book.getAuthor().length() > 30)
			{
				book.setAuthor(book.getAuthor().substring(0, 25) + "...");
			}

			if (book.getTitle().length() > 15)
			{
				book.setTitle(book.getTitle().substring(0, 15) + "...");
			}

			text = String.format(BOOK_FORMAT, ++i, book.getIsbn(), book.getAuthor(), book.getTitle(), book.getPubYear(), book.getRating(),
					book.getRatingCount(), book.getImageURL());
			println(text, out);

			LOG.debug(String.format(BOOK_FORMAT, ++i, book.getIsbn(), book.getAuthor(), book.getTitle(), book.getPubYear(), book.getRating(),
					book.getRatingCount(), book.getImageURL()));
		}
	}

	private static void println(String text, PrintStream out)
	{
		out.println(text);
		LOG.info(text);
	}
}
