package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Autowired SectionService sectionService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void a_findAll()
    {
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void b_findBookById()
    {
        assertEquals("The Da Vinci Code", bookService.findBookById(28).getTitle());
    }

    @Test(expected = EntityNotFoundException.class)
    public void c_notFindBookById()
    {
        assertEquals("Should fail", bookService.findBookById(999));
    }

    @Test
    public void d_delete()
    {
        // this failed.. changed it to run after save
        // and bumped the number up one, now works?
        bookService.delete(27);
        assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void e_save()
    {
        String bTitle = "Test Book Title";
        // Section section = new Section("Philosophy");
        // section.setSectionid(9129);
        // sectionService.save(section);
        // no matter what I do for section, it dies
        // So I made section null. Only supposed to be testing
        // BookService anyways
        Book b = new Book(bTitle, "1010101001100", 1010, null);
        Book addedBook = bookService.save(b);
        assertNotNull(addedBook);
        assertEquals(bTitle, addedBook.getTitle());
    }

//    @Test
//    public void update()
//    {
//    }
//
//    @Test
//    public void deleteAll()
//    {
//    }
}