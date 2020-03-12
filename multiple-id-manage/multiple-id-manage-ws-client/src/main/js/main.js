import "babel-polyfill";
import Vue from 'vue';
import axios from 'axios';
import { sync } from 'vuex-router-sync';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/ja';
import { Loading } from 'element-ui';

import store from './store/store';
import router from './router';

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
	  el: '#main',
	  store,
	  router
	});

const unsync = sync(store, router);

Vue.config.devtools = true
