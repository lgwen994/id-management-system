<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Company Name Details</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="120px">
                <el-form-item label="Company Name ID">
                    <el-input v-model="form.companyNameId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Company">
                    <el-input v-model="company" :disabled="true" id="company"></el-input>
                </el-form-item>
                <el-form-item label="Locale">
                  <el-select v-model="form.locale" :disabled="!editable" id="locale">
                      <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="Company Name">
                    <el-input v-model="form.companyName" :disabled="!editable" id="companyName"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
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
                <el-form-item label="Deleted Logically">
                    <el-input v-model="form.deletedFlg" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Version">
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
import Vue from 'vue'
import { mapState } from 'vuex'
import { showErrorMessage } from '../common'

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
            },
            editable: state => state.editable
        }),
        ...mapState('common', ['locales'])
    },
    methods: {
        changeMode: function(flg) {
            this.$store.dispatch('companyName/changeMode', flg);
        },
        update: function(event) {
            this.$confirm('Comfirm to update', 'Confirmation', {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('companyName/updateCompanyName').then(() => {
                    this.changeMode(false);
                    this.$alert('Updated', 'Updated', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                 // when cancel
            });
        },
        resetForm() {
            this.$store.dispatch('companyName/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        back() {
            this.$router.push({name: 'companyUpdate'});
            this.$store.dispatch('common/deleteNavi', {name: 'Company Name Details', path: 'companyName-update'});
            this.$store.dispatch('common/setTable', {name: "Company", value: 'company'});
            this.$store.dispatch('common/setSelectedTable', "company");
        }
    },
    created: function() {
        this.$store.dispatch('companyName/showCompanyName', this.$route.params.companyNameId).catch(error => {
            showErrorMessage(error);
        })
    }
}
</script>
