package example.staveware.stvwebstore.orguserinfo;

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
 * 組織階層自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfOrgHierarchyControllerTests
        extends StvSimpleAppApplicationTests {
    public static final String PATH_URL = "orgHierarchy";

    /**
     * テスト準備処理。
     */
    @Before
    public void setup() {
        super.setup();
        gotoRegistPage("org-regist");
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("orgCode")).click();
        driver.findElement(By.id("orgCode")).clear();
        driver.findElement(By.id("orgCode")).sendKeys("test1");

        // 会社マスタ設定
        pressButton("参照");
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // グループフラグ設定
        driver.findElement(By
                .xpath("//span[@class='el-radio__label' and contains(text(), '仮想組織')]"))
                .click();

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys("テスト①");
        driver.findElement(By.id("orgType")).click();
        driver.findElement(By.id("orgType")).clear();
        driver.findElement(By.id("orgType")).sendKeys("組織種類１");
        driver.findElement(By.id("registOrg")).click();
        pressButton("OK");
        pressButton("OK");
        sleep(DEFAULT_WAIT_TIME_2000L);
        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
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
        registSameHierarchyCode();
        registReset();
        registCancel();
    }

    /**
     * 登録入力チェック
     */
    private void registInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '追加')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        // 適用開始日設定
        driver.findElement(By.xpath("(//input[@id='activeStartTime'])[last()]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        driver.findElement(By.xpath("(//input[@id='activeEndTime'])[last()]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        // 階層コード
        driver.findElement(By.id("hierarchyCode")).click();
        driver.findElement(By.id("hierarchyCode")).sendKeys("①hierarchyCode");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        pressButton("更新");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        By by = By.xpath(
                "//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]");
        driver.findElement(by).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);

        Assert.assertEquals("エラーが発生しました。\nrecord inexistence error",
                getAlertMessage());

        driver.findElement(by).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                6);

        Assert.assertEquals("", getInputTextValue("highOrg"));

        // 上位組織マスタ設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("参照");
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[4]/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 階層コード
        driver.findElement(By.id("hierarchyCode")).clear();
        driver.findElement(By.id("hierarchyCode")).sendKeys(" ");
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                1);
        pressButton("更新");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                2);
        driver.findElement(by).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        driver.findElement(by).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                4);

        // 入力エラー（大きすぎる）時 「項目： 階層コード」
        driver.findElement(By.id("hierarchyCode")).click();
        driver.findElement(By.id("hierarchyCode")).clear();
        driver.findElement(By.id("hierarchyCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                1);
        pressButton("更新");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                2);
        driver.findElement(by).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        driver.findElement(by).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                4);
    }

    /**
     * 登録成功
     */
    private void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 階層コード
        driver.findElement(By.id("hierarchyCode")).click();
        driver.findElement(By.id("hierarchyCode")).clear();
        driver.findElement(By.id("hierarchyCode")).sendKeys("①hierarchyCode");
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("更新");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        By by = By.xpath(
                "//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]");
        driver.findElement(by).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());

        driver.findElement(by).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);

        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());

    }

    /**
     * 登録失敗（同じの階層コードが登録される場合）
     */
    private void registSameHierarchyCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '追加')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        // 階層コード
        By by = By.xpath(
                "(//table[@class='el-table__body'])[2]/tbody/tr[last()]/td/div/div/input[@id='hierarchyCode']");
        driver.findElement(by).click();
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys("①hierarchyCode");

        // 上位組織マスタ設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[4]/tbody/tr[2]/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用開始日設定
        driver.findElement(By
                .xpath("(//table[@class='el-table__body'])[2]/tbody/tr[last()]/td[3]/div/div/input[@id='activeStartTime']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("更新");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        by = By.xpath(
                "//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]");
        driver.findElement(by).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());

        driver.findElement(by).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
    }

    /**
     * 登録リセット
     */
    private void registReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("リセット");
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertNotNull(driver.findElement(By.xpath(
                "(//button[@type='button']/span[contains(text(), 'キャンセル')])")));
    }

    /**
     * 登録キャンセル
     */
    private void registCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '追加')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 階層コード
        By by = By.xpath(
                "(//table[@class='el-table__body'])[2]/tbody/tr[last()]/td/div/div/input[@id='hierarchyCode']");
        driver.findElement(by).click();
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys("②hierarchyCode");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("キャンセル");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertNotNull(driver.findElement(By.xpath(
                "(//button[@type='button']/span[contains(text(), '編集')])[2]")));
    }

    /**
     * 組織マスタ情報を検索
     */
    private void search() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("組織マスタ検索");

        driver.findElement(By.id("orgCode")).click();
        driver.findElement(By.id("orgCode")).clear();
        driver.findElement(By.id("orgCode")).sendKeys("test1");

        driver.findElement(By.id("searchOrg")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);
    }

    /**
     * 組織階層情報を更新
     */
    private void update() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 組織情報を選択
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                null);

        updateReset();
        updateCancel();
        updateSuccess();
    }

    /**
     * 組織階層情報更新をリセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '追加')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 階層コード
        By by = By.xpath(
                "(//table[@class='el-table__body'])[2]/tbody/tr[last()]/td/div/div/input[@id='hierarchyCode']");
        driver.findElement(by).click();
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys("②hierarchyCode");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("リセット");
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertNotNull(driver.findElement(By.xpath(
                "(//button[@type='button']/span[contains(text(), 'キャンセル')])")));
    }

    /**
     * 組織階層情報更新をキャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '追加')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 階層コード
        By by = By.xpath(
                "(//table[@class='el-table__body'])[2]/tbody/tr[last()]/td/div/div/input[@id='hierarchyCode']");
        driver.findElement(by).click();
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys("②hierarchyCode");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("キャンセル");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertNotNull(driver.findElement(By.xpath(
                "(//button[@type='button']/span[contains(text(), '編集')])[2]")));
    }

    /**
     * 組織階層情報の更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 上位組織マスタ設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("参照");
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[4]/tbody/tr[2]/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用開始日設定
        driver.findElement(By.xpath("(//input[@id='activeStartTime'])[last()]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        driver.findElement(By.xpath("(//input[@id='activeEndTime'])[last()]"))
                .click();
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
        pressButton("更新");
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
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
    }

    /**
     * 組織階層情報を削除
     */
    private void delete() {
        deleteSuccess();

        // 組織情報を削除
        deleteOrg();
    }

    /**
     * 組織階層情報の削除成功
     */
    private void deleteSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(
                By.xpath("(//table[@class='el-table__body'])[2]/tbody/tr/td"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '削除')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        pressButton("更新");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);
    }

    /**
     * 組織情報を削除
     */
    public void deleteOrg() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // delete Org
        pressButton("組織マスタ検索");
        driver.findElement(By.id("orgCode")).click();
        driver.findElement(By.id("orgCode")).clear();
        driver.findElement(By.id("orgCode")).sendKeys("test1");

        driver.findElement(By.id("searchOrg")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td//span[@class='el-checkbox__inner']"))
                .click();
        pressButton("削除");
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
    }
}
