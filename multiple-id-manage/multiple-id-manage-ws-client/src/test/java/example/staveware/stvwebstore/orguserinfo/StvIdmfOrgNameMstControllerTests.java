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
 * 組織名マスタ自動テストクラス。
 *
 * @since Staveware Core Ver.5.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvIdmfOrgNameMstControllerTests
        extends StvSimpleAppApplicationTests {
    public static final String PATH_URL = "orgName";

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
        registSameLocale();
        registClear();
        registBack();
    }

    /**
     * 登録入力チェック
     */
    private void registInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("追加");
        Assert.assertEquals("組織名マスタ登録", getHeaderTitle());

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();

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

        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '日本語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys(
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
    }

    /**
     * 登録成功
     */
    private void registSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '日本語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys("テスト名前①");

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
        Assert.assertEquals("登録が完了しました。", getAlertMessage());

        pressButton("OK");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                4);
        Assert.assertEquals("組織マスタ詳細", getHeaderTitle());

    }

    /**
     * 登録失敗（同じのロケールが登録される場合）
     */
    private void registSameLocale() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("追加");
        Assert.assertEquals("組織名マスタ登録", getHeaderTitle());

        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '日本語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys("テスト名前①②③");

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

        Assert.assertEquals("エラーが発生しました。\nthere are errors in request data",
                getAlertMessage());

        pressButton("OK");
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

        pressButton("追加");
        Assert.assertEquals("組織名マスタ登録", getHeaderTitle());

        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys("テスト名前①");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("クリア");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        Assert.assertEquals("", getInputTextValue("locale"));
        Assert.assertEquals("", getInputTextValue("orgName"));
        Assert.assertEquals("", getInputTextValue("activeStartTime"));
        Assert.assertEquals("", getInputTextValue("activeEndTime"));
    }

    /**
     * 登録から戻る
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
     * 組織名情報を更新
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

        // 組織名情報を選択
        action = new Actions(driver);
        element = driver.findElement(By.xpath(
                "(//table[@class='el-table__body'])[1]/tbody/tr/td[2]/div"));
        action.doubleClick(element).perform();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);

        updateInputcheck();
        updateReset();
        updateCancel();
        updateSuccess();
        updateBack();
    }

    /**
     * 組織名情報の入力チェック
     */
    private void updateInputcheck() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("編集");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys(
                "テスト名前②012345678901234567890123456789012345678901234567891");

        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                1);
        pressButton("更新");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                2);
        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                3);

        Assert.assertEquals("エラーが発生しました。\ninput check validation error",
                getAlertMessage());

        driver.findElement(By
                .xpath("//div[@class='el-message-box__btns']//span[contains(text(), 'OK')]"))
                .click();
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 2,
                4);

        pressButton("キャンセル");
    }

    /**
     * 組織名情報をリセット
     */
    private void updateReset() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("編集");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys("テスト名前②");

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
        Assert.assertEquals("テスト名前①", getInputTextValue("orgName"));
        Assert.assertEquals("日本語", getInputTextValue("locale"));
    }

    /**
     * 組織名情報をキャンセル
     */
    private void updateCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys("テスト名前②");

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
     * 組織名情報の更新成功
     */
    private void updateSuccess() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        pressButton("編集");
        sleep(DEFAULT_WAIT_TIME_2000L);

        // ロケール選択
        driver.findElement(By.xpath("(//div[@class='el-select'])//input"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        driver.findElement(By.xpath("//span[contains(text(), '英語')]")).click();
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By.id("orgName")).click();
        driver.findElement(By.id("orgName")).clear();
        driver.findElement(By.id("orgName")).sendKeys("テスト名前②");

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
     * 組織名情報を削除
     */
    private void delete() {
        deleteCancel();
        deleteSuccess();

        // 組織情報を削除
        deleteOrg();
    }

    /**
     * 組織名情報の削除キャンセル
     */
    private void deleteCancel() {
        sleep(DEFAULT_WAIT_TIME_2000L);

        driver.findElement(By
                .xpath("(//table[@class='el-table__body'])[1]/tbody/tr/td//span[@class='el-checkbox__inner']"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                1);
        pressButton("削除");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                2);
        pressButton("キャンセル");
        screenshoot(PATH_URL,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 1,
                3);
        Assert.assertEquals("英語",
                driver.findElement(By
                        .xpath("(//table[@class='el-table__body'])[1]/tbody/tr/td[2]/div"))
                        .getText());
    }

    /**
     * 組織名情報の削除成功
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
