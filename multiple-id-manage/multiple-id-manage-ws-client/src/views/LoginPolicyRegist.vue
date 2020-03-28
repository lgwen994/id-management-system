<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Create Login Policy</span>
        </div>
        <div>
            <el-form ref="loginPolicyForm" :model="loginPolicyForm" label-width="200px" id="loginPolicyRegistForm">
                <el-form-item label="Login Policy Code">
                    <el-input v-model="loginPolicyForm.loginPolicyCode" id="loginPolicyCode"></el-input>
                    <span id="message_loginPolicyCode"></span>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-input v-model="loginPolicyForm.companyCode" id="companyCode"></el-input>
                    <span id="message_companyCode"></span>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item label="Password Expire Term">
                    <el-input v-model="loginPolicyForm.passwordExpireTerm" id="passwordExpireTerm"></el-input>
                    <span id="message_passwordExpireTerm"></span>
                </el-form-item>
                <el-form-item label="Password Max Failure">
                    <el-input v-model="loginPolicyForm.passwordMaxFailure" id="passwordMaxFailure"></el-input>
                    <span id="message_passwordMaxFailure"></span>
                </el-form-item>
                <el-form-item label="Password Failure Reset Term">
                    <el-input v-model="loginPolicyForm.passwordFailureResetTerm" id="passwordFailureResetTerm"></el-input>
                    <span id="message_passwordFailureResetTerm"></span>
                </el-form-item>
                <el-form-item label="Lockout Term">
                    <el-input v-model="loginPolicyForm.lockoutTerm" id="lockoutTerm"></el-input>
                    <span id="message_lockoutTerm"></span>
                </el-form-item>
                <el-form-item label="Max Session Limit">
                    <el-input v-model="loginPolicyForm.maxSessionLimit" id="maxSessionLimit"></el-input>
                    <span id="message_maxSessionLimit"></span>
                </el-form-item>
                <el-form-item label="Permit Login Time">
                    <el-input v-model="loginPolicyForm.permitTime" id="permitTime"></el-input>
                    <span id="message_permitTime"></span>
                </el-form-item>
                <el-form-item label="Permit Domain">
                    <el-input v-model="loginPolicyForm.permitDomain" id="permitDomain"></el-input>
                    <span id="message_permitDomain"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registLoginPolicy">Create</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
    import { mapState } from 'vuex'
    import { showErrorMessage } from '../common';

    export default {
        computed: {
            ...mapState('loginPolicy', ['loginPolicyForm'])
        },
        methods: {
            regist: function() {
                this.$Confirm('It will create. Is it OK?', 'Confirmation of Creation', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('loginPolicy/registLoginPolicy').then(() => {
                        this.$router.push({ name: 'loginPolicyUpdate', params: { loginPolicyId: this.$store.state.loginPolicy.loginPolicyForm.loginPolicyId }});
                        this.$store.dispatch('common/setNaviList', {name: "Login Policy Details", path: 'loginPolicy-update'});
                        this.$alert('Creation has been completed.', 'Created', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // when cancel
                });
            },
            clear() {
                this.$store.dispatch('loginPolicy/clearForm')
            },
        },
        created: function () {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setNaviList', {name: 'Create Login Policy', path: 'loginPolicy-regist'});
            this.$store.dispatch('common/setTable', {name: "Login Policy", value: 'loginPolicy'});
            this.$store.dispatch('common/setSelectedTable', 'loginPolicy');
            this.$store.dispatch('loginPolicy/clearForm');
            this.$store.dispatch('loginPolicy/setActiveStartTime');
        }
    }
</script>
