<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>Search Company </span>
                </div>
                <div>
                    <el-form ref="searchForm" :model="searchForm" label-width="100px" id="companySearchForm">
                        <el-form-item label="Company Code">
                            <el-input v-model="searchForm.companyCode" id="companyCode"></el-input>
                                <span id="message_companyCode"></span>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Company Name">
                            <el-input v-model="searchForm.companyName" id="companyName"></el-input>
                            <span id="message_companyName"></span>
                        </el-form-item>
                        <el-form-item label="Company Type">
                            <el-input v-model="searchForm.companyType" id="companyType"></el-input>
                            <span id="message_companyType"></span>
                        </el-form-item>
                        <el-form-item label="Company Comment">
                            <el-input v-model="searchForm.companyComment" id="companyComment"></el-input>
                            <span id="message_companyComment"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary" id="searchCompany">Search</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </el-col>
        <el-col :span="12">
            <company-search-result></company-search-result>
        </el-col>
    </el-row>
</template>C

<script>
import { mapState } from 'vuex'
import CompanySearchResult from './CompanySearchResult.vue'
import {showErrorMessage} from '../common';

export default {
    components: {
        'company-search-result': CompanySearchResult
    },
    computed: {
        ...mapState('company', ['searchForm'])
    },
    methods: {
        search() {
            this.$store.dispatch('company/setPage', 1);
            this.$store.dispatch('company/searchCompanyList').then(() => {
                this.$store.dispatch('company/setSearchResultVisible', true);
            }).catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('company/clearSearchForm');
        }
    },
    created: function() {
        if(this.$store.state.company.searchResultVisible === true){
            this.$store.dispatch('company/searchCompanyList');
        }else{
            this.$store.dispatch('company/clearSearchForm');
        }
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setTable', {name: "Company", value: 'company'})
        this.$store.dispatch('common/setNaviList', {name: "Search Company", path: 'company-search'});
        this.$store.dispatch('common/setSelectedTable', "company");
    }
}
</script>
