package a00980032.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00980032.book.ApplicationException;
import a00980032.data.Book;

/**
 * @author Chris Noble, A00980032
 *
 */
public class BookReader
{
	public static final String RECORD_DELIMITER = ",";

	private static final Logger LOG = LogManager.getLogger();

	public BookReader()
	{

	}

	public static Map<Long, Book> read() throws ApplicationException, IOException
	{

		File file = new File("books500.csv");
		FileReader in;
		Iterable<CSVRecord> records;

		try
		{
			in = new FileReader(file);
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		} catch (IOException e)
		{
			throw new ApplicationException(e);
		}

		Map<Long, Book> books = new HashMap<>();

		LOG.debug("Reading" + file.getAbsolutePath());

		for (CSVRecord record : records)
		{
			long id = Integer.parseInt(record.get("book_id"));
			String isbn = record.get("isbn");
			String author = record.get("authors");
			long pubYear = Integer.parseInt(record.get("original_publication_year"));
			String title = record.get("original_title");
			double rating = Double.parseDouble(record.get("average_rating"));
			long ratingCount = Integer.parseInt(record.get("ratings_count"));
			String imageURL = record.get("image_url");

			Book b = new Book.Builder(id).setAuthor(author).setIsbn(isbn).setRating(rating).setTitle(title).setPubYear(pubYear)
					.setRatingCount(ratingCount).setImageURL(imageURL).build();

			books.put(b.getBookID(), b);
			LOG.debug(String.format("Reading the Book Report from '%s'", file.getAbsolutePath()));
		}

		return books;

	}

}
