Feature: Book Category

  @wip
  Scenario: verify book categories with DB
    Given the user logged in as "librarian"
    When the user navigates to "Books" page
    And the user gets all book categories in webpage
    Then verify book categories must match book categories table from db