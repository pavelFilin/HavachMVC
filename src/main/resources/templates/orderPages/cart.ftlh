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
                        <td><img class="card-img-top" style="width: 150px"
                                 src="/img/${cartItem.product.photo?if_exists}"/></td>
                        <td>${cartItem.product.name}</td>
                        <td>${cartItem.quantity}</td>
                        <td>${cartItem.product.price * cartItem.quantity}</td>
                    </tr>
                </#list>
                <tr class="bg-info text-light">
                    <td></td>
                    <td></td>
                    <td>${totalQuantity}</td>
                    <td>${totalPrice}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <hr>
        <div class="row">
            <form style="width: 100%">
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input name="phone" minlength="3" maxlength="15"
                                   <#if userContacts??>value="${userContacts.phone?if_exists}"</#if> type="text"
                                   class="form-control" id="phone" placeholder="phone">
                        </div>

                        <div class="form-group">
                            <label for="address">Address</label>
                            <input name="address" minlength="3" maxlength="255"
                                   <#if userContacts??>value="${userContacts.address?if_exists}"</#if> type="text"
                                   class="form-control" id="address" placeholder="address">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h3>Chose payment type</h3>
                        <div class="row ">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" checked type="radio" name="paymentType" id="cash"
                                       value="cash">
                                <label class="form-check-label" for="cash">cash</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="paymentType" id="card" value="cad">
                                <label class="form-check-label" for="card">card</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="paymentType" id="inlineRadio3"
                                       value=cardOnSite>
                                <label class="form-check-label" for="inlineRadio3">card on site</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary btn-lg btn-block mt-3">Buy</button>
                </div>
            </form>
        </div>

    </div>
</@c.page>