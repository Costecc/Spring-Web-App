package com.example.demo.bootstrap;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.model.repositories.AuthorRepository;
import com.example.demo.model.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData(){
        //Kostek
        Author kostek = new Author("Konstanty", "Sajnaga");
        Publisher pub = new Publisher("Diego Lopez", "Straight 1/4");
        Book book = new Book("White dog", "12234", pub);
        kostek.getBooks().add(book);
        book.getAuthors().add(kostek);

        authorRepository.save(kostek);
        bookRepository.save(book);

        //Ana
        Author unai = new Author("Unai", "Santini");
        Publisher publ = new Publisher("Aria Penne", "Sunny 12");
        Book book1 = new Book("Big War", "12344", publ);
        kostek.getBooks().add(book1);
        book.getAuthors().add(unai);

        authorRepository.save(unai);
        bookRepository.save(book1);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
