package example.staveware.stvwebstore.passwordinfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import example.staveware.stvwebstore.StvSimpleAppApplicationTests;

/**
 * パスワードポリシ自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfPasswordPolicyControllerTests
        extends StvSimpleAppApplicationTests {

    public static final String PATH_URL = "passwordPolicy";

    /**
     * 登録、検索、更新、削除をテストする。（標準テスト）
     */
    @Test
    public void testNormal() {
        gotoRegistPage("passwordPolicy-regist");
        regist();
        search();
        update();
        delete();
    }

    /**
     * 登録
     */
    private void regist() {
        registInputcheck();
        registClear();
        registCancel();
        registSuccess();
        registSameCode();
    }

    /**
     * 検索
     */
    private void search() {
        search0record();
        searchSuccess();
        searchClear();
    }

    /**
     * 更新
     */
    private void update() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        updateInputcheck();
        updateReset();
        updateCancel();
        updateSuccess();
    }

    /**
     * 削除
     */
    private void delete() {
        deleteCancel();
        deleteSuccess();
    }

    /**
     * 登録入力チェック
     */
    private void registInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 必須が空文字
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).clear();
        driver.findElement(By.id("registPasswordPolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,null);
        pressButton("OK");
        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("registPasswordPolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,null);
        pressButton("OK");

        // 複数エラー発生時
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("registPasswordPolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,null);
        pressButton("OK");
    }

    /**
     * 登録画面クリア
     */
    private void registClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).clear();
        driver.findElement(By.id("passwordPolicyCode")).sendKeys("test1");
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyCode")).sendKeys("テスト①");
        driver.findElement(By.id("activeEndTime")).click();
        driver.findElement(By.id("activeEndTime")).clear();
        driver.findElement(By.id("activeEndTime")).sendKeys("2018/03/08 16:52:45");
        driver.findElement(By.id("passwordMinLength")).click();
        driver.findElement(By.id("passwordMinLength")).clear();
        driver.findElement(By.id("passwordMinLength")).sendKeys("1");
        driver.findElement(By.id("passwordLetterType")).click();
        driver.findElement(By.id("passwordLetterType")).clear();
        driver.findElement(By.id("passwordLetterType")).sendKeys("3");
        driver.findElement(By.id("passwordMinLetterType")).click();
        driver.findElement(By.id("passwordMinLetterType")).clear();
        driver.findElement(By.id("passwordMinLetterType")).sendKeys("3");
        driver.findElement(By.id("passwordInHistory")).click();
        driver.findElement(By.id("passwordInHistory")).clear();
        driver.findElement(By.id("passwordInHistory")).sendKeys("4");

        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),null,
                null);
        Assert.assertEquals("", getInputTextValue("passwordPolicyCode"));
        Assert.assertEquals("", getInputTextValue("companyCode"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
        Assert.assertEquals("", getInputTextValue("passwordMinLength"));
        Assert.assertEquals("", getInputTextValue("passwordLetterType"));
        Assert.assertEquals("", getInputTextValue("passwordMinLetterType"));
        Assert.assertEquals("", getInputTextValue("passwordInHistory"));

    }

    /**
     * 登録キャンセル
     */
    private void registCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).clear();
        driver.findElement(By.id("passwordPolicyCode")).sendKeys("test1");
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyCode")).sendKeys("テスト①");
        driver.findElement(By.id("activeEndTime")).click();
        driver.findElement(By.id("activeEndTime")).clear();
        driver.findElement(By.id("activeEndTime")).sendKeys("2018/03/08 16:52:45");
        driver.findElement(By.id("passwordMinLength")).click();
        driver.findElement(By.id("passwordMinLength")).clear();
        driver.findElement(By.id("passwordMinLength")).sendKeys("1");
        driver.findElement(By.id("passwordLetterType")).click();
        driver.findElement(By.id("passwordLetterType")).clear();
        driver.findElement(By.id("passwordLetterType")).sendKeys("2");
        driver.findElement(By.id("passwordMinLetterType")).click();
        driver.findElement(By.id("passwordMinLetterType")).clear();
        driver.findElement(By.id("passwordMinLetterType")).sendKeys("3");
        driver.findElement(By.id("passwordInHistory")).click();
        driver.findElement(By.id("passwordInHistory")).clear();
        driver.findElement(By.id("passwordInHistory")).sendKeys("4");

        driver.findElement(By.id("registPasswordPolicy")).click();
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("test1", getInputTextValue("passwordPolicyCode"));
        Assert.assertEquals("テスト①", getInputTextValue("companyCode"));
        Assert.assertEquals("2018/03/08 16:52:45", getInputTextValue("activeEndTime"));
        Assert.assertEquals("1", getInputTextValue("passwordMinLength"));
        Assert.assertEquals("2", getInputTextValue("passwordLetterType"));
        Assert.assertEquals("3", getInputTextValue("passwordMinLetterType"));
        Assert.assertEquals("4", getInputTextValue("passwordInHistory"));

    }

    /**
     * 登録成功
     */
    public void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).clear();
        driver.findElement(By.id("passwordPolicyCode")).sendKeys("test1");
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyCode")).sendKeys("テスト①");
        driver.findElement(By.id("activeEndTime")).click();
        driver.findElement(By.id("activeEndTime")).clear();
        driver.findElement(By.id("activeEndTime")).sendKeys("2118/03/08 16:52:45");
        driver.findElement(By.id("passwordMinLength")).click();
        driver.findElement(By.id("passwordMinLength")).clear();
        driver.findElement(By.id("passwordMinLength")).sendKeys("1");
        driver.findElement(By.id("passwordLetterType")).click();
        driver.findElement(By.id("passwordLetterType")).clear();
        driver.findElement(By.id("passwordLetterType")).sendKeys("2");
        driver.findElement(By.id("passwordMinLetterType")).click();
        driver.findElement(By.id("passwordMinLetterType")).clear();
        driver.findElement(By.id("passwordMinLetterType")).sendKeys("3");
        driver.findElement(By.id("passwordInHistory")).click();
        driver.findElement(By.id("passwordInHistory")).clear();
        driver.findElement(By.id("passwordInHistory")).sendKeys("4");
        driver.findElement(By.id("registPasswordPolicy")).click();
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("登録が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        Assert.assertEquals("パスワードポリシ詳細", getHeaderTitle());
    }

    /**
     * 登録失敗
     */
    private void registSameCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("パスワードポリシ登録");
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).clear();
        driver.findElement(By.id("passwordPolicyCode")).sendKeys("test1");
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyCode")).sendKeys("テスト①");
        driver.findElement(By.id("activeEndTime")).click();
        driver.findElement(By.id("activeEndTime")).clear();
        driver.findElement(By.id("activeEndTime"))
                .sendKeys("2118/03/08 16:52:45");
        driver.findElement(By.id("passwordMinLength")).click();
        driver.findElement(By.id("passwordMinLength")).clear();
        driver.findElement(By.id("passwordMinLength")).sendKeys("1");
        driver.findElement(By.id("passwordLetterType")).click();
        driver.findElement(By.id("passwordLetterType")).clear();
        driver.findElement(By.id("passwordLetterType")).sendKeys("2");
        driver.findElement(By.id("passwordMinLetterType")).click();
        driver.findElement(By.id("passwordMinLetterType")).clear();
        driver.findElement(By.id("passwordMinLetterType")).sendKeys("3");
        driver.findElement(By.id("passwordInHistory")).click();
        driver.findElement(By.id("passwordInHistory")).clear();
        driver.findElement(By.id("passwordInHistory")).sendKeys("4");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("registPasswordPolicy")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();

    }

    /**
     * 検索条件クリア
     */
    private void searchClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("", getInputTextValue("passwordPolicyCode"));
    }

    /**
     * 検索結果 があり
     */
    private void searchSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("パスワードポリシ検索");
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).clear();
        driver.findElement(By.id("passwordPolicyCode")).sendKeys("test1");
        driver.findElement(By.id("searchRole")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("test1",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr[1]/td[2]/div"))
                        .getText());

    }

    /**
     * 検索結果 ゼロ件
     */
    private void search0record() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("パスワードポリシ検索");
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).clear();
        driver.findElement(By.id("passwordPolicyCode")).sendKeys("xxx");
        driver.findElement(By.id("searchRole")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("データなし",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]"))
                        .getText());

    }

    /**
     * 更新入力チェック
     */
    private void updateInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");

        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("passwordMinLength")).click();
        driver.findElement(By.id("passwordMinLength")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("updatePasswordPolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\nFailed to processing of server.",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,null);
        pressButton("OK");

        // 複数エラー発生時
        driver.findElement(By.id("passwordLetterType")).click();
        driver.findElement(By.id("passwordLetterType")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("updatePasswordPolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\nFailed to processing of server.",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,2);
        pressButton("OK");
    }

    /**
     * 更新キャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * 更新リセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("passwordMinLength")).click();
        driver.findElement(By.id("passwordMinLength")).clear();
        driver.findElement(By.id("passwordMinLength")).sendKeys("31");
        pressButton("リセット");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("1", getInputTextValue("passwordMinLength"));
    }

    /**
     * 更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");
        driver.findElement(By.id("passwordMinLength")).click();
        driver.findElement(By.id("passwordMinLength")).clear();
        driver.findElement(By.id("passwordMinLength")).sendKeys("52");
        driver.findElement(By.id("updatePasswordPolicy")).click();// 更新button
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        pressButton("OK");
    }

    /**
     * 削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("パスワードポリシ検索");
        driver.findElement(By.id("passwordPolicyCode")).click();
        driver.findElement(By.id("passwordPolicyCode")).clear();
        driver.findElement(By.id("passwordPolicyCode")).sendKeys("test1");
        driver.findElement(By.id("searchRole")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td/div/label/span/span"))
                .click();
        pressButton("削除");
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("test1",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr[1]/td[2]/div"))
                        .getText());
    }

    /**
     * 削除成功
     */
    private void deleteSuccess() {
        pressButton("削除");
        pressButton("OK");
        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        pressButton("OK");
    }

}
