<template>
    <el-dialog title="Orgnazation Selection" :visible="orgDialogVisible" width="1100px" @close="closeOrgDialog">
        <el-row>
            <el-col :span="12">
                <el-card class="box-card" body-style="height:600px;">
                    <div slot="header" class="clearfix">
                        <span>Search</span>
                    </div>
                    <div>
                        <el-form ref="searchForm" :model="searchForm" label-width="110px">
                            <el-form-item label="Orgnazation Code">
                                <el-input v-model="searchForm.orgCode"></el-input>
                            </el-form-item>
                            <el-form-item label="Group Flg">
                                <el-radio v-model="searchForm.groupFlg" label="0">Real Orgnazation </el-radio>
                                <el-radio v-model="searchForm.groupFlg" label="1">Logical Orgnazation </el-radio>
                            </el-form-item>
                            <el-form-item label="Active Start Time">
                                <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="Active End Time">
                                <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="Orgnazation Name">
                                <el-input v-model="searchForm.orgName"></el-input>
                            </el-form-item>
                            <el-form-item label="Orgnazation Type">
                                <el-input v-model="searchForm.orgType"></el-input>
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
                        <span>Search List</span>
                    </div>
                    <div>
                        <el-button @click="closeOrgDialog">Close</el-button>
                        <div>Double-click the data to set the data.</div>
                        <el-table :data="orgList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addOrg">
                            <el-table-column prop="orgCode" label="Orgnazation Code" width="110"></el-table-column>
                            <el-table-column prop="groupFlg" label="Group Flg" width="110" :formatter="groupLabel"></el-table-column>
                            <el-table-column prop="activeStartTime" label="Active Start Time" width="200" :formatter="formatActiveStartTime"></el-table-column>
                            <el-table-column prop="activeEndTime" label="Active End Time" width="200" :formatter="formatActiveEndTime"></el-table-column>
                            <el-table-column prop="orgName" label="Orgnazation Name" width="80"></el-table-column>
                            <el-table-column prop="orgType" label="Orgnazation Type" width="100"></el-table-column>
                        </el-table>
                        <el-pagination layout="prev, pager, next" :total="orgSize" page-size="10" @current-change="handleCurrentChange" :current-page="page" />
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-dialog>
</template>

<script>
//import Vue from 'vue'
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('orgReference', ['searchForm', 'orgDialogVisible', 'orgList', 'orgSize', 'page'])
    },
    methods: {
        //search(event) {
        search() {
            this.$store.dispatch('orgReference/setPage', 1);
            this.$store.dispatch('orgReference/searchOrgList').catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('orgReference/clearSearchForm');
        },
        handleCurrentChange(val) {
            this.$store.dispatch('orgReference/setPage', val);
            this.$store.dispatch('orgReference/searchOrgList').catch(error => showErrorMessage(error));
        },
        closeOrgDialog() {
            this.$store.dispatch('orgReference/closeOrgDialog');
        },
        // formatActiveStartTime(row, column) {
        //     return formatDate(row.activeStartTime);
        // },
        // formatActiveEndTime(row, column) {
        //     return formatDate(row.activeEndTime);
        // },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        addOrg(row) {
            this.$store.dispatch('orgReference/addOrg', row);
        },
        groupLabel(row) {
            if(row.groupFlg === 0) {
                return 'Real Orgnazation ';
            } else if(row.groupFlg === 1) {
                return 'Logical Orgnazation ';
            }
        }
    }
}
</script>
