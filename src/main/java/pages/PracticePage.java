package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PracticePage extends BaseSeleniumPage{
    // Radio buttons
    @FindBy(xpath = "//input[@value='radio3']")
    public WebElement radioButton3;
    @FindBy(xpath = "//input[@value='radio2']")
    public WebElement radioButton2;

    // Suggestion
    @FindBy(id = "autocomplete")
    public WebElement suggestionField;
    @FindBy(xpath = "//ul[@id='ui-id-1' and contains(@class, 'ui-menu') and contains(@class, 'ui-widget') and contains(@class, 'ui-widget-content') and contains(@class, 'ui-autocomplete') and contains(@class, 'ui-front')]")
    public WebElement autocomplete;
    // Show / Hide
    @FindBy(id = "hide-textbox")
    public WebElement hideButton;
    @FindBy(id = "show-textbox")
    public WebElement showButton;

    @FindBy(className = "totalAmount")
    public WebElement totalAmountCollected;

    public PracticePage(WebDriver driver) {
        super(driver);
    }
    public void clickButton(WebElement button){
        button.click();
        validateOnlyOneRadioButtonChecked();
    }
    public void suggestionInteraction(String firstValue,String option, String state){
        setValue(suggestionField,firstValue);
        waitUntilVisible(autocomplete);
        WebElement optionSouthAfrica = driver.findElement(By.xpath("//div[contains(@id, 'ui-id-') and contains(text(), '" + option +"')]"));
        optionSouthAfrica.click();
        suggestionField.clear();
        setValue(suggestionField,state);
        waitUntilVisible(autocomplete);
        WebElement firstOption = driver.findElement(By.xpath("//li[contains(@class, 'ui-menu-item')][1]"));
        firstOption.click();
    }

    public void checkboxInteractions(){
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
        validateAllCheckboxesChecked();
        checkboxes.get(0).click();
        validateOtherCheckboxesChecked();
    }

    public void showAndHideInteraction(){
        hideButton.click();
        validateElementHidden("displayed-text");

        showButton.click();
        validateElementVisible("displayed-text");
    }
    public void validateOnlyOneRadioButtonChecked() {
        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
        int checkedCount = 0;
        for (WebElement radioButton : radioButtons) {
            if (radioButton.isSelected()) {
                checkedCount++;
            }
        }
        if (checkedCount == 1) {
            System.out.println("Validation Passed: Only one radio button is checked");
        } else {
            System.out.println("Validation Failed: More than one radio button is checked");
        }
    }

    private void validateAllCheckboxesChecked() {
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                System.out.println("Validation Failed: Checkbox is not checked");
                return;
            }
        }
        System.out.println("Validation Passed: All checkboxes are checked");
    }

    private void validateOtherCheckboxesChecked() {
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        if (!checkboxes.get(0).isSelected() && checkboxes.get(1).isSelected() && checkboxes.get(2).isSelected()) {
            System.out.println("Validation Passed: Other checkboxes remain checked");
        } else {
            System.out.println("Validation Failed: Other checkboxes are not checked");
        }
    }

    private void validateElementHidden(String elementId) {
        WebElement element = driver.findElement(By.id(elementId));
        if (!element.isDisplayed()) {
            System.out.println("Validation Passed: Element is hidden");
        } else {
            System.out.println("Validation Failed: Element is visible");
        }
    }

    private void validateElementVisible(String elementId) {
        WebElement element = driver.findElement(By.id(elementId));
        if (element.isDisplayed()) {
            System.out.println("Validation Passed: Element is visible");
        } else {
            System.out.println("Validation Failed: Element is hidden");
        }
    }

    public static String getCellValue(String name, String position, String city, String columnName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='product']/tbody/tr"));
        for (WebElement row : rows) {
            String rowName = row.findElement(By.xpath("//td[normalize-space()='" + name +"']")).getText();
            String rowPosition = row.findElement(By.xpath("//td[normalize-space()='" + position +"']")).getText();
            String rowCity = row.findElement(By.xpath("//td[normalize-space()='" + city +"']")).getText();
            if (rowName.equals(name) && rowPosition.equals(position) && rowCity.equals(city)) {
                return row.findElement(By.xpath("//td[normalize-space()='" + name + "']/following-sibling::td[normalize-space()='" + position + "']/following-sibling::td[normalize-space()='" + city + "']/following-sibling::td")).getText();
            }
        }
        return "";
    }

    public static int getSumOfAllRows() {
        int sum = 0;
        List<WebElement> rows = driver.findElements(By.xpath("//div[contains(@class, 'tableFixHead')]//table[@id='product']/tbody/tr"));
        for (WebElement row : rows) {
            String amount = row.findElement(By.xpath(".//td[4]")).getText();
            sum += Integer.parseInt(amount);
        }
        return sum;
    }

    public static void switchToIframe(){
        // iFrame
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        WebElement element = driver.findElement(By.linkText("Practice"));
        element.click();

        // Switch back to the main page
        driver.switchTo().defaultContent();
        driver.quit();
    }
}
