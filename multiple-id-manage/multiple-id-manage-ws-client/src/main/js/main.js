import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import locale from "element-ui/lib/locale/lang/ja";
import { Loading } from "element-ui";

Vue.config.productionTip = false;

Vue.use(ElementUI, {locale});

let loading;

// 通信中に表示を切り替える
axios.interceptors.request.use(function (config) {
	loading = Loading.service({ fullscreen: true });
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
	loading.close();
    return response;
}, function (error) {
    // Do something with response error
	loading.close();
    return Promise.reject(error);
});

axios.defaults.baseURL = 'http://localhost:8080';

  new Vue({
    router,
    store,
    render: h => h(App)
  }).$mount("#app");

Vue.config.devtools = true