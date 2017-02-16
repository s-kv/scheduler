<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div>
    <div class="container">
	<spring:url var="authUrl" value="/static/j_spring_security_check" />
	<form style="   max-width: 300px;
                        padding: 19px 29px 29px;
                        margin: 0 auto 20px;
                        background-color: #fff;
                        border: 1px solid #e5e5e5;
                        -webkit-border-radius: 5px;
                           -moz-border-radius: 5px;
                                border-radius: 5px;
                        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                                box-shadow: 0 1px 2px rgba(0,0,0,.05);"             
            method="post" class="signin" action="${authUrl}">
            <h2 style="margin-bottom: 10px;" class="form-signin-heading">Вход</h2>
            <input id="username_or_email" class="input-block-level" placeholder="Эл. почта" name="j_username" type="text">
            <input id="password" class="input-block-level" placeholder="Пароль" name="j_password" type="password">
            <button class="btn btn-large btn-primary" type="submit">Войти</button>
            <button class="btn btn-large btn-warning" type="button" onclick="self.location.href='/scheduler/users/registration'">Зарегистрироваться</button>
            <p/><p/>
            <small><a href="/spitter/account/resendPassword">Напомнить пароль</a></small>
        </form>
    </div>

    <script type="text/javascript">
            document.getElementById('username_or_email').focus();
    </script>
    
</div>