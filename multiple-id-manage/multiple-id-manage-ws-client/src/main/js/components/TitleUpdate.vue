<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>役職マスタ詳細</span>
        </div>
        <div>
            <el-form ref="form" :model="form" label-width="110px" id="titleUpdateForm">
                <el-form-item label="役職ID">
                    <el-input v-model="form.titleId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="役職コード">
                    <el-input v-model="form.titleCode" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="会社マスタ">
                    <el-col :span="20">
                        <el-input v-model="company" :disabled="true" id="company"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="グループフラグ">
                    <el-radio v-model="form.groupFlg" label="0" :disabled="!editable">実在役職</el-radio>
                    <el-radio v-model="form.groupFlg" label="1" :disabled="!editable">仮想役職</el-radio>
                </el-form-item>
                <el-form-item label="有効開始日時">
                    <el-date-picker type="datetime" v-model="form.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeStartTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="有効終了日時">
                    <el-date-picker type="datetime" v-model="form.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss" :disabled="!editable" id="activeEndTime"></el-date-picker>
                </el-form-item>
                <el-form-item label="役職名">
                    <el-input v-model="form.titleName" :disabled="!editable" id="titleName"></el-input>
                    <span id="message_titleName"></span>
                </el-form-item>
                <el-form-item label="役職種別">
                    <el-input v-model="form.titleType" :disabled="!editable" id="titleType"></el-input>
                    <span id="message_titleType"></span>
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
                        <el-button type="primary" @click="update" id="updateTitle">更新</el-button>
                    </div>
                    <div v-else style="text-align:right;">
                        <el-button @click="changeMode(true)" type="primary">編集</el-button>
                    </div>
                </el-form-item>
            </el-form>
            <el-row>
                <el-col :span="12">
                    役職名一覧
                </el-col>
                <el-col :span="12">
                    <div style="text-align:right;">
                        <el-button type="primary" @click="regist">追加</el-button>
                        <el-button type="primary" @click="deleteTitleName">削除</el-button>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                データをダブルクリックすると詳細を表示します
                <el-table :data="titleNameList" stripe border highlight-current-row @row-dblclick="showTitleName" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="locale" label="ロケール" width="100" :formatter="getLocale"></el-table-column>
                    <el-table-column prop="titleName" label="役職名" width="100"></el-table-column>
                    <el-table-column prop="activeStartTime" label="有効開始日時" width="160" :formatter="formatActiveStartTime"></el-table-column>
                    <el-table-column prop="activeEndTime" label="有効終了日時" width="160" :formatter="formatActiveEndTime"></el-table-column>
                </el-table>
            </el-row>
        </div>
    </el-card>
</template>

<script>
    import Vue from 'vue'
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
                    name: 'titleNameRegist',
                    params: this.$store.state.title.form
                });
                this.$store.dispatch('common/setNaviList', {name: '役職名マスタ登録', path: 'titleName-regist'});
                this.$store.dispatch('common/setTable', null);
                this.$store.dispatch('common/setSelectedTable', 'titleName');
            },
            handleSelectionChange(val) {
                this.$store.dispatch('titleName/setSelectedList', val);
            },
            deleteTitleName(event) {
                if(this.$store.state.titleName.selectedList.length === 0) {
                    this.$alert('削除するデータを選択してください。', '確認', {
                        confirmButtonText: 'OK'
                    });
                } else {
                    this.$confirm('削除します。よろしいですか？', '削除確認', {
                        confirmButtonText: 'OK',
                        cancelButtonText: 'キャンセル',
                        type: 'warning'
                    }).then(() => {
                        this.$store.dispatch('titleName/deleteTitleName').then(() => {
                            this.$store.dispatch('title/searchTitleNameList', this.$store.state.title.form.titleId);
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
            showTitleName(row) {
                this.$router.push({ name: 'titleNameUpdate', params: { titleNameId: row.titleNameId }});
                this.$store.dispatch('common/setNaviList', {name: '役職名マスタ詳細', path: 'titleName-update'});
                this.$store.dispatch('common/setTable', null);
                this.$store.dispatch('common/setSelectedTable', 'titleName');
            },
            update: function(event) {
                this.$confirm('更新します。よろしいですか？', '更新確認', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'キャンセル',
                    type: 'warning'
                }).then(() => {
                    this.$store.dispatch('title/updateTitle').then(() => {
                        this.changeMode(false);
                        this.$alert('更新が完了しました。', '更新完了', {
                            confirmButtonText: 'OK'
                        });
                    }).catch(error => showErrorMessage(error));
                }).catch(() => {
                    // キャンセルの場合
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
                this.$store.dispatch('common/setTable', {name: '役職マスタ', value: 'title'});
                this.$store.dispatch('common/setSelectedTable', 'title');
            }
        }
    }
</script>
