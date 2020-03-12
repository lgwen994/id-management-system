<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>ユーザ_ログイン情報検索</span>
                </div>
                <div>
                    <el-form ref="searchForm" :model="searchForm" label-width="190px" id="userLoginInfoSearchForm">
                        <el-form-item label="ユーザ_ログイン情報コード">
                            <el-input v-model="searchForm.userLoginInfoCode" id="userLoginInfoCode"></el-input>
                            <span id="message_userLoginInfoCode"></span>
                        </el-form-item>
                        <el-form-item label="ユーザマスタ">
                            <el-col :span="20">
                                <el-input v-model="user" :disabled="true" id="user"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" @click="openUserDialog">参照</button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="ログインID">
                            <el-input v-model="searchForm.loginId" id="loginId"></el-input>
                            <span id="message_loginId"></span>
                        </el-form-item>
                        <el-form-item label="会社コード">
                            <el-col :span="20">
                                <el-input v-model="company" :disabled="true" id="company"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" @click="openCompanyDialog">参照</button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="有効開始日時">
                            <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="有効終了日時">
                            <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">クリア</el-button>
                                <el-button @click="search" type="primary" id="searchUserLoginInfo">検索</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
            <user-dialog></user-dialog>
            <company-dialog></company-dialog>
        </el-col>
        <el-col :span="12">
            <userLoginInfo-search-result></userLoginInfo-search-result>
        </el-col>
    </el-row>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common'
import UserLoginInfoSearchResult from './UserLoginInfoSearchResult.vue'
import CompanyReference from './CompanyReference.vue';
import UserReference from './UserReference.vue';

export default {
    components: {
        'userLoginInfo-search-result': UserLoginInfoSearchResult,
        'company-dialog': CompanyReference,
        'user-dialog': UserReference
    },
    computed: {
        ...mapState('userLoginInfo', {
            company(state) {
                if(state.searchForm.companyMst.companyId !== '') {
                    return state.searchForm.companyMst.companyCode + ':' + '(' + state.searchForm.companyMst.activeStartTime + '-' + state.searchForm.companyMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            user(state) {
                if(state.searchForm.userMst.userId !== '') {
                    return state.searchForm.userMst.userCode + ':' + '(' + state.searchForm.userMst.activeStartTime + '-' + state.searchForm.userMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }


        }),
        ...mapState('userLoginInfo', ['searchForm'])
    },
    methods: {
        search(event) {
            this.$store.dispatch('userLoginInfo/setPage', 1);
            this.$store.dispatch('userLoginInfo/searchUserLoginInfoList').then(() => {
                this.$store.dispatch('userLoginInfo/setSearchResultVisible', true);
            }).catch(error => showErrorMessage(error));
        },
        openCompanyDialog(event) {
            this.$store.dispatch('companyReference/openCompanyDialog', {screen: 'userLoginInfo', kbn: 'Search'});
        },
        openUserDialog(event) {
            this.$store.dispatch('userReference/openUserDialog', {screen: 'userLoginInfo', kbn: 'Search'});
        },
        clear() {
            this.$store.dispatch('userLoginInfo/clearSearchForm');
        }
    },
    created: function() {
        if(this.$store.state.userLoginInfo.searchResultVisible === true){
            this.$store.dispatch('userLoginInfo/searchUserLoginInfoList');
        }
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setTable', {name: 'ユーザ_ログイン情報', value: 'userLoginInfo'})
        this.$store.dispatch('common/setNaviList', {name: 'ユーザ_ログイン情報検索', path: 'userLoginInfo-search'});
        this.$store.dispatch('common/setSelectedTable', 'userLoginInfo');
    }
}
</script>
