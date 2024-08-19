package ru.maximkazakov.allurereport;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открываем главную страницу") // aspectjWeaver.set(true) это в build.gradle
    public void openMainPage(){
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repo}")// aspectjWeaver.set(true) это в build.gradle
    public void searchForRepository(String repo){
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")// aspectjWeaver.set(true) это в build.gradle
    public void clickOpenRepositoryLink(String repo){
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues ")// aspectjWeaver.set(true) это в build.gradle
    public void openIssuesTab(){
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issue}")// aspectjWeaver.set(true) это в build.gradle
    public void shouldSeeIssuesWithNumber(int issue){
        $(withText("#" + issue)).should(Condition.exist);
    }


    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
