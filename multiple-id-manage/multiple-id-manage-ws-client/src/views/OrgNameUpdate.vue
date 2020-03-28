<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Orgnazation NameDetails</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px">
                <el-form-item label="Orgnazation NameID">
                    <el-input v-model="form.orgNameId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Orgnazation ">
                    <el-input v-model="org" :disabled="true" id="org"></el-input>
                </el-form-item>
                <el-form-item label="Locale">
                  <el-select v-model="form.locale" :disabled="!editable" id="locale">
                      <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="Orgnazation Name">
                    <el-input v-model="form.orgName" :disabled="!editable" id="orgName"></el-input>
                    <span id="message_orgName"></span>
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
        ...mapState('orgName', {
            org(state) {
                if(state.form.orgMst.orgId !== '') {
                    return state.form.orgMst.orgCode + ':' + state.form.orgMst.orgName + '(' + state.form.orgMst.activeStartTime + '-' + state.form.orgMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('orgName', ['form', 'editable']),
        ...mapState('common', ['locales'])
    },
    methods: {
        changeMode: function(flg) {
            this.$store.dispatch('orgName/changeMode', flg);
        },
        update: function() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('orgName/updateOrgName').then(() => {
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
            this.$store.dispatch('orgName/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        back() {
            this.$router.push({name: 'orgUpdate'});
            this.$store.dispatch('common/deleteNavi', {name: 'Orgnazation NameDetails', path: 'orgName-update'});
            this.$store.dispatch('common/setTable', {name: 'Orgnazation ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    },
    created: function() {
        this.$store.dispatch('orgName/showOrgName', this.$route.params.orgNameId).catch(error => {
            showErrorMessage(error);
        });
    }
}
</script>
