<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>パスワードポリシ一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deletePasswordPolicy" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="passwordPolicyList" stripe border style="width: 100%" @row-dblclick="showPasswordPolicy" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="passwordPolicyCode" label="パスワードポリシコード" width="200" sortable></el-table-column>
                    <el-table-column prop="companyCode" label="会社コード" width="150" sortable></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="150" sortable :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="150" sortable :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="passwordMinLength" label="最低パスワード長" width="150"></el-table-column>
                    <el-table-column prop="passwordLetterType" label="パスワード文字種" width="150" ></el-table-column>
                    <el-table-column prop="passwordMinLetterType" label="パスワード最低文字種" width="180"></el-table-column>
                    <el-table-column prop="passwordInHistory" label="パスワード履歴数" width="180" sortable></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="passwordPolicySize" page-size="10" @current-change="handleCurrentChange"  :current-page="page">
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
        ...mapState('passwordPolicy', [
            'passwordPolicyList',
            'passwordPolicySize',
            'searchResultVisible',
            'page'
        ])
    },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('passwordPolicy/setPage', val);
            this.$store.dispatch('passwordPolicy/searchPasswordPolicyList');
        },
        handleSelectionChange(val) {
            this.$store.dispatch('passwordPolicy/setSelectedList', val);
        },
        showPasswordPolicy(row) {
             this.$router.push({ name: 'passwordPolicyUpdate', params: { passwordPolicyId: row.passwordPolicyId }});
             this.$store.dispatch('common/setNaviList', {name: 'パスワードポリシ詳細', path: 'passwordPolicy-update'});
         },
        deletePasswordPolicy(event) {
            if(this.$store.state.passwordPolicy.selectedList.length == 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('passwordPolicy/deletePasswordPolicy').then(() => {
                        this.$alert('削除が完了しました。', '削除完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
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
            this.$store.dispatch('loginPolicy/setSortKey', sortKey);
            this.$store.dispatch('role/searchLoginPolicyList').catch(error => showErrorMessage(error));
        }
    }
}

</script>
