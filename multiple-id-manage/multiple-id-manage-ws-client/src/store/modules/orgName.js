import axios from 'axios';
import {formatDate} from '../../common';
import store from '../';

export default {
	namespaced: true,
	state: {
		form: {
			orgNameId: '',
			orgId: '',
			orgMst: {
				orgId: '',
				orgCode: '',
				orgName: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			orgName: '',
			activeStartTime: '',
			activeEndTime: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deletedFlg: '',
			versionNo: ''
		},
		initialData: {
			orgNameId: '',
			orgId: '',
			orgMst: {
				orgId: '',
				orgCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			locale:'',
			orgName: '',
			activeStartTime: '',
			activeEndTime: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deletedFlg: '',
			versionNo: ''
		},
		selectedList: [],
		editable: false
	},
	mutations: {
		clearForm(state) {
			state.form.orgNameId = '';
			state.form.locale = '';
			state.form.orgName = '';
			state.form.activeStartTime = '';
			state.form.activeEndTime = '';
		},
		setForm(state, data) {
			var orgMst = {};
			if(data.orgMst){
				orgMst = data.orgMst;
			}
			state.form = {
					...data,
					orgMst: orgMst
				};
		},
		setInitialData(state, data) {
			state.initialData = {
					...data,
					orgMst: {}
				};
		},
		setOrg(state, data) {
			state.form.orgId = data.orgId;
			state.form.orgMst = {
					...data,
					activeStartTime: formatDate(data.activeStartTime),
					activeEndTime: formatDate(data.activeEndTime)
				};
			state.initialData.orgId = data.orgId;
			state.initialData.orgMst = {
					...data,
					activeStartTime: formatDate(data.activeStartTime),
					activeEndTime: formatDate(data.activeEndTime)
				};
		},
		setSelectedList(state, data) {
			state.selectedList = data;
		},
		setOrgNameId(state, data) {
			state.form.orgNameId = data;
		},
		setEditable(state, data) {
			state.editable = data;
		},
		setActiveTime(state) {
			state.form.activeStartTime = store.state.route.params.activeStartTime;
			state.form.activeEndTime = store.state.route.params.activeEndTime;
		}
	},
	actions: {
		clearForm(context) {
			context.commit('clearForm');
		},
		async showOrgName(context, orgNameId) {
			const response = await axios({
				method: 'get',
				url: '/idmf_org_names/' + orgNameId
			});
			console.log(response.data);
			context.commit('setForm', response.data);
			context.commit('setInitialData', response.data);
			const response_1 = await axios({
				method: 'get',
				url: '/idmf_orgs/' + response.data.orgId
			});
			console.log(response_1.data);
			context.commit('setOrg', response_1.data);
		},
		async updateOrgName(context) {
			const response = await axios({
				method: 'put',
				url: '/idmf_org_names/',
				data: {
					...context.state.form,
					updatedUser: context.rootState.common.user
				}
			});
			console.log(response.data);
			context.commit('setForm', response.data);
			context.commit('setInitialData', response.data);
		},
		async deleteOrgName(context) {
			context.state.selectedList = context.state.selectedList.map((element) => {
				return {
					...element,
					updatedUser: context.rootState.common.user
				}
			})
			const response = await axios({
				method: 'post',
				url: '/idmf_org_names/bulk_delete',
				data: context.state.selectedList
			});
			console.log(response.data);
		},
		async registOrgName(context) {
			const response = await axios({
				method: 'post',
				url: '/idmf_org_names/',
				data: {
					...context.state.form,
					createdUser: context.rootState.common.user
				}
			});
			console.log(response.data);
			context.commit('setOrgNameId', response.data.orgNameId);
		},
		setSelectedList(context, data) {
			context.commit('setSelectedList', data);
		},
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		setOrg(context) {
			context.commit('setOrg', store.state.route.params);
		},
		setActiveTime(context) {
			context.commit('setActiveTime');
		}
	}
}
