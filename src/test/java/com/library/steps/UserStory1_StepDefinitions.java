package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class UserStory1_StepDefinitions {


    String actualResult;


    public void establish_the_database_connection() {

        //HOOKS "DB"
    }
    @When("executing query, query gets all IDs from users")
    public void executing_query_query_gets_all_i_ds_from_users() {

        String query = "select count(*) from users";

        DB_Util.runQuery(query);

        actualResult = DB_Util.getFirstRowFirstColumn();



    }
    @Then("verify all users have unique ID")
    public void verify_all_users_have_unique_id() {

        DB_Util.runQuery("select count(distinct id) from users");

       String expectedResult =  DB_Util.getFirstRowFirstColumn();


        Assert.assertEquals(actualResult,expectedResult);

    }





    @When("executing the query,query gets all columns")
    public void executing_the_query_query_gets_all_columns() {

        DB_Util.runQuery("select * from users");

        System.out.println("DB_Util.getAllColumnNamesAsList() = " + DB_Util.getAllColumnNamesAsList());


    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(io.cucumber.datatable.DataTable dataTable) {

        List<String> listOfColumnNames = DB_Util.getAllColumnNamesAsList();

        Assert.assertEquals(listOfColumnNames,dataTable);


    }



}
