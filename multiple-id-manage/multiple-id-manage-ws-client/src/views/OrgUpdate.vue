<template>
    <div>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Orgnazation Details</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px" id="orgUpdateForm">
                <el-form-item label="Orgnazation ID">
                    <el-input v-model="form.orgId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Orgnazation Code">
                    <el-input v-model="form.orgCode" :disabled="true" id="orgCode"></el-input>
                </el-form-item>
                <el-form-item label="Company">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Group Flg">
                    <el-radio v-model="form.groupFlg" label="0" :disabled="!editable">Real Orgnazation </el-radio>
                    <el-radio v-model="form.groupFlg" label="1" :disabled="!editable">Logical Orgnazation </el-radio>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Orgnazation Name">
                    <el-input v-model="form.orgName" :disabled="!editable" id="orgName"></el-input>
                    <span id="message_orgName"></span>
                </el-form-item>
                <el-form-item label="Orgnazation Type">
                    <el-input v-model="form.orgType" :disabled="!editable" id="orgType"></el-input>
                    <span id="message_orgType"></span>
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
                        <el-button type="primary" @click="update" id="updateOrg">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                Orgnazation Name List
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">Create</el-button>
                    <el-button type="primary" @click="deleteOrgName">Delete</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              Double-click on data to see details
              <el-table :data="orgNameList" stripe border highlight-current-row @row-dblclick="showOrgName" @selection-change="handleOrgNameSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="locale" label="Locale" width="100" :formatter="getLocale"></el-table-column>
                  <el-table-column prop="orgName" label="Orgnazation Name" width="200"></el-table-column>
                  <el-table-column prop="activeStartTime" label="Active Start Time" width="160" :formatter="formatActiveStartTime"></el-table-column>
                  <el-table-column prop="activeEndTime" label="Active End Time" width="160" :formatter="formatActiveEndTime"></el-table-column>
              </el-table>
            </el-row>
            <br>
            <el-row>
                <el-col :span="12">
                Orgnazation 階層 List
                </el-col>
                <el-col :span="12">
                <div v-if="hierarchyEditable" style="text-align:right;">
                    <el-button type="primary" @click="addOrgHierarchy">Create</el-button>
                    <el-button type="primary" @click="deleteOrgHierarchy">Delete</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              Double-click on data to see details
              <el-table :data="orgHierarchyList" stripe border highlight-current-row @current-change="handleOrgHierarchySelectionChange">
                  <el-table-column prop="hierarchyCode" label="Orgnazation 階層Code" width="130">
                      <template slot-scope="scope">
                          <el-input v-model="scope.row.hierarchyCode" :disabled="!hierarchyEditable" v-if="scope.row.hierarchyCodeEditable" id="hierarchyCode"></el-input>
                          <el-input v-model="scope.row.hierarchyCode" :disabled="true" v-else id="hierarchyCode"></el-input>
                      </template>
                  </el-table-column>
                  <el-table-column prop="highOrg" label="上位Orgnazation " width="400">
                      <template slot-scope="scope">
                          <el-row>
                              <el-col :span="20">
                                  <el-input v-model="scope.row.highOrg" :disabled="true" id="highOrg"></el-input>
                              </el-col>
                              <el-col :span="4">
                                  <el-button :disabled="!hierarchyEditable" type="primary" @click="openOrgDialog(scope.$index)">Dialog</el-button>
                              </el-col>
                          </el-row>
                      </template>
                  </el-table-column>
                  <el-table-column prop="activeStartTime" label="Active Start Time" width="250">
                      <template slot-scope="scope">
                          <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="scope.row.activeStartTime" :disabled="!hierarchyEditable" id="activeStartTime"></el-date-picker>
                      </template>
                  </el-table-column>
                  <el-table-column prop="activeEndTime" label="Active End Time" width="250">
                      <template slot-scope="scope">
                          <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="scope.row.activeEndTime" :disabled="!hierarchyEditable" id="activeEndTime"></el-date-picker>
                      </template>
                  </el-table-column>
              </el-table>
            </el-row>
            <el-row>
                <br>
                <div v-if="hierarchyEditable" style="text-align:right;">
                    <el-button @click="cancelOrgHierarchy">Cancel</el-button>
                    <el-button @click="resetOrgHierarchy">Reset</el-button>
                    <el-button type="primary" @click="updateOrgHierarchy">Update</el-button>
                </div>
                <div v-else style="text-align:right;">
                    <el-button @click="orgHierarchyChangeMode(true)" type="primary">Edit</el-button>
                </div>
            </el-row>
            <br>
            <el-row>
                <el-col :span="12">
                    Position  List
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="registPosition">Create</el-button>
                    <el-button type="primary" @click="deletePosition">Delete</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              Double-click on data to see details
              <el-table :data="positionList" stripe border highlight-current-row @row-dblclick="showPosition" @selection-change="handlePositionSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="positionCode" label="Position Code" width="140"></el-table-column>
                  <el-table-column prop="user" label="User " width="200"></el-table-column>
                  <el-table-column prop="title" label="Title " width="200"></el-table-column>
                  <el-table-column prop="concurrentFlg" label="Concurrent Flg" width="100" :formatter="concurrentLabel"></el-table-column>
                  <el-table-column prop="activeStartTime" label="Active Start Time" width="160" :formatter="formatActiveStartTime"></el-table-column>
                  <el-table-column prop="activeEndTime" label="Active End Time" width="160" :formatter="formatActiveEndTime"></el-table-column>
              </el-table>
              <el-pagination layout="prev, pager, next" :total="positionSize" page-size="10" @current-change="handlePositionCurrentChange" :current-page="pagePosition">
              </el-pagination>
            </el-row>
        </div>
    </el-card>
    <org-dialog></org-dialog>
    </div>
</template>

<script>
//import Vue from 'vue'
import { mapState } from 'vuex'
import { formatDate, showErrorMessage } from '../common';
import OrgReference from './OrgReference.vue';

export default {
    components: {
        'org-dialog':OrgReference
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
        ...mapState('org', ['form', 'orgNameList','orgHierarchyList','editable', 'hierarchyEditable', 'positionList', 'positionSize', 'pagePosition'])
    },
    methods: {
        handleOrgNameSelectionChange(val) {
            this.$store.dispatch('orgName/setSelectedList', val);
        },
        handleOrgHierarchySelectionChange(val) {
            this.$store.dispatch('org/setSelectedOrgHierarchy', val);
        },
        handlePositionSelectionChange(val) {
            this.$store.dispatch('position/setPositionSelectedList', val);
        },
        orgHierarchyChangeMode(flg) {
            this.$store.dispatch('org/orgHierarchyChangeMode', flg);
        },
        changeMode(flg) {
            this.$store.dispatch('org/changeMode', flg);
        },
        handlePositionCurrentChange(val) {
            this.$store.dispatch('org/searchPositionInfo', {orgId : this.$store.state.org.form.orgId, page : val});
        },
        //getLocale(row, column) {
        getLocale(row) {
            return this.$store.getters['common/getLocale'](row.locale);
        },
        //formatActiveStartTime(row, column) {
        formatActiveStartTime(row) {
            return formatDate(row.activeStartTime);
        },
        //formatActiveEndTime(row, column) {
        formatActiveEndTime(row) {
            return formatDate(row.activeEndTime);
        },
        regist() {
            this.$router.push({
                name: 'orgNameRegist',
                params: this.$store.state.org.form
            });
            this.$store.dispatch('common/setNaviList', {name: 'Orgnazation Name Create', path: 'orgName-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'orgName');
        },
        deleteOrgName() {
            if(this.$store.state.orgName.selectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('orgName/deleteOrgName').then(() => {
                        this.$store.dispatch('org/searchOrgNameList', this.$store.state.org.form.orgId);
                        this.$alert('Delete completed.', 'Deleted', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // Cancel
                });
            }
        },
        registPosition() {
            this.$router.push({
                name: 'positionRegist',
                params: this.$store.state.org.form
            });
            this.$store.dispatch('common/setNaviList', {name: 'Position  Create', path: 'position-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'position');
        },
        deletePosition() {
            if(this.$store.state.position.positionSelectedList.length === 0) {
                this.$alert('Please select the data to be deleted.', 'Confirm', {
                    ConfirmButtonText: 'OK'
                });
            } else {
                this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('position/deletePosition').then(() => {
                        this.$store.dispatch('org/searchPositionInfo', {orgId : this.$store.state.org.form.orgId, page : this.$store.state.org.pagePosition});
                        this.$alert('Delete completed.', 'Deleted', {
                            ConfirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // Cancel
                });
            }
        },
        addOrgHierarchy() {
            this.$store.dispatch('org/addOrgHierarchy');
        },
        deleteOrgHierarchy() {
            this.$store.dispatch('org/deleteOrgHierarchy');
        },
        resetOrgHierarchy() {
            this.$store.dispatch('org/resetOrgHierarchy');
        },
        cancelOrgHierarchy() {
            this.resetOrgHierarchy();
            this.orgHierarchyChangeMode(false);
        },
        updateOrgHierarchy() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('org/updateOrgHierarchyList').then(() => {
                    this.orgHierarchyChangeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        update: function() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('org/updateOrg').then(() => {
                    this.changeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        showOrgName(row) {
            this.$router.push({ name: 'orgNameUpdate', params: { orgNameId: row.orgNameId }});
            this.$store.dispatch('common/setNaviList', {name: 'Orgnazation NameDetails', path: 'orgName-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'orgName');
        },
        showPosition(row) {
            this.$router.push({ name: 'positionUpdate', params: { positionId: row.positionId }});
            this.$store.dispatch('common/setNaviList', {name: 'Position Details', path: 'position-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'position');
        },
        openOrgDialog(index) {
            this.$store.dispatch('org/setOrgHierarchyIndex', index);
            this.$store.dispatch('orgReference/openOrgDialog', {screen: 'org'}).catch(error => showErrorMessage(error));
        },
        resetForm() {
            this.$store.dispatch('org/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        concurrentLabel(row) {
            if(row.concurrentFlg === 0) {
                return 'Own Post';
            } else if(row.concurrentFlg === 1) {
                return 'Additional Post';
            }
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('org/showOrg', this.$route.params.orgId).catch((error) => {
                showErrorMessage(error);
            })
         }else{
            this.$store.dispatch('org/searchOrgNameList', this.$store.state.org.form.orgId).catch((error) => { 
                showErrorMessage(error);
            });
            this.$store.dispatch('org/searchPositionInfo', {orgId : this.$store.state.org.form.orgId, page : this.$store.state.org.pagePosition}).catch((error) => { 
                showErrorMessage(error);
            });
        }
        if(this.$store.getters['common/getLastOperation'].path !== 'org-update') {
            this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
            this.$store.dispatch('common/setTable', {name: 'Orgnazation ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    }
}
</script>
