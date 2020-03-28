import axios from 'axios';

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
		setCompanyDialogVisible(state, data) {
			state.companyDialogVisible = data;
		},
		setCompanyList(state, data) {
			state.companyList = data;
		},
		setCompanySize(state, data) {
			state.companySize = data;
		},
		setPage(state, data) {
			state.page = data;
		},
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
		setScreen(state, data) {
			state.screen = data;
		},
		setKbn(state, data) {
			state.kbn = data;
		}
	},
	actions: {
		openCompanyDialog(context, {screen, kbn}) {
			context.commit('setScreen', screen);
			context.commit('setKbn', kbn);
			context.commit('setPage', 1);
			return context.dispatch('searchCompanyList').then(() => {
				context.commit('setCompanyDialogVisible', true);
			});
		},
		closeCompanyDialog(context) {
			context.commit('setCompanyDialogVisible', false);
		},
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		addCompany(context, data) {
			context.dispatch(context.state.screen + '/addCompanyOf' + context.state.kbn, data, {root: true});
			context.dispatch('closeCompanyDialog');
		},
		async searchCompanyList(context) {
			const response = await axios({
				method: 'post',
				url: '/idmf_companies/search',
				data: context.state.searchForm,
				params: {
					pageNo: context.state.page,
					pageSize: 10
				}
			});
			console.log(response.data.data);
			context.commit('setCompanySize', response.data.paging.total);
			context.commit('setCompanyList', response.data.data);
		}
	}
}
