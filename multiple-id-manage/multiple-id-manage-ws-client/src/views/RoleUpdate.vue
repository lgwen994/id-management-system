<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Role Details</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px" id="roleUpdateForm">
                <el-form-item label="Role ID">
                    <el-input v-model="form.roleId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Role Code">
                    <el-input v-model="form.roleCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="Role Name">
                    <el-input v-model="form.roleName" :disabled="!editable" id="roleName"></el-input>
                    <span id="message_roleName"></span>
                </el-form-item>
                <el-form-item label="Role Type">
                    <el-input v-model="form.roleType" :disabled="!editable" id="roleType"></el-input>
                    <span id="message_roleType"></span>
                </el-form-item>
                <el-form-item label="Role Comment">
                    <el-input v-model="form.roleComment" :disabled="!editable" id="roleComment"></el-input>
                    <span id="message_roleComment"></span>
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
                        <el-button type="primary" @click="update" id="updateRole">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                Role Name List
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">Create</el-button>
                    <el-button type="primary" @click="deleteRolename">Delete</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              Double-click on data to see details
              <el-table :data="rolenameList" stripe border highlight-current-row @row-dblclick="showRolename" @selection-change="handleSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="locale" label="Locale" width="100" :formatter="getLocale"></el-table-column>
                  <el-table-column prop="roleName" label="Role Name" width="100"></el-table-column>
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
        ...mapState('role', ['form', 'rolenameList','editable'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('role/changeMode', flg);
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
                name: 'rolenameRegist',
                params: {
                    roleId: this.$store.state.role.form.roleId,
                    roleCode: this.$store.state.role.form.roleCode,
                    roleName: this.$store.state.role.form.roleName,
                    activeStartTime: this.$store.state.role.form.activeStartTime,
                    activeEndTime: this.$store.state.role.form.activeEndTime
                }
            });
            this.$store.dispatch('common/setNaviList', {name: 'Role Name Create', path: 'rolename-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'rolename');
        },
        handleSelectionChange(val) {
            this.$store.dispatch('rolename/setSelectedList', val);
        },
        deleteRolename() {
            if(this.$store.state.rolename.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('rolename/deleteRolename').then(() => {
                        this.$store.dispatch('role/searchRolenameList', this.$store.state.role.form.roleId);
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
        showRolename(row) {
            this.$router.push({ name: 'rolenameUpdate', params: { roleNameId: row.roleNameId }});
            this.$store.dispatch('common/setNaviList', {name: 'Role NameDetails', path: 'rolename-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'rolename');
        },
        update: function() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('role/updateRole').then(() => {
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
            this.$store.dispatch('role/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('role/showRole', this.$route.params.roleId).catch((error) => {
                showErrorMessage(error);
            })
        }
        if(this.$store.getters['common/getLastOperation'].path !== 'role-update') {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: 'Role ', value: 'role'});
            this.$store.dispatch('common/setSelectedTable', 'role');
        }
    }
}
</script>
