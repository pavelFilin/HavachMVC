<#include "security.ftlh">
<#import "login.ftlh" as login>
<div class="container-fluid sticky-top navbar-dark bg-dark" id="navigator">
    <nav class="navbar navbar-expand-md navbar ">
        <a href="/" class="navbar-brand">Fish market</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Магазины</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="/news">Новости</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Контакты</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"></a>
                </li>
            </ul>
        </div>
        <#if !known>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/registration"><span class="fas fa-user"></span> Sign Up</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login"><span class="fas fa-sign-in-alt"></span> Login</a>
                </li>
            </ul>
        <#else>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#"><span><i class="fas fa-user-circle"></i></span> ${email}</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-danger btn-sm mt-1 " href="#">Cart</a>
                </li>
                <li class="nav-item">
                    <@login.logout/>
                </li>
            </ul>
        </#if>
    </nav>
</div>
