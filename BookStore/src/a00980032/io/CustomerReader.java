package a00980032.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00980032.book.ApplicationException;
import a00980032.data.Customer;

/**
 * @author Chris Noble, A00980032
 *
 *         /**
 *         Project: A00123456Lab3
 *         File: CustomerReader.java
 *         Copyright 2017 Sam Cirka. All rights reserved.
 */

public class CustomerReader
{

	public static final String RECORD_DELIMETER = ":";
	public static final String FIELD_DELIMITER = "\\|";

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private CustomerReader()
	{
	}

	/**
	 * Read the customer input data.
	 * 
	 * @param data
	 *            The input data.
	 * @return A list of customers.
	 * @throws ApplicationException
	 */
	public static List<String> read() throws ApplicationException
	{
		BufferedReader customerReader = null;
		List<String> customers = new ArrayList<>();
		// LOG.debug("Reading" + customerDataFile.getAbsolutePath());
		try
		{
			customerReader = new BufferedReader(new FileReader("customers.dat"));

			String line = null;
			line = customerReader.readLine(); // skip the header line

			while ((line = customerReader.readLine()) != null)
			{
				// String customer = readCustomerString(line);
				customers.add(line);
				// customers.add(customer);
				// LOG.debug("Added " + customer.toString() + " as " + customer.getId());
			}
			customerReader.close();

		} catch (IOException e)
		{
			LOG.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		} finally
		{
			try
			{
				if (customerReader != null)
				{
					customerReader.close();
				}
			} catch (IOException e)
			{
				LOG.error(e.getMessage());
				throw new ApplicationException(e.getMessage());
			}
		}

		return customers;
	}

	/**
	 * Parse a Customer data string into a Customer object;
	 * 
	 * @param row
	 * @throws ApplicationException
	 */
	private static Customer readCustomerString(String data) throws ApplicationException
	{
		String[] elements = data.split(FIELD_DELIMITER);
		if (elements.length != Customer.ATTRIBUTE_COUNT)
		{
			throw new ApplicationException(
					String.format("Expected %d but got %d: %s", Customer.ATTRIBUTE_COUNT, elements.length, Arrays.toString(elements)));
		}

		int index = 0;
		long id = Integer.parseInt(elements[index++]);
		String firstName = elements[index++];
		String lastName = elements[index++];
		String street = elements[index++];
		String city = elements[index++];
		String postalCode = elements[index++];
		String phone = elements[index++];
		String emailAddress = elements[index++];
		String yyyymmdd = elements[index];

		int year = Integer.parseInt(yyyymmdd.substring(0, 4));
		int month = Integer.parseInt(yyyymmdd.substring(4, 6));
		int day = Integer.parseInt(yyyymmdd.substring(6, 8));

		return new Customer.Builder(id, phone).setFirstName(firstName).setLastName(lastName).setStreet(street).setCity(city).setPostalCode(postalCode)
				.setEmailAddress(emailAddress).setJoinedDate(year, month, day).build();
	}

	public static Map<Long, Customer> mapReader() throws ApplicationException
	{
		List<String> customerList = new ArrayList<>();
		Map<Long, Customer> customerMap = new HashMap<>();

		String[] rows = null;

		customerList = read();

		for (int i = 0; i < customerList.size(); i++)
		{
			rows = customerList.get(i).split(RECORD_DELIMETER);

			for (String row : rows)
			{
				Customer c1 = readCustomerString(row);
				customerMap.put(c1.getId(), c1);
			}
		}

		return customerMap;

	}

}
