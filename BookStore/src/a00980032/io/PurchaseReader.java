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
import a00980032.data.Purchase;

/**
 * @author Chris Noble, A00980032
 *
 */
public class PurchaseReader
{
	public static final String RECORD_DELIMITER = ",";

	private static final Logger LOG = LogManager.getLogger();

	public PurchaseReader()
	{

	}

	public static Map<Long, Purchase> read() throws ApplicationException, IOException
	{

		File file = new File("purchases.csv");
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

		Map<Long, Purchase> purchases = new HashMap<>();

		LOG.debug("Reading" + file.getAbsolutePath());

		for (CSVRecord record : records)
		{
			long id = Integer.parseInt(record.get("id"));
			long customerID = Integer.parseInt(record.get("customer_id"));
			long bookID = Integer.parseInt(record.get("book_id"));
			double price = Double.parseDouble(record.get("price"));

			Purchase p = new Purchase.Builder(id, customerID, bookID, price).build();

			purchases.put(p.getId(), p);

			LOG.debug(String.format("Reading the Customers Report from '%s'", file.getAbsolutePath()));
		}

		return purchases;

	}
}
