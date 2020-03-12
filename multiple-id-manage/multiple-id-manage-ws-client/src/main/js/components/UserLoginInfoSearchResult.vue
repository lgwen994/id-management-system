<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>ユーザ_ログイン情報一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteUserLoginInfo" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="userLoginInfoList" stripe border ref="userLoginInfoTable" style="width: 100%" @row-dblclick="showUserLoginInfo" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="userLoginInfoCode" label="ユーザ_ログイン情報コード" width="160" sortable="custom"></el-table-column>
                    <el-table-column prop="userId" label="ユーザID" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="loginId" label="ログインID" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="companyCode" label="会社コード" width="110" sortable="custom"></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="200" sortable="custom" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="200" sortable="custom" :formatter="formatActiveEndTime"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="userLoginInfoSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
                </el-pagination>
            </div>
        </el-card>
    </div>
</template>

<script>
import { mapState } from 'vuex';
import Vue from 'vue'
import {formatDate, showErrorMessage} from '../common';

export default {
    computed: {
       ...mapState('userLoginInfo', [
           'userLoginInfoList',
           'userLoginInfoSize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('userLoginInfo/setPage', val);
            this.$store.dispatch('userLoginInfo/searchUserLoginInfoList');
        },
        showUserLoginInfo(row) {
            this.$router.push({ name: 'userLoginInfoUpdate', params: { userLoginInfoId: row.userLoginInfoId }});
            this.$store.dispatch('common/setNaviList', {name: 'ユーザ_ログイン情報詳細', path: 'userLoginInfo-update'});
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('userLoginInfo/setSelectedList', val);
        },
        deleteUserLoginInfo(event) {
            if(this.$store.state.userLoginInfo.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('userLoginInfo/deleteUserLoginInfo').then(() => {
                        this.$alert('削除が完了しました。', '削除完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // キャンセルの場合
                });
            }
        },
        sort({column, prop, order}) {
            let sortKey = prop.replace(/([A-Z])/g,
                function(s) {
                    return '_' + s.charAt(0).toLowerCase();
                }
            );
            if(order === 'descending') {
                sortKey = `-${sortKey}`
            }
            this.$store.dispatch('userLoginInfo/setSortKey', sortKey);
            this.$store.dispatch('userLoginInfo/searchUserLoginInfoList').catch(error => showErrorMessage(error));
        }
    }
}
</script>
