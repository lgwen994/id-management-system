<template>
    <div>
    <el-dialog title="Select Company" :visible="companyDialogVisible" width="1100px" @close="closeCompanyDialog">
        <el-row>
            <el-col :span="12">
                <el-card class="box-card" body-style="height:600px;">
                    <div slot="header" class="clearfix">
                        <span>Search</span>
                    </div>
                    <div>
                        <el-form ref="searchForm" :model="searchForm" label-width="110px">
                            <el-form-item label="CompanyCode">
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
                                <el-button @click="clear">Clear</el-button>
                                <el-button @click="search" type="primary">Search</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>Searched List</span>
                    </div>
                    <div>
                        <el-button @click="closeCompanyDialog">Close</el-button>
                        <div>Double-click the data to set.</div>
                        <el-table :data="companyList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addCompany">
                            <el-table-column prop="companyCode" label="Company Code" width="120" sortable></el-table-column>
                            <el-table-column prop="activeStartTime" label="Active Start Time" width="200" sortable :formatter="formatActiveStartTime"></el-table-column>
                            <el-table-column prop="activeEndTime" label="Active End Time" width="200" sortable :formatter="formatActiveEndTime"></el-table-column>
                            <el-table-column prop="companyName" label="Company Name" width="110"></el-table-column>
                            <el-table-column prop="companyType" label="Company Type" width="110"></el-table-column>
                            <el-table-column prop="companyComment" label="Company Comment" width="110"></el-table-column>
                        </el-table>
                        <el-pagination layout="prev, pager, next" :total="companySize" page-size="10" @current-change="handleCurrentChange" :current-page="page" />
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-dialog>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('companyReference', ['searchForm', 'companyDialogVisible', 'companyList', 'companySize', 'page']),
    },
    methods: {
        search() {
            this.$store.dispatch('companyReference/searchCompanyList').catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('companyReference/clearSearchForm');
        },
        handleCurrentChange(val) {
            this.$store.dispatch('companyReference/setPage', val);
            this.$store.dispatch('companyReference/searchCompanyList').catch(error => showErrorMessage(error));
        },
        closeCompanyDialog() {
            this.$store.dispatch('companyReference/closeCompanyDialog');
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        addCompany(row) {
            this.$store.dispatch('companyReference/addCompany', row);
        }
    }
}
</script>
