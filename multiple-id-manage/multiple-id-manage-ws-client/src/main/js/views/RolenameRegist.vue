<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Role Name Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="Role ">
                    <el-input v-model="role" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Locale">
                    <el-select v-model="form.locale" placeholder="">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Role Name">
                    <el-input v-model="form.roleName" id="roleName"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="back">Back</el-button>
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist"> Create</el-button>
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
        ...mapState('rolename', {
            role(state) {
                if(state.form.roleMst.roleId !== '') {
                    return state.form.roleMst.roleCode + ':' + state.form.roleMst.roleName + '(' + state.form.roleMst.activeStartTime + '-' + state.form.roleMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('rolename', ['form']),
        ...mapState('common', ['locales'])
    },
    methods: {
        regist: function() {
            this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('rolename/registRolename').then(() => {
                    this.$router.push({ name: 'roleUpdate', params: { roleId: this.$store.state.rolename.form.roleMst.roleId}});
                    this.$store.dispatch('common/deleteNavi', {name: 'Role Name Create', path: 'rolename-regist'});
                    this.$store.dispatch('common/setTable', {name: 'Role ', value: 'role'});
                    this.$alert(' Create is completed.', ' Created', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        clear() {
            this.$store.dispatch('rolename/clearForm');
        },
        back() {
            this.$router.push({
                name: 'roleUpdate',
                params: {
                    roleId: this.$store.state.rolename.form.roleMst.roleId,
                }
            });
            this.$store.dispatch('common/deleteNavi', {name: 'Role Name Create', path: 'rolename-regist'});
            this.$store.dispatch('common/setTable', {name: 'Role ', value: 'role'});
            this.$store.dispatch('common/setSelectedTable', 'role');
        }
    },
    created: function() {
        this.$store.dispatch('rolename/clearForm');
        this.$store.dispatch('rolename/setActiveTime');
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('rolename/setRole');
        }
    }
}
</script>
