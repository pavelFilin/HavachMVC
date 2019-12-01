<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3 ">
                <#include "../parts/userprofilenav.ftlh"/>
            </div>
            <div class="col-md-9">
                <div class="row">
                    <div class="col-md-8">
                        <table class="table table-bordered table-hover">
                            <thead class="thead-dark">
                                <th>id</th>
                                <th>title</th>
                                <th>parent_id</th>
                            </thead>
                            <tbody class="table-striped">
                            <#list categories as category>
                                <tr>
                                    <td>${category.id}</td>
                                    <td>${category.title}</td>
                                    <td>${category.parentId}</td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <form action="/category" method="POST">
                            <h4>Add new category</h4>
                            <div class="form-group">
                                <label for="titleCategory">Title</label>
                                <input name="title" minlength="3" maxlength="50" type="text" class="form-control" id="titleCategory" placeholder="Enter title">
                            </div>
                            <div class="form-group">
                                <label for="parentCategory">Parent ID</label>
                                <select name="parentId" class="form-control" id="parentCategory">
                                    <#list categories as category>
                                        <option>${category.id} - ${category.title}</option>
                                    </#list>
                                </select>
                            </div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="submit" class="btn btn-primary btn-sm btn-block" value="submit">
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</@c.page>