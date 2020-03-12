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
 * IDMFアクション自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfActionControllerTests extends StvSimpleAppApplicationTests {

    public static final String PATH_URL = "action";

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

        pressButton("追加");
        sleep(DEFAULT_WAIT_TIME_2000L);
        Assert.assertEquals("IDMFルール登録", getHeaderTitle());
        driver.findElement(By.id("ruleCode")).click();
        driver.findElement(By.id("ruleCode")).clear();
        driver.findElement(By.id("ruleCode")).sendKeys("neusoft");
        pressButton("登録");
        pressButton("OK");
        Assert.assertEquals("登録が完了しました。", getAlertMessage());
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
        registClear();
        registCancel();
        registSuccess();
        registSameCode();
        registBack();
    }

    /**
     * 登録入力チェック
     */
    private void registInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[2]/div/div[2]/div[1]/div[3]/table/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();

        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[5]/div[2]/div/button[1]/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        // 必須が空文字
        Assert.assertEquals("IDMFアクション登録", getHeaderTitle());
        driver.findElement(By.id("actionCode")).click();
        driver.findElement(By.id("actionCode")).clear();
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
        driver.findElement(By.id("actionCode")).click();
        driver.findElement(By.id("actionCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("registAction")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
        pressButton("OK");

        // 複数エラー発生時
        driver.findElement(By.id("actionCode")).click();
        driver.findElement(By.id("actionCode")).clear();
        driver.findElement(By.id("valueType")).click();
        driver.findElement(By.id("valueType")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("registAction")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                5);
        pressButton("OK");
    }

    /**
     * 登録クリア
     */
    private void registClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("actionCode")).click();
        driver.findElement(By.id("actionCode")).clear();
        driver.findElement(By.id("actionCode")).sendKeys("action");
        driver.findElement(By.id("valueType")).click();
        driver.findElement(By.id("valueType")).clear();
        driver.findElement(By.id("valueType")).sendKeys("テスト①");
        driver.findElement(By.id("evalType")).click();
        driver.findElement(By.id("evalType")).clear();
        driver.findElement(By.id("evalType")).sendKeys("eval");
        driver.findElement(By.id("value")).click();
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("value");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        Assert.assertEquals("", getInputTextValue("actionCode"));
        Assert.assertEquals("", getInputTextValue("valueType"));
        Assert.assertEquals("", getInputTextValue("evalType"));
        Assert.assertEquals("", getInputTextValue("value"));
    }

    /**
     * 登録キャンセル
     */
    private void registCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("actionCode")).click();
        driver.findElement(By.id("actionCode")).clear();
        driver.findElement(By.id("actionCode")).sendKeys("action");
        driver.findElement(By.id("valueType")).click();
        driver.findElement(By.id("valueType")).clear();
        driver.findElement(By.id("valueType")).sendKeys("テスト①");
        driver.findElement(By.id("evalType")).click();
        driver.findElement(By.id("evalType")).clear();
        driver.findElement(By.id("evalType")).sendKeys("eval");
        driver.findElement(By.id("value")).click();
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("value");
        driver.findElement(By.id("registAction")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        Assert.assertEquals("action", getInputTextValue("actionCode"));
        Assert.assertEquals("テスト①", getInputTextValue("valueType"));
    }

    /**
     * 登録成功
     */
    public void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("actionCode")).click();
        driver.findElement(By.id("actionCode")).clear();
        driver.findElement(By.id("actionCode")).sendKeys("action");
        driver.findElement(By.id("valueType")).click();
        driver.findElement(By.id("valueType")).clear();
        driver.findElement(By.id("valueType")).sendKeys("テスト①");
        driver.findElement(By.id("evalType")).click();
        driver.findElement(By.id("evalType")).clear();
        driver.findElement(By.id("evalType")).sendKeys("eval");
        driver.findElement(By.id("value")).click();
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("value");
        driver.findElement(By.id("registAction")).click();
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("登録が完了しました。", getAlertMessage());
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        Assert.assertEquals("IDMFルール詳細", getHeaderTitle());
    }

    /**
     * 登録失敗（同じのロケールが登録される場合）
     */
    private void registSameCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[5]/div[2]/div/button[1]/span"))
                .click();
        driver.findElement(By.id("actionCode")).click();
        driver.findElement(By.id("actionCode")).clear();
        driver.findElement(By.id("actionCode")).sendKeys("action");
        driver.findElement(By.id("valueType")).click();
        driver.findElement(By.id("valueType")).clear();
        driver.findElement(By.id("valueType")).sendKeys("valueType①");
        driver.findElement(By.id("evalType")).click();
        driver.findElement(By.id("evalType")).clear();
        driver.findElement(By.id("evalType")).sendKeys("evalType");
        driver.findElement(By.id("value")).click();
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("value2");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("registAction")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());
        pressButton("OK");
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
        Assert.assertEquals("IDMFルール詳細", getHeaderTitle());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
    }

    /**
     * IDMFアクションを検索
     */
    private void search() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[6]/div[1]/div[2]/table/thead/tr/th[1]/div/label/span/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        Assert.assertEquals("action",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div[6]/div[1]/div[3]/table/tbody/tr/td[2]/div"))
                        .getText());
    }

    /**
     * IDMFアクションを更新
     */
    private void update() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[2]/div/div[6]/div[1]/div[3]/table/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        updateInputcheck();
        updateReset();
        updateCancel();
        updateSuccess();
        updateBack();
    }

    /**
     * IDMFアクションの入力チェック
     */
    private void updateInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");

        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("valueType")).click();
        driver.findElement(By.id("valueType")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("updateAction")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("OK");
    }

    /**
     * IDMFアクションをリセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("valueType")).click();
        driver.findElement(By.id("valueType")).clear();
        driver.findElement(By.id("valueType")).sendKeys("テスト2");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("リセット");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        Assert.assertEquals("テスト①", getInputTextValue("valueType"));
    }

    /**
     * IDMFアクションをキャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * IDMFアクションの更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");
        driver.findElement(By.id("evalType")).click();
        driver.findElement(By.id("evalType")).clear();
        driver.findElement(By.id("evalType")).sendKeys("テスト2");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("updateAction")).click();// 更新button
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
        pressButton("OK");
    }

    /**
     * DMFルール詳細画面へ遷移
     */
    private void updateBack() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("戻る");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("IDMFルール詳細", getHeaderTitle());
    }

    /**
     * IDMFアクションを削除
     */
    private void delete() {
        deleteCancel();
        deleteSuccess();
        deleteRule();
        deletePolicy();
    }

    /**
     * IDMFアクションの削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[6]/div[1]/div[2]/table/thead/tr/th[1]/div/label/span/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[5]/div[2]/div/button[2]/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
        Assert.assertEquals("action",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div[6]/div[1]/div[3]/table/tbody/tr/td[2]/div"))
                        .getText());
    }

    /**
     * IDMFアクションの削除成功
     */
    private void deleteSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[5]/div[2]/div/button[2]/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
        pressButton("OK");
    }

    /**
     * IDMFルールを削除
     */
    private void deleteRule() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("戻る");
        Assert.assertEquals("IDMFポリシ詳細", getHeaderTitle());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[2]/div[1]/div[2]/table/thead/tr/th[1]/div/label/span/span"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("削除");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                4);
        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                5);
        pressButton("OK");

    }

    /**
     * IDMFポリシを削除
     */
    private void deletePolicy() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("IDMFポリシ検索");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("searchPolicy")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        driver.findElement(By
                .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td[1]/div/label"))
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
