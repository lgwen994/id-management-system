import axios from 'axios';
import {formatDate} from '../../common';
import store from '../store';

export default {
	namespaced: true,
	state: {
		searchForm: {
            companyId: null,
			companyMst: {
				companyId: null,
				companyCode: null,
				activeStartTime: null,
				activeEndTime: null
			},
			locale: null,
			companyName: null
		},
		form: {
			companyNameId: '',
            companyId : '',
			companyMst: {
				companyId: '',
				companyCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			companyName: '',
			activeStartTime: '',
			activeEndTime: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deleteFlg: '',
			versionNo: ''
		},
		initialData: {
			companyNameId: '',
            companyId : '',
			companyMst: {
				companyId: '',
				companyCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			companyName: '',
			activeStartTime: '',
			activeEndTime: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deleteFlg: '',
			versionNo: ''
		},
		companyNameList: null,
		companyNameSize:'',
		selectedList: [],
		prevPage:'',
		editable: false,
		searchResultVisible: false
	},
	mutations: {
		setCompanyNameList(state, data) {
			state.companyNameList = data;
		},
		setCompanyNameSize(state, data) {
			state.companyNameSize = data;
		},
		clearForm(state) {
			state.form.locale = '';
			state.form.companyName = '';
			state.form.activeStartTime = '';
			state.form.activeEndTime = '';
		},
		clearSearchForm(state) {
			state.searchForm.companyMst.companyId = null;
			state.searchForm.companyMst.companyCode = null;
			state.searchForm.companyMst.activeStartTime = null;
			state.searchForm.companyMst.activeEndTime = null;
			state.searchForm.locale = null;
			state.searchForm.companyName = null;
		},
		setForm(state, data) {
			state.form.companyNameId =  data.companyNameId;
            state.form.companyId = data.companyId;
			state.form.companyMst.companyId = data.companyId;
			state.form.locale =  data.locale;
			state.form.companyName =  data.companyName;
			state.form.activeStartTime =  data.activeStartTime;
			state.form.activeEndTime =  data.activeEndTime;
			state.form.createdTime =  data.createdTime;
			state.form.createdUser =  data.createdUser;
			state.form.updatedTime =  data.updatedTime;
			state.form.updatedUser =  data.updatedUser;
			state.form.deletedFlg =  data.deletedFlg;
			state.form.versionNo =  data.versionNo;
		},
		setInitialData(state, data) {
			state.initialData.companyNameId =  data.companyNameId;
            state.initialData.companyId = data.companyId;
			state.initialData.companyMst.companyId = data.companyId;
			state.initialData.locale =  data.locale;
			state.initialData.companyName =  data.companyName;
			state.initialData.activeStartTime =  data.activeStartTime;
			state.initialData.activeEndTime =  data.activeEndTime;
			state.initialData.createdTime =  data.createdTime;
			state.initialData.createdUser =  data.createdUser;
			state.initialData.updatedTime =  data.updatedTime;
			state.initialData.updatedUser =  data.updatedUser;
			state.initialData.deletedFlg =  data.deletedFlg;
			state.initialData.versionNo =  data.versionNo;
		},
		setCompany(state, data) {
			state.form.companyMst.companyId = data.companyId;
			state.form.companyMst.companyCode = data.companyCode;
			state.form.companyMst.activeStartTime = formatDate(data.activeStartTime);
			state.form.companyMst.activeEndTime = formatDate(data.activeEndTime);
		},
		setSelectedList(state, data) {
			state.selectedList = data;
		},
		setCompanyNameId(state, data) {
			state.form.companyNameId = data;
		},
		setPrevPage(state, data) {
			state.prevPage = data;
		},
		setEditable(state, data) {
			state.editable = data;
		},
		setSearchResultVisible(state, data) {
			state.searchResultVisible = true;
		},
		setActiveTime(state) {
			state.form.activeStartTime = store.state.route.params.activeStartTime;
			state.form.activeEndTime = store.state.route.params.activeEndTime;
		}
	},
	actions: {
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		clearForm(context) {
			context.commit('clearForm');
		},
		showCompanyName(context, companyNameId) {
			axios({
				method: 'get',
				url: '/idmf_company_names/' + companyNameId
			}).then(function(response) {
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
				console.log(response);
				axios({
					method: 'get',
					url: '/idmf_companies/' + response.data.companyId
				}).then(function(response) {
					context.commit('setCompany', response.data);
					console.log(response);
				});
			});
		},
		updateCompanyName(context) {
			return axios({
					method: 'put',
					url: '/idmf_company_names/',
					data: {
						companyNameId: context.state.form.companyNameId,
						companyId: context.state.form.companyMst.companyId,
						locale: context.state.form.locale,
						companyName: context.state.form.companyName,
						activeStartTime: context.state.form.activeStartTime,
						activeEndTime:context.state.form.activeEndTime,
                        createdUser: context.state.form.createdUser,
                        updatedUser: context.rootState.common.user,
						versionNo : context.state.form.versionNo
					}
				}).then(function(response) {
					console.log(response);
					context.commit("setForm", response.data);
					context.commit('setInitialData', response.data);
				});
		},
        deleteCompanyName(context) {
			context.state.selectedList = context.state.selectedList.map((element) => {
                return {
                    ...element,
                    updatedUser: context.rootState.common.user
                }
            })
            return axios({
                method: 'post',
                url: '/idmf_company_names/bulk_delete',
                data: context.state.selectedList
            }).then(function(response) {
                console.log(response);
                context.dispatch('company/showCompany', context.state.form.companyMst.companyId, {root: true});
            });
        },
		registCompanyName(context) {
			return axios({
				method: 'post',
				url: '/idmf_company_names/',
				data: {
					companyId: context.state.form.companyMst.companyId,
					locale: context.state.form.locale,
					companyName: context.state.form.companyName,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime:context.state.form.activeEndTime,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				context.commit("setCompanyNameId", response.data.companyNameId);
				console.log(response);
			});
		},
		setSelectedList(context, data) {
			context.commit('setSelectedList', data);
		},
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
		setPrevPage(context, data) {
			context.commit('setPrevPage', data);
		},
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		setSearchResultVisible(context, data) {
			context.commit('setSearchResultVisible', data);
		},
		setCompany(context) {
			var data = {
				companyId: store.state.route.params.companyId,
				companyCode: store.state.route.params.companyCode,
				activeStartTime: store.state.route.params.activeStartTime,
				activeEndTime: store.state.route.params.activeEndTime
			}
			context.commit('setCompany', data);
		},
		setActiveTime(context) {
			context.commit('setActiveTime');
		}
	}
}
