<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>所属登録</span>
            </div>
            <div>
                <el-row>
                    <el-form ref="form" :model="form" label-width="150px">
                        <el-form-item label="所属コード">
                            <el-input v-model="form.positionCode" id="positionCode"></el-input>
                        </el-form-item>
                        <el-form-item label="ユーザマスタ">
                            <el-col :span="20">
                                <el-input v-model="user" :disabled="true" id="user"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" @click="openUserDialog">参照</button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="組織マスタ">
                            <el-input v-model="org" :disabled="true" id="org"></el-input>
                        </el-form-item>
                        <el-form-item label="役職マスタ">
                            <el-col :span="20">
                                <el-input v-model="title" :disabled="true" id="title"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" @click="openTitleDialog">参照</button>
                                </el-col>
                        </el-form-item>
                        <el-form-item label="兼務フラグ">
                            <el-radio v-model="form.concurrentFlg" label="0">本務</el-radio>
                            <el-radio v-model="form.concurrentFlg" label="1">兼務</el-radio>
                        </el-form-item>
                        <el-form-item label="有効開始日時">
                            <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="有効終了日時">
                            <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                            <br>入力しない場合は、無期限に設定されます
                        </el-form-item>
                    </el-form>
                </el-row>
                <el-row>
                    <div style="text-align:right;">
                        <el-button @click="back">戻る</el-button>
                        <el-button @click="clear">クリア</el-button>
                        <el-button @click="regist" type="primary">登録</el-button>
                    </div>
                </el-row>
            </div>
        </el-card>
        <role-dialog></role-dialog>
        <user-dialog></user-dialog>
        <title-dialog></title-dialog>
    </div>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import {formatDate, showErrorMessage} from '../common';
import TitleReference from './TitleReference.vue';
import UserReference from './UserReference.vue';

export default {
    components: {
        'title-dialog': TitleReference,
        'user-dialog': UserReference
    },
    computed: {
        ...mapState('position', {
            user(state) {
                if(state.form.userMst.userId !== '') {
                    return state.form.userMst.userCode + ':' + state.form.userMst.userName + '(' + state.form.userMst.activeStartTime + '-' + state.form.userMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            org(state) {
                if(state.form.orgMst.orgId !== '') {
                    return state.form.orgMst.orgCode + ':' + state.form.orgMst.orgName +  '(' + state.form.orgMst.activeStartTime + '-' + state.form.orgMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            title(state) {
                if(state.form.titleMst.titleId !== '') {
                    return state.form.titleMst.titleCode + ':' + state.form.titleMst.titleName +  '(' + state.form.titleMst.activeStartTime + '-' + state.form.titleMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('position', ['form'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('登録します。よろしいですか？', '登録確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('position/regist').then(() => {
                    // 本来は組織詳細画面に遷移する
                    this.$router.push({ name: 'orgUpdate', params: { orgId: this.$store.state.position.form.orgMst.orgId}});
                    this.$alert('登録が完了しました。', '登録完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        openUserDialog(event) {
            this.$store.dispatch('userReference/openUserDialog', {screen: 'position', kbn: 'Regist'});
        },
        openTitleDialog(event) {
            this.$store.dispatch('titleReference/openTitleDialog', {companyId: this.$store.state.position.form.orgMst.companyId, screen: 'position'});
        },
        clear() {
            this.$store.dispatch('position/clearForm');
        },
        back() {
            this.$router.push({
                name: 'orgUpdate',
                params: {
                    orgId: this.$store.state.position.form.orgMst.orgId,
                }
            });
            this.$store.dispatch('common/deleteNavi', {name: '所属登録', path: 'position-regist'});
            this.$store.dispatch('common/setTable', {name: '組織マスタ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    },
    created: function() {
        // ポジションのフォームをクリアする
        this.$store.dispatch('position/clearForm');
        // 組織マスタ詳細画面から遷移
        this.$store.dispatch('position/setOrg', this.$route.params);
    }
}
</script>
