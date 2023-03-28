package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class UserInfoStepDefs {

    String actualUserCount;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        System.out.println("***CONNECTION WILL BE DONE WITH HOOK CLASS****");
        //DB_Util.createConnection();
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query = "SELECT count(id) FROM users";
        DB_Util.runQuery(query);

      actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualUserCount = " + actualUserCount);
    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query = "SELECT COUNT(DISTINCT id) FROM users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserCount = " + expectedUserCount);

        //MAKE ASSERTIONS
        Assert.assertEquals(expectedUserCount,actualUserCount);

        //CLOSE CONNECTION
        //WILL BE DONE WITH HOOKS
      //  DB_Util.destroy();

    }




}
