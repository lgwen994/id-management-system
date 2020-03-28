<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>PasswordPolicy Search</span>
                </div>
                <div>
                    <el-form ref="passwordPolicySearchForm" :model="passwordPolicySearchForm" label-width="180px" id="passwordPolicySearchForm">
                        <el-form-item label="PasswordPolicy Code">
                            <el-input v-model="passwordPolicySearchForm.passwordPolicyCode" id="passwordPolicyCode"></el-input>
                            <span id="message_passwordPolicyCode"></span>
                        </el-form-item>
                        <el-form-item label="Company Code">
                            <el-input v-model="passwordPolicySearchForm.companyCode" id="companyCode"></el-input>
                            <span id="message_companyCode"></span>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker type="datetime" v-model="passwordPolicySearchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker type="datetime" v-model="passwordPolicySearchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Min Password Length">
                            <el-input v-model="passwordPolicySearchForm.passwordMinLength" id="passwordMinLength"></el-input>
                            <span id="message_passwordMinLength"></span>
                        </el-form-item>
                        <el-form-item label="Password LetterType">
                            <el-input v-model="passwordPolicySearchForm.passwordLetterType" id="passwordLetterType"></el-input>
                            <span id="message_passwordLetterType"></span>
                        </el-form-item>
                        <el-form-item label="PasswordMin  LetterType">
                            <el-input v-model="passwordPolicySearchForm.passwordMinLetterType" id="passwordMinLetterType"></el-input>
                            <span id="message_passwordMinLetterType"></span>
                        </el-form-item>
                        <el-form-item label="Password In History">
                            <el-input v-model="passwordPolicySearchForm.passwordInHistory" id="passwordInHistory"></el-input>
                            <span id="message_passwordInHistory"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary" id="searchRole">Search</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </el-col>
        <el-col :span="12">
            <passwordPolicy-search-result></passwordPolicy-search-result>
        </el-col>
    </el-row>
</template>

<script>
import { mapState } from 'vuex'
import PasswordPolicySearchResult from './PasswordPolicySearchResult.vue'
import { showErrorMessage } from '../common'

export default {
    components: {
        'passwordPolicy-search-result': PasswordPolicySearchResult
    },
    computed: {
        ...mapState('passwordPolicy', ['passwordPolicySearchForm'])
    },
    methods: {
        search() {
            this.$store.dispatch('passwordPolicy/setPage', 1);
            this.$store.dispatch('passwordPolicy/searchPasswordPolicyList').then(() => {
                this.$store.dispatch('passwordPolicy/setSearchResultVisible', true);
            }).catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('passwordPolicy/clearSearchForm');
        }
    },
    created: function() {
        if(this.$store.state.passwordPolicy.searchResultVisible === true){
            this.$store.dispatch('passwordPolicy/searchPasswordPolicyList');
        }else{
            this.$store.dispatch('passwordPolicy/clearSearchForm');
        }
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setTable', {name: "PasswordPolicy ", value: 'passwordPolicy'})
        this.$store.dispatch('common/setNaviList', {name: "PasswordPolicy Search", path: 'passwordPolicy-search'});
        this.$store.dispatch('common/setSelectedTable', "passwordPolicy");
    }
}
</script>
