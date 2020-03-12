package example.staveware.stvwebstore.roleinfo;

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
 * ロールマスタ自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfRoleMstControllerTests
        extends StvSimpleAppApplicationTests {

    public static final String PATH_URL = "role";

    /**
     * 登録、検索、更新をテストする。（標準テスト）
     */
    @Test
    public void testNormal() {
        gotoRegistPage("role-regist");
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
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("registRole")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                null);
        pressButton("OK");
        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("registRole")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                null);
        pressButton("OK");

        // 複数エラー発生時
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("registRole")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                null);
        pressButton("OK");
    }

    /**
     * 登録クリア
     */
    private void registClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("test1");
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト①");
        driver.findElement(By.id("roleType")).click();
        driver.findElement(By.id("roleType")).clear();
        driver.findElement(By.id("roleType")).sendKeys("ロール種類１");
        driver.findElement(By.id("roleComment")).click();
        driver.findElement(By.id("roleComment")).clear();
        driver.findElement(By.id("roleComment")).sendKeys("ロール説明１");
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("", getInputTextValue("roleCode"));
        Assert.assertEquals("", getInputTextValue("roleName"));
        Assert.assertEquals("", getInputTextValue("roleType"));
        Assert.assertEquals("", getInputTextValue("roleComment"));
    }

    /**
     * 登録キャンセル
     */
    private void registCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("test1");
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト①");
        driver.findElement(By.id("roleType")).click();
        driver.findElement(By.id("roleType")).clear();
        driver.findElement(By.id("roleType")).sendKeys("ロール種類１");
        driver.findElement(By.id("roleComment")).click();
        driver.findElement(By.id("roleComment")).clear();
        driver.findElement(By.id("roleComment")).sendKeys("ロール説明１");
        driver.findElement(By.id("registRole")).click();
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("test1", getInputTextValue("roleCode"));
        Assert.assertEquals("テスト①", getInputTextValue("roleName"));
        Assert.assertEquals("ロール種類１", getInputTextValue("roleType"));
        Assert.assertEquals("ロール説明１", getInputTextValue("roleComment"));
    }

    /**
     * 登録成功
     */
    public void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("test1");
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト①");
        driver.findElement(By.id("roleType")).click();
        driver.findElement(By.id("roleType")).clear();
        driver.findElement(By.id("roleType")).sendKeys("ロール種類１");
        driver.findElement(By.id("roleComment")).click();
        driver.findElement(By.id("roleComment")).clear();
        driver.findElement(By.id("roleComment")).sendKeys("ロール説明１");
        driver.findElement(By.id("registRole")).click();
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("登録が完了しました。", getAlertMessage());
        pressButton("OK");
        Assert.assertEquals("ロールマスタ詳細", getHeaderTitle());
    }

    /**
     * 登録失敗（同じのロケールが登録される場合）
     */
    private void registSameCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("ロールマスタ登録");
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("test1");
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト①");
        driver.findElement(By.id("roleType")).click();
        driver.findElement(By.id("roleType")).clear();
        driver.findElement(By.id("roleType")).sendKeys("ロール種類１");
        driver.findElement(By.id("roleComment")).click();
        driver.findElement(By.id("roleComment")).clear();
        driver.findElement(By.id("roleComment")).sendKeys("ロール説明１");
        driver.findElement(By.id("registRole")).click();
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());
        pressButton("OK");
    }

    /**
     * ロールマスタを検索
     */
    private void search() {
        search0record();
        searchSuccess();
        searchClear();
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
        Assert.assertEquals("", getInputTextValue("roleCode"));
    }

    /**
     * 検索結果 があり
     */
    private void searchSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("ロールマスタ検索");
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("test1");
        driver.findElement(By.id("searchRole")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
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
        pressButton("ロールマスタ検索");
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("xxx");
        driver.findElement(By.id("searchRole")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("データなし",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]"))
                        .getText());

    }

    /**
     * ロールマスタを更新
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
     * ロールマスタをリセット
     */
    private void updateInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");

        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("updateRole")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        pressButton("OK");

        // 複数エラー発生時
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("roleType")).click();
        driver.findElement(By.id("roleType")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("updateRole")).click();
        pressButton("OK");
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);
        pressButton("OK");
    }

    /**
     * ロールマスタをキャンセル
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
     * ロールマスタをリセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト2");
        pressButton("リセット");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("テスト①", getInputTextValue("roleName"));
    }

    /**
     * ロールマスタの更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト2");
        driver.findElement(By.id("updateRole")).click();// 更新button
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        pressButton("OK");
    }

    /**
     * ロールマスタを削除
     */
    private void delete() {
        deleteCancel();
        deleteSuccess();
    }

    /**
     * ロールマスタの削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("ロールマスタ検索");
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("test1");
        driver.findElement(By.id("searchRole")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td/div/label/span/span"))
                .click();
        pressButton("削除");
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
        Assert.assertEquals("test1",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr[1]/td[2]/div"))
                        .getText());
    }

    /**
     * ロールマスタの削除成功
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
