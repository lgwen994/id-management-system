<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>Login Policy Search</span>
                </div>
                <div>
                    <el-form ref="loginPolicySearchForm" :model="loginPolicySearchForm" label-width="200px" id="loginPolicySearchForm">
                        <el-form-item label="Login Policy Code">
                            <el-input v-model="loginPolicySearchForm.loginPolicyCode" id="loginPolicyCode"></el-input>
                            <span id="message_loginPolicyCode"></span>
                        </el-form-item>
                        <el-form-item label="Company Code">
                            <el-input v-model="loginPolicySearchForm.companyCode" id="companyCode"></el-input>
                            <span id="message_companyCode"></span>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker type="datetime" v-model="loginPolicySearchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker type="datetime" v-model="loginPolicySearchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Password Expire Term">
                            <el-input v-model="loginPolicySearchForm.passwordExpireTerm" id="passwordExpireTerm"></el-input>
                            <span id="message_passwordExpireTerm"></span>
                        </el-form-item>
                        <el-form-item label="Login Failure Count Max Failure">
                            <el-input v-model="loginPolicySearchForm.passwordMaxFailure" id="passwordMaxFailure"></el-input>
                            <span id="message_passwordMaxFailure"></span>
                        </el-form-item>
                        <el-form-item label="Login Failure Count Failure Reset Term">
                            <el-input v-model="loginPolicySearchForm.passwordFailureResetTerm" id="passwordFailureResetTerm"></el-input>
                            <span id="message_passwordFailureResetTerm"></span>
                        </el-form-item>
                        <el-form-item label="Account Lock Out Term">
                            <el-input v-model="loginPolicySearchForm.lockoutTerm" id="lockoutTerm"></el-input>
                            <span id="message_lockoutTerm"></span>
                        </el-form-item>
                        <el-form-item label="Session Max Failure">
                            <el-input v-model="loginPolicySearchForm.maxSessionLimit" id="maxSessionLimit"></el-input>
                            <span id="message_maxSessionLimit"></span>
                        </el-form-item>
                        <el-form-item label="Permit Login Time">
                            <el-input v-model="loginPolicySearchForm.permitTime" id="permitTime"></el-input>
                            <span id="message_permitTime"></span>
                        </el-form-item>
                        <el-form-item label="Permit Domain">
                            <el-input v-model="loginPolicySearchForm.permitDomain" id="permitDomain"></el-input>
                            <span id="message_permitDomain"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary" id="searchLoginPolicy">Search</el-button>
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
            search() {
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
            this.$store.dispatch('common/setTable', {name: "Login Policy ", value: 'loginPolicy'})
            this.$store.dispatch('common/setNaviList', {name: "Login Policy Search", path: 'loginPolicy-search'});
            this.$store.dispatch('common/setSelectedTable', "loginPolicy");
        }
    }
</script>
