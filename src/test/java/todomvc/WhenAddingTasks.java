package todomvc;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.*;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenAddingTasks {
    @Managed(driver= "chrome", uniqueSession = true)
    WebDriver driver;

    @Steps
    TodoListActions todoList;

    @BeforeEach
    public void openApplication(){
        todoList.openApplication();
    }

    @AfterEach
    public void closeBrowser(){
        // JS command to clear HTMl5 browser storage
        todoList.clearList();
    }

    //Nested annotation, Junit5 use of nested test class, will use same browser session/context
    @Nested
    class ToAnEmptyList {
        // TODO: Exercise 1
        @DisplayName("Adding a single task")
        @Test
        void addingASingleTask() {
            // Add "Feed The Cat" to the list
            todoList.addItem("Feed the cat");
            // Check that "Feed The Cat" appears in the list
            Assertions.assertThat(todoList.items().contains("Feed the cat"));
        }

        // TODO: Exercise 2
        @Test
        void addingMultipleTasks() {
            // Add "Feed The Cat" and "Walk the dog" to the list
            todoList.addItems("Feed the cat", "Walk the dog");
            // Check that they all appear in the list
            Assertions.assertThat(todoList.items()).containsExactly("Feed the cat", "Walk the dog");
        }
    }

    //Nested annotation, Junit5 use of nested test class, will use same browser session/context
    @Nested
    class ToAListWithExistingEntries {
        @BeforeEach
        void aListWithSomeEntriesAdded() {
            todoList.addItems("Feed the Cat", "Walk the Dog");
        }

        @Test
        void theNewItemShouldBeAddedToTheEndOfTheList() {
            todoList.addItems("Fleece the Sheep");
            Serenity.reportThat("The new item should be at the end",
                    () -> Assertions.assertThat(todoList.items()).containsExactly("Feed the Cat", "Walk the Dog", "Fleece the Sheep")
            );
        }
    }

}
