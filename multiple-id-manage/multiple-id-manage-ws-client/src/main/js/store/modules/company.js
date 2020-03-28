import axios from 'axios';

export default {
	namespaced: true,
	state: {
		searchForm: {
		    companyCode: null,
			activeStartTime: null,
			activeEndTime: null,
            companyName: null,
            companyType: null,
            companyComment: null
		},
		form: {
            companyId: '',
            companyCode: '',
			activeStartTime: '',
			activeEndTime: '',
            companyName: '',
            companyType: '',
            companyComment: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deleteFlg: '',
			versionNo: ''
		},
		initialData: {
            companyId: '',
            companyCode: '',
			activeStartTime: '',
			activeEndTime: '',
            companyName: '',
            companyType: '',
            companyComment: '',
			createdTime: '',
			createdUser: '',
			updatedTime: '',
			updatedUser: '',
			deleteFlg: '',
			versionNo: ''
		},
        companyNameList: null,
		editable: false,
        companySize: '',
        companyList: null,
		searchResultVisible: false,
		selectedList: [],
        page: '',
		deleteCompanyNameList:[],
		referencedCompanyList:[],
        errorMessage:'',
        sortKey: ''
	},
    mutations: {
        setForm(state, data) {
            state.form = {
                ...data,
            };
        },
        setInitialData(state, data) {
            state.initialData = {
                ...data,
            };
        },
		setCompanyNameList(state, data) {
			state.companyNameList = data;
		},
		setEditable(state, data) {
			state.editable = data;
		},
		setCompanySize(state, data) {
			state.companySize = data;
		},
		setCompanyList(state, data) {
			state.companyList = data;
		},
		setSearchResultVisible(state) {
			state.searchResultVisible = true;
		},
		clearSearchForm(state) {
			state.searchForm.companyCode = null;
			state.searchForm.activeStartTime = null;
			state.searchForm.activeEndTime = null;
			state.searchForm.companyName = null;
			state.searchForm.companyType = null;
			state.searchForm.companyComment = null;
		},
		setCompanyId(state, data) {
			state.form.companyId = data;
		},
		clearForm(state) {
			state.form.companyId = '';
			state.form.companyCode = '';
			state.form.activeStartTime = '';
			state.form.activeEndTime = '';
			state.form.companyName = '';
			state.form.companyType = '';
			state.form.companyComment = '';
		},
		setActiveStartTime(state) {
			state.form.activeStartTime = new Date();
		},
		setSelectedList(state, data) {
			state.selectedList = data;
		},
        setPage(state, data) {
            state.page = data;
        },
		setDeleteCompanyNameList(state, data) {
			state.deleteCompanyNameList = state.deleteCompanyNameList.concat(data);
		},
		clearDeleteCompanyNameList(state) {
			state.deleteCompanyNameList = [];
		},
		setReferencedCompanyList(state, data) {
			state.referencedCompanyList = state.referencedCompanyList.concat(data);
		},
		clearReferencedCompanyList(state) {
			state.referencedCompanyList = [];
		},
		setErrorMessage(state, data){
    		state.errorMessage = data;
		},
        setSortKey(state, data) {
            state.sortKey = data;
        }
    },
    actions: {
		showCompany(context, companyId) {
			axios({
				method: 'get',
				url: '/idmf_companies/' + companyId
			}).then(function(response) {
				context.commit('setForm', response.data);
				context.commit('setInitialData', response.data);
				console.log(response.data);
				context.dispatch('searchCompanyNameList', companyId);
			});
		},
		changeMode(context, data) {
			context.commit('setEditable', data);
		},
		searchCompanyNameList(context, companyId) {
			axios({
				method: 'get',
				url: '/idmf_company_names/find_by_company/' + companyId
			}).then(function(response) {
				context.commit('setCompanyNameList', response.data);
				console.log(response);
			});
		},
		updateCompany(context) {
			return axios({
				method: 'put',
				url: '/idmf_companies/',
				data: {
					companyId: context.state.form.companyId,
					companyCode: context.state.form.companyCode,
					activeStartTime: context.state.form.activeStartTime,
					activeEndTime:context.state.form.activeEndTime,
					companyName: context.state.form.companyName,
					companyType: context.state.form.companyType,
					companyComment: context.state.form.companyComment,
					versionNo : context.state.form.versionNo,
                    createdUser: context.state.form.createdUser,
                    updatedUser: context.rootState.common.user
				}
			}).then(function(response) {
				console.log(response);
				context.commit("setForm", response.data);
				context.commit('setInitialData', response.data);
			});
		},
		resetForm(context) {
			context.commit('setForm', context.state.initialData);
		},

        searchCompanyList(context) {
            return context.dispatch('searchCompany').then((response) => {
                context.commit('setCompanySize', response.data.paging.total);
                context.commit('setCompanyList', response.data.data);
            });
        },
        searchCompany(context) {
            return axios({
                method: 'post',
                url: '/idmf_companies/search',
                data: context.state.searchForm,
                params: {
                    pageNo: context.state.page,
                    pageSize: 10,
                    sort: context.state.sortKey
                }
            });
        },
        setPage(context, page) {
            context.commit('setPage', page);
        },
		setSearchResultVisible(context, data) {
			context.commit('setSearchResultVisible', data);
		},
		clearSearchForm(context) {
			context.commit('clearSearchForm');
		},
		registCompany(context) {
			return axios({
				method: 'post',
				url: '/idmf_companies/',
				data: {
                       ...context.state.form,
                       createdUser: context.rootState.common.user
				}
			}).then(function(response) {
				context.commit("setCompanyId", response.data.companyId);
				console.log(response);
				
			});
		},
		clearForm(context) {
			context.commit('clearForm');
		},
		setActiveStartTime(context) {
			context.commit('setActiveStartTime');
		},
		setSelectedList(context, data) {
			context.commit('setSelectedList', data);
		},
		deleteCompany(context) {
			return new Promise((resolve) => {
				context.commit("setErrorMessage", '');
				var getCompanyNameListTaskList = [];
				context.commit('clearDeleteCompanyNameList');
				for(var i = 0; i < context.state.selectedList.length; i++) {
					getCompanyNameListTaskList.push(context.dispatch('getCompanyNameList', context.state.selectedList[i].companyId));
				}
                axios({
                    method: 'post',
                    url: '/idmf_companies/bulk_delete',
                    data: context.state.selectedList
                }).then(function() {
                    context.dispatch("searchCompanyList");
                    Promise.all(getCompanyNameListTaskList).then(()=> {
                        axios({
                            method: 'post',
                            url: '/idmf_company_names/bulk_delete',
                            data: context.state.deleteCompanyNameList
                        }).catch(function(error) {
                            if(error.response) {
                                console.log(error.response.data);
                                console.log(error.response.status);
                                console.log(error.response.headers);
                            } else {
                                console.log(error.config);
                            }
                        });
                    });
                    resolve();
                }).catch(function(error) {
                    if(error.response) {
                        context.commit("setErrorMessage", error.response.data.detail);
                        console.log(error.response.data);
                        console.log(error.response.status);
                        console.log(error.response.headers);
                    } else {
                        console.log(error.config);
                    }
                    resolve();
                });
			});
		},
		getCompanyNameList(context, companyId) {
			return new Promise((resolve) => {
				axios({
					method: 'get',
					url: '/idmf_company_names/find_by_company/' + companyId
				}).then(function(response) {
					context.commit('setDeleteCompanyNameList', response.data);
					resolve();
				}).catch(function(error) {
					if(error.response) {
						console.log(error.response.data);
						console.log(error.response.status);
						console.log(error.response.headers);
					} else {
						console.log(error.config);
					}
				});
			});
		},
        setSortKey(context, data) {
            context.commit('setSortKey', data);
        }
    }
}
