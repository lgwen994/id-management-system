package example.identity.management.webstore.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * StvWebStoreのサーバ側の画面遷移と商品情報を取り扱うコントローラ
 *
 * @since xxxx Ver.1.0
 */
@Controller
public class WebStoreController {

    /**
     * メインページ(ヘッダー部分)を表示する
     *
     * @return メインページを示す文字列
     * @since StvWebStore Ver.1.0
     */
    @RequestMapping(
        value = {"/", "/main"},
        method = RequestMethod.GET,
        produces = MediaType.TEXT_HTML_VALUE + "; charset=UTF-8"
    )
    public String showMain() {
        return "main";
    }

    /**
     * ユーザ名を取得する
     *
     * @return ユーザ名
     * @since StvWebStore Ver.1.0
     */
    @RequestMapping(
        value = {"/user"},
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public  Map<String, Object> getUsername() {

        String name = null;
        name = "\"名無し\"";
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            name = auth.getName();
//        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        return map;
    }

}