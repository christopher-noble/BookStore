package a00980032.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import a00980032.book.ApplicationException;
import a00980032.io.BookReader;
import a00980032.io.CustomerReader;

/**
 * @author Chris Noble, A00980032
 *
 */
public class Purchase
{
	public static final int ATTRIBUTE_COUNT = 4;

	private long id;
	private long customerID;
	private long bookID;
	private double price;

	public static class Builder
	{
		// required params
		private final long id;
		private final long customerID;
		private final long bookID;
		private final double price;

		public Builder(long id, long customerID, long bookID, double price)
		{
			this.id = id;
			this.customerID = customerID;
			this.bookID = bookID;
			this.price = price;
		}

		public Purchase build()
		{
			return new Purchase(this);
		}
	}

	private Purchase(Builder builder)
	{
		id = builder.id;
		customerID = builder.customerID;
		bookID = builder.bookID;
		price = builder.price;
	}

	public String getFirstName() throws ApplicationException
	{
		Map<Long, Customer> customersMap = new HashMap<>();

		customersMap = CustomerReader.mapReader();

		for (Customer customer : customersMap.values())
		{
			if (customer.getId() == customerID)
			{
				return customer.getFirstName();
			}
			// else
			// {
			// return null;
			// }
		}
		return null;
	}

	public String getLastName() throws ApplicationException
	{
		Map<Long, Customer> customersMap = new HashMap<>();

		customersMap = CustomerReader.mapReader();

		for (Customer customer : customersMap.values())
		{
			if (customer.getId() == customerID)
			{
				return customer.getLastName();
			}
			// else
			// {
			// return null;
			// }
		}
		return null;
	}

	public String getFullName() throws ApplicationException
	{
		return getFirstName() + " " + getLastName();
	}

	public String getTitle() throws ApplicationException, IOException
	{
		Map<Long, Book> bookMap = new HashMap<>();

		bookMap = BookReader.read();

		for (Book book : bookMap.values())
		{
			if (book.getBookID() == bookID)
			{
				return book.getTitle();
			}
			// else
			// {
			// return null;
			// }
		}
		return null;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getCustomerID()
	{
		return customerID;
	}

	public void setCustomerID(long customerID)
	{
		this.customerID = customerID;
	}

	public long getBookID()
	{
		return bookID;
	}

	public void setBookID(long bookID)
	{
		this.bookID = bookID;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	@Override
	public String toString()
	{
		return "Purchase [id=" + id + ", customerID=" + customerID + ", bookID=" + bookID + ", price=" + price + "]";
	}

}
