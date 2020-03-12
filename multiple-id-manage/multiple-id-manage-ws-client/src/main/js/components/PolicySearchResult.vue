<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>IDMFポリシ一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deletePolicy" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="policyList" stripe border style="width: 100%" @row-dblclick="showPolicy" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="policyCode" label="ポリシコード" width="500" sortable></el-table-column>
                    <el-table-column prop="effect" label="エフェクト" width="500"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="policySize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
        ...mapState('policy', [
            'policyList',
            'policySize',
            'searchResultVisible',
            'page'
        ])
    },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('policy/setPage', val);
            this.$store.dispatch('policy/searchPolicyList');
        },
        handleSelectionChange(val) {
            this.$store.dispatch('policy/setSelectedList', val);
        },
        showPolicy(row) {
            this.$router.push({ name: 'policyUpdate', params: { policyId: row.policyId }});
            this.$store.dispatch('common/setNaviList', {name: 'IDMFポリシ詳細', path: 'policy-update'});
        },
        deletePolicy(event) {
            if(this.$store.state.policy.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            }else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('policy/deletePolicy').then(() => {
                        var errorMessage = this.$store.state.policy.errorMessage;
                        if (errorMessage != '') {
                            this.$alert(errorMessage, 'エラー', {
                                confirmButtonText: 'OK',
                                type: 'error'
                            });
                        } else {
                            this.$alert('削除が完了しました。', '削除完了', {
                                confirmButtonText: 'OK'
                            });
                        }
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
            this.$store.dispatch('policy/setSortKey', sortKey);
            this.$store.dispatch('policy/searchPolicyList').catch(error => showErrorMessage(error));
        }
    }
}



</script>
