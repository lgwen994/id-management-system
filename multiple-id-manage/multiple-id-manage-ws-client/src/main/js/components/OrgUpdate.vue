<template>
    <div>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>組織マスタ詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px" id="orgUpdateForm">
                <el-form-item label="組織ID">
                    <el-input v-model="form.orgId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="組織コード">
                    <el-input v-model="form.orgCode" :disabled="true" id="orgCode"></el-input>
                </el-form-item>
                <el-form-item label="会社マスタ">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="グループフラグ">
                    <el-radio v-model="form.groupFlg" label="0" :disabled="!editable">実在組織</el-radio>
                    <el-radio v-model="form.groupFlg" label="1" :disabled="!editable">仮想組織</el-radio>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="組織名">
                    <el-input v-model="form.orgName" :disabled="!editable" id="orgName"></el-input>
                    <span id="message_orgName"></span>
                </el-form-item>
                <el-form-item label="組織種別">
                    <el-input v-model="form.orgType" :disabled="!editable" id="orgType"></el-input>
                    <span id="message_orgType"></span>
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
                        <el-button type="primary" @click="update" id="updateOrg">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                組織名一覧
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="regist">追加</el-button>
                    <el-button type="primary" @click="deleteOrgName">削除</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              データをダブルクリックすると詳細を表示します
              <el-table :data="orgNameList" stripe border highlight-current-row @row-dblclick="showOrgName" @selection-change="handleOrgNameSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="locale" label="ロケール" width="100" :formatter="getLocale"></el-table-column>
                  <el-table-column prop="orgName" label="組織名" width="200"></el-table-column>
                  <el-table-column prop="activeStartTime" label="有効開始日時" width="160" :formatter="formatActiveStartTime"></el-table-column>
                  <el-table-column prop="activeEndTime" label="有効終了日時" width="160" :formatter="formatActiveEndTime"></el-table-column>
              </el-table>
            </el-row>
            <br>
            <el-row>
                <el-col :span="12">
                組織階層一覧
                </el-col>
                <el-col :span="12">
                <div v-if="hierarchyEditable" style="text-align:right;">
                    <el-button type="primary" @click="addOrgHierarchy">追加</el-button>
                    <el-button type="primary" @click="deleteOrgHierarchy">削除</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              データをダブルクリックすると詳細を表示します
              <el-table :data="orgHierarchyList" stripe border highlight-current-row @current-change="handleOrgHierarchySelectionChange">
                  <el-table-column prop="hierarchyCode" label="組織階層コード" width="130">
                      <template slot-scope="scope">
                          <el-input v-model="scope.row.hierarchyCode" :disabled="!hierarchyEditable" v-if="scope.row.hierarchyCodeEditable" id="hierarchyCode"></el-input>
                          <el-input v-model="scope.row.hierarchyCode" :disabled="true" v-else id="hierarchyCode"></el-input>
                      </template>
                  </el-table-column>
                  <el-table-column prop="highOrg" label="上位組織マスタ" width="400">
                      <template slot-scope="scope">
                          <el-row>
                              <el-col :span="20">
                                  <el-input v-model="scope.row.highOrg" :disabled="true" id="highOrg"></el-input>
                              </el-col>
                              <el-col :span="4">
                                  <el-button :disabled="!hierarchyEditable" type="primary" @click="openOrgDialog(scope.$index)">参照</button>
                              </el-col>
                          </el-row>
                      </template>
                  </el-table-column>
                  <el-table-column prop="activeStartTime" label="有効開始日時" width="250">
                      <template slot-scope="scope">
                          <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="scope.row.activeStartTime" :disabled="!hierarchyEditable" id="activeStartTime"></el-date-picker>
                      </template>
                  </el-table-column>
                  <el-table-column prop="activeEndTime" label="有効終了日時" width="250">
                      <template slot-scope="scope">
                          <el-date-picker type="datetime" format="yyyy/MM/dd HH:mm:ss" v-model="scope.row.activeEndTime" :disabled="!hierarchyEditable" id="activeEndTime"></el-date-picker>
                      </template>
                  </el-table-column>
              </el-table>
            </el-row>
            <el-row>
                <br>
                <div v-if="hierarchyEditable" style="text-align:right;">
                    <el-button @click="cancelOrgHierarchy">キャンセル</el-button>
                    <el-button @click="resetOrgHierarchy">リセット</el-button>
                    <el-button type="primary" @click="updateOrgHierarchy">更新</el-button>
                </div>
                <div v-else style="text-align:right;">
                    <el-button @click="orgHierarchyChangeMode(true)" type="primary">編集</el-button>
                </div>
            </el-row>
            <br>
            <el-row>
                <el-col :span="12">
                    所属一覧
                </el-col>
                <el-col :span="12">
                <div style="text-align:right;">
                    <el-button type="primary" @click="registPosition">追加</el-button>
                    <el-button type="primary" @click="deletePosition">削除</el-button>
                </div>
                </el-col>
            </el-row>
            <el-row>
              データをダブルクリックすると詳細を表示します
              <el-table :data="positionList" stripe border highlight-current-row @row-dblclick="showPosition" @selection-change="handlePositionSelectionChange">
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column prop="positionCode" label="所属コード" width="140"></el-table-column>
                  <el-table-column prop="user" label="ユーザマスタ" width="200"></el-table-column>
                  <el-table-column prop="title" label="役職マスタ" width="200"></el-table-column>
                  <el-table-column prop="concurrentFlg" label="兼務フラグ" width="100" :formatter="concurrentLabel"></el-table-column>
                  <el-table-column prop="activeStartTime" label="有効開始日時" width="160" :formatter="formatActiveStartTime"></el-table-column>
                  <el-table-column prop="activeEndTime" label="有効終了日時" width="160" :formatter="formatActiveEndTime"></el-table-column>
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
import Vue from 'vue'
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
        getLocale(row, column) {
            return this.$store.getters['common/getLocale'](row.locale);
        },
        formatActiveStartTime(row, column) {
            return formatDate(row.activeStartTime);
        },
        formatActiveEndTime(row, column) {
            return formatDate(row.activeEndTime);
        },
        regist(event) {
            this.$router.push({
                name: 'orgNameRegist',
                params: this.$store.state.org.form
            });
            this.$store.dispatch('common/setNaviList', {name: '組織名マスタ登録', path: 'orgName-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'orgName');
        },
        deleteOrgName(event) {
            if(this.$store.state.orgName.selectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('orgName/deleteOrgName').then(() => {
                        this.$store.dispatch('org/searchOrgNameList', this.$store.state.org.form.orgId);
                        this.$alert('削除が完了しました。', '削除完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // キャンセルの場合
                });
            }
        },
        registPosition(event) {
            this.$router.push({
                name: 'positionRegist',
                params: this.$store.state.org.form
            });
            this.$store.dispatch('common/setNaviList', {name: '所属登録', path: 'position-regist'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'position');
        },
        deletePosition(event) {
            if(this.$store.state.position.positionSelectedList.length === 0) {
                this.$alert('削除するデータを選択してください。', '確認', {
                    confirmButtonText: 'OK'
                });
            } else {
                this.$confirm('削除します。よろしいですか？', '削除確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('position/deletePosition').then(() => {
                        this.$store.dispatch('org/searchPositionInfo', {orgId : this.$store.state.org.form.orgId, page : this.$store.state.org.pagePosition});
                        this.$alert('削除が完了しました。', '削除完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => {
                        showErrorMessage(error);
                    });
                }).catch(() => {
                    // キャンセルの場合
                });
            }
        },
        addOrgHierarchy(event) {
            this.$store.dispatch('org/addOrgHierarchy');
        },
        deleteOrgHierarchy(event) {
            this.$store.dispatch('org/deleteOrgHierarchy');
        },
        resetOrgHierarchy() {
            this.$store.dispatch('org/resetOrgHierarchy');
        },
        cancelOrgHierarchy() {
            this.resetOrgHierarchy();
            this.orgHierarchyChangeMode(false);
        },
        updateOrgHierarchy(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('org/updateOrgHierarchyList').then(() => {
                    this.orgHierarchyChangeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        update: function(event) {
            this.$confirm('更新します。よろしいですか？', '更新確認', {
                confirmButtonText: 'OK',
                cancelButtonText: 'キャンセル',
                type: 'warning'
            }).then(() => {
                this.$store.dispatch('org/updateOrg').then(() => {
                    this.changeMode(false);
                    this.$alert('更新が完了しました。', '更新完了', {
                        confirmButtonText: 'OK'
                    });
                }).catch(error => showErrorMessage(error));
            }).catch(() => {
                // キャンセルの場合
            });
        },
        showOrgName(row) {
            this.$router.push({ name: 'orgNameUpdate', params: { orgNameId: row.orgNameId }});
            this.$store.dispatch('common/setNaviList', {name: '組織名詳細', path: 'orgName-update'});
            this.$store.dispatch('common/setTable', null);
            this.$store.dispatch('common/setSelectedTable', 'orgName');
        },
        showPosition(row) {
            this.$router.push({ name: 'positionUpdate', params: { positionId: row.positionId }});
            this.$store.dispatch('common/setNaviList', {name: '所属詳細', path: 'position-update'});
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
                return '本務';
            } else if(row.concurrentFlg === 1) {
                return '兼務';
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
            this.$store.dispatch('common/setTable', {name: '組織マスタ', value: 'org'});
            this.$store.dispatch('common/setSelectedTable', 'org');
        }
    }
}
</script>
