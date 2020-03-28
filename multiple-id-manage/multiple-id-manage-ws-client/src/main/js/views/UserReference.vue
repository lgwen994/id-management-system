<template>
    <div>
        <el-dialog title="User Selection" :visible="userDialogVisible" width="1100px" @close="closeUserDialog">
            <el-row>
                <el-col :span="12">
                    <el-card class="box-card" body-style="height:600px;">
                        <div slot="header" class="clearfix">
                            <span>Search</span>
                        </div>
                        <div>
                            <el-form ref="searchForm" :model="searchForm" label-width="110px">
                                <el-form-item label="User Code">
                                    <el-input v-model="searchForm.userCode" id="userCode"></el-input>
                                    <span id="message_userCode"></span>
                                </el-form-item>
                                <el-form-item label="Active Start Time">
                                    <el-date-picker type="datetime" v-model="searchForm.activeStartTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                                </el-form-item>
                                <el-form-item label="Active End Time">
                                    <el-date-picker type="datetime" v-model="searchForm.activeEndTime" style="width: 100%;" format="yyyy/MM/dd HH:mm:ss"></el-date-picker>
                                </el-form-item>
                                <el-form-item label="User Name">
                                    <el-input v-model="searchForm.userName" id="userName"></el-input>
                                    <span id="message_userName"></span>
                                </el-form-item>
                                <el-form-item label="Mail Address">
                                    <el-input v-model="searchForm.mail" id="mail"></el-input>
                                    <span id="message_mail"></span>
                                </el-form-item>
                                <el-form-item label="Telephone Number">
                                    <el-input v-model="searchForm.tel" id="tel"></el-input>
                                    <span id="message_tel"></span>
                                </el-form-item>
                                <el-form-item label="FAX Number">
                                    <el-input v-model="searchForm.fax" id="fax"></el-input>
                                    <span id="message_fax"></span>
                                </el-form-item>
                                <el-form-item label="Locale">
                                    <el-select v-model="searchForm.locale" placeholder="">
                                        <el-option v-for="item in locales" :key="item.value" :label="item.label" :value="item.value"></el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item>
                                    <el-button @click="clear">Clear</el-button>
                                    <el-button @click="search" type="primary">Search</el-button>
                                </el-form-item>
                            </el-form>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="12">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>Search List</span>
                        </div>
                        <div>
                            <el-button @click="closeUserDialog">Close</el-button>
                            <div>Double-click the data to set the data.</div>
                            <el-table :data="userList" stripe border style="width: 100%" highlight-current-row @row-dblclick="addUser">
                                <el-table-column prop="userCode" label="User Code" width="110" sortable></el-table-column>
                                <el-table-column prop="activeStartTime" label="Active Start Time" width="200" sortable :formatter="formatActiveStartTime"></el-table-column>
                                <el-table-column prop="activeEndTime" label="Active End Time" width="200" sortable :formatter="formatActiveEndTime"></el-table-column>
                                <el-table-column prop="userName" label="User Name" width="110"></el-table-column>
                                <el-table-column prop="userType" label="User Type" width="110"></el-table-column>
                                <el-table-column prop="userComment" label="User Comment" width="110"></el-table-column>
                            </el-table>
                            <el-pagination layout="prev, pager, next" :total="userSize" page-size="10" @current-change="handleCurrentChange" :current-page="page" />
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-dialog>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import { formatDate, showErrorMessage } from '../common';

    export default {
        computed: {
            ...mapState('userReference', ['searchForm', 'userDialogVisible', 'userList', 'userSize', 'page']),
        },
        methods: {
            search() {
                this.$store.dispatch('userReference/searchUserList').catch(error => showErrorMessage(error));
            },
            clear() {
                this.$store.dispatch('userReference/clearSearchForm');
            },
            handleCurrentChange(val) {
                this.$store.dispatch('userReference/setPage', val);
                this.$store.dispatch('userReference/searchUserList').catch(error => showErrorMessage(error));
            },
            closeUserDialog() {
                this.$store.dispatch('userReference/closeUserDialog');
            },
            formatActiveStartTime(row) {
                return formatDate(row.activeStartTime);
            },
            formatActiveEndTime(row) {
                return formatDate(row.activeEndTime);
            },
            addUser(row) {
                this.$store.dispatch('userReference/addUser', row);
            }
        }
    }
</script>
