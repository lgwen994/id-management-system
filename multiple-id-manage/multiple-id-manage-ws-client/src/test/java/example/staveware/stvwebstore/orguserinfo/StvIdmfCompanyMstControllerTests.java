package example.staveware.stvwebstore.orguserinfo;

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
 * 会社マスタ自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfCompanyMstControllerTests
        extends StvSimpleAppApplicationTests {

    public static final String PATH_URL = "company";

    /**
     * 登録、検索、更新、削除をテストする。（標準テスト）
     */
    @Test
    public void testNormal() {
        gotoRegistPage("company-regist");
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
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("registCompany")).click();
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

        // 入力エラー（大きすぎる）時 「項目：会社コード」
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                1);
        driver.findElement(By.id("registCompany")).click();
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

        // 入力エラー（大きすぎる）時 「項目：会社名称」
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyName")).sendKeys(
                "012345678901234567890123456789012345678901234567891");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                1);
        driver.findElement(By.id("registCompany")).click();
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

        // 入力エラー（大きすぎる）時 「項目：会社種別」
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).sendKeys(
                "012345678901234567890123456789012345678901234567891");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 4,
                1);
        driver.findElement(By.id("registCompany")).click();
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

        // 複数エラー発生時
        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyType")).sendKeys(
                "012345678901234567890123456789012345678901234567891");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 5,
                1);
        driver.findElement(By.id("registCompany")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 5,
                2);
        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 5,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 5,
                4);
    }

    /**
     * 登録画面クリア
     */
    private void registClear() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyComment")).clear();
        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).sendKeys("test1");

        // 適用開始日設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).sendKeys("テスト①");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).sendKeys("会社種類１");
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明１");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);

        Assert.assertEquals("", getInputTextValue("companyCode"));
        Assert.assertEquals("", getInputTextValue("companyName"));
        Assert.assertEquals("", getInputTextValue("companyType"));
        Assert.assertEquals("", getInputTextValue("companyComment"));
        Assert.assertEquals("", getInputTextValue("activeStartTime"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
    }

    /**
     * 登録キャンセル
     */
    private void registCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyComment")).clear();
        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).sendKeys("test1");

        // 適用開始日設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).sendKeys("テスト①");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).sendKeys("会社種類１");
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明１");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("registCompany")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);

        Assert.assertEquals("test1", getInputTextValue("companyCode"));
        Assert.assertEquals("テスト①", getInputTextValue("companyName"));
        Assert.assertEquals("会社種類１", getInputTextValue("companyType"));
        Assert.assertEquals("会社説明１", getInputTextValue("companyComment"));
    }

    /**
     * 登録成功
     */
    private void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("クリア");

        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyComment")).clear();
        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).sendKeys("test1");

        // 適用開始日設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeStartTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), '現在')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        // 適用終了日設定
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();

        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).sendKeys("テスト①");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).sendKeys("会社種類１");
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明１");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("registCompany")).click();

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
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

        Assert.assertEquals("会社マスタ詳細", getHeaderTitle());
    }

    /**
     * 登録失敗（同じの会社コードが登録される場合）
     */
    private void registSameCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("会社マスタ登録");

        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyComment")).clear();

        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).sendKeys("test1");
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).sendKeys("テスト①");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).sendKeys("会社種類１");
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明１");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("registCompany")).click();
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
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

        Assert.assertEquals("", getInputTextValue("companyCode"));
        Assert.assertEquals("", getInputTextValue("companyName"));
        Assert.assertEquals("", getInputTextValue("companyType"));
        Assert.assertEquals("", getInputTextValue("companyComment"));
        Assert.assertEquals("", getInputTextValue("activeStartTime"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
    }

    /**
     * 検索結果 があり
     */
    private void searchSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("会社マスタ検索");

        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyComment")).clear();

        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).sendKeys("test1");

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

        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).sendKeys("テスト①");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).sendKeys("会社種類１");
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明１");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("searchCompany")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertEquals("test1",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"))
                        .getText());
        Assert.assertEquals("テスト①",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[5]/div"))
                        .getText());
        Assert.assertEquals("会社種類１",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[6]/div"))
                        .getText());
        Assert.assertEquals("会社説明１",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[7]/div"))
                        .getText());
    }

    /**
     * 検索結果 ゼロ件
     */
    private void search0record() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("会社マスタ検索");

        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyComment")).clear();

        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).sendKeys("x");

        // 適用開始日設定
        sleep(DEFAULT_WAIT_TIME_2000L);
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
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("activeEndTime")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-date-picker__header'])[last()]//button[@aria-label='Next Year']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By
                .xpath("(//div[@class='el-picker-panel__footer'])[last()]//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).sendKeys("テスト①");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).sendKeys("会社種類１");
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明１");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("searchCompany")).click();
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
     * 更新入力チェック
     */
    private void updateInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");

        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("updateCompany")).click();
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

        // 複数エラー発生時
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyType")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                1);
        driver.findElement(By.id("updateCompany")).click();
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
    }

    /**
     * 更新キャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyName")).sendKeys("テスト②");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyType")).sendKeys("会社種類２");
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明２");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertNotNull(driver.findElement(By.xpath(
                "//button[@type='button']/span[contains(text(), '編集')]")));
    }

    /**
     * 更新リセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();
        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyName")).sendKeys("テスト②");
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyType")).sendKeys("会社種類２");
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明２");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("リセット");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertEquals("テスト①", getInputTextValue("companyName"));
        Assert.assertEquals("会社種類１", getInputTextValue("companyType"));
        Assert.assertEquals("会社説明１", getInputTextValue("companyComment"));
    }

    /**
     * 更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("編集");

        driver.findElement(By.id("companyName")).click();
        driver.findElement(By.id("companyName")).clear();
        driver.findElement(By.id("companyName")).sendKeys("テスト②");
        driver.findElement(By.id("activeStartTime")).clear();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("companyType")).click();
        driver.findElement(By.id("companyType")).clear();
        driver.findElement(By.id("companyType")).sendKeys("会社種類２");
        driver.findElement(By.id("activeEndTime")).clear();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("companyComment")).click();
        driver.findElement(By.id("companyComment")).clear();
        driver.findElement(By.id("companyComment")).sendKeys("会社説明２");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("updateCompany")).click();
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

        pressButton("会社マスタ検索");
        pressButton("クリア");

        driver.findElement(By.id("companyCode")).click();
        driver.findElement(By.id("companyCode")).clear();
        driver.findElement(By.id("companyCode")).sendKeys("test1");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("searchCompany")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        driver.findElement(By
                .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td//span[@class='el-checkbox__inner']"))
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

        Assert.assertEquals("test1",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[2]/div"))
                        .getText());
        Assert.assertEquals("テスト②",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[5]/div"))
                        .getText());
        Assert.assertEquals("会社種類２",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[6]/div"))
                        .getText());
        Assert.assertEquals("会社説明２",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[7]/div"))
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
