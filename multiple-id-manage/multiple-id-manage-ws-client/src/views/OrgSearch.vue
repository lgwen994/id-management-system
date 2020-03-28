<template>
    <el-row>
        <el-col :span="12">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>Orgnazation Search</span>
                </div>
                <div>
                    <el-form ref="searchForm" :model="searchForm" label-width="110px" id="orgSearchForm">
                        <el-form-item label="Orgnazation Code">
                            <el-input v-model="searchForm.orgCode" id="orgCode"></el-input>
                            <span id="message_orgCode"></span>
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
                            <el-radio v-model="searchForm.groupFlg" label="0">Real Orgnazation </el-radio>
                            <el-radio v-model="searchForm.groupFlg" label="1">Logical Orgnazation </el-radio>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Orgnazation Name">
                            <el-input v-model="searchForm.orgName" id="orgName"></el-input>
                            <span id="message_orgName"></span>
                        </el-form-item>
                        <el-form-item label="Orgnazation Type">
                            <el-input v-model="searchForm.orgType" id="orgType"></el-input>
                            <span id="message_orgType"></span>
                        </el-form-item>
                        <el-form-item>
                            <div style="text-align:right;">
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary" id="searchOrg">Search</el-button>
                            </div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
            <company-dialog></company-dialog>
        </el-col>
        <el-col :span="12">
            <org-search-result></org-search-result>
        </el-col>
    </el-row>
</template>

<script>
import { mapState } from 'vuex'
import { showErrorMessage } from '../common'
import OrgSearchResult from './OrgSearchResult.vue';
import CompanyReference from './CompanyReference.vue';

export default {
    components: {
        'org-search-result': OrgSearchResult,
        'company-dialog': CompanyReference
    },
    computed: {
        ...mapState('org', {
            company(state) {
                if(state.searchForm.companyMst.companyId !== '') {
                    return state.searchForm.companyMst.companyCode + ':' + state.searchForm.companyMst.companyName + '(' + state.searchForm.companyMst.activeStartTime + '-' + state.searchForm.companyMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('org', ['searchForm', 'searchResultVisible'])
    },
    methods: {
        search() {
            this.$store.dispatch('org/setPage', 1);
            this.$store.dispatch('org/searchOrgList').then(() => {
                this.$store.dispatch('org/setSearchResultVisible', true);
            }).catch(error => showErrorMessage(error));
        },
        openCompanyDialog() {
            this.$store.dispatch('companyReference/openCompanyDialog', {screen: 'org', kbn: 'Search'});
        },
        clear() {
            this.$store.dispatch('org/clearSearchForm');
        }
    },
    created: function() {
        if(this.$store.state.org.searchResultVisible === true){
            this.$store.dispatch('org/searchOrgList');
        }
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setTable', {name: 'Orgnazation ', value: 'org'})
        this.$store.dispatch('common/setNaviList', {name: 'Orgnazation Search', path: 'org-search'});
        this.$store.dispatch('common/setSelectedTable', 'org');
    }
}
</script>
