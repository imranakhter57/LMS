package com.hexad.lmsproj.datamodels.dto;

import com.hexad.lmsproj.datamodels.entity.Books;

import java.util.List;

public class CheckoutDto {

    private List<Books> books;
    private Long userId;

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
