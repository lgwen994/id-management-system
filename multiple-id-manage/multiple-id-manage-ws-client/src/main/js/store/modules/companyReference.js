import axios from 'axios';
import {formatDate} from '../../common';

export default {
	namespaced: true,
	state() {
		return {
			companyDialogVisible: false,
			searchForm: {
				companyCode: null,
				activeStartTime: null,
				activeEndTime: null,
				companyName: null,
				companyType: null,
				companyComment: null
			},
			companySize: '',
			companyList: [],
			page: '',
			screen: '',
			kbn: ''
		}
	},
	mutations: {
		// 会社Dialog表示状態の設定
		setCompanyDialogVisible(state, data) {
			state.companyDialogVisible = data;
		},
		// 会社一覧情報の設定
		setCompanyList(state, data) {
			state.companyList = data;
		},
		// 会社一覧サイズの設定
		setCompanySize(state, data) {
			state.companySize = data;
		},
		// ページ設定
		setPage(state, data) {
			state.page = data;
		},
		// 検索条件フォームクリア
		clearSearchForm(state) {
			state.searchForm = {
					companyCode: null,
					activeStartTime: null,
					activeEndTime: null,
					companyName: null,
					companyType: null,
					companyComment: null
				};
		},
		// 処理切り替えの設定
		setScreen(state, data) {
			state.screen = data;
		},
		// 会社情報設定の区分（検索、または　表示「登録、更新」）
		setKbn(state, data) {
			state.kbn = data;
		}
	},
	actions: {
		// 会社Dialogを起動
		openCompanyDialog(context, {screen, kbn}) {
			context.commit('setScreen', screen);
			context.commit('setKbn', kbn);
			context.commit('setPage', 1);
			return context.dispatch('searchCompanyList').then(response => {
			}).then(() => {
				context.commit('setCompanyDialogVisible', true);
			});
		},
		// 会社Dialogをクローズ
		closeCompanyDialog(context) {
			context.commit('setCompanyDialogVisible', false);
		},
		// 検索条件フォームをクリア
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		// 選択された会社情報を追加
		addCompany(context, data) {
			context.dispatch(context.state.screen + '/addCompanyOf' + context.state.kbn, data, {root: true});
			context.dispatch('closeCompanyDialog');
		},
		// 会社一覧情報を検索
		searchCompanyList(context) {
			return axios({
				method: 'post',
				url: '/idmf_companies/search',
				data: context.state.searchForm,
				params: {
					pageNo: context.state.page,
					pageSize: 10
				}
			}).then(function(response) {
				console.log(response.data.data);
				context.commit('setCompanySize', response.data.paging.total);
				context.commit('setCompanyList', response.data.data);
			});
		}
	}
}
