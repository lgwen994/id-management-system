<template>
    <div>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>User Login Info  Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="190px" id="userLoginInfoRegistForm">
                <el-form-item label="User Login Info Code">
                    <el-input v-model="form.userLoginInfoCode" id="userLoginInfoCode"></el-input>
                    <span id="message_userLoginInfoCode"></span>
                </el-form-item>
                <el-form-item label="User ">
                    <el-col :span="20">
                        <el-input v-model="user" :disabled="true" id="user"></el-input>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" @click="openUserDialog">Dialog</el-button>
                    </el-col>
                </el-form-item>
                <el-form-item label="Login ID">
                    <el-input v-model="form.loginId" id="loginId"></el-input>
                    <span id="message_loginId"></span>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" @click="openCompanyDialog">Dialog</el-button>
                    </el-col>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registUserLoginInfo"> Create</el-button>
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
        regist: function() {
            this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userLoginInfo/registUserLoginInfo').then(() => {
                    this.$router.push({ name: 'userLoginInfoUpdate', params: { userLoginInfoId: this.$store.state.userLoginInfo.form.userLoginInfoId }});
                    this.$store.dispatch('common/setNaviList', {name: 'User Login Info Details', path: 'userLoginInfo-update'});
                    this.$alert(' Create is completed.', ' Created', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        openCompanyDialog() {
            this.$store.dispatch('companyReference/openCompanyDialog', {screen: 'userLoginInfo', kbn: 'Regist'});
        },
        openUserDialog() {
            this.$store.dispatch('userReference/openUserDialog', {screen: 'userLoginInfo', kbn: 'Regist'});
        },
        clear() {
            this.$store.dispatch('userLoginInfo/clearForm');
        },
    },
    created: function() {
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setNaviList', {name: 'User Login Info  Create', path: 'userLoginInfo-regist'});
        this.$store.dispatch('common/setTable', {name: 'User Login Info ', value: 'userLoginInfo'})
        this.$store.dispatch('common/setSelectedTable', 'userLoginInfo');
        this.$store.dispatch('userLoginInfo/clearForm');
        this.$store.dispatch('userLoginInfo/setActiveStartTime');
    }
}
</script>
