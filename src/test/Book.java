package test;

import java.util.List;

public class Book {
private int id;
private String name;
private Author author;
private List<String> genere;

public Book() {
	super();
}
public List<String> getGenere() {
	return genere;
}
public void setGenere(List<String> genere) {
	this.genere = genere;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Author getAuthor() {
	return author;
}
public void setAuthor(Author author) {
	this.author = author;
}

}
