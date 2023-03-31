Feature: As a data consumer, I want the user information are stored in mySql DB correctly in users table.


  @db
  Scenario: verify users have unique IDs
    When executing query, query gets all IDs from users
    Then verify all users have unique ID

  @db2
  Scenario: verify users table columns
    When executing the query,query gets all columns
    Then verify the below columns are listed in result

      | id            |
      | full_name     |
      | email         |
      | password      |
      | user_group_id |
      | image         |
      | extra_data    |
      | status        |
      | is_admin      |
      | start_date    |
      | end_date      |
      | address       |

