package todomvc.actions;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import java.util.List;
import static todomvc.actions.TodoListForm.*;

public class TodoListActions extends UIInteractionSteps {
    @Step("Add item {0}")
    public void addItem(String item) {
        $(".new-todo").typeAndEnter(item);
    }

    @Step("Get todo list")
    public List<String> items() {
        return $$(ITEM_LABELS).texts();
    }

    @Step("Open TodoMVC web application")
    public void openApplication() {
        // https://todomvc.com/examples/angularjs/#/
        openUrl("https://todomvc.com/examples/angularjs/#/");
    }

    @Step("Add itemS {0}")
    public void addItems(String... items) {
        for(String item: items){
            addItem(item);
        }
    }

    @Step("Complete item {0}")
    public void completeItem(String item) {
        // traverse back to sibling web element checkbox and click
        $(COMPLETE_CHECKBOX, item).click();
    }

    @Step("Filter item by {0}")
    public void filterBy(String filterName) {
        // from list UL element traverse inside to link element and click
        $(FILTER_BUTTON, filterName).click();
    }
}
