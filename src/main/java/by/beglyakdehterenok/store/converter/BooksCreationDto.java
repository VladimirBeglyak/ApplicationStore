package by.beglyakdehterenok.store.converter;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BooksCreationDto {
    private List<Book> booksDto=new ArrayList<>();

    public void addBook(Book book){
        this.booksDto.add(book);
    }
}
