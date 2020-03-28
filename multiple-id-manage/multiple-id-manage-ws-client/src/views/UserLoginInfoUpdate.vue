<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>User Login Info Details</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="230px" id="userLoginInfoUpdateForm">
                <el-form-item label="User Login Info ID">
                    <el-input v-model="form.userLoginInfoId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="User Login Info Code">
                    <el-input v-model="form.userLoginInfoCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="User ID">
                    <el-input v-model="form.userId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Login ID">
                    <el-input v-model="form.loginId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-input v-model="form.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Created Time">
                    <el-date-picker type="datetime" v-model="form.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Created User">
                    <el-input v-model="form.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Updated Time">
                    <el-date-picker type="datetime" v-model="form.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Updated User">
                    <el-input v-model="form.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Deleted Flg">
                    <el-input v-model="form.deletedFlg" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Version No">
                    <el-input v-model="form.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">Cancel</el-button>
                        <el-button @click="resetForm">Reset</el-button>
                        <el-button type="primary" @click="update" id="updateUserLoginInfo">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
        <div>
            <span>PasswordDetails</span>
        </div>
        <div>
            <el-form ref="passwordForm" :model="passwordForm" label-width="230px" id="passwordUpdateForm">
                <el-form-item label="Login ID">
                    <el-input v-model="passwordForm.loginId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-input v-model="passwordForm.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Password">
                    <el-input v-model="passwordForm.password" :disabled="!passwordEditable" id="password"></el-input>
                </el-form-item>
                <el-form-item label="Password Must Change Flg">
                    <el-input v-model="passwordForm.passwordMustChangeFlg" :disabled="!passwordEditable" id="passwordMustChangeFlg"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="passwordEditable" style="text-align:right;">
                        <el-button @click="cancelPassword">Cancel</el-button>
                        <el-button @click="resetPassword">Reset</el-button>
                        <el-button type="primary" @click="updatePassword" id="updatePassword">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="passwordChangeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>

        <div>
            <span>Login 制御情報Details</span>
        </div>
        <div>
            <el-form ref="loginControlInfoForm" :model="loginControlInfoForm" label-width="230px" id="loginControlInfoUpdateForm">
                <el-form-item label="Login ID">
                    <el-input v-model="loginControlInfoForm.loginId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-input v-model="loginControlInfoForm.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Account Inactive Flg">
                    <el-input v-model="loginControlInfoForm.accountInactiveFlg" :disabled="!loginControlInfoEditable" id="accountInactiveFlg"></el-input>
                </el-form-item>
                <el-form-item label="Account Active Start Time">
                    <el-date-picker type="datetime" v-model="loginControlInfoForm.accountActiveStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!loginControlInfoEditable" id="accountActiveStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Account Active End Time">
                    <el-date-picker type="datetime" v-model="loginControlInfoForm.accountActiveEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!loginControlInfoEditable" id="accountActiveEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Login Failure Count">
                    <el-input v-model="loginControlInfoForm.passwordFailureCount" :disabled="!loginControlInfoEditable" id="passwordFailureCount"></el-input>
                </el-form-item>
                <el-form-item label="Login Failure Count Reset Time">
                    <el-date-picker type="datetime" v-model="loginControlInfoForm.passwordFailureResetTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!loginControlInfoEditable" id="passwordFailureResetTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Account Lock Out End Time">
                    <el-date-picker type="datetime" v-model="loginControlInfoForm.lockoutEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!loginControlInfoEditable" id="lockoutEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <div v-if="loginControlInfoEditable" style="text-align:right;">
                        <el-button @click="cancelLoginControlInfo">Cancel</el-button>
                        <el-button @click="resetLoginControlInfo">Reset</el-button>
                        <el-button type="primary" @click="updateLoginControlInfo" id="updateLoginControlInfo">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="loginControlInfoChangeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('userLoginInfo', ['form','passwordForm','loginControlInfoForm','passwordEditable','loginControlInfoEditable','editable'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('userLoginInfo/changeMode', flg);
        },
        resetForm() {
            this.$store.dispatch('userLoginInfo/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        update: function() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userLoginInfo/updateUserLoginInfo').then(() => {
                    this.changeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        passwordChangeMode(flg) {
            this.$store.dispatch('userLoginInfo/passwordChangeMode', flg);
        },
        resetPassword() {
            this.$store.dispatch('userLoginInfo/resetPassword');
        },
        cancelPassword() {
            this.resetPassword();
            this.passwordChangeMode(false);
        },
        updatePassword() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userLoginInfo/updatePassword').then(() => {
                    this.passwordChangeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        loginControlInfoChangeMode(flg) {
            this.$store.dispatch('userLoginInfo/loginControlInfoChangeMode', flg);
        },
        resetLoginControlInfo() {
            this.$store.dispatch('userLoginInfo/resetLoginControlInfo');
        },
        cancelLoginControlInfo() {
            this.resetLoginControlInfo();
            this.loginControlInfoChangeMode(false);
        },
        updateLoginControlInfo() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('userLoginInfo/updateLoginControlInfo').then(() => {
                    this.loginControlInfoChangeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('userLoginInfo/showUserLoginInfo', this.$route.params.userLoginInfoId).catch((error) => {
                showErrorMessage(error);
            })
        }
        if(this.$store.getters['common/getLastOperation'].path !== 'userLoginInfo-update') {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: 'User Login Info ', value: 'userLoginInfo'});
            this.$store.dispatch('common/setSelectedTable', 'userLoginInfo');
        }
    }
}
</script>
