package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("������ �߰��Ǿ����ϴ�: " + book.getTitle());
    }

    public void displayStatus() {
        long availableCount = books.stream().filter(Book::isAvailable).count();
        long borrowedCount = books.size() - availableCount;
        System.out.println("===== " + name + " =====");
        System.out.println("��ü ���� ��: " + books.size());
        System.out.println("���� ���� ���� ��: " + availableCount);
        System.out.println("���� ���� ���� ��: " + borrowedCount);
        System.out.println();
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().contains(title)) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().contains(author)) {
                result.add(b);
            }
        }
        return result;
    }

    public boolean checkOutBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title) && b.isAvailable()) {
                b.checkOut();
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title) && !b.isAvailable()) {
                b.returnBook();
                return true;
            }
        }
        return false;
    }

    public void displayAvailableBooks() {
        System.out.println("===== ���� ������ ���� ��� =====");
        for (Book b : books) {
            if (b.isAvailable()) {
                System.out.println(b);
                System.out.println("------------------------");
            }
        }
    }
}
