<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Company Details</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="100px" id="companyUpdateForm">
                <el-form-item label="Company ID">
                    <el-input v-model="form.companyId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Company Code">
                    <el-input v-model="form.companyCode" :disabled="true" id="companyCode"></el-input>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Company Name">
                    <el-input v-model="form.companyName" :disabled="!editable" id="companyName"></el-input>
                    <span id="message_companyName"></span>
                </el-form-item>
                <el-form-item label="Company Type">
                    <el-input v-model="form.companyType" :disabled="!editable" id="companyType"></el-input>
                    <span id="message_companyType"></span>
                </el-form-item>
                <el-form-item label="Company Comment">
                    <el-input v-model="form.companyComment" :disabled="!editable" id="companyComment"></el-input>
                    <span id="message_companyComment"></span>
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
                        <el-button type="primary" @click="update" id="updateCompany">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                Company Name List
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">Create</el-button>
                    <el-button type="primary" @click="deleteCompanyName">Delete</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              Double click on data to see details
              <el-table :data="companyNameList" stripe border highlight-current-row @row-dblclick="showCompanyName" @selection-change="handleSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="locale" label="Locale" width="100" :formatter="getLocale"></el-table-column>
                  <el-table-column prop="companyName" label="Company Name" width="100"></el-table-column>
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
        ...mapState('company', ['form', 'companyNameList','editable'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('company/changeMode', flg);
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
                name: 'companyNameRegist',
                params: {
                    companyId: this.$store.state.company.form.companyId,
                    companyCode: this.$store.state.company.form.companyCode,
                    activeStartTime: this.$store.state.company.form.activeStartTime,
                    activeEndTime: this.$store.state.company.form.activeEndTime
                }
            });
            this.$store.dispatch('common/setNaviList', {name: 'Create Company Name', path: 'companyName-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', "companyName");
        },
        handleSelectionChange(val) {
            this.$store.dispatch('companyName/setSelectedList', val);
        },
        deleteCompanyName() {
            if(this.$store.state.companyName.selectedList.length == 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'Confirmation of deletion', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('companyName/deleteCompanyName').then(() => {
                        this.$store.dispatch('company/searchCompanyNameList', this.$store.state.company.form.companyId);
                        this.$alert('Deletion completed.', 'Deleted', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // when cancel
                });
            }
        },
        showCompanyName(row) {
            this.$router.push({ name: 'companyNameUpdate', params: { companyNameId: row.companyNameId }});
            this.$store.dispatch('common/setNaviList', {name: 'Company Name Details', path: 'companyName-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', "companyName");
        },
        update: function() {
            this.$Confirm('Update Is it OK?', 'Update Confirmation', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('company/updateCompany').then(() => {
                    this.changeMode(false);
                    this.$alert('Update completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // when cancel
            });
        },
        resetForm() {
            this.$store.dispatch('company/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('company/showCompany', this.$route.params.companyId).catch((error) => {
                showErrorMessage(error);
            })
        } else{
            this.$store.dispatch('company/searchCompanyNameList', this.$store.state.company.form.companyId).catch((error) => {
                showErrorMessage(error);
            });
        }
        if(this.$store.getters['common/getLastOperation'].path != "company-update") {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: "Company ", value: 'company'});
            this.$store.dispatch('common/setSelectedTable', "company");
        }
    }
}
</script>
