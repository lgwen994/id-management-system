<template>
    <div v-if="searchResultVisible">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>ユーザマスタ一覧</span>
            </div>
            <div>
                <el-col :span="16">
                    データをダブルクリックすると詳細を表示します
                </el-col>
                <el-col :span="8">
                    <div style="text-align:right;">
                        <el-button @click="deleteUser" type="primary">削除</el-button>
                    </div>
                </el-col>
                <el-table :data="userList" stripe border style="width: 100%" @row-dblclick="showUser" @selection-change="handleSelectionChange" @sort-change="sort">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="userCode" label="ユーザコード" width="110" sortable></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="200" sortable :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="200" sortable :formatter="formatActiveEndTime"></el-table-column>
                    <el-table-column prop="userName" label="ユーザ名" width="110"></el-table-column>
                    <el-table-column prop="mail" label="メールアドレス" width="110"></el-table-column>
                    <el-table-column prop="tel" label="電話番号" width="110"></el-table-column>
                    <el-table-column prop="fax" label="FAX番号" width="110"></el-table-column>
                    <el-table-column prop="locale" label="ロケール" width="110"></el-table-column>
                </el-table>
                <el-pagination layout="prev, pager, next" :total="userSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
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
       ...mapState('user', [
           'userList',
           'userSize',
           'searchResultVisible',
           'page'
       ])
   },
    methods: {
        handleCurrentChange(val) {
            this.$store.dispatch('user/setPage', val);
            this.$store.dispatch('user/searchUserList');
        },
        showUser(row) {
            this.$router.push({ name: 'userUpdate', params: { userId: row.userId }});
            this.$store.dispatch('common/setNaviList', {name: 'ユーザマスタ詳細', path: 'user-update'});
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        handleSelectionChange(val) {
            this.$store.dispatch('user/setSelectedList', val);
        },
        deleteUser(event) {
            if(this.$store.state.user.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    // ポジション_ロールから参照されている役職があるかを確認する
                    this.$store.dispatch('user/checkPositionUser').then(()=> {
                        if(this.$store.state.user.referencedUserList.length === 0) {
                            this.$store.dispatch('user/deleteUser').then(() => {
                                var errorMessage = this.$store.state.user.errorMessage;
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
                            });
                        } else {
                            this.$alert('ユーザコード('+ this.$store.state.user.referencedUserList +')は、所属から参照されているため削除ができません。', 'エラー', {
                                confirmButtonText: 'OK',
                                type: 'error'
                            });
                        }
                    }).catch(error => showErrorMessage(error));
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
            this.$store.dispatch('user/setSortKey', sortKey);
            this.$store.dispatch('user/searchUserList').catch(error => showErrorMessage(error));
        }
    }
}
</script>
