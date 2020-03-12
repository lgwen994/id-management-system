<template>
    <div>
    <el-dialog title="所属選択" :visible="positionDialogVisible" width="1100px" @close="closePositionDialog">
        <el-row>
            <el-col :span="12">
                <el-card class="box-card" body-style="height:600px;">
                    <div slot="header" class="clearfix">
                        <span>検索</span>
                    </div>
                    <div>
                        <el-form ref="searchForm" :model="searchForm" label-width="110px">
                            <el-form-item label="所属コード">
                                <el-input v-model="searchForm.positionCode"></el-input>
                            </el-form-item>
                            <el-form-item label="ユーザマスタ">
                                <el-col :span="20">
                                    <el-input v-model="user"></el-input>
                                </el-col>
                                <el-col :span="4">
                                    <el-button type="primary">参照</button>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="組織マスタ">
                                <el-col :span="20">
                                    <el-input v-model="org"></el-input>
                                </el-col>
                                <el-col :span="4">
                                    <el-button type="primary" @click="openOrgDialog">参照</button>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="役職マスタ">
                                <el-col :span="20">
                                    <el-input v-model="title"></el-input>
                                </el-col>
                                <el-col :span="4">
                                    <el-button type="primary" @click="openTitleDialog">参照</button>
                                </el-col>
                            </el-form-item>
                            <el-form-item label="兼務フラグ">
                                <el-radio v-model="searchForm.concurrentFlg" label="0">本務</el-radio>
                                <el-radio v-model="searchForm.concurrentFlg" label="1">兼務</el-radio>
                            </el-form-item>
                            <el-form-item label="有効開始日時">
                                <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd hh:mm:ss"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="有効終了日時">
                                <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd hh:mm:ss"></el-date-picker>
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
                        <el-button @click="closePositionDialog">閉じる</el-button>
                        <div>データをダブルクリックするとデータを設定します。</div>
                        <el-table :data="positionList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addPosition">
                            <el-table-column prop="positionCode" label="所属コード" width="110"></el-table-column>
                            <el-table-column prop="userMst.userCode" label="ユーザマスタ" width="110"></el-table-column>
                            <el-table-column prop="orgMst.orgCode" label="組織マスタ" width="110"></el-table-column>
                            <el-table-column prop="titleMst.titleCode" label="役職マスタ" width="110"></el-table-column>
                            <el-table-column prop="concurrentFlg" label="兼務フラグ" width="110" :formatter="groupLabel"></el-table-column>
                            <el-table-column prop="activeStartTime" label="有効開始日時" width="200" :formatter="formatActiveStartTime"></el-table-column>
                            <el-table-column prop="activeEndTime" label="有効終了日時" width="200" :formatter="formatActiveEndTime"></el-table-column>
                        </el-table>
                        <el-pagination layout="prev, pager, next" :total="positionSize" page-size="10" @current-change="handleCurrentChange" :current-page="page">
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-dialog>
    <title-dialog></title-dialog>
    <org-dialog></org-dialog>
    </div>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';
import TitleReference from './TitleReference.vue';
import OrgReference from './OrgReference.vue';

export default {
    components: {
        'title-dialog': TitleReference,
        'org-dialog':OrgReference
    },
    computed: {
        ...mapState('positionReference', ['searchForm', 'positionDialogVisible', 'positionList', 'positionSize', 'page']),
        ...mapState('positionReference', {
            user(state) {
                if(state.searchForm.userMst.userId !== null) {
                    return state.searchForm.userMst.userCode + ':' + state.searchForm.userMst.userName + '(' + state.searchForm.userMst.activeStartTime + '-' + state.searchForm.userMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            org(state) {
                if(state.searchForm.orgMst.orgId !== null) {
                    return state.searchForm.orgMst.orgCode + ':' + state.searchForm.orgMst.orgName +  '(' + state.searchForm.orgMst.activeStartTime + '-' + state.searchForm.orgMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            title(state) {
                if(state.searchForm.titleMst.titleId !== null) {
                    return state.searchForm.titleMst.titleCode + ':' + state.searchForm.titleMst.titleName +  '(' + state.searchForm.titleMst.activeStartTime + '-' + state.searchForm.titleMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
    },
    methods: {
        search(event) {
            this.$store.dispatch('positionReference/setPage', 1);
            this.$store.dispatch('positionReference/searchPositionList').catch(error => showErrorMessage(error));
        },
        clear() {
            this.$store.dispatch('positionReference/clearSearchForm');
        },
        handleCurrentChange(val) {
            this.$store.dispatch('positionReference/setPage', val);
            this.$store.dispatch('positionReference/searchPositionList').catch(error => showErrorMessage(error));
        },
        closePositionDialog() {
            this.$store.dispatch('positionReference/closePositionDialog');
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        addPosition(row) {
            this.$store.dispatch('position/setPosition', row);
            this.$store.dispatch('positionReference/closePositionDialog');
        },
        openTitleDialog(event) {
            this.$store.dispatch('titleReference/openTitleDialog', {
                companyId: this.$store.state.position.form.orgMst.companyId,
                screen: 'positionReference'
            }).catch(error => showErrorMessage(error));
        },
        groupLabel(row) {
            if(row.concurrentFlg === 0) {
                return '本務';
            } else if(row.concurrentFlg === 1) {
                return '兼務';
            }
        },
        openOrgDialog(event) {
            this.$store.dispatch('orgReference/openOrgDialog', {screen: 'positionReference'}).catch(error => showErrorMessage(error));;
        }
    }
}
</script>
