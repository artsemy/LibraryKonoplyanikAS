package by.training.epam.bean;

import by.training.epam.data.Constant;

public class Book {

    private String title;
    private String author;

    public Book() {
        title = "";
        author = "";
    }

    public Book(String str) {
        this();
        String divider = Constant.dividerBookLine;
        String[] array = str.split(divider);
        if (array.length != 1) {
            title = array[0];
            author = array[1];
        }
    }

    public Book(String title, String author) {
        setTitle(title);
        setAuthor(author);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? "" : title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        int n = 31;
        int res = 1;
        res = res*n + title.hashCode();
        res = res*n + author.hashCode();
        return res;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
