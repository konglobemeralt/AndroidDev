package com.jesperblidkvist.android.androidlab_phase_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 2017-08-15.
 */

public class SimpleBookManager implements BookManager {

    List<Book> bookList = new ArrayList<Book>();

    SimpleBookManager(){
        //Add five dummy books to have something to work with
        for(int i = 0; i < 5; i++){
            Book tmpBook = new Book("Author " + i, "Title " + i, i, "ISBN " + i, "Course " + i);
            bookList.add(tmpBook);
        }
    }


    @Override
    public int count() {
        return bookList.size();
    }

    @Override
    public Book getBook(int index) {
        return bookList.get(index);
    }

    @Override
    public Book createBook() {
        //Dummy create new book
        int count = count() + 1;
        Book tmpBook = new Book("Author " + count, "Title " + count, count, "ISBN " + count, "Course " + count);
        return tmpBook;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return (ArrayList)bookList;
    }

    @Override
    public void removeBook(Book book) {
        bookList.remove(book);
    }

    @Override
    public void moveBook(int from, int to) {

    }

    @Override
    public int getMinPrice() {
        return 0;
    }

    @Override
    public int getMaxPrice() {
        return 0;
    }

    @Override
    public float getMeanPrice() {
        return 0;
    }

    @Override
    public int getTotalCost() {
        return 0;
    }

    @Override
    public void saveChanges() {

    }
}
