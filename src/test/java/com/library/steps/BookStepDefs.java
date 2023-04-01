package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class BookStepDefs {
    LoginPage loginPage;
    BookPage bookPage;

    @Given("the {string} on the home page hm")
    public void theOnTheHomePageHm(String user) {
        loginPage=new LoginPage();
        loginPage.login(user);
        BrowserUtil.waitFor(3);

    }

    @When("the user navigates to {string} page hm")
    public void theUserNavigatesToPageHm(String moduleName) {
        bookPage=new BookPage();
        bookPage.navigateModule(moduleName);
        BrowserUtil.waitFor(1);
    }
    List<String> actualCategoryList ;
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        actualCategoryList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println("actualCategoryList = " + actualCategoryList);
        actualCategoryList.remove(0);
        System.out.println("------- AFTER REMOVE FIRST ONE --------");
        System.out.println("actualCategoryList = " + actualCategoryList);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        DB_Util.runQuery("select name from book_categories");

        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);

        System.out.println("expectedCategoryList = " + expectedCategoryList);

        Assert.assertEquals(expectedCategoryList,actualCategoryList);


    }


}
