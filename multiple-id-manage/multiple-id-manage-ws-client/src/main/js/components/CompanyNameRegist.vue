<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Company Name Create</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="Company">
                    <el-input v-model="company" :disabled="true" id="company"></el-input>
                </el-form-item>
                <el-form-item label="Locale">
                    <el-select v-model="form.locale" placeholder="" id="locale">
                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Company Name">
                    <el-input v-model="form.companyName" id="companyName"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" id="activeEndTime"></el-date-picker>
                    <br>if nothing to tape, it means active forever. 
                </el-form-item>
                <el-form-item>
                    <div style="text-align:right;">
                        <el-button @click="back">Back</el-button>
                        <el-button @click="clear">Clear</el-button>
                        <el-button type="primary" @click="regist">Create</el-button>
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
        ...mapState('companyName', {
            form: state => state.form,
            company(state) {
                if(state.form.companyMst.companyId != "") {
                    return state.form.companyMst.companyCode + "(" + state.form.companyMst.activeStartTime + "-" + state.form.companyMst.activeEndTime + ")";
                } else {
                    return "";
                }
            }
        }),
        ...mapState('common', ['locales'])
    },
    methods: {
        regist: function(event) {
            this.$confirm('Comfirm to create?', ' Confirmation', {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('companyName/registCompanyName').then(() => {
                    var companyNameId = this.$store.state.companyName.form.companyNameId;
                    this.$router.push({ name: 'companyUpdate', params: { companyId: this.$store.state.companyName.form.companyMst.companyId}});
                    this.$store.dispatch('common/deleteNavi', {name: 'Company Name Create', path: 'companyName-regist'});
                    this.$store.dispatch('common/setTable', {name: "Company", value: 'company'});
                    this.$alert('Created', ' Created', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // when cancel
            });
        },
        clear() {
            this.$store.dispatch('companyName/clearForm');
        },
        back() {
            this.$router.push({
                name: 'companyUpdate',
                params: {
                    companyId: this.$store.state.companyName.form.companyMst.companyId,
                }
            });
            this.$store.dispatch('common/deleteNavi', {name: 'Company Name Create', path: 'companyName-regist'});
            this.$store.dispatch('common/setTable', {name: "Company", value: 'company'});
            this.$store.dispatch('common/setSelectedTable', "company");
        }
    },
    created: function() {
        this.$store.dispatch('companyName/clearForm');
        this.$store.dispatch('companyName/setActiveTime');
        if(Object.keys(this.$route.params).length != 0) {
            this.$store.dispatch('companyName/setCompany');
        }
    }
}
</script>
