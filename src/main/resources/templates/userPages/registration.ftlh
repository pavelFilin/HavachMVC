<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page>
    ${message?ifExists}
    <@l.login "/regitration" true />
</@c.page>