package com.hexad.lmsproj.datamodels.vo;


public class BooksVO {

    private Long bookId;
    private String bookName;
    private String authorName;
    private Long quantityAvailable;
    private Long quantityBorrowed;
    private String dueDate;
    private String borrowedOn;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Long quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Long getQuantityBorrowed() {
        return quantityBorrowed;
    }

    public void setQuantityBorrowed(Long quantityBorrowed) {
        this.quantityBorrowed = quantityBorrowed;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getBorrowedOn() {
        return borrowedOn;
    }

    public void setBorrowedOn(String borrowedOn) {
        this.borrowedOn = borrowedOn;
    }
}
