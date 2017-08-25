package com.jesperblidkvist.android.androidlab_phase_1;

/**
 * Created by Jesper on 2017-08-15.
 */

public class Book {

    private String author;
    private String title;
    private int price;
    private String isbn;
    private String course;

    Book(String author, String title, int price, String isbn, String course){
        this.author = author;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.course = course;
    }

    Book(){
        this.author = null;
        this.title = null;
        this.price = 0;
        this.isbn = null;
        this.course = null;
    }


    public String getAuthor() {
        if(author !=null){
            return author;
        }
        else
            return "Author not avaliable";

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        if(title !=null){
            return title;
        }
        else
            return "Title not avaliable";

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIsbn() {
        if(isbn !=null){
            return isbn;
        }
        else
            return "ISBN not avaliable";

    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCourse() {
        if(course !=null){
            return course;
        }
        else
            return "Course not avaliable";
    }

    public void setCourse(String course) {
        course = course;
    }
}
