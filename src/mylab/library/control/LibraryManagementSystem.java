package mylab.library.control;

import mylab.library.entity.*;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("Áß¾Ó µµ¼­°ü");

<<<<<<< HEAD
        // »ùÇÃ µµ¼­ Ãß°¡
=======
>>>>>>> 904b890 (ì€í–‰ ê³„ì¢Œ ê´€ë¦¬ ì‹œìŠ¤í…œ êµ¬í˜„í•˜ê¸°)
        library.addBook(new Book("ÀÚ¹Ù ÇÁ·Î±×·¡¹Ö", "±èÀÚ¹Ù", "978-89-01-12345-6", 2022));
        library.addBook(new Book("°´Ã¼ÁöÇâÀÇ »ç½Ç°ú ¿ÀÇØ", "Á¶¿µÈ£", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("ÀÚ¹ÙÀÇ Á¤¼®", "³²±Ã¼º", "978-89-01-14077-4", 2019));

        library.displayStatus();

        System.out.println("===== µµ¼­ °Ë»ö Å×½ºÆ® =====");
        System.out.println("Á¦¸ñÀ¸·Î °Ë»ö °á°ú:");
        for (Book b : library.searchByTitle("ÀÚ¹ÙÀÇ Á¤¼®")) {
            System.out.println(b);
        }
        System.out.println();
        System.out.println("ÀúÀÚ·Î °Ë»ö °á°ú:");
        for (Book b : library.searchByAuthor("Robert C. Martin")) {
            System.out.println(b);
        }
        System.out.println();

        System.out.println("===== µµ¼­ ´ëÃâ Å×½ºÆ® =====");
        if (library.checkOutBook("ÀÚ¹ÙÀÇ Á¤¼®")) {
            System.out.println("µµ¼­ ´ëÃâ ¼º°ø!");
            for (Book b : library.searchByTitle("ÀÚ¹ÙÀÇ Á¤¼®")) {
                System.out.println("´ëÃâµÈ µµ¼­ Á¤º¸:");
                System.out.println(b);
            }
        }
        System.out.println();
        System.out.println("µµ¼­°ü ÇöÀç »óÅÂ:");
        library.displayStatus();

        System.out.println("===== µµ¼­ ¹İ³³ Å×½ºÆ® =====");
        if (library.returnBook("ÀÚ¹ÙÀÇ Á¤¼®")) {
            System.out.println("µµ¼­ ¹İ³³ ¼º°ø!");
            for (Book b : library.searchByTitle("ÀÚ¹ÙÀÇ Á¤¼®")) {
                System.out.println("¹İ³³µÈ µµ¼­ Á¤º¸:");
                System.out.println(b);
            }
        }
        System.out.println();
        System.out.println("µµ¼­°ü ÇöÀç »óÅÂ:");
        library.displayStatus();

        library.displayAvailableBooks();
    }
}
