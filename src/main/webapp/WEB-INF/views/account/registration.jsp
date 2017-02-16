<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div>
    <div class="container">
    <sf:form style="   max-width: 455px;
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
            method="POST" modelAttribute="user">
        <table id="innerTable" style="margin: auto;">
            <tr align="left">
                <td colspan="2"><h2>Регистрация</h2></td>
            </tr>
            <tr>
                <th align="left"><label for="user_email" class="label3">Эл. почта: </label></th>
                <td align="left" class="padding_td" >
                    <sf:input path="email" size="30" id="user_email" /> 
                </td>
            </tr>
            <tr>
                <td></td>
                <td class="padding_td"><sf:errors path="email" class="error"/></td>
            </tr>
            <tr>
                <th align="left"><label for="user_password" class="label3">Пароль: </label></th>
                <td align="left" class="padding_td">
                    <sf:password path="password" size="30" showPassword="true" id="user_password" /> 
                    <small class="padding_td">Не менее 6 символов</small> 
                </td>
            </tr>
            <tr>
                <td></td>
                <td class="padding_td"><sf:errors path="password" cssClass="error" /></td>
            </tr>
            <tr>
                <th align="left">
                    <label for="user_full_name" class="label3">Имя: </label>
                </th>
                <td align="left" class="padding_td">
                    <sf:input path="fullName" size="15" id="user_full_name" /> 
                </td>
            </tr>
            <tr>
                <td></td>
                <td class="padding_td"><sf:errors path="fullName" cssClass="error"/></td>
            </tr>
            <tr>
                <th align="left"><label for="user_phone" class="label3">Телефон: </label></th>
                <td align="left" class="padding_td">
                    <sf:input path="phone" size="15" maxlength="15" id="user_phone" /> 
                    <small id="phone_msg" class="padding_td">10 цифр без пробелов</small> 
                </td>
            </tr>
            <tr>
                <td></td>
                <td class="padding_td"><sf:errors path="phone" cssClass="error"/></td>
            </tr>            
            <tr>
                
                <td align="left" colspan="2"><input name="commit" type="submit" class="btn btn-large btn-warning"
                        value="Зарегистрироваться" class="submit" /></td>
            </tr>
        </table>
    </sf:form>
    </div>
    <script type="text/javascript">
            document.getElementById('user_email').focus();
    </script>
</div>