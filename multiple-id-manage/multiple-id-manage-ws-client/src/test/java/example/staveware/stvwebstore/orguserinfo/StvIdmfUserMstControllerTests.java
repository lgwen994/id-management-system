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
 * ユーザマスタ自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfUserMstControllerTests
        extends StvSimpleAppApplicationTests {

    public static final String PATH_URL = "user";

    /**
     * 登録、検索、更新、削除をテストする。（標準テスト）
     */
    @Test
    public void testNormal() {
        gotoRegistPage("user-regist");
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
        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).clear();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("registUser")).click();
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

        // 入力エラー（大きすぎる）時 「項目：ユーザコード」
        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userCode")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                1);
        driver.findElement(By.id("registUser")).click();
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

        // 入力エラー（大きすぎる）時 「項目：ユーザ名称」
        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys(
                "012345678901234567890123456789012345678901234567891");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 3,
                1);
        driver.findElement(By.id("registUser")).click();
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

        // 入力エラー（大きすぎる）時 「項目：TEL」
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).sendKeys(
                "012345678901234567890123456789012345678901234567891");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 4,
                1);
        driver.findElement(By.id("registUser")).click();
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
        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("tel")).sendKeys(
                "012345678901234567890123456789012345678901234567891");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 5,
                1);
        driver.findElement(By.id("registUser")).click();
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

        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("fax")).clear();

        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).sendKeys("test1");

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

        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys("テスト①");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).sendKeys("780722694");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).sendKeys("8243366");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).sendKeys("xiaodong");
        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                2);

        Assert.assertEquals("", getInputTextValue("userCode"));
        Assert.assertEquals("", getInputTextValue("activeStartTime"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
        Assert.assertEquals("", getInputTextValue("userName"));
        Assert.assertEquals("", getInputTextValue("mail"));
        Assert.assertEquals("", getInputTextValue("tel"));
        Assert.assertEquals("", getInputTextValue("fax"));
    }

    /**
     * 登録キャンセル
     */
    private void registCancel() {

        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("fax")).clear();

        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();

        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).sendKeys("test1");

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

        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys("テスト①");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).sendKeys("780722694");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).sendKeys("8243366");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).sendKeys("xiaodong");
        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        driver.findElement(By.id("registUser")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        Assert.assertEquals("test1", getInputTextValue("userCode"));
        Assert.assertEquals("テスト①", getInputTextValue("userName"));
        Assert.assertEquals("780722694", getInputTextValue("mail"));
        Assert.assertEquals("8243366", getInputTextValue("tel"));
        Assert.assertEquals("xiaodong", getInputTextValue("fax"));
    }

    /**
     * 登録成功
     */
    private void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("クリア");
        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).sendKeys("test1");

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

        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys("テスト①");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).sendKeys("780722694");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).sendKeys("8243366");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).sendKeys("xiaodong");
        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        driver.findElement(By.id("registUser")).click();
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

        Assert.assertEquals("ユーザマスタ詳細", getHeaderTitle());
    }

    /**
     * 登録失敗（同じのユーザコードが登録される場合）
     */
    private void registSameCode() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("ユーザマスタ登録");
        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("fax")).clear();
        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).sendKeys("test1");

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

        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys("テスト①");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).sendKeys("780722694");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).sendKeys("8243366");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).sendKeys("xiaodong");
        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        driver.findElement(By.id("registUser")).click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
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
     * 検索結果 ゼロ件
     */
    private void search0record() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("ユーザマスタ検索");

        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("fax")).clear();
        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).sendKeys("xxx");

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
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys("テスト①");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).sendKeys("780722694");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).sendKeys("8243366");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).sendKeys("xiaodong");
        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        driver.findElement(By.id("searchUser")).click();
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

        Assert.assertEquals("", getInputTextValue("userCode"));
        Assert.assertEquals("", getInputTextValue("activeStartTime"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
        Assert.assertEquals("", getInputTextValue("userName"));
        Assert.assertEquals("", getInputTextValue("mail"));
        Assert.assertEquals("", getInputTextValue("tel"));
        Assert.assertEquals("", getInputTextValue("fax"));
    }

    /**
     * 検索結果 があり
     */
    private void searchSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("fax")).clear();
        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).sendKeys("test1");

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
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys("テスト①");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).sendKeys("780722694");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).sendKeys("8243366");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).sendKeys("xiaodong");
        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), null,
                1);
        driver.findElement(By.id("searchUser")).click();
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
        Assert.assertEquals("780722694",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[6]/div"))
                        .getText());
        Assert.assertEquals("8243366",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[7]/div"))
                        .getText());
        Assert.assertEquals("xiaodong",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[last()]/tbody/tr/td[8]/div"))
                        .getText());
    }

    /**
     * 更新入力チェック
     */
    private void updateInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);
        pressButton("編集");

        // 入力エラー（大きすぎる）時
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("updateUser")).click();
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
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("mail")).sendKeys(
                "012345678901234567890123456789012345678901234567890123456789");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                1);
        driver.findElement(By.id("updateUser")).click();
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
     * 更新リセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys("テスト②");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("mail")).sendKeys("946171817");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("tel")).sendKeys("8243577");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).clear();
        driver.findElement(By.id("fax")).sendKeys("dasu");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("リセット");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        Assert.assertEquals("テスト①", getInputTextValue("userName"));
        Assert.assertEquals("780722694", getInputTextValue("mail"));
        Assert.assertEquals("8243366", getInputTextValue("tel"));
        Assert.assertEquals("xiaodong", getInputTextValue("fax"));
    }

    /**
     * 更新キャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("activeStartTime")).clear();
        driver.findElement(By.id("activeEndTime")).clear();
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys("テスト②");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("mail")).sendKeys("946171817");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("tel")).sendKeys("8243577");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).clear();
        driver.findElement(By.id("fax")).sendKeys("dasu");

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
     * 更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("編集");
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys("テスト②");
        driver.findElement(By.id("mail")).click();
        driver.findElement(By.id("mail")).clear();
        driver.findElement(By.id("mail")).sendKeys("946171817");
        driver.findElement(By.id("tel")).click();
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("tel")).sendKeys("8243577");
        driver.findElement(By.id("fax")).click();
        driver.findElement(By.id("fax")).clear();
        driver.findElement(By.id("fax")).sendKeys("dasu");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("updateUser")).click();
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
        pressButton("ユーザマスタ検索");
        pressButton("クリア");

        driver.findElement(By.id("userCode")).click();
        driver.findElement(By.id("userCode")).clear();
        driver.findElement(By.id("userCode")).sendKeys("test1");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        driver.findElement(By.id("searchUser")).click();
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
