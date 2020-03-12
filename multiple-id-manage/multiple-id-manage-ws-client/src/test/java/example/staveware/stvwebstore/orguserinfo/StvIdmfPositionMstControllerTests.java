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
 * ポジションマスタ自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfPositionMstControllerTests
        extends StvSimpleAppApplicationTests {
    public static final String PATH_URL = "position";

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
        role();
        agent();
        delete();
    }

    /**
     * 登録
     */
    private void regist() {
        registInputcheck();
        registSuccess();
        registSameLocale();
        registClear();
        registBack();
    }

    /**
     * 登録入力チェック
     */
    private void registInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '追加')])[2]"))
                .click();
        Assert.assertEquals("所属登録", getHeaderTitle());

        // 所属コード
        driver.findElement(By.id("positionCode")).click();
        driver.findElement(By.id("positionCode")).clear();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("登録");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        pressButton("OK");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);

        // 所属コード
        driver.findElement(By.id("positionCode")).click();
        driver.findElement(By.id("positionCode")).clear();
        driver.findElement(By.id("positionCode")).sendKeys(
                "012345678901234567890123456789012345678901234567891");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                1);
        pressButton("登録");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                2);
        pressButton("OK");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                4);

        // 所属コード
        driver.findElement(By.id("positionCode")).click();
        driver.findElement(By.id("positionCode")).clear();
        driver.findElement(By.id("positionCode")).sendKeys("①positionCode");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                1);
        pressButton("登録");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                2);
        pressButton("OK");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                3);

        Assert.assertEquals("エラーが発生しました。\nrecord inexistence error",
                getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                4);
    }

    /**
     * 登録成功
     */
    private void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[1]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 役職マスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        action = new Actions(driver);
        element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // グループフラグ設定
        driver.findElement(By
                .xpath("//span[@class='el-radio__label' and contains(text(), '兼務')]"))
                .click();
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
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("登録");
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
        Assert.assertEquals("登録が完了しました。", getAlertMessage());

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());

    }

    /**
     * 登録失敗（同じの所属コードが登録される場合）
     */
    private void registSameLocale() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '追加')])[2]"))
                .click();
        Assert.assertEquals("所属登録", getHeaderTitle());

        // 所属コード
        driver.findElement(By.id("positionCode")).click();
        driver.findElement(By.id("positionCode")).clear();
        driver.findElement(By.id("positionCode")).sendKeys("①positionCode");

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[1]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 役職マスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        action = new Actions(driver);
        element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // グループフラグ設定
        driver.findElement(By
                .xpath("//span[@class='el-radio__label' and contains(text(), '兼務')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("登録");
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

        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);

        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("戻る");
    }

    /**
     * 登録クリア
     */
    private void registClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '追加')])[2]"))
                .click();
        Assert.assertEquals("所属登録", getHeaderTitle());

        // 所属コード
        driver.findElement(By.id("positionCode")).click();
        driver.findElement(By.id("positionCode")).clear();
        driver.findElement(By.id("positionCode")).sendKeys("①positionCode");

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[1]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(
                By.xpath("//table[@class='el-table__body']/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 役職マスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        action = new Actions(driver);
        element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // グループフラグ設定
        driver.findElement(By
                .xpath("//span[@class='el-radio__label' and contains(text(), '兼務')]"))
                .click();
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
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertEquals("", getInputTextValue("positionCode"));
        Assert.assertEquals("", getInputTextValue("user"));
        Assert.assertEquals("", getInputTextValue("title"));
        Assert.assertEquals("", getInputTextValue("activeStartTime"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
    }

    /**
     * 所属登録から戻る
     */
    private void registBack() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("戻る");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());
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
     * 組織所属情報を更新
     */
    private void update() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 組織情報を選択
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        sleep(DEFAULT_WAIT_TIME_2000L);

        // 所属情報を選択
        action = new Actions(driver);
        element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        updateReset();
        updateCancel();
        updateSuccess();
        updateBack();
    }

    /**
     * 所属情報をリセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        String user = getInputTextValue("user");
        String title = getInputTextValue("title");
        String activeStartTime = getInputTextValue("activeStartTime");
        String activeEndTime = getInputTextValue("activeEndTime");

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[1]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[1]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By
                .xpath("//table[@class='el-table__body']/tbody/tr[2]/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 役職マスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        action = new Actions(driver);
        element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr[2]/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // グループフラグ設定
        driver.findElement(By
                .xpath("//span[@class='el-radio__label' and contains(text(), '本務')]"))
                .click();
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
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("リセット");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertEquals(user, getInputTextValue("user"));
        Assert.assertEquals(title, getInputTextValue("title"));
        Assert.assertEquals(activeStartTime,
                getInputTextValue("activeStartTime"));
        Assert.assertEquals(activeEndTime, getInputTextValue("activeEndTime"));
    }

    /**
     * 所属情報をキャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[1]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By
                .xpath("//table[@class='el-table__body']/tbody/tr[2]/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 役職マスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        action = new Actions(driver);
        element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr[2]/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // グループフラグ設定
        driver.findElement(By
                .xpath("//span[@class='el-radio__label' and contains(text(), '本務')]"))
                .click();
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
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("キャンセル");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * 所属情報の更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[1]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ユーザマスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[1]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By
                .xpath("//table[@class='el-table__body']/tbody/tr[2]/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 役職マスタ設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        action = new Actions(driver);
        element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr[2]/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // グループフラグ設定
        driver.findElement(By
                .xpath("//span[@class='el-radio__label' and contains(text(), '本務')]"))
                .click();
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
     * 組織詳細画面へ遷移
     */
    private void updateBack() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("戻る");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());
    }

    /**
     * 所属画面のロール
     */
    private void role() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 所属情報を選択
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        registRole();
        updateRoleReset();
        updateRoleCancel();
        updateRole();
        deleteRole();
    }

    /**
     * 所属画面のロール情報を登録
     */
    private void registRole() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        this.pressButton("追加");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        // ロール選択
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        this.pressButton("更新");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                6);
    }

    /**
     * 所属画面のロール情報をリセット
     */
    private void updateRoleReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        String activeStartTime = getInputTextValue("activeStartTime");
        String activeEndTime = getInputTextValue("activeEndTime");

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

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

        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        pressButton("リセット");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals(activeStartTime,
                getInputTextValue("activeStartTime"));
        Assert.assertEquals(activeEndTime, getInputTextValue("activeEndTime"));
    }

    /**
     * 所属画面のロール情報をキャンセル
     */
    private void updateRoleCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        String activeStartTime = getInputTextValue("activeStartTime");
        String activeEndTime = getInputTextValue("activeEndTime");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

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

        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        pressButton("キャンセル");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals(activeStartTime,
                getInputTextValue("activeStartTime"));
        Assert.assertEquals(activeEndTime, getInputTextValue("activeEndTime"));

    }

    /**
     * 所属画面のロール情報を更新
     */
    private void updateRole() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

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

        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        this.pressButton("更新");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);
    }

    /**
     * 所属画面のロール情報を削除
     */
    private void deleteRole() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[2]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        driver.findElement(
                By.xpath("(//table[@class='el-table__body'])[1]/tbody/tr/td"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        pressButton("削除");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        pressButton("更新");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                6);

        // 組織マスタ詳細画面へ戻る
        pressButton("戻る");
        sleep(DEFAULT_WAIT_TIME_2000L);
        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());
    }

    /**
     * 所属画面の代行
     */
    private void agent() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 所属情報を選択
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        registAgent();
        updateAgentReset();
        updateAgentCancel();
        updateAgent();
        deleteAgent();
    }

    /**
     * 所属画面の代行情報を登録
     */
    private void registAgent() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[last()]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        this.pressButton("追加");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        // 代行コード
        driver.findElement(By.id("agentCode")).click();
        driver.findElement(By.id("agentCode")).clear();
        driver.findElement(By.id("agentCode")).sendKeys("①agentCode");

        // 非代行所属の設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[last()]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr/td/div"));
        action.doubleClick(element).perform();
        sleep(DEFAULT_WAIT_TIME_2000L);

        this.pressButton("更新");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                6);
    }

    /**
     * 所属画面の代行情報をリセット
     */
    private void updateAgentReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        String activeStartTime = getInputTextValue("activeStartTime");
        String activeEndTime = getInputTextValue("activeEndTime");

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[last()]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        // 非代行所属の設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[3]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr[2]/td/div"));
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

        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        pressButton("リセット");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals(activeStartTime,
                getInputTextValue("activeStartTime"));
        Assert.assertEquals(activeEndTime, getInputTextValue("activeEndTime"));
    }

    /**
     * 所属画面の代行情報をキャンセル
     */
    private void updateAgentCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        String activeStartTime = getInputTextValue("activeStartTime");
        String activeEndTime = getInputTextValue("activeEndTime");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        // 非代行所属の設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[3]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr[2]/td/div"));
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

        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        pressButton("キャンセル");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals(activeStartTime,
                getInputTextValue("activeStartTime"));
        Assert.assertEquals(activeEndTime, getInputTextValue("activeEndTime"));

    }

    /**
     * 所属画面の代行情報を更新
     */
    private void updateAgent() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[last()]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        // 非代行所属の設定
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '参照')])[3]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[last()]/tbody/tr[2]/td/div"));
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

        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        this.pressButton("更新");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);
    }

    /**
     * 所属画面の代行情報を削除
     */
    private void deleteAgent() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '編集')])[last()]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);

        driver.findElement(
                By.xpath("(//table[@class='el-table__body'])[2]/tbody/tr/td"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        pressButton("削除");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        pressButton("更新");
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        Assert.assertEquals("更新が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                5);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                6);

        // 組織マスタ詳細画面へ戻る
        pressButton("戻る");
        sleep(DEFAULT_WAIT_TIME_2000L);
        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());
    }

    /**
     * 所属情報を削除
     */
    private void delete() {
        deleteCancel();
        deleteSuccess();

        // 組織情報を削除
        deleteOrg();
    }

    /**
     * 所属情報の削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td//span[@class='el-checkbox__inner']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '削除')])[last()]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        Assert.assertEquals("①positionCode",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"))
                        .getText());
    }

    /**
     * 所属情報の削除成功
     */
    private void deleteSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By
                .xpath("(//button[@type='button']/span[contains(text(), '削除')])[last()]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        Assert.assertEquals("削除が完了しました。", getAlertMessage());
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
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
        sleep(DEFAULT_WAIT_TIME_2000L);
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
