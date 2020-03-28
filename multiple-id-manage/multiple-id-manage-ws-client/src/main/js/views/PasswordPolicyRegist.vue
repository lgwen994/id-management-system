<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>PasswordPolicy  Create</span>
        </div>
        <div>
            <el-form ref="passwordPolicyForm" :model="passwordPolicyForm" label-width="180px" id="passwordPolicyRegistForm">
                <el-form-item label="PasswordPolicy Code">
                    <el-input v-model="passwordPolicyForm.passwordPolicyCode" id="passwordPolicyCode"></el-input>
                    <span id="message_passwordPolicyCode"></span>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-input v-model="passwordPolicyForm.companyCode" id="companyCode"></el-input>
                    <span id="message_companyCode"></span>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item label="Min Password Length">
                    <el-input v-model="passwordPolicyForm.passwordMinLength" id="passwordMinLength"></el-input>
                    <span id="message_passwordMinLength"></span>
                </el-form-item>
                <el-form-item label="Password LetterType">
                    <el-input v-model="passwordPolicyForm.passwordLetterType" id="passwordLetterType"></el-input>
                    <span id="message_passwordLetterType"></span>
                </el-form-item>
                <el-form-item label="Password Min LetterType">
                    <el-input v-model="passwordPolicyForm.passwordMinLetterType" id="passwordMinLetterType"></el-input>
                    <span id="message_passwordMinLetterType"></span>
                </el-form-item>
                <el-form-item label="Password In History">
                    <el-input v-model="passwordPolicyForm.passwordInHistory" id="passwordInHistory"></el-input>
                    <span id="message_passwordInHistory"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registPasswordPolicy"> Create</el-button>
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
            ...mapState('passwordPolicy', ['passwordPolicyForm'])
        },
        methods: {
            regist: function() {
                this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('passwordPolicy/registPasswordPolicy').then(() => {
                        this.$router.push({ name: 'passwordPolicyUpdate', params: { passwordPolicyId: this.$store.state.passwordPolicy.passwordPolicyForm.passwordPolicyId }});
                        this.$store.dispatch('common/setNaviList', {name: "PasswordPolicy Details", path: 'passwordPolicy-update'});
                        this.$alert(' Create is completed.', ' Created', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                        // Cancel
                });
            },
            clear() {
                this.$store.dispatch('passwordPolicy/clearForm')
            },
        },
        created: function () {
            this.$store.dispatch('common/deleteNaviList');
            this.$store.dispatch('common/setNaviList', {name: 'PasswordPolicy  Create', path: 'passwordPolicy-regist'});
            this.$store.dispatch('common/setTable', {name: "PasswordPolicy ", value: 'passwordPolicy'});
            this.$store.dispatch('common/setSelectedTable', 'passwordPolicy');
            this.$store.dispatch('passwordPolicy/clearForm');
            this.$store.dispatch('passwordPolicy/setActiveStartTime');
        }
    }
</script>
