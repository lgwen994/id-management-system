<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>ログインポリシ一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteLoginPolicy" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="loginPolicyList" stripe border style="width: 100%" @row-dblclick="showLoginPolicy" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="loginPolicyCode" label="ログインポリシコード" width="200" sortable></el-table-column>
                    <el-table-column prop="companyCode" label="会社コード" width="150" sortable></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="150" sortable :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="150" sortable :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="passwordExpireTerm" label="パスワード有効期間" width="180"></el-table-column>
                    <el-table-column prop="passwordMaxFailure" label="ログイン失敗回数上限値" width="180"></el-table-column>
                    <el-table-column prop="passwordFailureResetTerm" label="ログイン失敗回数解除期間" width="150" ></el-table-column>
                    <el-table-column prop="lockoutTerm" label="アカウントロックアウト期間" width="180"></el-table-column>
                    <el-table-column prop="maxSessionLimit" label="セッション数上限値" width="180"></el-table-column>
                    <el-table-column prop="permitTime" label="許可ログイン時刻" width="180"></el-table-column>
                    <el-table-column prop="permitDomain" label="許可ドメイン" width="180"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="loginPolicySize" page-size="10" @current-change="handleCurrentChange"  :current-page="page">
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
            ...mapState('loginPolicy', [
                'loginPolicyList',
                'loginPolicySize',
                'searchResultVisible',
                'page'
            ])
        },
        methods: {
            handleCurrentChange(val) {
                this.$store.dispatch('loginPolicy/setPage', val);
                this.$store.dispatch('loginPolicy/searchLoginPolicyList');
            },
            handleSelectionChange(val) {
                this.$store.dispatch('loginPolicy/setSelectedList', val);
            },
            showLoginPolicy(row) {
                this.$router.push({ name: 'loginPolicyUpdate', params: { loginPolicyId: row.loginPolicyId }});
                this.$store.dispatch('common/setNaviList', {name: 'ログインポリシ詳細', path: 'loginPolicy-update'});
            },
            deleteLoginPolicy(event) {
                if(this.$store.state.loginPolicy.selectedList.length == 0) {
                    this.$alert('削除するデータを選択してください。', '確認', {
                        confirmButtonText: 'OK'
                    });
                } else {
                    this.$confirm('削除します。よろしいですか？', '削除確認', {
                        confirmButtonText: 'OK',
                        cancelButtonText: 'キャンセル',
                        type: 'warning'
                    }).then(() => {
                        this.$store.dispatch('loginPolicy/deleteLoginPolicy').then(() => {
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
