<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Role  Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px" id="roleRegistForm">
                <el-form-item label="Role Code">
                    <el-input v-model="form.roleCode" id="roleCode"></el-input>
                    <span id="message_roleCode"></span>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item label="Role Name">
                    <el-input v-model="form.roleName" id="roleName"></el-input>
                    <span id="message_roleName"></span>
                </el-form-item>
                <el-form-item label="Role Type">
                    <el-input v-model="form.roleType" id="roleType"></el-input>
                    <span id="message_roleType"></span>
                </el-form-item>
                <el-form-item label="Role Comment">
                    <el-input v-model="form.roleComment" id="roleComment"></el-input>
                    <span id="message_roleComment"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registRole"> Create</el-button>
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
        ...mapState('role', ['form'])
    },
    methods: {
        regist: function() {
            this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('role/registRole').then(() => {
                    this.$router.push({ name: 'roleUpdate', params: { roleId: this.$store.state.role.form.roleId }});
                    this.$store.dispatch('common/setNaviList', {name: 'Role Details', path: 'role-update'});
                    this.$alert(' Create is completed.', ' Created', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        clear() {
            this.$store.dispatch('role/clearForm');
        },
    },
    created: function() {
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setNaviList', {name: 'Role  Create', path: 'role-regist'});
        this.$store.dispatch('common/setTable', {name: 'Role ', value: 'role'})
        this.$store.dispatch('common/setSelectedTable', 'role');
        this.$store.dispatch('role/clearForm');
        this.$store.dispatch('role/setActiveStartTime');
    }
}
</script>
