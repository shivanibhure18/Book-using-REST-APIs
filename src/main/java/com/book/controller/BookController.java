package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.model.Book;
import com.book.serviceI.BookServiceI;

@RestController
public class BookController {
	
	@Autowired
	BookServiceI bi;
	
	
	@PostMapping("/postBook")
	public Book postBook(@RequestBody Book b) {
		System.out.println("In RestController class...!!");
		System.out.println(b.getAuthor_name());
		bi.AddBook(b);
		return b;
	}
	@GetMapping("/getAllRecords")
	public List<Book> getAllBook()
	{
		return bi.ListAllBook();
	}
	@GetMapping("/getBookById/{id}")
	 public Book getBookById(@PathVariable int id)
	 {
		 return bi.getBookById(id);
	 }
	
	@PutMapping("/updateBookData/{id}")
	public Book updateBookInfo(@PathVariable int id, @RequestBody Book b) {
		Book SingleRecord=bi.getBookById(id);
		SingleRecord.setId(b.getId());
		SingleRecord.setTitle(b.getTitle());
		SingleRecord.setPrice(b.getPrice());
		SingleRecord.setAuthor_name(b.getAuthor_name());
		SingleRecord.setIsbnNo(b.getIsbnNo());
		
		bi.AddBook(SingleRecord);
		
		return SingleRecord;
		
	}
	@DeleteMapping("/deleteById/{id}")
	public String deleteRecord(@PathVariable int id)
	{
		bi.deleteBookById(id);
		return "Record deleted...!!";
	}
	@GetMapping("/getBookByTitle/{title}")
	public List<Book> getBookByTitle(@PathVariable String title)
	{
		return bi.getBookByTitle(title);
	}
	@GetMapping("/getSingleRecordByisbn/{isbnNo}")
	public Book getSingleBook(@PathVariable String isbnNo) {
		return bi.getBookByIsbnNumber(isbnNo);
				
		
	}
	
}
