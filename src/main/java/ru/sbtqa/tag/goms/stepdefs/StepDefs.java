package ru.sbtqa.tag.goms.stepdefs;

import cucumber.api.java.ru.Допустим;

public class StepDefs {

    @Допустим("^пользователь находится на странице \"([^\"]*)\"$")
    public void userIsOnPage(String page) {
    }

    @Допустим("^пользователь нажимает клавишу \"([^\"]*)\"$")
    public void userClickButton(String element) {
    }

    @Допустим("^пользователь нажимает кнопку \"([^\"]*)\"$")
    public void userPressKey(String keyName) {
    }

    @Допустим("^пользователь заполняет поле \"([^\"]*)\" \"([^\"]*)\"$")
    public void userFillField(String element, String text) {
    }

    @Допустим("^пользователь отмечает признак \"([^\"]*)\"$")
    public void userCheck(String element) {
    }

    @Допустим("^пользователь выбирает \"([^\"]*)\" \"([^\"]*)\"$")
    public void userSelect(String element, String option) {
    }

    @Допустим("^пользователь выбирает без отклика системы \"([^\"]*)\" \"([^\"]*)\"$")
    public void userSelectWithoutWait(String element, String option) {
    }

    @Допустим("^пользователь прикладывает таблетку$")
    public void userApplyKey() {
    }

    @Допустим("^пользователь сканирует штрихкод$")
    public void userScan() {
    }

    @Допустим("^в фокусе находится элемент \"([^\"]*)\"$")
    public void elementIsInFocus(String element) {
    }

    @Допустим("^пользователь скролит экран$")
    public void userScroll() {
    }

    @Допустим("^пользователь перетаскивает элемент \"([^\"]*)\"$")
    public void userDragNDrop(String element) {
    }
}
