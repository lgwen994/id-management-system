<template>
    <el-dialog title="組織マスタ選択" :visible="orgDialogVisible" width="1100px" @close="closeOrgDialog">
        <el-row>
            <el-col :span="12">
                <el-card class="box-card" body-style="height:600px;">
                    <div slot="header" class="clearfix">
                        <span>検索</span>
                    </div>
                    <div>
                        <el-form ref="searchForm" :model="searchForm" label-width="110px">
                            <el-form-item label="組織コード">
                                <el-input v-model="searchForm.orgCode"></el-input>
                            </el-form-item>
                            <el-form-item label="グループフラグ">
                                <el-radio v-model="searchForm.groupFlg" label="0">実在組織</el-radio>
                                <el-radio v-model="searchForm.groupFlg" label="1">仮想組織</el-radio>
                            </el-form-item>
                            <el-form-item label="有効開始日時">
                                <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="有効終了日時">
                                <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="組織名">
                                <el-input v-model="searchForm.orgName"></el-input>
                            </el-form-item>
                            <el-form-item label="組織種類">
                                <el-input v-model="searchForm.orgType"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="clear">クリア</el-button>
                                <el-button @click="search" type="primary">検索</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>検索一覧</span>
                    </div>
                    <div>
                        <el-button @click="closeOrgDialog">閉じる</el-button>
                        <div>データをダブルクリックするとデータを設定します。</div>
                        <el-table :data="orgList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addOrg">
                            <el-table-column prop="orgCode" label="組織コード" width="110"></el-table-column>
                            <el-table-column prop="groupFlg" label="グループフラグ" width="110" :formatter="groupLabel"></el-table-column>
                            <el-table-column prop="activeStartTime" label="有効開始日時" width="200" :formatter="formatActiveStartTime"></el-table-column>
                            <el-table-column prop="activeEndTime" label="有効終了日時" width="200" :formatter="formatActiveEndTime"></el-table-column>
                            <el-table-column prop="orgName" label="組織名" width="80"></el-table-column>
                            <el-table-column prop="orgType" label="組織種別" width="100"></el-table-column>
                        </el-table>
                        <el-pagination layout="prev, pager, next" :total="orgSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-dialog>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('orgReference', ['searchForm', 'orgDialogVisible', 'orgList', 'orgSize', 'page'])
    },
    methods: {
        search(event) {
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
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        addOrg(row) {
            this.$store.dispatch('orgReference/addOrg', row);
        },
        groupLabel(row) {
            if(row.groupFlg === 0) {
                return '実在組織';
            } else if(row.groupFlg === 1) {
                return '仮想組織';
            }
        }
    }
}
</script>
