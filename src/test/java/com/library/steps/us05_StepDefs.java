package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class us05_StepDefs {
    String query = "SELECT bc.name, count(*) FROM book_borrow bb\n" +
            "join books b on bb.book_id = b.id\n" +
            "join book_categories bc on b.book_category_id = bc.id\n" +
            "group by name\n" +
            "order by 2 desc";
    String expectedBookGenre ;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
     DB_Util.runQuery(query);


    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String string) {

        DB_Util.runQuery(query);

        String actualBookGenre = DB_Util.getFirstRowFirstColumn();
        expectedBookGenre = string;
        System.out.println("actualBookGenre = " + actualBookGenre);
        System.out.println("expectedBookGenre = " + expectedBookGenre);
        Assert.assertEquals(expectedBookGenre,actualBookGenre);
    }



}
