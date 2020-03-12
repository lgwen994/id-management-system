<template>
    <div>
        <el-dialog title="ユーザマスタ選択" :visible="userDialogVisible" width="1100px" @close="closeUserDialog">
            <el-row>
                <el-col :span="12">
                    <el-card class="box-card" body-style="height:600px;">
                        <div slot="header" class="clearfix">
                            <span>検索</span>
                        </div>
                        <div>
                            <el-form ref="searchForm" :model="searchForm" label-width="110px">
                                <el-form-item label="ユーザコード">
                                    <el-input v-model="searchForm.userCode" id="userCode"></el-input>
                                    <span id="message_userCode"></span>
                                </el-form-item>
                                <el-form-item label="有効開始日時">
                                    <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                                </el-form-item>
                                <el-form-item label="有効終了日時">
                                    <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                                </el-form-item>
                                <el-form-item label="ユーザ名">
                                    <el-input v-model="searchForm.userName" id="userName"></el-input>
                                    <span id="message_userName"></span>
                                </el-form-item>
                                <el-form-item label="メールアドレス">
                                    <el-input v-model="searchForm.mail" id="mail"></el-input>
                                    <span id="message_mail"></span>
                                </el-form-item>
                                <el-form-item label="電話番号">
                                    <el-input v-model="searchForm.tel" id="tel"></el-input>
                                    <span id="message_tel"></span>
                                </el-form-item>
                                <el-form-item label="FAX番号">
                                    <el-input v-model="searchForm.fax" id="fax"></el-input>
                                    <span id="message_fax"></span>
                                </el-form-item>
                                <el-form-item label="ロケール">
                                    <el-select v-model="searchForm.locale" placeholder="">
                                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                                    </el-select>
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
                            <el-button @click="closeUserDialog">閉じる</el-button>
                            <div>データをダブルクリックするとデータを設定します。</div>
                            <el-table :data="userList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addUser">
                                <el-table-column prop="userCode" label="ユーザコード" width="110" sortable></el-table-column>
                                <el-table-column prop="activeStartTime" label="有効開始日時" width="200" sortable :formatter="formatActiveStartTime"></el-table-column>
                                <el-table-column prop="activeEndTime" label="有効終了日時" width="200" sortable :formatter="formatActiveEndTime"></el-table-column>
                                <el-table-column prop="userName" label="ユーザ名" width="110"></el-table-column>
                                <el-table-column prop="userType" label="ユーザ種類" width="110"></el-table-column>
                                <el-table-column prop="userComment" label="ユーザ説明" width="110"></el-table-column>
                            </el-table>
                            <el-pagination layout="prev, pager, next" :total="userSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-dialog>
    </div>
</template>

<script>
    import Vue from 'vue'
    import { mapState } from 'vuex'
    import { formatDate, showErrorMessage } from '../common';

    export default {
        computed: {
            ...mapState('userReference', ['searchForm', 'userDialogVisible', 'userList', 'userSize', 'page']),
        },
        methods: {
            search(event) {
                this.$store.dispatch('userReference/searchUserList').catch(error => showErrorMessage(error));
            },
            clear() {
                this.$store.dispatch('userReference/clearSearchForm');
            },
            handleCurrentChange(val) {
                this.$store.dispatch('userReference/setPage', val);
                this.$store.dispatch('userReference/searchUserList').catch(error => showErrorMessage(error));
            },
            closeUserDialog() {
                this.$store.dispatch('userReference/closeUserDialog');
            },
            formatActiveStartTime(row, column) {
                return formatDate(row.activeStartTime);
            },
            formatActiveEndTime(row, column) {
                return formatDate(row.activeEndTime);
            },
            addUser(row) {
                this.$store.dispatch('userReference/addUser', row);
            }
        }
    }
</script>
