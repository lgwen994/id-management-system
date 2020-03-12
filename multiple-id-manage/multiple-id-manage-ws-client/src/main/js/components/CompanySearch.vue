<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>会社マスタ検索</span>
                </div>
                <div>
                    <el-form ref="searchForm" :model="searchForm" label-width="100px" id="companySearchForm">
                        <el-form-item label="会社コード">
                            <el-input v-model="searchForm.companyCode" id="companyCode"></el-input>
                                <span id="message_companyCode"></span>
                        </el-form-item>
                        <el-form-item label="有効開始日時">
                            <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="有効終了日時">
                            <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="会社名">
                            <el-input v-model="searchForm.companyName" id="companyName"></el-input>
                            <span id="message_companyName"></span>
                        </el-form-item>
                        <el-form-item label="会社種類">
                            <el-input v-model="searchForm.companyType" id="companyType"></el-input>
                            <span id="message_companyType"></span>
                        </el-form-item>
                        <el-form-item label="会社説明">
                            <el-input v-model="searchForm.companyComment" id="companyComment"></el-input>
                            <span id="message_companyComment"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">クリア</el-button>
                                <el-button @click="search" type="primary" id="searchCompany">検索</el-button>
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
import Vue from 'vue'
import { mapState } from 'vuex'
import CompanySearchResult from './CompanySearchResult.vue'

export default {
    components: {
        'company-search-result': CompanySearchResult
    },
    computed: {
        ...mapState('company', ['searchForm'])
    },
    methods: {
        search(event) {
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
        this.$store.dispatch('common/setTable', {name: "会社マスタ", value: 'company'})
        this.$store.dispatch('common/setNaviList', {name: "会社マスタ検索", path: 'company-search'});
        this.$store.dispatch('common/setSelectedTable', "company");
    }
}
</script>
