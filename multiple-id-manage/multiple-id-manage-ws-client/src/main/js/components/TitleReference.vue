<template>
    <el-dialog title="役職マスタ選択" :visible="titleDialogVisible" width="1100px" @close="closeTitleDialog">
        <el-row>
            <el-col :span="12">
                <el-card class="box-card" body-style="height:600px;">
                    <div slot="header" class="clearfix">
                        <span>検索</span>
                    </div>
                    <div>
                        <el-form ref="searchForm" :model="searchForm" label-width="110px">
                            <el-form-item label="役職コード">
                                <el-input v-model="searchForm.titleCode"></el-input>
                            </el-form-item>
                            <el-form-item label="会社マスタ">
                                <el-input v-model="company" :disabled="true"></el-input>
                            </el-form-item>
                            <el-form-item label="グループフラグ">
                                <el-radio v-model="searchForm.groupFlg" label="0">実在役職</el-radio>
                                <el-radio v-model="searchForm.groupFlg" label="1">仮想役職</el-radio>
                            </el-form-item>
                            <el-form-item label="有効開始日時">
                                <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="有効終了日時">
                                <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="役職名">
                                <el-input v-model="searchForm.titleName"></el-input>
                            </el-form-item>
                            <el-form-item label="役職種類">
                                <el-input v-model="searchForm.titleType"></el-input>
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
                        <el-button @click="closeTitleDialog">閉じる</el-button>
                        <div>データをダブルクリックするとデータを設定します。</div>
                        <el-table :data="titleList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addTitle">
                            <el-table-column prop="titleCode" label="役職コード" width="110"></el-table-column>
                            <el-table-column prop="companyMst.companyCode" label="会社マスタ" width="200"></el-table-column>
                            <el-table-column prop="groupFlg" label="グループフラグ" width="110" :formatter="groupLabel"></el-table-column>
                            <el-table-column prop="activeStartTime" label="有効開始日時" width="200" :formatter="formatActiveStartTime"></el-table-column>
                            <el-table-column prop="activeEndTime" label="有効終了日時" width="200" :formatter="formatActiveEndTime"></el-table-column>
                            <el-table-column prop="titleName" label="役職名" width="80"></el-table-column>
                            <el-table-column prop="titleType" label="役職種別" width="100"></el-table-column>
                        </el-table>
                        <el-pagination layout="prev, pager, next" :total="titleSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
        search(event) {
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
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        addTitle(row) {
            this.$store.dispatch('titleReference/addTitle', row);
        },
        groupLabel(row) {
            if(row.groupFlg === 0) {
                return '実在役職';
            } else if(row.groupFlg === 1) {
                return '仮想役職';
            }
        }
    }
}
</script>
