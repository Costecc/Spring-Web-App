package com.example.demo.bootstrap;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.model.repositories.AuthorRepository;
import com.example.demo.model.repositories.PublisherRepository;
import com.example.demo.model.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        Publisher publisher = new Publisher();
        publisher.setName("foo11");
        publisher.setAddress("Sunny 2/17");
        publisherRepository.save(publisher);

        //Kostek
        Author kostek = new Author("Kostek", "Sajnaga");
        Book book = new Book("White dog", "12234", publisher);
        kostek.getBooks().add(book);
        book.getAuthors().add(kostek);

        authorRepository.save(kostek);
        bookRepository.save(book);

        //Ana
        Author unai = new Author("Unai", "Santini");
        Book book1 = new Book("Big War", "12344", publisher);
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
