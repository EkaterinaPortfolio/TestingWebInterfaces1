package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class DebitCardApplicationTest {
    @Test
    void successfullyCompletedFormOnlyName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Оксана");
        $("[data-test-id=phone] input").setValue("+79043966554");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void successfullyCompletedFormNameSurname() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Шишкина Ирина");
        $("[data-test-id=phone] input").setValue("+79043933554");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void successfullyCompletedFormNameThroughDash() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Жан-Поль");
        $("[data-test-id=phone] input").setValue("+79043933454");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void notSuccessfullyCompletedFormNameLatin() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Maria");
        $("[data-test-id=phone] input").setValue("+79043933454");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void notSuccessfullyCompletedFormNameCyrillicWithSymbol() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ирина@");
        $("[data-test-id=phone] input").setValue("+79043933454");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void notSuccessfullyCompletedFormFieldNameEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79043933454");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void notSuccessfullyCompletedFormShortPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ирина");
        $("[data-test-id=phone] input").setValue("+7904393454");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void notSuccessfullyCompletedFormLongerPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ирина");
        $("[data-test-id=phone] input").setValue("+790439634754");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void notSuccessfullyCompletedFormPhoneNumberWithEight() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ирина");
        $("[data-test-id=phone] input").setValue("890439634754");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void notSuccessfullyCompletedFormFieldPhoneEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ольга");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void notSuccessfullyCompletedFormPhoneNumberFilledWithLetters() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ирина");
        $("[data-test-id=phone] input").setValue("+vf9pfgjtjr");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void SuccessfullyCompletedFormWithoutACheckpoint() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ирина");
        $("[data-test-id=phone] input").setValue("+79067564798");
        $("button.button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
