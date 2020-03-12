<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>ログインポリシ検索</span>
                </div>
                <div>
                    <el-form ref="loginPolicySearchForm" :model="loginPolicySearchForm" label-width="200px" id="loginPolicySearchForm">
                        <el-form-item label="ログインポリシコード">
                            <el-input v-model="loginPolicySearchForm.loginPolicyCode" id="loginPolicyCode"></el-input>
                            <span id="message_loginPolicyCode"></span>
                        </el-form-item>
                        <el-form-item label="会社コード">
                            <el-input v-model="loginPolicySearchForm.companyCode" id="companyCode"></el-input>
                            <span id="message_companyCode"></span>
                        </el-form-item>
                        <el-form-item label="有効開始日時">
                            <el-date-picker type="datetime" v-model="loginPolicySearchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="有効終了日時">
                            <el-date-picker type="datetime" v-model="loginPolicySearchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="パスワード有効期間">
                            <el-input v-model="loginPolicySearchForm.passwordExpireTerm" id="passwordExpireTerm"></el-input>
                            <span id="message_passwordExpireTerm"></span>
                        </el-form-item>
                        <el-form-item label="ログイン失敗回数上限値">
                            <el-input v-model="loginPolicySearchForm.passwordMaxFailure" id="passwordMaxFailure"></el-input>
                            <span id="message_passwordMaxFailure"></span>
                        </el-form-item>
                        <el-form-item label="ログイン失敗回数解除期間">
                            <el-input v-model="loginPolicySearchForm.passwordFailureResetTerm" id="passwordFailureResetTerm"></el-input>
                            <span id="message_passwordFailureResetTer"></span>
                        </el-form-item>
                        <el-form-item label="アカウントロックアウト期間">
                            <el-input v-model="loginPolicySearchForm.lockoutTerm" id="lockoutTerm"></el-input>
                            <span id="message_lockoutTerm"></span>
                        </el-form-item>
                        <el-form-item label="セッション数上限値">
                            <el-input v-model="loginPolicySearchForm.maxSessionLimit" id="maxSessionLimit"></el-input>
                            <span id="message_maxSessionLimit"></span>
                        </el-form-item>
                        <el-form-item label="許可ログイン時刻">
                            <el-input v-model="loginPolicySearchForm.permitTime" id="permitTime"></el-input>
                            <span id="message_permitTime"></span>
                        </el-form-item>
                        <el-form-item label="許可ドメイン">
                            <el-input v-model="loginPolicySearchForm.permitDomain" id="permitDomain"></el-input>
                            <span id="message_permitDomain"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">クリア</el-button>
                                <el-button @click="search" type="primary" id="searchLoginPolicy">検索</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </el-col>
        <el-col :span="12">
            <loginPolicy-search-result></loginPolicy-search-result>
        </el-col>
    </el-row>
</template>

<script>
    import Vue from 'vue'
    import { mapState } from 'vuex'
    import LoginPolicySearchResult from './LoginPolicySearchResult.vue'
    import { showErrorMessage } from '../common'

    export default {
        components: {
            'loginPolicy-search-result': LoginPolicySearchResult
        },
        computed: {
            ...mapState('loginPolicy', ['loginPolicySearchForm'])
        },
        methods: {
            search(event) {
                this.$store.dispatch('loginPolicy/setPage', 1);
                this.$store.dispatch('loginPolicy/searchLoginPolicyList').then(() => {
                    this.$store.dispatch('loginPolicy/setSearchResultVisible', true);
                }).catch(error => showErrorMessage(error));
            },
            clear() {
                this.$store.dispatch('loginPolicy/clearSearchForm');
            }
        },
        created: function() {
            if(this.$store.state.loginPolicy.searchResultVisible === true){
                this.$store.dispatch('loginPolicy/searchLoginPolicyList');
            }else{
                this.$store.dispatch('loginPolicy/clearSearchForm');
            }
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setTable', {name: "ログインポリシ", value: 'loginPolicy'})
            this.$store.dispatch('common/setNaviList', {name: "ログインポリシ検索", path: 'loginPolicy-search'});
            this.$store.dispatch('common/setSelectedTable', "loginPolicy");
        }
    }
</script>
