package by.training.epam.bean;

public class Book implements Comparable{// для bean- класса не хватает некоторых пунктов Serializable, конструктор без параметров
    // повтори еще раз эту тему

    private String title;
    private String author;
    private int id;

    public Book(String title, String author, int id) {
        setTitle(title);
        setAuthor(author);
        setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author) && id == book.id;
    }

    @Override
    public int hashCode() {
        int n = 31;
        int res = 1;
        res = res*n + title.hashCode();
        res = res*n + author.hashCode();
        res = res*n + id;
        return res;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Book book = (Book) o;
        return Integer.compare(id, book.id);
    }

}
