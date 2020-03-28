<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>Title Details</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px" id="titleUpdateForm">
                <el-form-item label="Title ID">
                    <el-input v-model="form.titleId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Title Code">
                    <el-input v-model="form.titleCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="Company">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="Group Flg">
                    <el-radio v-model="form.groupFlg" label="0" :disabled="!editable">Real Title </el-radio>
                    <el-radio v-model="form.groupFlg" label="1" :disabled="!editable">Logical Title </el-radio>
                </el-form-item>
                <el-form-item label="Active Start Time">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Active End Time">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="Title Name">
                    <el-input v-model="form.titleName" :disabled="!editable" id="titleName"></el-input>
                    <span id="message_titleName"></span>
                </el-form-item>
                <el-form-item label="Title Type">
                    <el-input v-model="form.titleType" :disabled="!editable" id="titleType"></el-input>
                    <span id="message_titleType"></span>
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
                        <el-button type="primary" @click="update" id="updateTitle">Update</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">Edit</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                    Title Name List
                </el-col>
                <el-col :span="12">
                    <div style="text-align:right;">
                        <el-button type="primary" @click="regist">Create</el-button>
                        <el-button type="primary" @click="deleteTitleName">Delete</el-button>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                Double-click on data to see details
                <el-table :data="titleNameList" stripe border highlight-current-row @row-dblclick="showTitleName" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="locale" label="Locale" width="100" :formatter="getLocale"></el-table-column>
                    <el-table-column prop="titleName" label="Title Name" width="100"></el-table-column>
                    <el-table-column prop="activeStartTime" label="Active Start Time" width="160" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="Active End Time" width="160" :formatter="formatActiveEndTime"></el-table-column>
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
            ...mapState('title', {
                company(state) {
                    if(state.form.companyMst.companyId !== '') {
                        return state.form.companyMst.companyCode + ':' + state.form.companyMst.companyName + '(' + state.form.companyMst.activeStartTime + '-' + state.form.companyMst.activeEndTime + ')';
                    } else {
                        return '';
                    }
                }
            }),
            ...mapState('title', ['form', 'titleNameList','editable'])
        },
        methods: {
            changeMode(flg) {
                this.$store.dispatch('title/changeMode', flg);
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
                    name: 'titleNameRegist',
                    params: this.$store.state.title.form
                });
                this.$store.dispatch('common/setNaviList', {name: 'Title Name Create', path: 'titleName-regist'});
                this.$store.dispatch('common/setTable', null);
                this.$store.dispatch('common/setSelectedTable', 'titleName');
            },
            handleSelectionChange(val) {
                this.$store.dispatch('titleName/setSelectedList', val);
            },
            deleteTitleName() {
                if(this.$store.state.titleName.selectedList.length === 0) {
                    this.$alert('Please select the data to be deleted.', 'Confirm', {
                        ConfirmButtonText: 'OK'
                    });
                } else {
                    this.$Confirm('Delete. Is it OK?', 'DeleteConfirm', {
                        ConfirmButtonText: 'OK',
                        cancelButtonText: 'Cancel',
                        type: 'warning'
                    }).then(() => {
                        this.$store.dispatch('titleName/deleteTitleName').then(() => {
                            this.$store.dispatch('title/searchTitleNameList', this.$store.state.title.form.titleId);
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
            showTitleName(row) {
                this.$router.push({ name: 'titleNameUpdate', params: { titleNameId: row.titleNameId }});
                this.$store.dispatch('common/setNaviList', {name: 'Title NameDetails', path: 'titleName-update'});
                this.$store.dispatch('common/setTable', null);
                this.$store.dispatch('common/setSelectedTable', 'titleName');
            },
            update: function() {
                this.$Confirm('Update. Is it OK?', 'UpdateConfirm', {
                    ConfirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('title/updateTitle').then(() => {
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
                this.$store.dispatch('title/resetForm');
            },
            cancel() {
                this.resetForm();
                this.changeMode(false);
            }
        },
        created: function() {
            if(Object.keys(this.$route.params).length !== 0) {
                this.$store.dispatch('title/showTitle', this.$route.params.titleId).catch((error) => {
                    showErrorMessage(error);
                });
            }else{
                this.$store.dispatch('title/searchTitleNameList', this.$store.state.title.form.titleId).catch((error) => {
                    showErrorMessage(error);
                });
            }
            if(this.$store.getters['common/getLastOperation'].path !== 'title-update') {
                this.$store.dispatch('common/deleteNavi', this.$store.getters['common/getLastOperation']);
                this.$store.dispatch('common/setTable', {name: 'Title ', value: 'title'});
                this.$store.dispatch('common/setSelectedTable', 'title');
            }
        }
    }
</script>
