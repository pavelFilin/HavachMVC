<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <form>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="product-name">Name</label>
                    <input name="name" type="text" minlength="3" maxlength="50"
                           class="form-control" id="product-name" placeholder="Name">
                </div>
                <div class="form-group col-md-4 offset-2">
                    <label for="product-price">Price</label>
                    <input name="price" min="1" max="1000000" type="number" class="form-control" id="product-price"
                           placeholder="Price">
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label for="product-description">Description</label>
                <textarea name="description" minlength="1" maxlength="2048" class="form-control"
                          id="product-description" rows="3" placeholder="Description"></textarea>
            </div>
            <hr>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="customFile">
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary btn-lg btn-block mt-3">Sign in</button>
        </form>
    </div>
</@c.page>