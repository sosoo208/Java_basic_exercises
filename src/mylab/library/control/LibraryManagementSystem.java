package mylab.library.control;

import mylab.library.entity.*;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("�߾� ������");

        // ���� ���� �߰�
        library.addBook(new Book("�ڹ� ���α׷���", "���ڹ�", "978-89-01-12345-6", 2022));
        library.addBook(new Book("��ü������ ��ǰ� ����", "����ȣ", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("�ڹ��� ����", "���ü�", "978-89-01-14077-4", 2019));

        library.displayStatus();

        System.out.println("===== ���� �˻� �׽�Ʈ =====");
        System.out.println("�������� �˻� ���:");
        for (Book b : library.searchByTitle("�ڹ��� ����")) {
            System.out.println(b);
        }
        System.out.println();
        System.out.println("���ڷ� �˻� ���:");
        for (Book b : library.searchByAuthor("Robert C. Martin")) {
            System.out.println(b);
        }
        System.out.println();

        System.out.println("===== ���� ���� �׽�Ʈ =====");
        if (library.checkOutBook("�ڹ��� ����")) {
            System.out.println("���� ���� ����!");
            for (Book b : library.searchByTitle("�ڹ��� ����")) {
                System.out.println("����� ���� ����:");
                System.out.println(b);
            }
        }
        System.out.println();
        System.out.println("������ ���� ����:");
        library.displayStatus();

        System.out.println("===== ���� �ݳ� �׽�Ʈ =====");
        if (library.returnBook("�ڹ��� ����")) {
            System.out.println("���� �ݳ� ����!");
            for (Book b : library.searchByTitle("�ڹ��� ����")) {
                System.out.println("�ݳ��� ���� ����:");
                System.out.println(b);
            }
        }
        System.out.println();
        System.out.println("������ ���� ����:");
        library.displayStatus();

        library.displayAvailableBooks();
    }
}
