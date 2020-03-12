<template>
    <div>
        <el-row type="flex" align="middle">
            <el-col :span="20">
                <div>ID Management</div>
            </el-col>
            <el-col :span="2">
                <div>{{user}}</div>
            </el-col>
            <el-col :span="2">
                <el-button>logout</el-button>
            </el-col>
        </el-row>
        <el-row type="flex" align="middle" style="background-color: #696969;color:white;">
            <el-col :span="6">
                <el-menu class="el-menu" mode="horizontal" @select="handleSelect" background-color="#696969" text-color="#ffffff" active-text-color="#ffffff" router="true">
                    <el-submenu index="menu">
                        <template slot="title">{{selectedTable}}</template>
                        <el-submenu index="role">
                            <template slot="title">Role Management</template>
                            <el-menu-item index="/role-search">Search</el-menu-item>
                            <el-menu-item index="/role-regist">Create</el-menu-item>
                        </el-submenu>
                        <el-submenu index="passwordPolicy">
                            <template slot="title">Password Policy</template>
                            <el-menu-item index="/passwordPolicy-search">Search</el-menu-item>
                            <el-menu-item index="/passwordPolicy-regist">Create</el-menu-item>
                        </el-submenu>
                        <el-submenu index="loginPolicy">
                            <template slot="title">Login Policy</template>
                            <el-menu-item index="/loginPolicy-search">Search</el-menu-item>
                            <el-menu-item index="/loginPolicy-regist">Create</el-menu-item>
                        </el-submenu>
                        <el-submenu index="company">
                            <template slot="title">Company Management</template>
                            <el-menu-item index="/company-search">Search</el-menu-item>
                            <el-menu-item index="/company-regist">Create</el-menu-item>
                        </el-submenu>
                        <el-submenu index="user">
                            <template slot="title">User Management</template>
                            <el-menu-item index="/user-search">Search</el-menu-item>
                            <el-menu-item index="/user-regist">Create</el-menu-item>
                        </el-submenu>
                        <el-submenu index="title">
                            <template slot="title">Title Management</template>
                            <el-menu-item index="/title-search">Search</el-menu-item>
                            <el-menu-item index="/title-regist">Create</el-menu-item>
                        </el-submenu>
                        <el-submenu index="org">
                            <template slot="title">Orgnazation Management</template>
                            <el-menu-item index="/org-search">Search</el-menu-item>
                            <el-menu-item index="/org-regist">Create</el-menu-item>
                        </el-submenu>
                        <el-submenu index="userLoginInfo">
                            <template slot="title">Users Login Information Management</template>
                            <el-menu-item index="/userLoginInfo-search">Search</el-menu-item>
                            <el-menu-item index="/userLoginInfo-regist">Create</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                </el-menu>
            </el-col>
            <el-col :span="10">
                <el-breadcrumb separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item v-for="navi in naviList" :to="{ path: navi.path }">{{navi.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </el-col>
            <el-col :span="8">
                <div v-if="table != null" style="text-align:right;">
                    <el-button @click="forward('search')">{{table.name}}Search</el-button>
                    <el-button @click="forward('regist')">{{table.name}}Create</el-button>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import Vue from 'vue'
import { mapState } from 'vuex'

export default {
    computed: {
        ...mapState('common', ['table', 'naviList', 'selectedTable', 'user'])
    },
    methods: {
        forward(operation) {
            if(operation === 'search') {
                this.$router.push({name: this.$store.state.common.table.value + 'Search'});
            } else if(operation === 'regist') {
                this.$router.push({name: this.$store.state.common.table.value + 'Regist'});
            }
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
            this.$store.dispatch('common/setSelectedTable', keyPath[1]);
        }
    },
    created: function() {
        this.$store.dispatch('common/setUser');
    }
}
</script>

<style>
.el-breadcrumb__inner, .el-breadcrumb__item:last-child .el-breadcrumb__inner, .el-breadcrumb__item:last-child .el-breadcrumb__inner:hover {
    color:#ffffff;
}
</style>
