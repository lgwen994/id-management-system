package example.staveware.stvwebstore.logininfo;

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
 * ユーザ_ログイン情報自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfUserLoginInfoControllerTests
        extends StvSimpleAppApplicationTests {

    public static final String PATH_URL = "userLoginInfo";

    /**
     * 登録、検索、更新、削除をテストする。（標準テスト）
     */
    @Test
    public void testNormal() {
        gotoRegistPage("userLoginInfo-regist");
        regist();
        search();
        update();
        updatePsaaword();
        updateLoginControlInfo();
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
        searchClear();
        searchSuccess();
    }

    /**
     * 更新
     */
    private void update() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        updateReset();
        updateCancel();
        updateSuccess();
    }

    /**
     * パスワード更新
     */
    private void updatePsaaword() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("//*[@id='passwordUpdateForm']/div[5]/div/div/button/span"))
                .click();
        updatePasswordReset();
        updatePsaawordCancel();
        updatePsaawordSuccess();
    }

    /**
     * ログイン制御情報更新
     */
    private void updateLoginControlInfo() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("//*[@id='loginControlInfoUpdateForm']/div[9]/div/div/button/span"))
                .click();
        updateLoginControlInfoReset();
        updateLoginControlInfoCancel();
        updateLoginControlInfoSuccess();
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
        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).clear();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("registUserLoginInfo")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);

        // 入力エラー（大きすぎる）時 「項目：ユーザ_ログイン情報コード」
        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                1);
        driver.findElement(By.id("registUserLoginInfo")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                4);

        // 入力エラー（大きすぎる）時 「項目：ログインID」
        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys("neusoft");
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.id("loginId")).clear();
        driver.findElement(By.id("loginId")).sendKeys(
                "012345678901234567890123456789012345678901234567891");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                1);
        driver.findElement(By.id("registUserLoginInfo")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                4);

        // 複数エラー発生時
        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.id("loginId")).clear();
        driver.findElement(By.id("loginId")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 4,
                1);
        driver.findElement(By.id("registUserLoginInfo")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 4,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 4,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 4,
                4);
    }

    /**
     * 登録画面クリア
     */
    private void registClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("loginId")).clear();
        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys("test1");

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoRegistForm']/div[2]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ログインID
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.id("loginId")).sendKeys("loginId");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 会社マスタ設定設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoRegistForm']/div[4]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div[3]/table/tbody/tr/td[1]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用開始日設定
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);

        Assert.assertEquals("", getInputTextValue("userLoginInfoCode"));
        Assert.assertEquals("", getInputTextValue("user"));
        Assert.assertEquals("", getInputTextValue("loginId"));
        Assert.assertEquals("", getInputTextValue("company"));
        Assert.assertEquals("", getInputTextValue("activeStartTime"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
    }

    /**
     * 登録キャンセル
     */
    private void registCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys("test①");

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoRegistForm']/div[2]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ログインID
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.id("loginId")).sendKeys("admin01");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 会社マスタ設定設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoRegistForm']/div[4]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div[3]/table/tbody/tr/td[1]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用開始日設定
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        driver.findElement(By.id("registUserLoginInfo")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        Assert.assertEquals("test①", getInputTextValue("userLoginInfoCode"));
    }

    /**
     * 登録成功
     */
    private void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("loginId")).clear();
        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys("test①");

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoRegistForm']/div[2]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ログインID
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.id("loginId")).sendKeys("admin01");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 会社マスタ設定設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoRegistForm']/div[4]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div[3]/table/tbody/tr/td[1]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用開始日設定
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        driver.findElement(By.id("registUserLoginInfo")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        sleep(DEFAULT_WAIT_TIME_2000L);

        Assert.assertEquals("登録が完了しました。", getAlertMessage());

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);

        Assert.assertEquals("ユーザ_ログイン情報詳細", getHeaderTitle());
    }

    /**
     * 登録失敗（同じの役職コードが登録される場合）
     */
    private void registSameCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("ユーザ_ログイン情報登録");
        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("loginId")).clear();
        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys("test①");

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoRegistForm']/div[2]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ログインID
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.id("loginId")).sendKeys("admin01");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 会社マスタ設定設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoRegistForm']/div[4]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div[3]/table/tbody/tr/td[1]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        driver.findElement(By.id("registUserLoginInfo")).click();
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        sleep(DEFAULT_WAIT_TIME_2000L);

        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
    }

    /**
     * 検索結果 ゼロ件
     */
    private void search0record() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("ユーザ_ログイン情報検索");
        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("loginId")).clear();

        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys("xxx");

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoSearchForm']/div[2]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ログインID
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.id("loginId")).sendKeys("admin01");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 会社マスタ設定設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoSearchForm']/div[4]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[1]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div[3]/table/tbody/tr/td[1]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用開始日設定
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .click();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .clear();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .sendKeys("00:00:00");
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("searchUserLoginInfo")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertEquals("データなし",
                driver.findElement(
                        By.xpath("//div[@class='el-table__empty-block']"))
                        .getText());

    }

    /**
     * 検索条件クリア
     */
    private void searchClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertEquals("", getInputTextValue("userLoginInfoCode"));
        Assert.assertEquals("", getInputTextValue("loginId"));
        Assert.assertEquals("", getInputTextValue("user"));
        Assert.assertEquals("", getInputTextValue("activeStartTime"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
        Assert.assertEquals("", getInputTextValue("company"));
    }

    /**
     * 検索結果 があり
     */
    private void searchSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("loginId")).clear();

        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys("test①");

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoSearchForm']/div[2]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ログインID
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.id("loginId")).sendKeys("admin01");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 会社マスタ設定設定
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoSearchForm']/div[4]/div/div[2]/button/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[1]/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div[3]/table/tbody/tr/td[1]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用開始日設定
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .click();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .clear();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .sendKeys("00:00:00");
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("searchUserLoginInfo")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertEquals("test①",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"))
                        .getText());

        Assert.assertEquals("I_USER_01",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[3]/div"))
                        .getText());
        Assert.assertEquals("admin01",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[4]/div"))
                        .getText());
        Assert.assertEquals("Toshiba",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[5]/div"))
                        .getText());

    }

    /**
     * 更新リセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoUpdateForm']/div[14]/div/div/button/span"))
                .click();
        // 適用開始日設定
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .click();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .clear();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .sendKeys("00:00:00");
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoUpdateForm']/div[14]/div/div/button[2]/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertNotEquals("2018/03/10 15:29:41",
                getInputTextValue("activeStartTime"));
    }

    /**
     * 更新キャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用開始日設定
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .click();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .clear();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .sendKeys("00:00:00");
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoUpdateForm']/div[14]/div/div/button[1]/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * 更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//*[@id='userLoginInfoUpdateForm']/div[14]/div/div/button/span"))
                .click();
        // 適用開始日設定
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .click();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .clear();
        driver.findElement(By
                .xpath("(//div[@class='el-input el-input--small'])[last()]//input[@placeholder='時間を選択']"))
                .sendKeys("00:00:00");
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("updateUserLoginInfo")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
    }

    /**
     * 更新リセット
     */
    private void updatePasswordReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("passwordMustChangeFlg")).click();
        driver.findElement(By.id("passwordMustChangeFlg")).clear();
        driver.findElement(By.id("passwordMustChangeFlg")).sendKeys("２");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("//*[@id='passwordUpdateForm']/div[5]/div/div/button[2]/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertEquals(
                "$2a$10$b0kl7hfFttD7rBL2FBAJv.Q0I6gfpgkI9Kpu0WddoulhLPy2nY41u",
                getInputTextValue("password"));
        Assert.assertEquals("", getInputTextValue("passwordMustChangeFlg"));
    }

    /**
     * 更新キャンセル
     */
    private void updatePsaawordCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("passwordMustChangeFlg")).click();
        driver.findElement(By.id("passwordMustChangeFlg")).clear();
        driver.findElement(By.id("passwordMustChangeFlg")).sendKeys("２");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("//*[@id='passwordUpdateForm']/div[5]/div/div/button[1]/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * 更新成功
     */
    private void updatePsaawordSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//*[@id='passwordUpdateForm']/div[5]/div/div/button/span"))
                .click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("666666");
        driver.findElement(By.id("passwordMustChangeFlg")).click();
        driver.findElement(By.id("passwordMustChangeFlg")).clear();
        driver.findElement(By.id("passwordMustChangeFlg")).sendKeys("1");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("updatePassword")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
    }

    /**
     * 更新リセット
     */
    private void updateLoginControlInfoReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("accountInactiveFlg")).click();
        driver.findElement(By.id("accountInactiveFlg")).clear();
        driver.findElement(By.id("accountInactiveFlg")).sendKeys("1");
        driver.findElement(By.id("passwordFailureCount")).click();
        driver.findElement(By.id("passwordFailureCount")).clear();
        driver.findElement(By.id("passwordFailureCount")).sendKeys("2");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("//*[@id='loginControlInfoUpdateForm']/div[9]/div/div/button[2]/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertEquals("0", getInputTextValue("accountInactiveFlg"));
        Assert.assertEquals("", getInputTextValue("passwordFailureCount"));
    }

    /**
     * 更新キャンセル
     */
    private void updateLoginControlInfoCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("accountInactiveFlg")).click();
        driver.findElement(By.id("accountInactiveFlg")).clear();
        driver.findElement(By.id("accountInactiveFlg")).sendKeys("1");
        driver.findElement(By.id("passwordFailureCount")).click();
        driver.findElement(By.id("passwordFailureCount")).clear();
        driver.findElement(By.id("passwordFailureCount")).sendKeys("2");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("//*[@id='loginControlInfoUpdateForm']/div[9]/div/div/button[1]/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * 更新成功
     */
    private void updateLoginControlInfoSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//*[@id='loginControlInfoUpdateForm']/div[9]/div/div/button/span"))
                .click();
        driver.findElement(By.id("accountInactiveFlg")).click();
        driver.findElement(By.id("accountInactiveFlg")).clear();
        driver.findElement(By.id("accountInactiveFlg")).sendKeys("1");
        driver.findElement(By.id("passwordFailureCount")).click();
        driver.findElement(By.id("passwordFailureCount")).clear();
        driver.findElement(By.id("passwordFailureCount")).sendKeys("2");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("updateLoginControlInfo")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
    }

    /**
     * 削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("ユーザ_ログイン情報検索");
        pressButton("クリア");

        driver.findElement(By.id("userLoginInfoCode")).click();
        driver.findElement(By.id("userLoginInfoCode")).clear();
        driver.findElement(By.id("userLoginInfoCode")).sendKeys("test");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("searchUserLoginInfo")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[2]/table/thead/tr/th[1]/div/label"))
                .click();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("削除");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);
        Assert.assertEquals("test①",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"))
                        .getText());
        Assert.assertEquals("I_USER_01",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[3]/div"))
                        .getText());
        Assert.assertEquals("admin01",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[4]/div"))
                        .getText());
        Assert.assertEquals("Toshiba",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[5]/div"))
                        .getText());
    }

    /**
     * 削除成功
     */
    private void deleteSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("削除");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
    }

}
