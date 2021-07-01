/**
 * Project: A00123456Lab4
 * File: CompareByJoinedDate.java
 * Copyright 2017 Sam Cirka. All rights reserved.
 */

package a00980032.util;

import java.util.Comparator;

import a00980032.data.Book;
import a00980032.data.Customer;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class Sorters
{

	public static class CompareByJoinedDate implements Comparator<Customer>
	{
		@Override
		public int compare(Customer customer1, Customer customer2)
		{
			return customer1.getJoinedDate().compareTo(customer2.getJoinedDate());
		}
	}

	public static class CompareByAuthor implements Comparator<Book>
	{

		@Override
		public int compare(Book book1, Book book2)
		{
			return book1.getAuthor().compareTo(book2.getAuthor());
		}
	}
	//
	// public static class CompareByTitle implements Comparator<Purchase>
	// {
	//
	// @Override
	//// public int compare(Purchase purchase1, Purchase purchase2)
	//// {
	////
	//// try
	//// {
	//// return purchase1.getTitle().compareTo(purchase2.getTitle());
	//// } catch (ApplicationException e)
	//// {
	//// // TODO Auto-generated catch block
	//// e.getMessage();
	//// } catch (IOException e)
	//// {
	//// // TODO Auto-generated catch block
	//// e.getMessage();
	//// }
	//// return 0;
	//// }
	//
	// }
}
