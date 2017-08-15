package com.jesperblidkvist.android.androidlab_phase_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 2017-08-15.
 */

public class SimpleBookManager implements BookManager {

    List<Book> bookList = new ArrayList<Book>();

    SimpleBookManager(){
        for(int i = 0; i < 5; i++){
            Book tmpBook = new Book("Author " + i, "Title " + i, i, "ISBN " + i, "Course " + i);
            bookList.add(tmpBook);
        }
    }


    @Override
    public int count() {
        return 0;
    }

    @Override
    public Book getBook(int index) {
        return null;
    }

    @Override
    public Book createBook() {
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return null;
    }

    @Override
    public void removeBook(Book book) {

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
