package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US2_StepDefinitions {



    String actualBorrowedBookNumbers;
    LoginPage loginPage=new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();

    @Given("the {string} on the home page")
    public void theOnTheHomePage(String arg0) {
        loginPage.login(arg0);

    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);
        DB_Util.runQuery("select count(*) from book_borrow\n" +
                "where is_returned = 0");

        String expectedBorrowedBookNumbers= DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBookNumbers = " + expectedBorrowedBookNumbers);

        Assert.assertEquals(actualBorrowedBookNumbers,expectedBorrowedBookNumbers);

    }

}
