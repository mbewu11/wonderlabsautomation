import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BaseTest;
import pages.PracticePage;
import utils.MyScreenRecorder;

public class PractiseTest {
    @Test()
    public  void PractiseTest() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        MyScreenRecorder.startRecording("setupBrowser");
        WebDriver driver = BaseTest.setupBrowser();
        PracticePage practicePage = new PracticePage(driver);

        practicePage.clickButton(practicePage.radioButton3);
        practicePage.clickButton(practicePage.radioButton2);

        practicePage.suggestionInteraction("South","South Africa","Republic");
        practicePage.checkboxInteractions();
        practicePage.showAndHideInteraction();

        String amount = practicePage.getCellValue( "Joe", "Postman", "Chennai", "Amount");
        softAssert.assertEquals(amount,"46");
        softAssert.assertEquals(practicePage.totalAmountCollected.getText(),"Total Amount Collected: 296");

        String sumOfRows = String.valueOf(practicePage.getSumOfAllRows());
        softAssert.assertEquals(sumOfRows,"296");
        softAssert.assertAll();
        practicePage.switchToIframe();
        MyScreenRecorder.stopRecording();
    }
}
