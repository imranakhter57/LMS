package com.hexad.lmsproj.services;

import com.hexad.lmsproj.datamodels.dto.CheckoutDto;
import com.hexad.lmsproj.datamodels.dto.DashboardDto;
import com.hexad.lmsproj.datamodels.dto.RemoveBookDto;
import com.hexad.lmsproj.datamodels.entity.Books;
import com.hexad.lmsproj.datamodels.vo.BookListVO;

public interface DashboardServices {
    public String addNewBook(Books book);
    public Books getBookByBookName(String bookName);
    public Books getBookByBookId(Long bookId);
    public BookListVO getBookList(String searchString);
    public BookListVO getAvailableBooks(DashboardDto dashboard);
    public BookListVO getBorrowedBooks(DashboardDto dashboard);
    public String checkoutBooks(CheckoutDto checkout);
    public String removeBooks(RemoveBookDto remove);
}
