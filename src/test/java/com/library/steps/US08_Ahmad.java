package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class US08_Ahmad {

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        //HOOKS "DB"
    }
    @When("executing query, query gets all IDs from users")
    public void executing_query_query_gets_all_i_ds_from_users() {

        String query = "select * from users";

        DB_Util.runQuery(query);



    }
    @Then("verify all users have unique ID")
    public void verify_all_users_have_unique_id() {

        System.out.println("DB_Util.getColumnDataAsList(1) = " + DB_Util.getColumnDataAsList(1));

        List list = DB_Util.getColumnDataAsList(1);

        int[] arr = { list.size()};
        HashMap<Integer, Integer> map = new HashMap<>();// create Hashmap of Integers
        for (int i = 0; i < arr.length; i++) {// Loop to access all array elemnts
            if( map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);// Each key is the element, value is the frequency, if e
            }
            else {
                map.put(arr[i],1);// if Key( element ) is present once, add number 1
            }
        }
        for (Map.Entry entry:map.entrySet()) { // reach to each element

            System.out.println("Amount of elements " +entry.getKey() +" "+"Frequency of elements "+ entry.getValue());

            Assert.assertTrue(entry.getValue().equals(1));

        }


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
