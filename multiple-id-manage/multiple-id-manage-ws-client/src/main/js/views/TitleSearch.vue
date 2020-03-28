<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>Title Search</span>
                </div>
                <div>
                    <el-form ref="searchForm" :model="searchForm" label-width="110px" id="titleSearchForm">
                        <el-form-item label="Title Code">
                            <el-input v-model="searchForm.titleCode" id="titleCode"></el-input>
                            <span id="message_TitleCode"></span>
                        </el-form-item>
                        <el-form-item label="Company">
                            <el-col :span="20">
                                <el-input v-model="company" :disabled="true" id="company"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" @click="openCompanyDialog">Dialog</el-button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="Group Flg">
                            <el-radio v-model="searchForm.groupFlg" label="0">Real Title </el-radio>
                            <el-radio v-model="searchForm.groupFlg" label="1">Logical Title </el-radio>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Title Name">
                            <el-input v-model="searchForm.titleName" id="titleName"></el-input>
                            <span id="message_titleName"></span>
                        </el-form-item>
                        <el-form-item label="Title Type">
                            <el-input v-model="searchForm.titleType" id="titleType"></el-input>
                            <span id="message_titleType"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary" id="searchTitle">Search</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
            <company-dialog></company-dialog>
        </el-col>
        <el-col :span="12">
            <title-search-result></title-search-result>
        </el-col>
    </el-row>
</template>

<script>
import { mapState } from 'vuex'
import { showErrorMessage } from '../common'
import TitleSearchResult from './TitleSearchResult.vue';
import CompanyReference from './CompanyReference.vue';

export default {
    components: {
        'title-search-result': TitleSearchResult,
        'company-dialog': CompanyReference
    },
    computed: {
        ...mapState('title', {
            company(state) {
                if(state.searchForm.companyMst.companyId !== '') {
                    return state.searchForm.companyMst.companyCode + ':' + state.searchForm.companyMst.companyName + '(' + state.searchForm.companyMst.activeStartTime + '-' + state.searchForm.companyMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('title', ['searchForm', 'searchResultVisible'])
    },
    methods: {
        search() {
            this.$store.dispatch('title/setPage', 1);
            this.$store.dispatch('title/searchTitleList').then(() => {
                this.$store.dispatch('title/setSearchResultVisible', true);
            }).catch(error => showErrorMessage(error));
        },
        openCompanyDialog() {
            this.$store.dispatch('companyReference/openCompanyDialog', {screen: 'title', kbn: 'Search'});
        },
        clear() {
            this.$store.dispatch('title/clearSearchForm');
        }
    },
    created: function() {
        if(this.$store.state.title.searchResultVisible === true){
            this.$store.dispatch('title/searchTitleList');
        }else{
            this.$store.dispatch('title/clearSearchForm');
        }
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setTable', {name: 'Title ', value: 'title'})
        this.$store.dispatch('common/setNaviList', {name: 'Title Search', path: 'title-search'});
        this.$store.dispatch('common/setSelectedTable', 'title');
    }
}
</script>
