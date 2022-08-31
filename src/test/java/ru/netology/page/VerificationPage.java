package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify] .button__text");


    public VerificationPage() {
        codeField.shouldBe(visible);
        Duration.ofSeconds(15);
    }

    public VerificationPage validVerify(Object verificationCode) {
        codeField.setValue((String) verificationCode);
        verifyButton.click();
        return new VerificationPage();
    }
}
