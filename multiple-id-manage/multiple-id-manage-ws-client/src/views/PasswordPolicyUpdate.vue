<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>PasswordPolicy Details</span>
        </div>
        <div>
            <el-form ref="passwordPolicyForm" :model="passwordPolicyForm" label-width="180px" id="passwordPolicyUpdateForm">
                <el-form-item label="PasswordPolicy ID">
                    <el-input v-model="passwordPolicyForm.passwordPolicyId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="PasswordPolicy Code">
                    <el-input v-model="passwordPolicyForm.passwordPolicyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-input v-model="passwordPolicyForm.companyCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="Min Password Length">
                    <el-input v-model="passwordPolicyForm.passwordMinLength" :disabled="!editable" id="passwordMinLength"></el-input>
                    <span id="message_passwordMinLength"></span>
                </el-form-item>
                <el-form-item label="Password LetterType">
                    <el-input v-model="passwordPolicyForm.passwordLetterType" :disabled="!editable" id="passwordLetterType"></el-input>
                    <span id="message_passwordLetterType"></span>
                </el-form-item>
                <el-form-item label="PasswordMin  LetterType">
                    <el-input v-model="passwordPolicyForm.passwordMinLetterType" :disabled="!editable" id="passwordMinLetterType"></el-input>
                    <span id="message_passwordMinLetterType"></span>
                </el-form-item>
                <el-form-item label="Password In History">
                    <el-input v-model="passwordPolicyForm.passwordInHistory" :disabled="!editable" id="passwordInHistory"></el-input>
                    <span id="message_passwordInHistory"></span>
                </el-form-item>
                <el-form-item label="Created Time">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Created User">
                    <el-input v-model="passwordPolicyForm.createdUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Updated Time">
                    <el-date-picker type="datetime" v-model="passwordPolicyForm.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                </el-form-item>
                <el-form-item label="Updated User">
                    <el-input v-model="passwordPolicyForm.updatedUser" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Deleted Flg">
                    <el-input v-model="passwordPolicyForm.deletedFlg" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Version No">
                    <el-input v-model="passwordPolicyForm.versionNo" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item>
                    <div v-if="editable" style="text-align:right;">
                        <el-button @click="cancel">Cancel</el-button>
                        <el-button @click="resetForm">Reset</el-button>
                        <el-button type="primary" @click="update" id="updatePasswordPolicy">Update</el-button>
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
            ...mapState('passwordPolicy', ['passwordPolicyForm','editable'])
        },
        methods: {
            changeMode(flg) {
                this.$store.dispatch('passwordPolicy/changeMode', flg);
            },
            resetForm() {
                this.$store.dispatch('passwordPolicy/resetForm');
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
                    this.$store.dispatch('passwordPolicy/updatePasswordPolicy').then(() => {
                        this.changeMode(false);
                        this.$alert('Update is completed.', 'Updated', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // Cancel
                });
            },
        },
        created: function() {
            if(Object.keys(this.$route.params).length !== 0) {
                this.$store.dispatch('passwordPolicy/showPasswordPolicy', this.$route.params.passwordPolicyId).catch((error) => {
                    showErrorMessage(error);
                })
            }
            if(this.$store.getters['common/getLastOperation'].path != "passwordPolicy-update") {
                this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
                this.$store.dispatch('common/setTable', {name: "PasswordPolicy ", value: 'passwordPolicy'});
                this.$store.dispatch('common/setSelectedTable', "passwordPolicy");
            }
        }
    }
</script>
