package apiAprilAlinaK.helpers;

import apiAprilAlinaK.domain.Board;
import apiAprilAlinaK.domain.List;
import io.cucumber.java.Scenario;
import lombok.Getter;
import lombok.Setter;

public class TestCaseContext {
    @Setter @Getter
    private static Board board;
    @Setter @Getter
    private static List list;
    @Setter @Getter
    private static Scenario scenario;

    public static void init(){
        board = null;
        list = null;
        scenario = null;
    }
}
