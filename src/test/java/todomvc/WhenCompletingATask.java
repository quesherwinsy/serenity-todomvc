package todomvc;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenCompletingATask {
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
        driver.close();
    }

    // TODO: Exercise 3
    @Test
    public void activeTasksShouldNotShowCompletedTasks() {
        // Add "Feed the cat" and "Walk the dog" to the list
        todoList.addItems("Feed the cat", "Walk the dog");
        // Complete "Feed the cat"
        todoList.completeItem("Feed the cat");
        // Filter by "Active"
        todoList.filterBy("Active");
        // Check that only "Walk the dog" appears
        Assertions.assertThat(todoList.items()).containsExactly("Walk the dog");
    }

    // TODO: Exercise 4
    @Test
    public void completedTasksShouldNotShowActiveTasks() {
        // Add "Feed the cat" and "Walk the dog" to the list
        todoList.addItems("Feed the cat", "Walk the dog");
        // Complete "Feed the cat"
        todoList.completeItem("Feed the cat");
        // Filter by "Completed"
        todoList.filterBy("Completed");
        // Check that only "Feed the cat" appears
        Assertions.assertThat(todoList.items()).containsExactly("Feed the cat");
    }
}
