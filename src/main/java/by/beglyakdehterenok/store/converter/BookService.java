package by.beglyakdehterenok.store.converter;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private static List<Book> books=new ArrayList<>();

    public static List<Book> findAll(){
        books.add(new Book(1L, "One", "One"));
        books.add(new Book(2L, "Two", "Two"));
        books.add(new Book(3L, "Three", "Three"));
        return books;
    }
}
