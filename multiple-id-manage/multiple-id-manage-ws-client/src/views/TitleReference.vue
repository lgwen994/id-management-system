<template>
    <el-dialog title="Title Selection" :visible="titleDialogVisible" width="1100px" @close="closeTitleDialog">
        <el-row>
            <el-col :span="12">
                <el-card class="box-card" body-style="height:600px;">
                    <div slot="header" class="clearfix">
                        <span>Search</span>
                    </div>
                    <div>
                        <el-form ref="searchForm" :model="searchForm" label-width="110px">
                            <el-form-item label="Title Code">
                                <el-input v-model="searchForm.titleCode"></el-input>
                            </el-form-item>
                            <el-form-item label="Company">
                                <el-input v-model="company" :disabled="true"></el-input>
                            </el-form-item>
                            <el-form-item label="Group Flg">
                                <el-radio v-model="searchForm.groupFlg" label="0">Real Title </el-radio>
                                <el-radio v-model="searchForm.groupFlg" label="1">Logical Title </el-radio>
                            </el-form-item>
                            <el-form-item label="Active Start Time">
                                <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="Active End Time">
                                <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="Title Name">
                                <el-input v-model="searchForm.titleName"></el-input>
                            </el-form-item>
                            <el-form-item label="Title Type">
                                <el-input v-model="searchForm.titleType"></el-input>
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
                        <el-button @click="closeTitleDialog">Close</el-button>
                        <div>Double-click the data to set the data.</div>
                        <el-table :data="titleList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addTitle">
                            <el-table-column prop="titleCode" label="Title Code" width="110"></el-table-column>
                            <el-table-column prop="companyMst.companyCode" label="Company" width="200"></el-table-column>
                            <el-table-column prop="groupFlg" label="Group Flg" width="110" :formatter="groupLabel"></el-table-column>
                            <el-table-column prop="activeStartTime" label="Active Start Time" width="200" :formatter="formatActiveStartTime"></el-table-column>
                            <el-table-column prop="activeEndTime" label="Active End Time" width="200" :formatter="formatActiveEndTime"></el-table-column>
                            <el-table-column prop="titleName" label="Title Name" width="80"></el-table-column>
                            <el-table-column prop="titleType" label="Title Type" width="100"></el-table-column>
                        </el-table>
                        <el-pagination layout="prev, pager, next" :total="titleSize" page-size="10" @current-change="handleCurrentChange" :current-page="page" />
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-dialog>
</template>

<script>
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('titleReference', ['searchForm', 'titleDialogVisible', 'titleList', 'titleSize', 'page']),
        ...mapState('titleReference', {
            company(state) {
                if(state.searchForm.companyMst.companyId !== '') {
                    return state.searchForm.companyMst.companyCode + ':' + state.searchForm.companyMst.companyName + '(' + state.searchForm.companyMst.activeStartTime + '-' + state.searchForm.companyMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        })
    },
    methods: {
        search() {
            this.$store.dispatch('titleReference/setPage', 1);
            this.$store.dispatch('titleReference/searchTitleList').catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('titleReference/clearSearchForm');
        },
        handleCurrentChange(val) {
            this.$store.dispatch('titleReference/setPage', val);
            this.$store.dispatch('titleReference/searchTitleList').catch(error => showErrorMessage(error));
        },
        closeTitleDialog() {
            this.$store.dispatch('titleReference/closeTitleDialog');
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        addTitle(row) {
            this.$store.dispatch('titleReference/addTitle', row);
        },
        groupLabel(row) {
            if(row.groupFlg === 0) {
                return 'Real Title ';
            } else if(row.groupFlg === 1) {
                return 'Logical Title ';
            }
        }
    }
}
</script>
