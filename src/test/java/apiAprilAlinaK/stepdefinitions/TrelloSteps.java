package apiAprilAlinaK.stepdefinitions;

import apiAprilAlinaK.domain.Board;
import apiAprilAlinaK.domain.List;
import apiAprilAlinaK.helpers.TestCaseContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

// import static apiAprilAlinaK.clients.TrelloClient.*;
import static apiAprilAlinaK.clients.TrelloClient.getBoardInfo;
import static apiAprilAlinaK.clients.TrelloClient.updateBoardInfo;
import static apiAprilAlinaK.clients.TrelloClient.createList;
import static apiAprilAlinaK.constants.ProjectConstants.BOARD_ID;
import static apiAprilAlinaK.constants.ProjectConstants.BOARD_NAME;

public class TrelloSteps {

    Board board;

    // Mac - option + enter
    // Win - alt + enter
    @Given("The board exists and contains the correct information")
    public void getBoardDataAndCheckInfo(){
        Response response = getBoardInfo(BOARD_ID);
        Board board = response.as(Board.class);

        Assertions.assertThat(board.getId())
                .as("We assert that the board ID is correct")
                .isEqualTo(BOARD_ID);

        Assertions.assertThat(board.getName())
                .as("We assert that the board Name is correct")
                .isEqualTo(BOARD_NAME);
//        System.out.println(board.getId());
//        System.out.println(board.getName());
//        System.out.println("The 1st step was executed");
    }

    @When("I change the board title to {string}")
    public void iChangeTheBoardTitleTo(String title) {
        Response response = updateBoardInfo(title, BOARD_ID);
        Board board = response.as(Board.class);
        TestCaseContext.setBoard(board);
//        System.out.println("The 2nd step was executed");
    }

    @And("I check that the board name was updated to {string}")
    public void iCheckThatTheBoardNameWasUpdatedTo(String title) {
        Assertions.assertThat(TestCaseContext.getBoard().getName())
                .as("We check that the board name was updated")
                .isEqualTo(title);
//        System.out.println("The 3rd step was executed");
    }

    @Then("I add a list with a name {string} to the board")
    public void iAddAListWithANameToTheBoard(String listName) {
        Response response = createList(listName, TestCaseContext.getBoard().getId());
        List list = response.as(List.class);
        TestCaseContext.setList(list);

        Assertions.assertThat(list.getName())
                .as("We check that the list was created with correct name")
                .isEqualTo(listName);

        TestCaseContext.getScenario().log(list.getId());
//        System.out.println("The 4th step was executed");
    }
}
