package com.hexad.lmsproj.datamodels.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "tbl_books_borrowed")
@Entity
public class BooksBorrowed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_BORROW_ID", insertable = false, nullable = false)
    private Long bookBorrowId;

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "BORROWER_ID")
    private Long borrowerId;

    @Column(name = "BORROWED_ON")
    private Timestamp borrowedOn;

    @Column(name = "DUE_DATE")
    private Timestamp dueDate;

    @Column(name = "RETURN_DATE")
    private Timestamp returnDate;

    @Column(name = "IS_RETURNED")
    private Boolean isReturned = false;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Timestamp getBorrowedOn() {
        return borrowedOn;
    }

    public void setBorrowedOn(Timestamp borrowedOn) {
        this.borrowedOn = borrowedOn;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "BooksBorrowed{" +
                "bookId=" + bookId +
                ", borrowerId=" + borrowerId +
                ", borrowedOn=" + borrowedOn +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", isReturned=" + isReturned +
                '}';
    }
}