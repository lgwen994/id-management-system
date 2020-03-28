<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>User Details</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="120px" id="companyUpdateForm">
                <el-form-item label="User ID">
                    <el-input v-model="form.userId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="User Code">
                    <el-input v-model="form.userCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="User Name">
                    <el-input v-model="form.userName" :disabled="!editable" id="userName"></el-input>
                    <span id="message_userName"></span>
                </el-form-item>
                <el-form-item label="Mail Address">
                    <el-input v-model="form.mail" :disabled="!editable" id="mail"></el-input>
                    <span id="message_mail"></span>
                </el-form-item>
                <el-form-item label="Telephone Number">
                <el-input v-model="form.tel" :disabled="!editable" id="tel"></el-input>
                <span id="message_tel"></span>
                </el-form-item>
                <el-form-item label="FAX Number">
                    <el-input v-model="form.fax" :disabled="!editable" id="fax"></el-input>
                    <span id="message_fax"></span>
                </el-form-item>
                <el-form-item label="Locale">
                    <el-select v-model="form.locale" :disabled="!editable">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
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
                        <el-button type="primary" @click="update" id="updateUser">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                    User Name List
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">Create</el-button>
                    <el-button type="primary" @click="deleteUserName">Delete</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              Double-click on data to see details
              <el-table :data="userNameList" stripe border highlight-current-row @row-dblclick="showUserName" @selection-change="handleSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="locale" label="Locale" width="100" :formatter="getLocale"></el-table-column>
                  <el-table-column prop="userName" label="User Name" width="100"></el-table-column>
                  <el-table-column prop="activeStartTime" label="Active Start Time" width="150" :formatter="formatActiveStartTime"></el-table-column>
                  <el-table-column prop="activeEndTime" label="Active End Time" width="150" :formatter="formatActiveEndTime"></el-table-column>
              </el-table>
            </el-row>
        </div>
    </el-card>
</template>

<script>
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('user', ['form', 'userNameList','editable']),
        ...mapState('common', ['locales'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('user/changeMode', flg);
        },
        getLocale(row) {
            return this.$store.getters['common/getLocale'](row.locale);
        },
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        regist() {
            this.$router.push({
                name: 'userNameRegist',
                params: {
                    userId: this.$store.state.user.form.userId,
                    userCode: this.$store.state.user.form.userCode,
                    activeStartTime: this.$store.state.user.form.activeStartTime,
                    activeEndTime: this.$store.state.user.form.activeEndTime
                }
            });
            this.$store.dispatch('common/setNaviList', {name: 'User Name Create', path: 'userName-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', "userName");
        },
        handleSelectionChange(val) {
            this.$store.dispatch('userName/setSelectedList', val);
        },
        deleteUserName() {
            if(this.$store.state.userName.selectedList.length == 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'Delete Confirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('userName/deleteUserName').then(() => {
                        this.$store.dispatch('user/searchUserNameList', this.$store.state.user.form.userId);
                        this.$alert('Delete completed.', 'Deleted', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // Cancel
                });
            }
        },
        showUserName(row) {
            this.$router.push({ name: 'userNameUpdate', params: { userNameId: row.userNameId }});
            this.$store.dispatch('common/setNaviList', {name: 'User NameDetails', path: 'userName-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', "userName");
        },
        update: function() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('user/updateUser').then(() => {
                    this.changeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        resetForm() {
            this.$store.dispatch('user/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('user/showUser', this.$route.params.userId).catch((error) => {
                showErrorMessage(error);
            })
        } else{
            this.$store.dispatch('user/searchUserNameList', this.$store.state.user.form.userId).catch((error) => {
                showErrorMessage(error);
            });
        }
        if(this.$store.getters['common/getLastOperation'].path != "user-update") {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: "User ", value: 'user'});
            this.$store.dispatch('common/setSelectedTable', "user");
        }
    }
}
</script>
