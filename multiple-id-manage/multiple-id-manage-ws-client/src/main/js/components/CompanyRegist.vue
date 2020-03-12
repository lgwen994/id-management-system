<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Create Company</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px" id="companyRegistForm">
                <el-form-item label="Companyコード">
                    <el-input v-model="form.companyCode" id="companyCode"></el-input>
                    <span id="message_companyCode"></span>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>if nothing to tape, it means active forever.
                </el-form-item>
                <el-form-item label="Company Name">
                    <el-input v-model="form.companyName" id="companyName"></el-input>
                    <span id="message_companyName"></span>
                </el-form-item>
                <el-form-item label="Company Type">
                    <el-input v-model="form.companyType" id="companyType"></el-input>
                    <span id="message_companyType"></span>
                </el-form-item>
                <el-form-item label="Company Comment">
                    <el-input v-model="form.companyComment" id="companyComment"></el-input>
                    <span id="message_companyComment"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registCompany">Create</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common';

export default {
    computed: {
        ...mapState('company', ['form'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('Comfirm to create?', 'Confirmation', {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('company/registCompany').then(() => {
                    this.$router.push({ name: 'companyUpdate', params: { companyId: this.$store.state.company.form.companyId }});
                    this.$store.dispatch('common/setNaviList', {name: "Company詳細", path: 'company-update'});
                    this.$alert('Created', 'Created', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // when cancel
            });
        },
        clear() {
            this.$store.dispatch('company/clearForm');
        },
    },
    created: function() {
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setNaviList', {name: 'CompanyAdd', path: 'company-regist'});
        this.$store.dispatch('common/setTable', {name: "Company", value: 'company'})
        this.$store.dispatch('common/setSelectedTable', "company");
        this.$store.dispatch('company/clearForm');
        this.$store.dispatch('company/setActiveStartTime');
    }
}
</script>
