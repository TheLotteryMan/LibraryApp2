package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class BooksStepsDefs {

    BookPage bookPage = new BookPage();
    LoginPage loginPage = new LoginPage();
    List<String> actualCategoryList;

    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
        BrowserUtil.waitFor(2);

    }
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);
    }
    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        actualCategoryList=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
        System.out.println("actualCategoryList = " + actualCategoryList);
    }
    @Then("verify book categories must match book categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("select name from book_categories");
        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(expectedCategoryList,actualCategoryList);
    }
}
