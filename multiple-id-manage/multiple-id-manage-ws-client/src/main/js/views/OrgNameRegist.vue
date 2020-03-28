<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Orgnazation Name Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px">
                <el-form-item label="Orgnazation ">
                    <el-input v-model="org" :disabled="true" id="org"></el-input>
                </el-form-item>
                <el-form-item label="Locale">
                    <el-select v-model="form.locale" placeholder="" id="locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Orgnazation Name">
                    <el-input v-model="form.orgName" id="orgName"></el-input>
                    <span id="message_orgName"></span>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
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
        ...mapState('orgName', {
            org(state) {
                if(state.form.orgMst.orgId !== '') {
                    return state.form.orgMst.orgCode + ':' + state.form.orgMst.orgName + '(' + state.form.orgMst.activeStartTime + '-' + state.form.orgMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('orgName', ['form']),
        ...mapState('common', ['locales'])
    },
    methods: {
        regist: function() {
            this.$Confirm(' Create. Is it OK?', ' CreateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('orgName/registOrgName').then(() => {
                    this.$router.push({ name: 'orgUpdate', params: { orgId: this.$store.state.orgName.form.orgId}});
                    this.$store.dispatch('common/deleteNavi', {name: 'Orgnazation Name Create', path: 'orgName-regist'});
                    this.$store.dispatch('common/setTable', {name: 'Orgnazation ', value: 'org'});
                    this.$alert(' Create is completed.', ' Created', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        clear() {
            this.$store.dispatch('orgName/clearForm');
        },
        back() {
            this.$router.push({name: 'orgUpdate'});
            this.$store.dispatch('common/deleteNavi', {name: 'Orgnazation Name Create', path: 'orgName-regist'});
            this.$store.dispatch('common/setTable', {name: 'Orgnazation ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    },
    created: function() {
        this.$store.dispatch('orgName/clearForm');
        this.$store.dispatch('orgName/setActiveTime');
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('orgName/setOrg');
        }
    }
}
</script>
