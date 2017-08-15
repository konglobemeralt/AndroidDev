package com.jesperblidkvist.android.androidlab_phase_1;

import java.util.ArrayList;
import java.util.Collections;
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
        if(index <= count()){
            return bookList.get(index);
        }
        else {
            return null;
        }

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
        Collections.swap(bookList, from, to);
    }

    @Override
    public int getMinPrice() {

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < count() ; i++){
            if(getBook(i).getPrice() < min){
                min = getBook(i).getPrice();
            }
        }
        return min;
    }

    @Override
    public int getMaxPrice() {

        int max = 0;

        for(int i = 0; i < count() ; i++){
            if(getBook(i).getPrice() > max){
                max = getBook(i).getPrice();
            }
        }
        return max;
    }

    @Override
    public float getMeanPrice() {
        if(count() != 0){
            return getTotalCost()/count();
        }
        return 0;
    }

    @Override
    public int getTotalCost() {

        int total = 0;

        for(int i = 0; i < count() ; i++){
            total += getBook(i).getPrice();
        }
        return total;
    }

    @Override
    public void saveChanges() {
        //Empty for now
    }
}
