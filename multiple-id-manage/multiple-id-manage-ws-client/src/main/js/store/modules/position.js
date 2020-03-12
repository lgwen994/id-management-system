import axios from 'axios';
import {formatDate} from '../../common';

export default {
	namespaced: true,
	state: {
		form: {
			positionId: '',
			positionCode: '',
			userMst: {
				userId: '',
				userCode: '',
				userName: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			orgMst: {
				orgId: '',
				orgCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			titleMst: {
				titleId: '',
				titleCode: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			concurrentFlg: '',
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
			positionId: '',
			positionCode: '',
			userMst: {
				userId: '',
				userCode: '',
				userName: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			orgMst: {
				orgId: '',
				orgCode: '',
				orgName: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			titleMst: {
				titleId: '',
				titleCode: '',
				titleName: '',
				activeStartTime: '',
				activeEndTime: ''
			},
			concurrentFlg: '',
			activeStartTime: '',
			activeEndTime: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deletedFlg: '',
			versionNo: ''
		},
		positionList: null,
		editable: false,
		positionRoleList: null,
		roleList: [],
		initialRoleList: [],
		searchRoleForm: {
		roleCode: null,
		activeStartTime: null,
		activeEndTime: null,
		roleName: null,
		roleType: null,
		roleComment: null
		},
		roleEditable: false,
		agentList: [],
		initialAgentList: [],
		selectedAgent: null,
		agentEditable: false,
		positionIndex: '',
		positionSelectedList: []
	},
	mutations: {
		setForm(state, data) {
			var userMst = {};
			if(data.userMst){
				userMst = data.userMst;
			}
			var orgMst = {};
			if(data.orgMst){
				orgMst = data.orgMst;
			}
			var titleMst = {};
			if(data.titleMst){
				titleMst = data.titleMst;
			}
			state.form = {
				...data,
				concurrentFlg: String(data.concurrentFlg),
				userMst : userMst,
				orgMst : orgMst,
				titleMst : titleMst
			};
		},
		setInitialData(state, data) {
			state.initialData = {
				...data,
				concurrentFlg: String(data.concurrentFlg),
				userMst : {},
				orgMst : {},
				titleMst : {}
			};
		},
		setUser(state, data) {
			state.form.userMst = {
				...data,
				activeStartTime: formatDate(data.activeStartTime),
				activeEndTime: formatDate(data.activeEndTime)
			};
			state.initialData.userMst = {
				...data,
				activeStartTime: formatDate(data.activeStartTime),
				activeEndTime: formatDate(data.activeEndTime)
			};
		},
		setOrg(state, data) {
			state.form.orgMst = {
				...data,
				activeStartTime: formatDate(data.activeStartTime),
				activeEndTime: formatDate(data.activeEndTime)
			};
			state.initialData.orgMst = {
				...data,
				activeStartTime: formatDate(data.activeStartTime),
				activeEndTime: formatDate(data.activeEndTime)
			};
		},
		setTitle(state, data) {
			state.form.titleMst = {
				...data,
				activeStartTime: formatDate(data.activeStartTime),
				activeEndTime: formatDate(data.activeEndTime)
			};
			state.initialData.titleMst = {
				...data,
				activeStartTime: formatDate(data.activeStartTime),
				activeEndTime: formatDate(data.activeEndTime)
			};
		},
		clearForm(state) {
			let clearData = {
				positionId: '',
				positionCode: '',
				userMst: {
					userId: '',
					userCode: '',
					activeStartTime: '',
					activeEndTime: ''
				},
				titleMst: {
					titleId: '',
					titleCode: '',
					activeStartTime: '',
					activeEndTime: ''
				},
				concurrentFlg: '',
				activeStartTime: '',
				activeEndTime: ''
			};
			state.form = Object.assign({}, state.form, clearData);
		},
		setPositionRole(state, data) {
			state.positionRoleList = data;
		},
		setRole(state, data) {
			state.roleList.push({
				role: Object.assign({}, data.role),
				positionRole: Object.assign({}, data.positionRole)
			});
			state.initialRoleList.push({
				role: Object.assign({}, data.role),
				positionRole: Object.assign({}, data.positionRole)
			});
		},
		addRole(state, data) {
			var positionRole = {
				positionRoleCode: state.form.positionCode + '_' + data.roleCode,
				positionId: state.form.positionId,
				roleId: data.roleId,
				activeStartTime: '',
				activeEndTime: ''
			}
			state.roleList.push({role:data, positionRole:positionRole});
		},
		setEditable(state, data) {
			state.editable = data;
		},
		resetRole(state) {
			state.roleList = state.initialRoleList.map(element => {
				return {
					role: Object.assign({}, element.role),
					positionRole: Object.assign({}, element.positionRole)
				};
			});
		},
		setSelectedRole(state, data) {
			state.selectedRole = data;
		},
		deleteRole(state) {
			state.roleList.some(function(v, i){
				if (v.role.roleId === state.selectedRole.role.roleId) state.roleList.splice(i,1);
			});
		},
		clearRoleList(state) {
			state.roleList = [];
			state.initialRoleList = [];
		},
		setRoleEditable(state, data) {
			state.roleEditable = data;
		},
		addTitle(state, data) {
			state.form.titleMst = {
				...data,
				activeStartTime: formatDate(data.activeStartTime),
				activeEndTime: formatDate(data.activeEndTime)
			};
		},
		setPositionId(state, data) {
			state.form.positionId = data;
		},
		setAgentEditable(state, data) {
			state.agentEditable = data;
		},
		setAgentList(state, data) {
			data.forEach((value, index, array) => {
				value = {
					...value,
					index,
					agentCodeEditable:false
				};
			});
			state.agentList = data;
			state.initialAgentList = data.map(agent => Object.assign({}, agent));
		},
		setSelectedAgent(state, data) {
			state.selectedAgent = data;
		},
		setPosition(state, data) {
			state.agentList[state.positionIndex].agentedPositionMst = data;
			state.agentList[state.positionIndex].agentedPositionId = data.positionId;
		},
		addAgent(state) {
			var agent = {
				agentId: '',
				agentCode: '',
				agentedPositionId: '',
				agentedPositionMst : {},
				positionId: state.form.positionId,
				activeStartTime: '',
				activeEndTime: '',
				agentCodeEditable: true,
				index: state.agentList.length
			};
			state.agentList.push(agent);
		},
		setPositionIndex(state, data) {
			state.positionIndex = data;
		},
		deleteAgentList(state) {
			state.agentList.some(function(v, i){
				if (v.index === state.selectedAgent.index) state.agentList.splice(i,1);
			});
		},
		resetAgent(state) {
			state.agentList = state.initialAgentList.map(agent => Object.assign({}, agent));
		},
		clearAgentList(state) {
			state.agentList = [];
			state.initialAgentList = [];
		},
		// 所属の選択情報設定
		setPositionSelectedList(state, data) {
			state.positionSelectedList = data;
		},
        setUserOfRegist(state, data) {
            state.form.userMst = {
                ...data,
                activeStartTime: formatDate(data.activeStartTime),
                activeEndTime: formatDate(data.activeEndTime)
            };
        }
	},
	actions: {
		showPosition(context, positionId) {
			context.commit('clearRoleList');
			context.commit('clearAgentList');
			return axios({
				method: 'get',
				url: '/idmf_positions/' + positionId
			}).then(function(response) {
				console.log(response);
				let position = response.data;
				let taskList = [
					context.dispatch('searchUser', response.data.userId),
					context.dispatch('searchOrg', response.data.orgId),
					context.dispatch('searchTitle', response.data.titleId)
				];
				Promise.all(taskList).then((response) => {
					context.commit('setForm', position);
					context.commit('setInitialData', position);
					context.commit('setUser', response[0].data);
					context.commit('setOrg', response[1].data);
					context.commit('setTitle', response[2].data);
				});
				context.dispatch('searchPositionRole', response.data.positionId).then(() => {
					for(var i=0; i < context.state.positionRoleList.length; i++) {
						context.dispatch('searchRole', context.state.positionRoleList[i]);
					}
				});
				context.dispatch('searchAgent', response.data.positionId);
			});
		},
		searchUser(context, userId) {
			return axios({
				method: 'get',
				url: '/idmf_users/' + userId
			});
		},
		searchOrg(context, orgId) {
			return axios({
				method: 'get',
				url: '/idmf_orgs/' + orgId
			});
		},
		searchTitle(context, titleId) {
			return axios({
				method: 'get',
				url: '/idmf_titles/' + titleId
			});
		},
		searchPositionRole(context, positionId) {
			return axios({
				method: 'post',
				url: '/idmf_position_roles/search',
				data: {
					positionId
				}
			}).then(function(response) {
				context.commit('setPositionRole', response.data.data);
				console.log(response);
			});
		},
		searchRole(context, positionRole) {
			return axios({
				method: 'get',
				url: '/idmf_roles/' + positionRole.roleId
			}).then(function(response) {
				context.commit('setRole', {role:response.data, positionRole:positionRole});
				console.log(response);
			});
		},
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		updateRole(context) {
			let taskList = [];
			context.state.roleList.forEach(role => {
				let index = context.state.initialRoleList.findIndex(initialRole => {
					return role.role.roleId === initialRole.role.roleId;
				});
				if(index === -1) {
					taskList.push(context.dispatch('registPositionRole', role.positionRole));
				} else {
					taskList.push(context.dispatch('updatePositionRole', role.positionRole));
				}
			});

			let deletePositionRoleList = [];
			context.state.initialRoleList.forEach(initialRole => {
				let index = context.state.roleList.findIndex(role => {
					return role.role.roleId === initialRole.role.roleId;
				})
				if(index === -1) {
					deletePositionRoleList.push({
						...initialRole.positionRole,
						updatedUser: context.rootState.common.user
					});
				}
			});
			if(deletePositionRoleList.length !== 0) {
				taskList.push(context.dispatch('deletePositionRole', deletePositionRoleList));
			}

			return Promise.all(taskList).then(() => {
				context.dispatch('showPosition', context.state.form.positionId);
			});
		},
		updatePositionRole(context, data) {
			return axios({
				method: 'put',
				url: '/idmf_position_roles/',
				data: {
					positionRoleId: data.positionRoleId,
					positionRoleCode: data.positionRoleCode,
					positionId: data.positionId,
					roleId: data.roleId,
					activeStartTime: data.activeStartTime,
					activeEndTime: data.activeEndTime,
					createdUser: context.state.form.createdUser,
					updatedUser: context.rootState.common.user,
					versionNo : data.versionNo
				}
			}).then(function(response) {
				console.log(response);
			});
		},
		deletePositionRole(context, positionRoleList) {
			return axios({
				method: 'post',
				url: '/idmf_position_roles/bulk_delete',
				data: positionRoleList
			}).then(function(response) {
				console.log(response);
			});
		},
		registPositionRole(context, data) {
			return axios({
				method: 'post',
				url: '/idmf_position_roles/',
				data: {
					positionRoleCode: data.positionRoleCode,
					positionId: data.positionId,
					roleId: data.roleId,
					activeStartTime: data.activeStartTime,
					activeEndTime: data.activeEndTime,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				console.log(response);
			});
		},
		updatePosition(context) {
			return axios({
				method: 'put',
				url: '/idmf_positions/',
				data: {
					positionId: context.state.form.positionId,
					positionCode: context.state.form.positionCode,
					userId: context.state.form.userMst.userId,
					orgId: context.state.form.orgMst.orgId,
					titleId: context.state.form.titleMst.titleId,
					concurrentFlg: context.state.form.concurrentFlg,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime: context.state.form.activeEndTime,
					createdUser: context.state.form.createdUser,
					updatedUser:context.rootState.common.user,
					versionNo : context.state.form.versionNo
				}
			}).then(function(response) {
				console.log(response);
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
				context.dispatch('showPosition', response.data.positionId);
			});
		},
		addRole(context, data) {
			context.commit('addRole', data);
			context.dispatch('roleReference/closeRoleDialog', null, {root: true});
		},
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},
		setSelectedRole(context, data) {
			context.commit('setSelectedRole', data);
		},
		deleteRole(context) {
			context.commit('deleteRole');
		},
		addTitle(context, data) {
			context.commit('addTitle', data);
		},
		regist(context) {
			return axios({
				method: 'post',
				url: '/idmf_positions/',
				data: {
					...context.state.form,
					orgId: context.state.form.orgMst.orgId,
					titleId: context.state.form.titleMst.titleId,
					// 入力できないのでダミーデータを設定
					//userId: context.state.form.userMst.userId,
					userId: 'I_USER_01',
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				context.commit('setPositionId', response.data.positionId);
				console.log(response);
			});
		},
		clearForm(context) {
			context.commit('clearForm');
		},
		roleChangeMode(context, data) {
			context.commit('setRoleEditable', data);
		},
		resetRole(context) {
			context.commit('resetRole');
		},
		setOrg(context, data) {
			context.commit('setOrg', data);
		},
		agentChangeMode(context, data) {
			context.commit('setAgentEditable', data);
		},
		searchAgent(context, positionId) {
			return axios({
				method: 'post',
				url: '/idmf_agents/search',
				data: {
					positionId
				}
			}).then(function(response) {
				console.log(response.data);
				let agentList = response.data.data;
				let positionIdList = [...new Set(agentList.map(agent => agent.agentedPositionId))];
				let searchPositionTask = positionIdList.map(positionId => context.dispatch('searchPosition', positionId));
				Promise.all(searchPositionTask).then(response => {
					agentList.forEach((agent, index) => {
						let agentedPosition = response.find(position => {
							return agent.agentedPositionId === position.data.positionId;
						});
						agent.agentedPositionMst = agentedPosition.data;
						agent.index = index;
					});
					context.commit('setAgentList', agentList);
				});
			});
		},
		searchPosition(context, positionId) {
			return axios({
				method: 'get',
				url: '/idmf_positions/' + positionId
			});
		},
		setPosition(context, data) {
			context.commit('setPosition', data);
		},
		setPositionIndex(context, data) {
			context.commit('setPositionIndex', data);
		},
		setSelectedAgent(context, data) {
			context.commit('setSelectedAgent', data);
		},
		addAgent(context) {
			context.commit('addAgent');
		},
		deleteAgentList(context) {
			context.commit('deleteAgentList');
		},
		updateAgentList(context) {
			let taskList = context.state.agentList.map(agent => {
				if(agent.agentId === '') {
					return context.dispatch('registAgent', agent);
				} else{
					return context.dispatch('updateAgent', agent);
				}
			});

			let deleteAgentList = [];
			context.state.initialAgentList.forEach(initialAgent => {
				let index = context.state.agentList.findIndex(agent => {
					return agent.agentId === initialAgent.agentId;
				});
				if(index === -1) {
					deleteAgentList.push({
						...initialAgent,
						updatedUser: context.rootState.common.user
					});
				}
			});
			if(deleteAgentList.length !== 0) {
				taskList.push(context.dispatch('deleteAgent', deleteAgentList));
			}

			return Promise.all(taskList).then(() => {
				context.dispatch('showPosition', context.state.form.positionId);
			});
		},
		updateAgent(context, agent) {
			return axios({
				method: 'put',
				url: '/idmf_agents/',
				data: {
					...agent,
					updatedUser: context.rootState.common.user
				}
			}).then(function(response) {
				console.log(response);
			});
		},
		registAgent(context, agent) {
			return axios({
				method: 'post',
				url: '/idmf_agents/',
				data: {
					...agent,
					createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				console.log(response);
			});
		},
		deleteAgent(context, agentList) {
			return axios({
				method: 'post',
				url: '/idmf_agents/bulk_delete',
				data: agentList
			}).then(function(response) {
				console.log(response);
			});
		},
		resetAgent(context) {
			context.commit('resetAgent');
		},
		// 所属の選択情報を設定
		setPositionSelectedList(context, data) {
			context.commit('setPositionSelectedList', data);
		},
		// 選択された所属情報を削除
		deletePosition(context) {
			context.state.positionSelectedList.map((element) => {
				return {
					...element,
					updatedUser: context.rootState.common.user
				}
			})
			return axios({
				method: 'post',
				url: '/idmf_positions/bulk_delete',
				data: context.state.positionSelectedList
			}).then(function(response) {
				console.log(response.data);
			});
		},
		// Dialogで選択されたユーザ情報を追加
        addUserOfRegist(context, data) {
            context.commit('setUserOfRegist', data);
        }
	}
}
