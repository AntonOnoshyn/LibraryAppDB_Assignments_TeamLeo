package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class Sofia_Ahmad_StepDefinitions {

    String actualUserCount;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        // Make conn with database
        // DB_Util.createConnection();
        System.out.println("**********************************************");
        System.out.println("*** CONNECTION WILL BE DONE WITH HOOK CLASS***");
        System.out.println("**********************************************");

    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query="select count(id) from users"; // I write the SQL language to get all the id
        DB_Util.runQuery(query); // I use the page DBL to run query, to get the info from the DB

        actualUserCount = DB_Util.getFirstRowFirstColumn(); // I use utile package to use the method to get the info from the 1st row 1st column of the DB
        System.out.println("actualUserCount = " + actualUserCount);

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {

        String query="select count(distinct id) from users"; //I write the SQL language to get unique id
        DB_Util.runQuery(query); // // I use the page DBL to run query, to get the info from the DB
        String expectedUserCount = DB_Util.getFirstRowFirstColumn(); // get the info from table using this method
        System.out.println("expectedUserCount = " + expectedUserCount);

        // MAKE ASSERTIONS
        Assert.assertEquals(expectedUserCount,actualUserCount); // i compare info from all users and unique users

        //CLOSE CONN
        // DB_Util.destroy();
        System.out.println("**********************************************");
        System.out.println("*** DESTROY  WILL BE DONE WITH HOOK CLASS***");
        System.out.println("**********************************************");


    }

    List<String> actualList;
    // US01-2
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users"); // // I use the page DBL to run query, to get the info from the DB
       List<String> actualList = DB_Util.getAllColumnNamesAsList(); // i get list for all column names
        System.out.println("actualList = " + actualList);

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedList) {

        System.out.println("expectedList = " + expectedList);

        // Assertions
        Assert.assertEquals(expectedList,actualList); // I compare the info from the feature file with the info from the DB

    }



}
