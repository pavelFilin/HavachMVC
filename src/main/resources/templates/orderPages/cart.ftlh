<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <div class="row">
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                <th></th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                </thead>
                <tbody class="table-striped">
                <#list cart as cartItem>
                    <tr style="max-width: 25px">
                        <td><img class="card-img-top" style="width: 150px" src="/img/${cartItem.product.photo?if_exists}"/></td>
                        <td>${cartItem.product.name}</td>
                        <td>${cartItem.quantity}</td>
                        <td>${cartItem.product.price * cartItem.quantity}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="row">
            <form>
                <div class="form-group">
                    <label for="phone">Address</label>
                    <input name="phone" minlength="3" maxlength="15"
                           <#if userContacts??>value="${userContacts.address?if_exists}"</#if> type="text"
                           class="form-control" id="phone" placeholder="phone">
                </div>

                <div class="form-group">
                    <label for="address">Address</label>
                    <input name="address" minlength="3" maxlength="255"
                           <#if userContacts??>value="${userContacts.address?if_exists}"</#if> type="text"
                           class="form-control" id="address" placeholder="address">
                </div>
            </form>
        </div>

    </div>
</@c.page>