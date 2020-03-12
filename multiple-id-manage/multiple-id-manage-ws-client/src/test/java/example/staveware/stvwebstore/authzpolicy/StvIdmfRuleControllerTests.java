package example.staveware.stvwebstore.authzpolicy;

import org.junit.Assert;
import org.junit.Before;
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
 * IDMFルール自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfRuleControllerTests extends StvSimpleAppApplicationTests {

    public static final String PATH_URL = "rule";


    /**
     * テスト準備処理。
     */
    @Before
    public void setup() {
        super.setup();
        gotoRegistPage("policy-regist");
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).clear();
        driver.findElement(By.id("effect")).sendKeys("テスト①");
        driver.findElement(By.id("registPolicy")).click();
        pressButton("OK");
        pressButton("OK");
        Assert.assertEquals("IDMFポリシ詳細", getHeaderTitle());

    }

    /**
     * 登録、検索、更新をテストする。（標準テスト）
     */
    @Test
    public void testNormal() {
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
        registSuccess();
        registSameCode();
        registClear();
        registBack();
    }

    /**
     * 登録入力チェック
     */
    private void registInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("追加");
        // 必須が空文字
        Assert.assertEquals("IDMFルール登録", getHeaderTitle());
        driver.findElement(By.id("ruleCode")).click();
        driver.findElement(By.id("ruleCode")).clear();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("登録");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        pressButton("OK");

        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("ruleCode")).click();
        driver.findElement(By.id("ruleCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        pressButton("登録");
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
        pressButton("OK");

    }

    /**
     * 登録成功
     */
    private void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("ruleCode")).click();
        driver.findElement(By.id("ruleCode")).clear();
        driver.findElement(By.id("ruleCode")).sendKeys("neusoft");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("登録");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
        Assert.assertEquals("登録が完了しました。", getAlertMessage());
        pressButton("OK");
        Assert.assertEquals("IDMFポリシ詳細", getHeaderTitle());
    }
    
    /**
     * 登録失敗（同じのロケールが登録される場合）
     */
    private void registSameCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[1]/div[2]/div/button[1]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("ruleCode")).click();
        driver.findElement(By.id("ruleCode")).clear();
        driver.findElement(By.id("ruleCode")).sendKeys("neusoft");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("登録");
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());
        pressButton("OK");
    }

    /**
     * 登録クリア
     */
    private void registClear() {
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("ruleCode")).click();
        driver.findElement(By.id("ruleCode")).clear();
        driver.findElement(By.id("ruleCode")).sendKeys("neusoft①");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        Assert.assertEquals("", getInputTextValue("ruleCode"));
    }

    /**
     * 登録から戻る
     */
    private void registBack() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("戻る");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("IDMFポリシ詳細", getHeaderTitle());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
    }

    /**
     * IDMFルールを検索
     */
    private void search() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("IDMFポリシ検索");
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("searchPolicy")).click();
    }

    /**
     * IDMFルールを更新
     */
    private void update() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);
        element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[2]/div/div[2]/div[1]/div[3]/table/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        updateReset();
        updateCancel();
        updateSuccess();
    }

    /**
     * IDMFルールをリセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");
        pressButton("リセット");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("neusoft", getInputTextValue("ruleCode"));
    }

    /**
     * IDMFルールをキャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * IDMFルールの更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("編集");
        // 更新button
        pressButton("更新");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
    }
    
    /**
     * IDMFルールを削除
     */
    private void delete() {
        deleteCancel();
        deleteSuccess();
        deletePolicy();
    }

    /**
     * IDMFルールの削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("戻る");
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[2]/div[1]/div[2]/table/thead/tr/th[1]/div/label/span/span"))
                .click();
        pressButton("削除");
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("neusoft",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div[2]/div[1]/div[3]/table/tbody/tr/td[2]/div"))
                        .getText());
    }

    /**
     * IDMFルールの削除成功
     */
    private void deleteSuccess() {
        pressButton("削除");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);

    }

    /**
     * IDMFポリシを削除
     */
    public void deletePolicy() {
        pressButton("IDMFポリシ検索");
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("searchPolicy")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[2]/table/thead/tr/th[1]/div/label/span/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        pressButton("削除");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                5);
        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                6);
        pressButton("OK");
    }
}
