package example.staveware.stvwebstore.roleinfo;

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
 * ロール名マスタ自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfRoleNameMstControllerTests
        extends StvSimpleAppApplicationTests {
    public static final String PATH_URL = "rolename";

    /**
     * テスト準備処理。
     */
    @Before
    public void setup() {
        super.setup();
        gotoRegistPage("role-regist");
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
        pressButton("OK");
        Assert.assertEquals("ロールマスタ詳細", getHeaderTitle());

    }

    /**
     * 登録、検索、更新をテストする。（標準テスト）
     */
    @Test
    public void testNormal() {
        regist();
        search();
        update();
        //delete();
    }

    /**
     * 登録
     */
    private void regist() {
        registInputcheck();
        registSuccess();
        registClear();
        registBack();
    }

    /**
     * 登録入力チェック
     */
    private void registInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("追加");
        Assert.assertEquals("ロール名マスタ登録", getHeaderTitle());
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        pressButton("登録");
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());
        pressButton("OK");
    }

    /**
     * 登録成功
     */
    private void registSuccess() {

        driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//li[2]/span")).click();
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト名前①");
        pressButton("登録");
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("登録が完了しました。", getAlertMessage());
        pressButton("OK");
        Assert.assertEquals("ロールマスタ詳細", getHeaderTitle());

    }

    /**
     * 登録クリア
     */
    private void registClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("追加");
        Assert.assertEquals("ロール名マスタ登録", getHeaderTitle());
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//li[2]/span")).click();
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト名前①");
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("", getInputTextValue("roleName"));
    }

    /**
     * 登録から戻る
     */
    private void registBack() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("戻る");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("ロールマスタ詳細", getHeaderTitle());
    }

    /**
     * ロール名マスタを検索
     */
    private void search() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("ロールマスタ検索");
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("test1");
        driver.findElement(By.id("searchRole")).click();
    }

    /**
     * ロール名マスタを更新
     */
    private void update() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);
        element = driver.findElement(By.xpath(
                "//div[@id='main']/section/main/div/div[2]/div/div[2]/div/div[3]/table/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        // TODO if do reset before the success， the role id is null when submit.
        // updateReset();
        // updateCancel();
        updateSuccess();
        updateBack();
    }

    /**
     * ロール名マスタをリセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト名前2");
        pressButton("リセット");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("テスト名前①", getInputTextValue("roleName"));
    }

    /**
     * ロール名マスタをキャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト名前2");
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * ロール名マスタの更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");
        driver.findElement(By.id("roleName")).click();
        driver.findElement(By.id("roleName")).clear();
        driver.findElement(By.id("roleName")).sendKeys("テスト2");
        // 更新button
        pressButton("更新");
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        pressButton("OK");
    }

    /**
     * 更新名から戻る
     */
    private void updateBack() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("戻る");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("ロールマスタ詳細", getHeaderTitle());
    }

    /**
     * ロール名マスタを削除
     */
    private void delete() {
        deleteCancel();
        deleteSuccess();

        deleteRole();
    }

    /**
     * ロール名マスタの削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("//*[@id='main']/section/main/div/div[2]/div/div[2]/div/div[3]/table/tbody/tr/td[1]/div/label/span/span"))
                .click();
        pressButton("削除");
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(),
                null,null);
        Assert.assertEquals("英語",
                driver.findElement(By
                        .xpath("//div[@id='main']/section/main/div/div[2]/div/div[2]/div/div[3]/table/tbody/tr/td[2]/div"))
                        .getText());
    }

    /**
     * ロール名マスタの削除成功
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
    
    /**
     * ロールマスタを削除
     */
    public void deleteRole() {
        pressButton("ロールマスタ検索");
        driver.findElement(By.id("roleCode")).click();
        driver.findElement(By.id("roleCode")).clear();
        driver.findElement(By.id("roleCode")).sendKeys("test1");
        driver.findElement(By.id("searchRole")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("//div[@id='main']/section/main/div/div[2]/div/div/div[2]/div/div[3]/div[3]/table/tbody/tr/td/div/label/span/span"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("削除");
        pressButton("OK");
        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        pressButton("OK");
    }
}
