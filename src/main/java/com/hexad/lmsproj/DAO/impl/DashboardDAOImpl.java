package com.hexad.lmsproj.DAO.impl;

import com.hexad.lmsproj.DAO.DashboardDAO;
import com.hexad.lmsproj.datamodels.dto.DashboardDto;
import com.hexad.lmsproj.datamodels.dto.RemoveBookDto;
import com.hexad.lmsproj.datamodels.entity.Books;
import com.hexad.lmsproj.datamodels.entity.BooksBorrowed;
import com.hexad.lmsproj.datamodels.vo.BookListVO;
import com.hexad.lmsproj.datamodels.vo.BooksVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DashboardDAOImpl implements DashboardDAO {
    private static Logger logger = LogManager.getLogger(DashboardDAOImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String addNewBook(Books book) {
        Session session = null;
        String response = "Failed to add new book";
        try {
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(book);
            tx.commit();
            response = "Successfully added new book";
        }catch(Exception e) {
            logger.error("(:) Error in UpdateEntity Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return response;
    }

    @Override
    public Books getBookByBookName(String bookName) {
        Session session = null;
        Books book = null;
        try {
            session = sessionFactory.openSession();
            book = (Books) session.createCriteria(Books.class).add(Restrictions.eq("bookName",bookName)).uniqueResult();
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public Books getBookByBookId(Long bookId) {
        Session session = null;
        Books book = null;
        try {
            session = sessionFactory.openSession();
            book = session.get(Books.class,bookId);
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public BookListVO getBookList(String searchString) {
        Session session = null;
        List<BooksVO> books = new ArrayList<>();
        Long countOfRows = 0L;
        BookListVO bookListVO = new BookListVO();
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM TBL_BOOKS ";
            if(searchString != null && !searchString.isEmpty()){
                sql = sql+ " WHERE BOOK_NAME LIKE '%"+searchString.toLowerCase()+"%' OR  AUTHOR_NAME LIKE '%"+searchString.toLowerCase()+"%'";
            }
            Query query = session.createSQLQuery(sql);
            List<Object[]> obj=query.list();
            sql = "SELECT FOUND_ROWS()";
            query = session.createSQLQuery(sql);
            countOfRows = ((BigInteger)query.uniqueResult()).longValue();
            for(Object[] object :obj){
                BooksVO book = new BooksVO();
                book.setBookId(object[0] != null ? ((BigInteger)object[0]).longValue() :0L);
                book.setBookName(object[1] != null ? (String)object[1] :"");
                book.setAuthorName(object[2] != null ? (String)object[2] :"");
                book.setQuantityAvailable(object[3] != null ? ((BigInteger)object[3]).longValue() :0L);
                book.setQuantityBorrowed(object[4] != null ? ((BigInteger)object[4]).longValue() :0L);
                books.add(book);
            }
            bookListVO.setBooks(books);
            bookListVO.setTotalNumberRecords(countOfRows);
            bookListVO.setTotalNumberPages(Math.floor(countOfRows% 10) == 0 ? (countOfRows / 10) : (int) (Math.floor(countOfRows / 10) + 1));
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return bookListVO;
    }

    @Override
    public BookListVO getAvailableBooks(DashboardDto dashboard) {
        Session session = null;
        List<BooksVO> books = new ArrayList<>();
        Long countOfRows = 0L;
        BookListVO bookListVO = new BookListVO();
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM TBL_BOOKS AS BK WHERE BK.QUANTITY_AVAILABLE >0 AND " +
                    "BK.BOOK_ID NOT IN (SELECT BOOK_ID FROM TBL_BOOKS_BORROWED WHERE BORROWER_ID ="+dashboard.getUserId()+
                    " AND IS_RETURNED = 0)";
            if(dashboard.getSearchString() != null && !dashboard.getSearchString().isEmpty()){
                sql = sql + " AND BK.BOOK_NAME LIKE '%"+dashboard.getSearchString().toLowerCase()+"%'  OR BK.AUTHOR_NAME LIKE '%"+dashboard.getSearchString().toLowerCase()+"%'";
            }
            Query query = session.createSQLQuery(sql);
            List<Object[]> obj=query.list();
            sql = "SELECT FOUND_ROWS()";
            query = session.createSQLQuery(sql);
            countOfRows = ((BigInteger)query.uniqueResult()).longValue();
            for(Object[] object :obj){
                BooksVO book = new BooksVO();
                book.setBookId(object[0] != null ? ((BigInteger)object[0]).longValue() :0L);
                book.setBookName(object[1] != null ? (String)object[1] :"");
                book.setAuthorName(object[2] != null ? (String)object[2] :"");
                book.setQuantityAvailable(object[3] != null ? ((BigInteger)object[3]).longValue() :0L);
                book.setQuantityBorrowed(object[4] != null ? ((BigInteger)object[4]).longValue() :0L);
                books.add(book);
            }
            bookListVO.setBooks(books);
            bookListVO.setTotalNumberRecords(countOfRows);
            bookListVO.setTotalNumberPages(Math.floor(countOfRows% 10) == 0 ? (countOfRows / 10) : (int) (Math.floor(countOfRows / 10) + 1));
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return bookListVO;
    }

    @Override
    public BookListVO getBorrowedBooks(DashboardDto dashboard) {
        Session session = null;
        List<BooksVO> books = new ArrayList<>();
        SimpleDateFormat sd  = new SimpleDateFormat("dd-MM-yyyy");
        Long countOfRows = 0L;
        BookListVO bookListVO = new BookListVO();
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT SQL_CALC_FOUND_ROWS BK.BOOK_ID,BK.BOOK_NAME," +
                    "BK.AUTHOR_NAME, BB.BORROWED_ON, BB.DUE_DATE FROM TBL_BOOKS AS BK " +
                    "JOIN TBL_BOOKS_BORROWED AS BB ON BK.BOOK_ID = BB.BOOK_ID  WHERE " +
                    "IS_RETURNED = 0 AND BB.BORROWER_ID ="+dashboard.getUserId();
            if(dashboard.getSearchString() != null && !dashboard.getSearchString().isEmpty()){
                sql = sql + " AND BK.BOOK_NAME LIKE '%"+dashboard.getSearchString()+"%' OR  BK.AUTHOR_NAME LIKE '%"+dashboard.getSearchString()+"%'";
            }
            Query query = session.createSQLQuery(sql);
            List<Object[]> obj=query.list();
            sql = "SELECT FOUND_ROWS()";
            query = session.createSQLQuery(sql);
            countOfRows = ((BigInteger)query.uniqueResult()).longValue();
            for(Object[] object :obj){
                BooksVO book = new BooksVO();
                book.setBookId(object[0] != null ? ((BigInteger)object[0]).longValue() :0L);
                book.setBookName(object[1] != null ? (String)object[1] :"");
                book.setAuthorName(object[2] != null ? (String)object[2] :"");
                book.setBorrowedOn(object[3] != null ? sd.format((java.sql.Timestamp)object[3]) :null);
                book.setDueDate(object[4] != null ?sd.format((java.sql.Timestamp)object[4]) :null);
                books.add(book);
            }
            bookListVO.setBooks(books);
            bookListVO.setTotalNumberRecords(countOfRows);
            bookListVO.setTotalNumberPages(Math.floor(countOfRows% 10) == 0 ? (countOfRows / 10) : (int) (Math.floor(countOfRows / 10) + 1));
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return bookListVO;
    }

    @Override
    public String checkoutBooks(List<BooksBorrowed> booksBorrowed) {
        Session session = null;
        String response = "Failed to add new book";
        try {
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            for(BooksBorrowed book : booksBorrowed) {
                session.saveOrUpdate(book);
                String sql = "UPDATE TBL_BOOKS SET QUANTITY_AVAILABLE = QUANTITY_AVAILABLE-1, QUANTITY_BORROWED = QUANTITY_BORROWED +1 " +
                        "WHERE BOOK_ID =  "+book.getBookId();
                Query query = session.createSQLQuery(sql);
                query.executeUpdate();
            }

            tx.commit();
            response = "Successfully added new book";
        }catch(Exception e) {
            logger.error("(:) Error in UpdateEntity Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return response;
    }

    @Override
    public String removeBooks(RemoveBookDto remove) {
        Session session = null;
        String response = "Failed to remove book";
        try {
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            String sql = "UPDATE TBL_BOOKS_BORROWED SET IS_RETURNED = 1, RETURN_DATE = '"+new Timestamp(System.currentTimeMillis())
                    +"' WHERE BORROWER_ID ="+remove.getUserId()+" AND BOOK_ID = "+remove.getBookId();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();

            sql = "UPDATE TBL_BOOKS SET QUANTITY_AVAILABLE = QUANTITY_AVAILABLE+1, QUANTITY_BORROWED = QUANTITY_BORROWED -1 " +
                    "WHERE BOOK_ID =  "+remove.getBookId();
            query = session.createSQLQuery(sql);
            query.executeUpdate();
            tx.commit();
            response = "Successfully removed book";
        }catch(Exception e) {
            logger.error("(:) Error in Delete Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return  response;
    }
}
