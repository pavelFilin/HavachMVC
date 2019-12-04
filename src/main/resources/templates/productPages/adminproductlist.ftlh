<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container ">
        <div class="row">
            <table class="table admin-product-table table-bordered table-hover">
                <thead class="thead-dark">
                <th>Id</th>
                <th>Photo</th>
                <th>Product Name</th>
                <th>Category Name</th>
                <th>Stock</th>
                <th>Active</th>
                </thead>
                <tbody class="table-striped">
                <#list products as product>
                    <tr>
                        <td>${product.id}</td>
                        <td><img style="width: 100px;" class="photo-table-product-list"
                                 src="/img/${product.photo?if_exists}"/></td>
                        <td><a href="/product/${product.id}">${product.name}</a></td>
                        <td>${product.categoryId} - ${product.category.title}</td>
                        <td>${product.stock}</td>
                        <td><input <#if product.active>checked</#if> class="form-control-input" type="checkbox"/></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>