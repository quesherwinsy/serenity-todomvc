package todomvc;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SerenityJUnit5Extension.class)
@Concurrent
public class WhenFilteringTasks {
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

    //Junit5 parameterized test, using static method to get dynamic test data/arguments.
    @ParameterizedTest
    @MethodSource("StringAndListProvider")
    void shouldDisplayCorrectlyFilteredItems(String filterBy, List<String> todoItems, String itemToComplete, List<String> filteredItems) {
        todoList.openApplication();
        todoList.addItems(todoItems);
        todoList.completeItem(itemToComplete);
        todoList.filterBy(filterBy);
        Assertions.assertThat(todoList.items()).containsExactlyElementsOf(filteredItems);
    }

    static Stream<Arguments> StringAndListProvider() {
        return Stream.of(
                Arguments.of("Active", Arrays.asList("Feed the cat", "Walk the dog"), "Feed the cat", Arrays.asList("Walk the dog")),
                Arguments.of("Completed", Arrays.asList("Feed the cat", "Walk the dog"), "Feed the cat", Arrays.asList("Feed the cat")),
                Arguments.of("All", Arrays.asList("Feed the cat", "Walk the dog"), "Feed the cat", Arrays.asList("Feed the cat", "Walk the dog"))
        );
    }

    public static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.of("apple", 1, Arrays.asList("a", "b")),
                Arguments.of("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }
}
