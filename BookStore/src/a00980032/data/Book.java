package a00980032.data;

/**
 * @author Chris Noble, A00980032
 *
 */
public class Book
{

	public static final int ATTRIBUTE_COUNT = 8;

	private long bookID;
	private String isbn;
	private String author;
	private long pubYear;
	private String title;
	private double rating;
	private long ratingCount;
	private String imageURL;

	public static class Builder
	{
		// required param
		private final long bookID;

		// optional params
		private String isbn;
		private String author;
		private long pubYear;
		private String title;
		private double rating;
		private long ratingCount;
		private String imageURL;

		// constructor
		public Builder(long id)
		{
			this.bookID = id;
		}

		public Builder setIsbn(String isbn)
		{
			this.isbn = isbn;
			return this;
		}

		public Builder setAuthor(String author)
		{
			this.author = author;
			return this;
		}

		public Builder setPubYear(long pubYear)
		{
			this.pubYear = pubYear;
			return this;
		}

		public Builder setTitle(String title)
		{
			this.title = title;
			return this;
		}

		public Builder setRating(double rating)
		{
			this.rating = rating;
			return this;
		}

		public Builder setRatingCount(long ratingCount)
		{
			this.ratingCount = ratingCount;
			return this;
		}

		public Builder setImageURL(String imageURL)
		{
			this.imageURL = imageURL;
			return this;
		}

		public Book build()
		{
			return new Book(this);
		}

	}

	private Book(Builder builder)
	{
		bookID = builder.bookID;
		isbn = builder.isbn;
		author = builder.author;
		pubYear = builder.pubYear;
		title = builder.title;
		rating = builder.rating;
		ratingCount = builder.ratingCount;
		imageURL = builder.imageURL;
	}

	public long getBookID()
	{
		return bookID;
	}

	public void setBookID(long bookID)
	{
		this.bookID = bookID;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public long getPubYear()
	{
		return pubYear;
	}

	public void setPubYear(long pubYear)
	{
		this.pubYear = pubYear;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public double getRating()
	{
		return rating;
	}

	public void setRating(double rating)
	{
		this.rating = rating;
	}

	public long getRatingCount()
	{
		return ratingCount;
	}

	public void setRatingCount(int ratingCount)
	{
		this.ratingCount = ratingCount;
	}

	public String getImageURL()
	{
		return imageURL;
	}

	public void setImageURL(String imageURL)
	{
		this.imageURL = imageURL;
	}

	@Override
	public String toString()
	{
		return "Book [bookID=" + bookID + ", isbn=" + isbn + ", author=" + author + ", pubYear=" + pubYear + ", title=" + title + ", rating=" + rating
				+ ", ratingCount=" + ratingCount + ", imageURL=" + imageURL + "]";
	}

}
