package com.hexad.lmsproj.controller;

import com.hexad.lmsproj.datamodels.dto.BookDto;
import com.hexad.lmsproj.datamodels.dto.CheckoutDto;
import com.hexad.lmsproj.datamodels.dto.DashboardDto;
import com.hexad.lmsproj.datamodels.dto.RemoveBookDto;
import com.hexad.lmsproj.datamodels.entity.Books;
import com.hexad.lmsproj.datamodels.vo.BookListVO;
import com.hexad.lmsproj.services.DashboardServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private static final Logger logger = LogManager.getLogger(DashboardController.class);

    @Autowired
    DashboardServices dashboardServices;

    @RequestMapping(value = "/addNewBook", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addNewBook(@RequestBody BookDto book, @RequestHeader HttpHeaders rawHeaders) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Books updateBook = null;
        logger.info("In addNewBook");
        try{
            if(book.getBookId() != null){
                updateBook = dashboardServices.getBookByBookId(book.getBookId());
            } else {
                updateBook = dashboardServices.getBookByBookName(book.getBookName());
            }
            if(updateBook == null){
                updateBook = new Books();
                updateBook.setAuthorName(book.getAuthorName());
                updateBook.setBookName(book.getBookName());
            }
            updateBook.setQuantityAvailable(book.getQuantityAvailable());
            String response = dashboardServices.addNewBook(updateBook);
            jsonObject.put("errorMessage",response);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Something went wrong while adding book", e);
            jsonObject.put("errorMessage","Something went wrong while adding book");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getBooksList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BookListVO> getBooksList(@RequestBody DashboardDto dashboard,@RequestHeader HttpHeaders rawHeaders) throws JSONException {
       BookListVO books = null;
        logger.info("In getBooksList");
        try{
            books = dashboardServices.getBookList(dashboard.getSearchString());
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Something went wrong while getting book List", e);
            return new ResponseEntity<>(books, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getAvailableBooks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BookListVO> getAvailableList(@RequestBody DashboardDto dashboard, @RequestHeader HttpHeaders rawHeaders) throws JSONException {
        BookListVO books = null;
        logger.info("In getAvailableBooks");
        try{
            books = dashboardServices.getAvailableBooks(dashboard);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Something went wrong while getting book List", e);
            return new ResponseEntity<>(books, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getBorrowedBooks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BookListVO> getBorrowedList(@RequestBody DashboardDto dashboard,@RequestHeader HttpHeaders rawHeaders) throws JSONException {
        BookListVO books = null;
        logger.info("In getBorrowedBooks");
        try{
            books = dashboardServices.getBorrowedBooks(dashboard);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Something went wrong while getting book List", e);
            return new ResponseEntity<>(books, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/checkoutBooks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> checkoutBooks(@RequestBody CheckoutDto checkout, @RequestHeader HttpHeaders rawHeaders) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        logger.info("In checkoutBooks");
        try{
            String response = dashboardServices.checkoutBooks(checkout);
            jsonObject.put("errorMessage",response);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Something went wrong while getting book List", e);
            jsonObject.put("errorMessage","Something went wrong while adding book");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/removeBooks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> removeBooks(@RequestBody RemoveBookDto remove, @RequestHeader HttpHeaders rawHeaders) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        logger.info("In removeBooks");
        try{
            String response = dashboardServices.removeBooks(remove);
            jsonObject.put("errorMessage",response);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Something went wrong while removing book List", e);
            jsonObject.put("errorMessage","Something went wrong while adding book");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
        }
    }

}