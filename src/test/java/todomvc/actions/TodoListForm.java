package todomvc.actions;
import org.openqa.selenium.By;

class TodoListForm {
    // somewhat a POM class design
    static final By NEW_TODO_FIELD = By.className(".new-todo");
    static final String ITEM_LABELS = ".todo-list label";
    // traverse back to sibling web element checkbox and click
    static final String COMPLETE_CHECKBOX = "//label[.='{0}']/preceding-sibling::input[@type='checkbox']";
    // from list UL element traverse inside to link element and click
    static final String FILTER_BUTTON = "//ul[@class='filters']//a[.='{0}']";
    // need to click to show delete icon element
    static final String ITEM_LABEL = "//label[.='{0}']";
    // traverse from parent to sibling element, label going to button and click.
    static final String DELETE_ICON = "//label[.='{0}']//following-sibling::button[@class='destroy']";
}
