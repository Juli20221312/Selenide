import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class SelenideTest {
    @Test
    public void test1() {
        open("http://localhost:9999/");
        $("span[data-test-id=city] input").setValue("Москва");

        SelenideElement dateInput = $("span[data-test-id=date] input");
        if (!dateInput.getValue().isEmpty()) {
            dateInput.clear();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(calendar.getTime());

        $("span[data-test-id=name] input").setValue("Иванов Иван");
        $("span[data-test-id=phone] input").setValue("+79138565456");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
    }


}