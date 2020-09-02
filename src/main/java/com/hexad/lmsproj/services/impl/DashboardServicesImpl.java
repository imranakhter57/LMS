package com.hexad.lmsproj.services.impl;

import com.hexad.lmsproj.DAO.DashboardDAO;
import com.hexad.lmsproj.datamodels.dto.CheckoutDto;
import com.hexad.lmsproj.datamodels.dto.DashboardDto;
import com.hexad.lmsproj.datamodels.dto.RemoveBookDto;
import com.hexad.lmsproj.datamodels.entity.Books;
import com.hexad.lmsproj.datamodels.entity.BooksBorrowed;
import com.hexad.lmsproj.datamodels.vo.BookListVO;
import com.hexad.lmsproj.services.DashboardServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DashboardServicesImpl implements DashboardServices {

    private static Logger logger = LogManager.getLogger(DashboardServicesImpl.class);

    @Autowired
    DashboardDAO dashboardDAO;

    @Override
    public String addNewBook(Books book) {
        String response = null;
        try{
            response = dashboardDAO.addNewBook(book);
        } catch (Exception e){
            response = "Failure";
        }
        return response;
    }

    @Override
    public Books getBookByBookName(String bookName) {
        Books book = null;
        try{
            book = dashboardDAO.getBookByBookName(bookName);
        } catch (Exception e){
            logger.info("Something Went worng");
        }
        return book;
    }

    @Override
    public Books getBookByBookId(Long bookId) {
        Books book = null;
        try {
            book = dashboardDAO.getBookByBookId(bookId);
        } catch (Exception e) {
            logger.info("Something Went worng");
        }
        return book;
    }

    @Override
    public BookListVO getBookList(String searchString) {
        BookListVO bookListVO = null;
        try{
            bookListVO = dashboardDAO.getBookList(searchString);

        } catch (Exception e){
            logger.info("Something Went worng");
        }
        return bookListVO;
    }

    @Override
    public BookListVO getAvailableBooks(DashboardDto dashboard) {
        BookListVO bookListVO = null;
        try{
            bookListVO = dashboardDAO.getAvailableBooks(dashboard);

        } catch (Exception e){
            logger.info("Something Went worng");
        }
        return bookListVO;
    }

    @Override
    public BookListVO getBorrowedBooks(DashboardDto dashboard) {
        BookListVO bookListVO = null;
        try{
            bookListVO = dashboardDAO.getBorrowedBooks(dashboard);

        } catch (Exception e){
            logger.info("Something Went worng");
        }
        return bookListVO;
    }

    @Override
    public String checkoutBooks(CheckoutDto checkout) {
        try{
            List<BooksBorrowed> booksBorrowed = new ArrayList<>();
            for(Books book : checkout.getBooks()){
                BooksBorrowed borrowed = new BooksBorrowed();
                borrowed.setBookId(book.getBookId());
                borrowed.setBorrowerId(checkout.getUserId());
                borrowed.setBorrowedOn(new Timestamp(System.currentTimeMillis()));
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE,10);
                borrowed.setDueDate(new Timestamp(c.getTimeInMillis()));
                booksBorrowed.add(borrowed);
            }
            return dashboardDAO.checkoutBooks(booksBorrowed);
        } catch (Exception e){
          return "Failure";
        }
    }

    @Override
    public String removeBooks(RemoveBookDto remove) {
        try{
            String response = dashboardDAO.removeBooks(remove);
            return response;
        } catch (Exception e){
            logger.info("Error while removing book");
            return "Error while removing book";
        }
    }
}
