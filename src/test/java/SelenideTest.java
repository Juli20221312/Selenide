import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SelenideTest {
    @Test
    public void test1() {

        open("http://localhost:9999/");
        $("span[data-test-id=city] input").setValue("Москва");

        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);

        $("span[data-test-id=name] input").setValue("Иванов Иван");
        $("span[data-test-id=phone] input").setValue("+79138565456");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate),
                        Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}