package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US04BookInfoMatchStepDefintions {

    LoginPage loginPage = new LoginPage();
   // US04_BooksPage booksPage=new US04_BooksPage();
    BookPage bookPage = new BookPage();


    String actualBookName ;
    String actualAuthorName;
    String actualISBN;
    String actualYear;
    String actualDesc;
    String actualCategory;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String librarian) {
        loginPage.login(librarian);
       // loginPage.loginButton.click();
    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String string) {
      //Driver.getDriver().get("https://library2.cydeo.com/#books");
        bookPage.navigateModule(string);

    }
    String bookName;
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) throws InterruptedException {
       bookName=bookName;
        bookPage.search.sendKeys(bookName);


    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBookRufi.click();
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        bookPage=new BookPage();

        actualBookName = bookPage.bookName.getAttribute("value");
        actualAuthorName = bookPage.author.getAttribute("value");
        actualISBN = bookPage.isbn.getAttribute("value");
        actualYear = bookPage.year.getAttribute("value");
        actualDesc = bookPage.description.getAttribute("value");

        Select select = new Select(bookPage.categoryDropdown);
        actualCategory = select.getFirstSelectedOption().getText();
        List<String> bookInfoFromUI=new ArrayList<>(Arrays.asList(actualBookName,actualAuthorName,actualISBN,actualYear,actualDesc,actualCategory));


        String query = "select distinct b.name, author,isbn,b.description,year,bc.name as \"name_genre\" from books b\n" +
                "inner join book_categories bc on b.book_category_id = bc.id\n" +
                "where b.name like '%Clean Code_BK%'\n" +
                "order by isbn desc;";

        DB_Util.runQuery(query);

       List<String> bookInfo = DB_Util.getRowDataAsList(1);

        System.out.println("bookInfoMap=" +bookInfo);

        String expectedBookName = bookInfo.get(0);
        String expectedAuthorName =bookInfo.get(1);
        String expectedISBN = bookInfo.get(2);
        String expecteddescription = bookInfo.get(3);
        String expectedYear = bookInfo.get(4);
        String expectedCategory = bookInfo.get(5);


        Assert.assertEquals(actualBookName,expectedBookName);
        Assert.assertEquals(actualAuthorName,expectedAuthorName);
        Assert.assertEquals(actualISBN,expectedISBN);
        Assert.assertEquals(actualYear,expectedYear);
        Assert.assertEquals(actualDesc,expecteddescription);







    }
}
