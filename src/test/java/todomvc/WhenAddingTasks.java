package todomvc;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        driver.close();
    }

    // TODO: Exercise 1
    @Test
    public void addingASingleTask() {
        // Add "Feed The Cat" to the list
        todoList.addItem("Feed the cat");
        // Check that "Feed The Cat" appears in the list
        Assertions.assertThat(todoList.items().contains("Feed the cat"));
    }

    // TODO: Exercise 2
    @Test
    public void addingMultipleTasks() {
        // Add "Feed The Cat" and "Walk the dog" to the list
        todoList.addItems("Feed the cat", "Feed the dog");
        // Check that they all appear in the list
        Assertions.assertThat(todoList.items()).containsExactly("Feed the cat", "Walk the dog");
    }

}
