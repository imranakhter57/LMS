package com.hexad.lmsproj.datamodels.vo;

import com.hexad.lmsproj.datamodels.entity.Books;

import java.util.List;

public class BookListVO extends  ErrorVO{

    private Long totalBooks;
    private Long totalAvailableBooks;
    private Long totalNumberRecords;
    private Long totalNumberPages;
    private  List<BooksVO> books;


    public List<BooksVO> getBooks() {
        return books;
    }

    public void setBooks(List<BooksVO> books) {
        this.books = books;
    }

    public Long getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(Long totalBooks) {
        this.totalBooks = totalBooks;
    }

    public Long getTotalAvailableBooks() {
        return totalAvailableBooks;
    }

    public void setTotalAvailableBooks(Long totalAvailableBooks) {
        this.totalAvailableBooks = totalAvailableBooks;
    }

    public Long getTotalNumberRecords() {
        return totalNumberRecords;
    }

    public void setTotalNumberRecords(Long totalNumberRecords) {
        this.totalNumberRecords = totalNumberRecords;
    }

    public Long getTotalNumberPages() {
        return totalNumberPages;
    }

    public void setTotalNumberPages(Long totalNumberPages) {
        this.totalNumberPages = totalNumberPages;
    }
}
