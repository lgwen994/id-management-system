<template>
    <div>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Orgnazation  Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px" id="orgRegistForm">
                <el-form-item label="Orgnazation Code">
                    <el-input v-model="form.orgCode" id="orgCode"></el-input>
                    <span id="message_orgCode"></span>
                </el-form-item>
                <el-form-item label="Company">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" @click="openCompanyDialog">Dialog</el-button>
                    </el-col>
                </el-form-item>
                <el-form-item label="Group Flg">
                    <el-radio v-model="form.groupFlg" label="0">Real Orgnazation </el-radio>
                    <el-radio v-model="form.groupFlg" label="1">Logical Orgnazation </el-radio>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>If you do not enter it, it will be set indefinitely
                </el-form-item>
                <el-form-item label="Orgnazation Name">
                    <el-input v-model="form.orgName" id="orgName"></el-input>
                    <span id="message_orgName"></span>
                </el-form-item>
                <el-form-item label="Orgnazation Type">
                    <el-input v-model="form.orgType" id="orgType"></el-input>
                    <span id="message_orgType"></span>
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist" id="registOrg"> Create</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
    <company-dialog></company-dialog>
    </div>
</template>

<script>
//import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common';
import CompanyReference from './CompanyReference.vue';

export default {
    components: {
        'company-dialog': CompanyReference
    },
    computed: {
        ...mapState('org', {
            company(state) {
                if(state.form.companyMst.companyId !== '') {
                    return state.form.companyMst.companyCode + ':' + state.form.companyMst.companyName + '(' + state.form.companyMst.activeStartTime + '-' + state.form.companyMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('org', ['form'])
    },
    methods: {
        //regist: function(event) {
        regist: function() {
            this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('org/registOrg').then(() => {
                    this.$router.push({ name: 'orgUpdate', params: { orgId: this.$store.state.org.form.orgId }});
                    this.$store.dispatch('common/setNaviList', {name: 'Orgnazation Details', path: 'org-update'});
                    this.$alert(' Create is completed.', ' Created', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        //openCompanyDialog(event) {
        openCompanyDialog() {
            this.$store.dispatch('companyReference/openCompanyDialog', {screen: 'org', kbn: 'Regist'});
        },
        clear() {
            this.$store.dispatch('org/clearForm');
        },
    },
    created: function() {
        this.$store.dispatch('common/deleteNaviList');
        this.$store.dispatch('common/setNaviList', {name: 'Orgnazation  Create', path: 'org-regist'});
        this.$store.dispatch('common/setTable', {name: 'Orgnazation ', value: 'org'})
        this.$store.dispatch('common/setSelectedTable', 'org');
        this.$store.dispatch('org/clearForm');
        this.$store.dispatch('org/setActiveStartTime');
    }
}
</script>
