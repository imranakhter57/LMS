package com.hexad.lmsproj.DAO;

import com.hexad.lmsproj.datamodels.dto.DashboardDto;
import com.hexad.lmsproj.datamodels.dto.RemoveBookDto;
import com.hexad.lmsproj.datamodels.entity.Books;
import com.hexad.lmsproj.datamodels.entity.BooksBorrowed;
import com.hexad.lmsproj.datamodels.vo.BookListVO;

import java.util.List;

public interface DashboardDAO {
    public String addNewBook(Books book);
    public Books getBookByBookName(String bookName);
    public Books getBookByBookId(Long bookId);
    public BookListVO getBookList(String searchString);
    public BookListVO getAvailableBooks(DashboardDto dashboard);
    public BookListVO getBorrowedBooks(DashboardDto dashboard);
    public String checkoutBooks(List<BooksBorrowed> booksBorrowed);
    public String removeBooks(RemoveBookDto remove);

}
