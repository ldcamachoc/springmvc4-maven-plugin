package com.accenture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the books database table.
 * 
 */
@Entity
@Table(name="books")
@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value = { "authors", "categories","booksOutOnLoans" })
@ToString(of = {"isbn","title","dateOfPublication"})
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String isbn;
	@Temporal(TemporalType.DATE)
	@Column(name="date_of_publication")
	private Date dateOfPublication;
   	private String title;

	//bi-directional many-to-many association to Author
    @XmlTransient
	@ManyToMany
	@JoinTable(
		name="books_by_author"
		, joinColumns={
			@JoinColumn(name="isbn")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idAuthor")
			}
		)
	private List<Author> authors;

	//bi-directional many-to-many association to Category
    @XmlTransient
	@ManyToMany(mappedBy="books")
	private List<Category> categories;

	//bi-directional many-to-one association to BooksOutOnLoan
    @XmlTransient
	@OneToMany(mappedBy="book")
	private List<BooksOutOnLoan> booksOutOnLoans;

	public Book() {
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getDateOfPublication() {
		return this.dateOfPublication;
	}

	public void setDateOfPublication(Date dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<BooksOutOnLoan> getBooksOutOnLoans() {
		return this.booksOutOnLoans;
	}

	public void setBooksOutOnLoans(List<BooksOutOnLoan> booksOutOnLoans) {
		this.booksOutOnLoans = booksOutOnLoans;
	}

	public BooksOutOnLoan addBooksOutOnLoan(BooksOutOnLoan booksOutOnLoan) {
		getBooksOutOnLoans().add(booksOutOnLoan);
		booksOutOnLoan.setBook(this);

		return booksOutOnLoan;
	}

	public BooksOutOnLoan removeBooksOutOnLoan(BooksOutOnLoan booksOutOnLoan) {
		getBooksOutOnLoans().remove(booksOutOnLoan);
		booksOutOnLoan.setBook(null);

		return booksOutOnLoan;
	}

}