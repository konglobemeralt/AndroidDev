package com.jesperblidkvist.android.androidlab_phase_1;

import java.util.ArrayList;

/**
 * Created by Jesper on 2017-08-15.
 */

public interface BookManager {

    public int count();
    public Book getBook(int index);
    public Book createBook();
    public ArrayList<Book> getAllBooks();
    public void removeBook(Book book);
    public void moveBook(int from, int to);
    public int getMinPrice();
    public int getMaxPrice();
    public float getMeanPrice();
    public int getTotalCost();
    public void saveChanges();


}
