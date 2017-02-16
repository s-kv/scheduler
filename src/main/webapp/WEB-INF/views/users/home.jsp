<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="span10">
    <table id="xTable">
	<tr>
            <td align="left">
		<h2>${loggedUser.fullName}</h2>
            </td>
            <td></td>
	</tr>
	<tr>
            <td valign="top" width="400">
		<table id="wideTable">
                    <tr >
                        <th width="200" align="left">Электронная почта:</th>
                        <td width="200" align="left" class="padding_td">${loggedUser.email}</td>
                    </tr>
                    <tr>
                        <th align="left">Телефон:</th>
                        <td align="left" class="padding_td">${loggedUser.phone}</td>
                    </tr>                    
                    <tr>
                        <th valign="top" align="left">Описание:</th>
                        <td align="left" class="padding_td">${loggedUser.description}</td>
                    </tr>
                    <tr >
                        <td></td>
                    </tr>
		</table>
            </td>
            <td align="left" width="300">
                <img src="<spring:url value="/resources/avatars/${loggedUser.avatar}"/>"/>
            </td>
        </tr>
        <tr>
            <td align="left" colspan="2" style="padding-top: 30px">
                <button class="btn btn-warning" type="button" onclick="self.location.href='/scheduler/logged/edit'">Редактировать</button>            
            </td>
        </tr>
    </table>
</div>