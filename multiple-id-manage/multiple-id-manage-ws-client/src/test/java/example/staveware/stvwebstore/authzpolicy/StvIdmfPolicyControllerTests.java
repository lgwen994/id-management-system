package example.staveware.stvwebstore.authzpolicy;

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
 * IDMFポリシ自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfPolicyControllerTests extends StvSimpleAppApplicationTests {

    public static final String PATH_URL = "policy";

    /**
     * 登録、検索、更新をテストする。（標準テスト）
     */
    @Test
    public void testNormal() {
        gotoRegistPage("policy-regist");
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
     * 登録入力チェック
     */
    private void registInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 必須が空文字
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("registPolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        pressButton("OK");
        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("registPolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("OK");

        // 複数エラー発生時
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("registPolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                3);
        pressButton("OK");
    }

    /**
     * 登録クリア
     */
    private void registClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).clear();
        driver.findElement(By.id("effect")).sendKeys("テスト①");
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("", getInputTextValue("policyCode"));
        Assert.assertEquals("", getInputTextValue("effect"));
    }

    /**
     * 登録キャンセル
     */
    private void registCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).clear();
        driver.findElement(By.id("effect")).sendKeys("テスト①");
        driver.findElement(By.id("registPolicy")).click();
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("test1", getInputTextValue("policyCode"));
        Assert.assertEquals("テスト①", getInputTextValue("effect"));
    }

    /**
     * 登録成功
     */
    public void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).clear();
        driver.findElement(By.id("effect")).sendKeys("テスト①");
        driver.findElement(By.id("registPolicy")).click();
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("登録が完了しました。", getAlertMessage());
        pressButton("OK");
        Assert.assertEquals("IDMFポリシ詳細", getHeaderTitle());
    }

    /**
     * 登録失敗（同じのロケールが登録される場合）
     */
    private void registSameCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("IDMFポリシ登録");
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).clear();
        driver.findElement(By.id("effect")).sendKeys("テスト①");
        driver.findElement(By.id("registPolicy")).click();
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());
        pressButton("OK");
    }

    /**
     * IDMFポリシを検索
     */
    private void search() {
        search0record();
        searchSuccess();
        searchClear();
    }

    /**
     * 検索結果 ゼロ件
     */
    private void search0record() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("IDMFポリシ検索");
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("xxx");
        driver.findElement(By.id("searchPolicy")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("データなし",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/div/span"))
                        .getText());
    }

    /**
     * 検索結果 があり
     */
    private void searchSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("IDMFポリシ検索");
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("searchPolicy")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("test1",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td[2]/div"))
                        .getText());

    }

    /**
     * 検索条件クリア
     */
    private void searchClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("", getInputTextValue("policyCode"));
    }

    /**
     * IDMFポリシを更新
     */
    private void update() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr[1]/td[2]/div"));
        action.doubleClick(element).perform();
        ruleSearch();
        updateInputcheck();
        updateReset();
        updateCancel();
        updateSuccess();
    }

    /**
     * IDMFポリシの入力チェック
     */
    private void updateInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");

        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("updatePolicy")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("OK");
    }

    /**
     * IDMFポリシをキャンセル
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
     * IDMFルールを検索
     */
    private void ruleSearch() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        Assert.assertEquals("データなし",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div[2]/div[1]/div[3]/div/span"))
                        .getText());
    }

    /**
     * IDMFポリシをリセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).clear();
        driver.findElement(By.id("effect")).sendKeys("テスト2");
        pressButton("リセット");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("テスト①", getInputTextValue("effect"));
    }

    /**
     * IDMFポリシの更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");
        driver.findElement(By.id("effect")).click();
        driver.findElement(By.id("effect")).clear();
        driver.findElement(By.id("effect")).sendKeys("テスト2");
        driver.findElement(By.id("updatePolicy")).click();// 更新button
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        pressButton("OK");
    }

    /**
     * IDMFポリシを削除
     */
    private void delete() {
        deleteCancel();
        deleteSuccess();
    }

    /**
     * IDMFポリシの削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("IDMFポリシ検索");
        driver.findElement(By.id("policyCode")).click();
        driver.findElement(By.id("policyCode")).clear();
        driver.findElement(By.id("policyCode")).sendKeys("test1");
        driver.findElement(By.id("searchPolicy")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td[1]/div/label"))
                .click();
        pressButton("削除");
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("test1",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td[2]/div"))
                        .getText());
    }

    /**
     * IDMFポリシの削除成功
     */
    private void deleteSuccess() {
        pressButton("削除");
        pressButton("OK");
        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("OK");
    }
}
