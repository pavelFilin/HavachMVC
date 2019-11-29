<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3 ">
                <#include "../parts/userprofilenav.ftlh"/>
            </div>
            <div class="col-md-9">
                <div class="row">
                    <table class="table table-light text-dark ">
                        <thead>
                        <tr>
                            <th>Email</th>
                            <th>First Name</th>
                            <th>Second Name</th>
                            <th>Role</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list users as user>
                            <tr>
                                <td><a href="/user/${user.id}">${user.email}</a></td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td><#list user.roles as role>${role.name}<#sep>, </#list></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</@c.page>