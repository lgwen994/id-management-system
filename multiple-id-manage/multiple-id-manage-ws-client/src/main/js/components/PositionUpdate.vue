<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>所属詳細</span>
            </div>
            <div>
                <el-row>
                    <el-form ref="form" :model="form" label-width="150px">
                        <el-form-item label="所属ID">
                            <el-input v-model="form.positionId" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item label="所属コード">
                            <el-input v-model="form.positionCode" :disabled="true" id="positionCode"></el-input>
                        </el-form-item>
                        <el-form-item label="ユーザマスタ">
                            <el-col :span="20">
                                <el-input v-model="user" :disabled="true" id="user"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button :disabled="!editable" type="primary" @click="openUserDialog">参照</button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="組織マスタ">
                            <el-input v-model="org" :disabled="true" id="org"></el-input>
                        </el-form-item>
                        <el-form-item label="役職マスタ">
                            <el-col :span="20">
                                <el-input v-model="title" :disabled="true" id="title"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button :disabled="!editable" type="primary" @click="openTitleDialog">参照</button>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="兼務フラグ">
                            <el-radio v-model="form.concurrentFlg" label="0" :disabled="!editable">本務</el-radio>
                            <el-radio v-model="form.concurrentFlg" label="1" :disabled="!editable">兼務</el-radio>
                        </el-form-item>
                        <el-form-item label="有効開始日時">
                            <el-date-picker v-model="form.activeStartTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" style="width: 100%;" id="activeStartTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="有効終了日時">
                            <el-date-picker v-model="form.activeEndTime" type="datetime" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" style="width: 100%;" id="activeEndTime"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="作成日時">
                            <el-date-picker type="datetime" v-model="form.createdTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="作成者">
                            <el-input v-model="form.createdUser" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item label="変更日時">
                            <el-date-picker type="datetime" v-model="form.updatedTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="true"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="変更者">
                            <el-input v-model="form.updatedUser" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item label="論理削除フラグ">
                            <el-input v-model="form.deletedFlg" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item label="バージョン">
                            <el-input v-model="form.versionNo" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <div v-if="editable" style="text-align:right;">
                                <el-button @click="cancel">キャンセル</el-button>
                                <el-button @click="resetForm">リセット</el-button>
                                <el-button type="primary" @click="updatePosition">更新</el-button>
                            </div>
                            <div v-else style="text-align:right;">
                              <span v-if="!editable && !agentEditable">
                                  <el-button @click="back">戻る</el-button>
                              </span>
                              <el-button @click="changeMode(true)" type="primary">編集</el-button>
                          </div>
                        </el-form-item>
                    </el-form>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        ロール一覧
                    </el-col>
                    <el-col :span="12">
                        <div v-if="roleEditable" style="text-align:right;">
                            <el-button type="primary" @click="openRoleDialog">追加</el-button>
                            <el-button type="primary" @click="deleteRole">削除</el-button>
                        </div>
                    </el-col>
                </el-row>
                <el-row>
                    <el-table :data="roleList" stripe border highlight-current-row @current-change="handleRoleSelectionChange">
                        <el-table-column prop="role.roleCode" label="ロールコード" width="130"></el-table-column>
                        <el-table-column prop="role.roleName" label="ロール名" width="100"></el-table-column>
                        <el-table-column prop="positionRole.activeStartTime" label="有効開始日時" width="250">
                            <template slot-scope="scope">
                                <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="roleList[scope.$index].positionRole.activeStartTime" :disabled="!roleEditable" id="activeStartTime"></el-date-picker>
                            </template>
                        </el-table-column>
                        <el-table-column prop="positionRole.activeEndTime" label="有効終了日時" width="250">
                            <template slot-scope="scope">
                                <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="roleList[scope.$index].positionRole.activeEndTime" :disabled="!roleEditable" id="activeEndTime"></el-date-picker>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-row>
                <el-row>
                    <div v-if="roleEditable" style="text-align:right;">
                        <el-button @click="cancelRole">キャンセル</el-button>
                        <el-button @click="resetRole">リセット</el-button>
                        <el-button type="primary" @click="updateRole">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="roleChangeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-row>
                <br>
                <el-row>
                    <el-col :span="12">
                        代行一覧
                    </el-col>
                    <el-col :span="12">
                        <div v-if="agentEditable" style="text-align:right;">
                            <el-button type="primary" @click="addAgent">追加</el-button>
                            <el-button type="primary" @click="deleteAgent">削除</el-button>
                        </div>
                    </el-col>
                </el-row>
                <el-row>
                    <el-table :data="agentList" stripe border highlight-current-row @current-change="handleAgentSelectionChange">
                        <el-table-column prop="agentCode" label="代行コード" width="130">
                            <template slot-scope="scope">
                                <el-input v-model="scope.row.agentCode" :disabled="!agentEditable" v-if="scope.row.agentCodeEditable" id="agentCode"></el-input>
                                <el-input v-model="scope.row.agentCode" :disabled="true" v-else id="agentCode"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="agentedPositionId" label="非代行所属" width="400">
                            <template slot-scope="scope">
                                <el-row>
                                    <el-col :span="20">
                                        <el-input v-model="scope.row.agentedPositionMst.positionCode" :disabled="true" id="agentedPositionCode"></el-input>
                                    </el-col>
                                    <el-col :span="4">
                                        <el-button :disabled="!agentEditable" type="primary" @click="openPositionDialog(scope.$index)">参照</button>
                                    </el-col>
                                </el-row>
                            </template>
                        </el-table-column>
                        <el-table-column prop="activeStartTime" label="有効開始日時" width="250">
                            <template slot-scope="scope">
                                <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="scope.row.activeStartTime" :disabled="!agentEditable" id="activeStartTime"></el-date-picker>
                            </template>
                        </el-table-column>
                        <el-table-column prop="activeEndTime" label="有効終了日時" width="250">
                            <template slot-scope="scope">
                                <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="scope.row.activeEndTime" :disabled="!agentEditable" id="activeEndTime"></el-date-picker>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-row>
                <el-row>
                    <div v-if="agentEditable" style="text-align:right;">
                        <el-button @click="cancelAgent">キャンセル</el-button>
                        <el-button @click="resetAgent">リセット</el-button>
                        <el-button type="primary" @click="updateAgent">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="agentChangeMode(true)" type="primary">編集</el-button>
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
import Vue from 'vue'
import { mapState } from 'vuex'
import {formatDate, showErrorMessage} from '../common';
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
        updatePosition(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('position/updatePosition').then(() => {
                    this.changeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        openRoleDialog(event) {
            this.$store.dispatch('roleReference/openRoleDialog').catch(error => showErrorMessage(error));
        },
        handleRoleSelectionChange(val) {
            this.$store.dispatch('position/setSelectedRole', val);
        },
        deleteRole(event) {
            this.$store.dispatch('position/deleteRole');
        },
        roleChangeMode(flg) {
            this.$store.dispatch('position/roleChangeMode', flg);
        },
        updateRole(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('position/updateRole').then(() => {
                    this.roleChangeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        resetRole() {
            this.$store.dispatch('position/resetRole');
        },
        cancelRole() {
            this.resetRole();
            this.roleChangeMode(false);
        },
        openUserDialog(event) {
            this.$store.dispatch('userReference/openUserDialog', {screen: 'position', kbn: 'Regist'}).catch(error => showErrorMessage(error));
        },
        openTitleDialog(event) {
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
        updateAgent(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('position/updateAgentList').then(() => {
                    this.agentChangeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        addAgent(event) {
            this.$store.dispatch('position/addAgent');
        },
        deleteAgent(event) {
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
            this.$store.dispatch('common/deleteNavi', {name: '所属詳細', path: 'position-update'});
            this.$store.dispatch('common/setTable', {name: '組織マスタ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    },
    created: function() {
        if(Object.keys(this.$route.params).length !== 0) {
            this.$store.dispatch('position/showPosition', this.$route.params.positionId);
        } else {
            // 本来は組織マスタ詳細画面から遷移するときにポジションIDが渡される
            // 組織マスタ詳細画面がないので、ポジションIDを指定している
            var positionId = 'I_POSITION_01';
            this.$store.dispatch('position/showPosition', positionId);
        }

        // if(this.$store.getters['common/getLastOperation'].path !== 'role-update') {
        //     this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
        //     this.$store.dispatch('common/setTable', {name: 'ロール', value: 'role'});
        //     this.$store.dispatch('common/setSelectedTable', 'role');
        // }
    }

}
</script>
