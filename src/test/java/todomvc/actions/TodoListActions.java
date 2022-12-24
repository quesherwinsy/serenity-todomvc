package todomvc.actions;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import static todomvc.actions.TodoListForm.*;

public class TodoListActions extends UIInteractionSteps {
    @Step("Add item {0}")
    public void addItem(String item) {
        $(".new-todo").typeAndEnter(item);
        // $(NEW_TODO_FIELD).typeAndEnter(item);
    }

    @Step("Get todo list")
    public List<String> items() {
        return $$(ITEM_LABELS).texts();
    }

    @Step("Open TodoMVC web application")
    public void openApplication() {
        // https://todomvc.com/examples/angularjs/#/
        // openPageNamed() - open URL based on serenity.conf file environment/s and pages{}.
        openPageNamed("home");
    }

    @Step("Add itemS {0}")
    public void addItems(List<String> items) {
        // Accept List of string arguments, method override
        items.forEach(this::addItem);
    }
    @Step("Add itemS {0}")
    public void addItems(String... items) {
        // Accept stringS argument, , method override
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

    @Step("Delete item {0}")
    public void deleteItem(String item) {
        // need to click to show delete icon element
        $(ITEM_LABEL, item).click();
        // traverse from parent to sibling element, label going to button and click.
        $(DELETE_ICON, item).click();
    }

    public int itemLeftCount() {
        return 2;
    }

    public void clearList() {
        // JS command to clear HTMl5 browser storage
        ((JavascriptExecutor)getDriver()).executeScript("localStorage.clear()");
    }
}
