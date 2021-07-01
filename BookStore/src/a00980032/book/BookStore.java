package a00980032.book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import a00980032.data.Book;
import a00980032.data.Customer;
import a00980032.data.Purchase;
import a00980032.io.BookReader;
import a00980032.io.BookReport;
import a00980032.io.CustomerReader;
import a00980032.io.CustomerReport;
import a00980032.io.PurchaseReader;
import a00980032.io.PurchaseReport;
import a00980032.util.Sorters;

/**
 * Project: Book
 * File: BookStore.java
 * Date: October, 2017
 * Time: 1:22:25 PM
 */

/**
 * @author Sam Cirka, A00123456
 *
 */
public class BookStore
{

	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	public static final String CUSTOMER_DATA_FILE = "customers.dat";
	public static final String BOOK_DATA_FILE = "books500.csv";
	static
	{
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger();

	private List<Customer> customers;
	private List<Book> books;
	private List<Purchase> purchases;

	/**
	 * Bcmc Constructor. Processes the commandline arguments
	 * ex. -inventory -make=honda -by_count -desc -total -service
	 * 
	 * @throws ApplicationException
	 * @throws ParseException
	 */
	public BookStore(String[] args) throws ApplicationException, ParseException
	{
		LOG.info("Created Bcmc");

		BookOptions.process(args);
	}

	public BookStore()
	{
		LOG.debug("BookStore");
	}

	/**
	 * Entry point to GIS
	 * 
	 * @param args
	 * @throws ApplicationException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ApplicationException, IOException
	{
		Instant startTime = Instant.now();

		LOG.info(startTime);

		try
		{
			Instant endTime = Instant.now();
			LOG.info(endTime);
			LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));

			new BookStore().run();
		} catch (

		Exception e)
		{
			e.printStackTrace();
			LOG.debug(e.getMessage());
		}
		Instant endTime1 = Instant.now();
		LOG.info(endTime1);
		LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime1).toMillis()));
	}

	/**
	 * Configures log4j2 from the external configuration file specified in LOG4J_CONFIG_FILENAME.
	 * If the configuration file isn't found then log4j2's DefaultConfiguration is used.
	 */
	private static void configureLogging()
	{
		ConfigurationSource source;
		try
		{
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e)
		{
			System.out.println(String.format("WARNING! Can't find the log4j logging configuration file %s; using DefaultConfiguration for logging.",
					LOG4J_CONFIG_FILENAME));
			Configurator.initialize(new DefaultConfiguration());
		}
	}

	/**
	 * @throws ApplicationException
	 * @throws IOException
	 * 
	 */
	private void run() throws ApplicationException, IOException
	{
		LOG.debug("run()");

		generateReports();

		loadCustomers();
		printCustomers();

		loadBooks();
		printBooks();

		loadPurchases();
		printPurchases();
	}

	/**
	 * Generate the reports from the input data
	 * 
	 * @throws FileNotFoundException
	 */
	private void generateReports() throws FileNotFoundException
	{
		LOG.debug("generating the reports");

		if (BookOptions.isCustomersOptionSet())
		{
			LOG.debug("generating the customer report");
			// for program args: -c -J -d
			System.out.println("Cutomer Report: " + BookOptions.isCustomersOptionSet());
			System.out.println("Cutomer Join Date: " + BookOptions.isByJoinDateOptionSet());
			System.out.println("Cutomer Join Date DESC: " + BookOptions.isDescendingOptionSet());

		}

		if (BookOptions.isBooksOptionSet())
		{
			LOG.debug("generating the book report");

		}

		if (BookOptions.isPurchasesOptionSet())
		{
			LOG.debug("generating the inventory report");

		}
	}

	private void loadCustomers() throws ApplicationException
	{
		Map<Long, Customer> customerMap = CustomerReader.mapReader();

		customers = new ArrayList<>(customerMap.values());

		Collections.sort(customers, new Sorters.CompareByJoinedDate());
	}

	private void printCustomers()
	{
		File customersFile = new File("customers_report.txt");
		LOG.debug(String.format("Writing the Customers Report to '%s'", customersFile.getAbsolutePath()));
		PrintStream out = null;
		try
		{
			out = new PrintStream(new FileOutputStream(customersFile));
			CustomerReport.write(customers, out);

		} catch (FileNotFoundException e)
		{
			LOG.error(e.getMessage());
		}
	}

	private void loadBooks() throws ApplicationException, IOException
	{
		Map<Long, Book> booksMap = BookReader.read();

		books = new ArrayList<>(booksMap.values());

		Collections.sort(books, new Sorters.CompareByAuthor());
	}

	private void printBooks()
	{
		File booksFile = new File("book_report.txt");
		LOG.debug(String.format("Writing the Book Report to '%s'", booksFile.getAbsolutePath()));
		PrintStream out = null;
		try
		{
			out = new PrintStream(new FileOutputStream(booksFile));
			BookReport.write(books, out);

		} catch (FileNotFoundException e)
		{
			LOG.error(e.getMessage());
		}
	}

	private void loadPurchases() throws ApplicationException, IOException
	{
		Map<Long, Purchase> purchasesMap = PurchaseReader.read();

		purchases = new ArrayList<>(purchasesMap.values());

		// Collections.sort(purchases, new Sorters.CompareByTitle());
	}

	private void printPurchases() throws ApplicationException, IOException
	{
		File purchasesFile = new File("purchase_report.txt");
		LOG.debug(String.format("Writing the Purchase Report to '%s'", purchasesFile.getAbsolutePath()));
		PrintStream out = null;
		try
		{
			out = new PrintStream(new FileOutputStream(purchasesFile));
			PurchaseReport.write(purchases, out);

		} catch (FileNotFoundException e)
		{
			LOG.error(e.getMessage());
		}
	}

}
