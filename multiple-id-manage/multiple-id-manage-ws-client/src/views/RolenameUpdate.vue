<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Role NameDetails</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="Role NameID">
                    <el-input v-model="form.roleNameId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Role ">
                    <el-input v-model="role" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Locale">
                  <el-select v-model="form.locale" :disabled="!editable">
                      <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="Role Name">
                    <el-input v-model="form.roleName" :disabled="!editable" id="roleName"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable"></el-date-picker>
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
                        <el-button type="primary" @click="update">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="back">Back</el-button>
                        <el-button type="primary" @click="changeMode(true)">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
import { mapState } from 'vuex'
import { showErrorMessage } from '../common'

export default {
    computed: {
        ...mapState('rolename', {
            role(state) {
                if(state.form.roleMst.roleId !== '') {
                    return state.form.roleMst.roleCode + ':' + state.form.roleMst.roleName + '(' + state.form.roleMst.activeStartTime + '-' + state.form.roleMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('rolename', ['form', 'editable']),
        ...mapState('common', ['locales'])
    },
    methods: {
        changeMode: function(flg) {
            this.$store.dispatch('rolename/changeMode', flg);
        },
        update: function() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('rolename/updateRolename').then(() => {
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
            this.$store.dispatch('rolename/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        back() {
            this.$router.push({name: 'roleUpdate'});
            this.$store.dispatch('common/deleteNavi', {name: 'Role NameDetails', path: 'rolename-update'});
            this.$store.dispatch('common/setTable', {name: 'Role ', value: 'role'});
            this.$store.dispatch('common/setSelectedTable', 'role');
        }
    },
    created: function() {
        this.$store.dispatch('rolename/showRolename', this.$route.params.roleNameId).catch(error => {
            showErrorMessage(error);
        })
    }
}
</script>
