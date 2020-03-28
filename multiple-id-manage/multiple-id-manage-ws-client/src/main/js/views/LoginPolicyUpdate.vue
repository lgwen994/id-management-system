<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Login Policy Details</span>
        </div>
        <div>
            <el-form ref="loginPolicyForm" :model="loginPolicyForm" label-width="200px" id="loginPolicyUpdateForm">
                <el-form-item label="Login Policy ID">
                    <el-input v-model="loginPolicyForm.loginPolicyId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Login Policy Code">
                    <el-input v-model="loginPolicyForm.loginPolicyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-input v-model="loginPolicyForm.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="Password Expire Term">
                    <el-input v-model="loginPolicyForm.passwordExpireTerm" :disabled="!editable" id="passwordExpireTerm"></el-input>
                    <span id="message_passwordExpireTerm"></span>
                </el-form-item>
                <el-form-item label="Login Failure Count Max Failure">
                    <el-input v-model="loginPolicyForm.passwordMaxFailure" :disabled="!editable" id="passwordMaxFailure"></el-input>
                    <span id="message_passwordMaxFailure"></span>
                </el-form-item>
                <el-form-item label="Login Failure Count Failure Reset Term">
                    <el-input v-model="loginPolicyForm.passwordFailureResetTerm" :disabled="!editable" id="passwordFailureResetTerm"></el-input>
                    <span id="message_passwordFailureResetTerm"></span>
                </el-form-item>
                <el-form-item label="Account Lock Out Term">
                    <el-input v-model="loginPolicyForm.lockoutTerm" :disabled="!editable" id="lockoutTerm"></el-input>
                    <span id="message_lockoutTerm"></span>
                </el-form-item>
                <el-form-item label="Session Max Failure">
                    <el-input v-model="loginPolicyForm.maxSessionLimit" :disabled="!editable" id="maxSessionLimit"></el-input>
                    <span id="message_maxSessionLimit"></span>
                </el-form-item>
                <el-form-item label="Permit Login Time">
                    <el-input v-model="loginPolicyForm.permitTime" :disabled="!editable" id="permitTime"></el-input>
                    <span id="message_permitTime"></span>
                </el-form-item>
                <el-form-item label="Permit Domain">
                    <el-input v-model="loginPolicyForm.permitDomain" :disabled="!editable" id="permitDomain"></el-input>
                    <span id="message_permitDomain"></span>
                </el-form-item>
                <el-form-item label="Created Time">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Created User">
                    <el-input v-model="loginPolicyForm.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Updated Time">
                    <el-date-picker type="datetime" v-model="loginPolicyForm.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Updated User">
                    <el-input v-model="loginPolicyForm.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Deleted Flg">
                    <el-input v-model="loginPolicyForm.deletedFlg" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Version No">
                    <el-input v-model="loginPolicyForm.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">Cancel</el-button>
                        <el-button @click="resetForm">Reset</el-button>
                        <el-button type="primary" @click="update" id="updateLoginPolicy">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">Edit</el-button>
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
            ...mapState('loginPolicy', ['loginPolicyForm','editable'])
        },
        methods: {
            changeMode(flg) {
                this.$store.dispatch('loginPolicy/changeMode', flg);
            },
            resetForm() {
                this.$store.dispatch('loginPolicy/resetForm');
            },
            cancel() {
                this.resetForm();
                this.changeMode(false);
            },
            update: function() {
                this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('loginPolicy/updateLoginPolicy').then(() => {
                        this.changeMode(false);
                        this.$alert('Update is completed.', 'Updated', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // Cancel
                });
            },
        },
        created: function() {
            if(Object.keys(this.$route.params).length !== 0) {
                this.$store.dispatch('loginPolicy/showLoginPolicy', this.$route.params.loginPolicyId).catch((error) => {
                    showErrorMessage(error);
                })
            }
            if(this.$store.getters['common/getLastOperation'].path != "loginPolicy-update") {
                this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
                this.$store.dispatch('common/setTable', {name: "Login Policy ", value: 'loginPolicy'});
                this.$store.dispatch('common/setSelectedTable', "loginPolicy");
            }
        }
    }
</script>
