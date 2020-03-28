<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>Position Details</span>
            </div>
            <div>
                <el-row>
                    <el-form ref="form" :model="form" label-width="150px">
                        <el-form-item label="Position ID">
                            <el-input v-model="form.positionId" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item label="Position Code">
                            <el-input v-model="form.positionCode" :disabled="true" id="positionCode"></el-input>
                        </el-form-item>
                        <el-form-item label="User ">
                            <el-col :span="20">
                                <el-input v-model="user" :disabled="true" id="user"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button :disabled="!editable" type="primary" @click="openUserDialog">Dialog</el-button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="Orgnazation">
                            <el-input v-model="org" :disabled="true" id="org"></el-input>
                        </el-form-item>
                        <el-form-item label="Title ">
                            <el-col :span="20">
                                <el-input v-model="title" :disabled="true" id="title"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button :disabled="!editable" type="primary" @click="openTitleDialog">Dialog</el-button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="Concurrent Flg">
                            <el-radio v-model="form.concurrentFlg" label="0" :disabled="!editable">Own Post</el-radio>
                            <el-radio v-model="form.concurrentFlg" label="1" :disabled="!editable">Additional Post</el-radio>
                        </el-form-item>
                        <el-form-item label="Active Start Time">
                            <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" style="width: 100%;" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="Active End Time">
                            <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" style="width: 100%;" id="activeEndTime"></el-date-picker>
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
                                <el-button type="primary" @click="updatePosition">Update</el-button>
                            </div>
                            <div v-else style="text-align:right;">
                              <span v-if="!editable && !agentEditable">
                                  <el-button @click="back">Back</el-button>
                              </span>
                              <el-button @click="changeMode(true)" type="primary">Edit</el-button>
                          </div>
                        </el-form-item>
                    </el-form>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        Role  List
                    </el-col>
                    <el-col :span="12">
                        <div v-if="roleEditable" style="text-align:right;">
                            <el-button type="primary" @click="openRoleDialog">Create</el-button>
                            <el-button type="primary" @click="deleteRole">Delete</el-button>
                        </div>
                    </el-col>
                </el-row>
                <el-row>
                    <el-table :data="roleList" stripe border highlight-current-row @current-change="handleRoleSelectionChange">
                        <el-table-column prop="role.roleCode" label="Role Code" width="130"></el-table-column>
                        <el-table-column prop="role.roleName" label="Role Name" width="100"></el-table-column>
                        <el-table-column prop="positionRole.activeStartTime" label="Active Start Time" width="250">
                            <template slot-scope="scope">
                                <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="roleList[scope.$index].positionRole.activeStartTime" :disabled="!roleEditable" id="activeStartTime"></el-date-picker>
                            </template>
                        </el-table-column>
                        <el-table-column prop="positionRole.activeEndTime" label="Active End Time" width="250">
                            <template slot-scope="scope">
                                <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="roleList[scope.$index].positionRole.activeEndTime" :disabled="!roleEditable" id="activeEndTime"></el-date-picker>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-row>
                <el-row>
                    <div v-if="roleEditable" style="text-align:right;">
                        <el-button @click="cancelRole">Cancel</el-button>
                        <el-button @click="resetRole">Reset</el-button>
                        <el-button type="primary" @click="updateRole">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="roleChangeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-row>
                <br>
                <el-row>
                    <el-col :span="12">
                        Agent List
                    </el-col>
                    <el-col :span="12">
                        <div v-if="agentEditable" style="text-align:right;">
                            <el-button type="primary" @click="addAgent">Create</el-button>
                            <el-button type="primary" @click="deleteAgent">Delete</el-button>
                        </div>
                    </el-col>
                </el-row>
                <el-row>
                    <el-table :data="agentList" stripe border highlight-current-row @current-change="handleAgentSelectionChange">
                        <el-table-column prop="agentCode" label="Agent Code" width="130">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.agentCode" :disabled="!agentEditable" v-if="scope.row.agentCodeEditable" id="agentCode"></el-input>
                                <el-input v-model="scope.row.agentCode" :disabled="true" v-else id="agentCode"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="agentedPositionId" label="Non Agent Position " width="400">
                            <template slot-scope="scope">
                                <el-row>
                                    <el-col :span="20">
                                        <el-input v-model="scope.row.agentedPositionMst.positionCode" :disabled="true" id="agentedPositionCode"></el-input>
                                    </el-col>
                                    <el-col :span="4">
                                        <el-button :disabled="!agentEditable" type="primary" @click="openPositionDialog(scope.$index)">Dialog</el-button>
                                    </el-col>
                                </el-row>
                            </template>
                        </el-table-column>
                        <el-table-column prop="activeStartTime" label="Active Start Time" width="250">
                            <template slot-scope="scope">
                                <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="scope.row.activeStartTime" :disabled="!agentEditable" id="activeStartTime"></el-date-picker>
                            </template>
                        </el-table-column>
                        <el-table-column prop="activeEndTime" label="Active End Time" width="250">
                            <template slot-scope="scope">
                                <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="scope.row.activeEndTime" :disabled="!agentEditable" id="activeEndTime"></el-date-picker>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-row>
                <el-row>
                    <div v-if="agentEditable" style="text-align:right;">
                        <el-button @click="cancelAgent">Cancel</el-button>
                        <el-button @click="resetAgent">Reset</el-button>
                        <el-button type="primary" @click="updateAgent">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="agentChangeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-row>
            </div>
        </el-card>
        <role-dialog></role-dialog>
        <title-dialog></title-dialog>
        <user-dialog></user-dialog>
        <position-dialog></position-dialog>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import showErrorMessage from '../common';
import RoleReference from './RoleReference.vue';
import TitleReference from './TitleReference.vue';
import PositionReference from './PositionReference.vue';
import UserReference from './UserReference.vue';

export default {
    components: {
        'role-dialog': RoleReference,
        'title-dialog': TitleReference,
        'position-dialog': PositionReference,
        'user-dialog': UserReference
    },
    computed: {
        ...mapState('position', {
            user(state) {
                if(state.form.userMst.userId !== '') {
                    return state.form.userMst.userCode + ':' + state.form.userMst.userName + '(' + state.form.userMst.activeStartTime + '-' + state.form.userMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            org(state) {
                if(state.form.orgMst.orgId !== '') {
                    return state.form.orgMst.orgCode + ':' + state.form.orgMst.orgName +  '(' + state.form.orgMst.activeStartTime + '-' + state.form.orgMst.activeEndTime + ')';
                } else {
                    return '';
                }
            },
            title(state) {
                if(state.form.titleMst.titleId !== '') {
                    return state.form.titleMst.titleCode + ':' + state.form.titleMst.titleName +  '(' + state.form.titleMst.activeStartTime + '-' + state.form.titleMst.activeEndTime + ')';
                } else {
                    return '';
                }
            }
        }),
        ...mapState('position', ['form', 'editable', 'roleList', 'roleEditable', 'agentList', 'agentEditable'])
    },
    methods: {
        changeMode(flg) {
            this.$store.dispatch('position/changeMode', flg);
        },
        resetForm() {
            this.$store.dispatch('position/resetForm');
        },
        cancel() {
            this.resetForm();
            this.changeMode(false);
        },
        updatePosition() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('position/updatePosition').then(() => {
                    this.changeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        openRoleDialog() {
            this.$store.dispatch('roleReference/openRoleDialog').catch(error => showErrorMessage(error));
        },
        handleRoleSelectionChange(val) {
            this.$store.dispatch('position/setSelectedRole', val);
        },
        deleteRole() {
            this.$store.dispatch('position/deleteRole');
        },
        roleChangeMode(flg) {
            this.$store.dispatch('position/roleChangeMode', flg);
        },
        updateRole() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('position/updateRole').then(() => {
                    this.roleChangeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        resetRole() {
            this.$store.dispatch('position/resetRole');
        },
        cancelRole() {
            this.resetRole();
            this.roleChangeMode(false);
        },
        openUserDialog() {
            this.$store.dispatch('userReference/openUserDialog', {screen: 'position', kbn: 'Regist'}).catch(error => showErrorMessage(error));
        },
        openTitleDialog() {
            this.$store.dispatch('titleReference/openTitleDialog', {
                companyId: this.$store.state.position.form.orgMst.companyId,
                screen: 'position'
            }).catch(error => showErrorMessage(error));
        },
        agentChangeMode(flg) {
            this.$store.dispatch('position/agentChangeMode', flg);
        },
        openPositionDialog(index) {
            this.$store.dispatch('position/setPositionIndex', index);
            this.$store.dispatch('positionReference/openPositionDialog').catch(error => showErrorMessage(error));;
        },
        handleAgentSelectionChange(val) {
            this.$store.dispatch('position/setSelectedAgent', val);
        },
        updateAgent() {
            this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                ConfirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('position/updateAgentList').then(() => {
                    this.agentChangeMode(false);
                    this.$alert('Update is completed.', 'Updated', {
                        ConfirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // Cancel
            });
        },
        addAgent() {
            this.$store.dispatch('position/addAgent');
        },
        deleteAgent() {
            this.$store.dispatch('position/deleteAgentList');
        },
        resetAgent() {
            this.$store.dispatch('position/resetAgent');
        },
        cancelAgent() {
            this.resetAgent();
            this.agentChangeMode(false);
        },
        back() {
            this.$router.push({name: 'orgUpdate'});
            this.$store.dispatch('common/deleteNavi', {name: 'Position Details', path: 'position-update'});
            this.$store.dispatch('common/setTable', {name: 'Orgnazation ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('position/showPosition', this.$route.params.positionId);
        } else {
            var positionId = 'I_POSITION_01';
            this.$store.dispatch('position/showPosition', positionId);
        }

    }

}
</script>
