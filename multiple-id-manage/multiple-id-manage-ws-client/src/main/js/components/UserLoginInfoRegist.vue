<template>
    <div>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>ユーザ_ログイン情報登録</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="190px" id="userLoginInfoRegistForm">
                <el-form-item label="ユーザ_ログイン情報コード">
                    <el-input v-model="form.userLoginInfoCode" id="userLoginInfoCode"></el-input>
                    <span id="message_userLoginInfoCode"></span>
                </el-form-item>
                <el-form-item label="ユーザマスタ">
                    <el-col :span="20">
                        <el-input v-model="user" :disabled="true" id="user"></el-input>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" @click="openUserDialog">参照</button>
                    </el-col>
                </el-form-item>
                <el-form-item label="ログインID">
                    <el-input v-model="form.loginId" id="loginId"></el-input>
                    <span id="message_loginId"></span>
                </el-form-item>
                <el-form-item label="会社コード">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" @click="openCompanyDialog">参照</button>
                    </el-col>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>入力しない場合は、無期限に設定されます
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">クリア</el-button>
                        <el-button type="primary" @click="regist" id="registUserLoginInfo">登録</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
        <user-dialog></user-dialog>
        <company-dialog></company-dialog>
    </div>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common';
import CompanyReference from './CompanyReference.vue';
import UserReference from './UserReference.vue';

export default {
    components: {
        'company-dialog': CompanyReference,
        'user-dialog': UserReference
    },
    computed: {
        ...mapState('userLoginInfo', {
            company(state) {
                if(state.form.companyMst.companyId !== '') {
                    return state.form.companyMst.companyCode + ':' + '(' + state.form.companyMst.activeStartTime + '-' + state.form.companyMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            user(state) {
                if(state.form.userMst.userId !== '') {
                    return state.form.userMst.userCode + ':' + '(' + state.form.userMst.activeStartTime + '-' + state.form.userMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('userLoginInfo', ['form'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('登録します。よろしいですか？', '登録確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userLoginInfo/registUserLoginInfo').then(() => {
                    this.$router.push({ name: 'userLoginInfoUpdate', params: { userLoginInfoId: this.$store.state.userLoginInfo.form.userLoginInfoId }});
                    this.$store.dispatch('common/setNaviList', {name: 'ユーザ_ログイン情報詳細', path: 'userLoginInfo-update'});
                    this.$alert('登録が完了しました。', '登録完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        openCompanyDialog(event) {
            this.$store.dispatch('companyReference/openCompanyDialog', {screen: 'userLoginInfo', kbn: 'Regist'});
        },
        openUserDialog(event) {
            this.$store.dispatch('userReference/openUserDialog', {screen: 'userLoginInfo', kbn: 'Regist'});
        },
        clear() {
            this.$store.dispatch('userLoginInfo/clearForm');
        },
    },
    created: function() {
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setNaviList', {name: 'ユーザ_ログイン情報登録', path: 'userLoginInfo-regist'});
        this.$store.dispatch('common/setTable', {name: 'ユーザ_ログイン情報', value: 'userLoginInfo'})
        this.$store.dispatch('common/setSelectedTable', 'userLoginInfo');
        this.$store.dispatch('userLoginInfo/clearForm');
        this.$store.dispatch('userLoginInfo/setActiveStartTime');
    }
}
</script>
