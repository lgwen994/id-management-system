<template>
    <div>
        <h3>Menu</h3>
        <table style="border-spacing : 20px 5px;">
            <tbody>
                <tr>
                    <td>Role Management</td>
                    <td><router-link :to="{ name: 'roleSearch'}">Search</router-link></td>
                    <td><router-link :to="{ name: 'roleRegist'}">Create</router-link></td>
                </tr>
                <tr>
                    <td>Company Management</td>
                    <td><router-link :to="{ name: 'companySearch'}">Search</router-link></td>
                    <td><router-link :to="{ name: 'companyRegist'}">Create</router-link></td>
                </tr>
                <tr>
                    <td>User Management</td>
                    <td><router-link :to="{ name: 'userSearch'}">Search</router-link></td>
                    <td><router-link :to="{ name: 'userRegist'}">Create</router-link></td>
                </tr>
                <tr>
                    <td>Password Policy</td>
                    <td><router-link :to="{ name: 'passwordPolicySearch'}">Search</router-link></td>
                    <td><router-link :to="{ name: 'passwordPolicyRegist'}">Create</router-link></td>
                </tr>
                <tr>
                    <td>Login Policy</td>
                    <td><router-link :to="{ name: 'loginPolicySearch'}">Search</router-link></td>
                    <td><router-link :to="{ name: 'loginPolicyRegist'}">Create</router-link></td>
                </tr>
                <tr>
                    <td>Title Management</td>
                    <td><router-link :to="{ name: 'titleSearch'}">Search</router-link></td>
                    <td><router-link :to="{ name: 'titleRegist'}">Create</router-link></td>
                </tr>
                <tr>
                    <td>Orgnazation Management</td>
                    <td><router-link :to="{ name: 'orgSearch'}">Search</router-link></td>
                    <td><router-link :to="{ name: 'orgRegist'}">Create</router-link></td>
                </tr>
                <tr>
                    <td>Users' Login Information Management</td>
                    <td><router-link :to="{ name: 'userLoginInfoSearch'}">Search</router-link></td>
                    <td><router-link :to="{ name: 'userLoginInfoRegist'}">Create</router-link></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
