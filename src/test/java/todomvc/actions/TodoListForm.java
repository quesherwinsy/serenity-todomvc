package todomvc.actions;

import org.openqa.selenium.By;

class TodoListForm {
    // somewhat a POM class design
    static final By NEW_TODO_FIELD = By.className(".new-todo ");
    static final String ITEM_LABELS = ".todo-list label";
    static final String COMPLETE_CHECKBOX = "//label[.='{0}']/preceding-sibling::input[@type='checkbox']";
    static final String FILTER_BUTTON = "//ul[@class='filters']//a[.='{0}']";
}
