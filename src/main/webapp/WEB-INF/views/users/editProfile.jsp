<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="span10">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/dateTime/jquery.js"/>"></script>
    <script src="<spring:url value="/resources/js/dateTime/jquery.datetimepicker.full.js"/>"></script>

    <sf:form method="POST" modelAttribute="editUser"
        enctype="multipart/form-data">
	<table width="100%" style = "table-layout:fixed">
            <tr align="left">
                <td width="50%" style="padding-bottom: 20px">
                        <h2>Редактирование профиля</h2>
                </td>
                <td width="50%"> 
                </td>
            </tr>
            <tr>
                <td valign="top" width="50%">
                    <table id="wideTable">
                        <tr>
                            <th align="left"><label for="user_name" class="label3">Имя:</label></th>
                            <td align="left" class="padding_td"><sf:input path="fullName" size="15"
                                            id="user_name" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><sf:errors path="fullName" cssClass="error" /></td>
                        </tr>
                        <tr>
                            <th align="left"><label for="user_phone" class="label3">Телефон:</label></th>
                            <td align="left" class="padding_td"><sf:input path="phone" size="30"
                                            id="user_phone"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><sf:errors path="phone" cssClass="error" /></td>
                        </tr>
                        <tr>
                            <th align="left"><label for="user_description" class="label3">Описание:</label></th>
                            <td align="left" class="padding_td"><sf:input path="description" size="30"
                                            id="user_description"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><sf:errors path="description" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <th align="left"><label class="label3" style="padding-top: 20px; padding-bottom: 20px">Принимать заявки на прием</label></th>
                            <td class="padding_td" style="padding-top: 20px; padding-bottom: 20px">
                                <sf:checkbox path="allowBooking" id="allowBooking"/>
                            </td>                            
                        </tr>
                        <tr>
                            <th align="left"><label for="timepicker_beg" class="label3">Время работы:</label></th>
                            <td>
                                <table><tr>
                                <td class="padding_td"> с </td>        
                                <td class="padding_td">
                                    <sf:input path="begTime" id="timepicker_beg" type="text" style="width: 40px"/>                            
                                    <script type="text/javascript">// <![CDATA[
                                        jQuery(function(){
                                            jQuery('#timepicker_beg').datetimepicker({
                                                datepicker:false,
                                                format:'H:i'
                                            });
                                        });
                                    // ]]></script>
                                </td>
                                <td class="padding_td"> до </td>
                                <td class="padding_td">
                                    <sf:input path="endTime" id="timepicker_end" type="text" style="width: 40px"/>
                                    <script type="text/javascript">// <![CDATA[
                                        jQuery(function(){
                                            jQuery('#timepicker_end').datetimepicker({
                                                datepicker:false,
                                                format:'H:i'
                                            });
                                        });
                                    // ]]></script>                             
                                </td>
                                </tr></table>
                            </td>
                        </tr>
                        <tr>
                            <th align="left"><label for="timepicker_int" class="label3">Продолжительность приема:</label></th>
                            <td class="padding_td">
                                <sf:input path="duration" id="timepicker_int" type="text" style="width: 40px"/>
                                <script type="text/javascript">// <![CDATA[
                                    jQuery(function(){
                                        jQuery('#timepicker_int').datetimepicker({
                                            datepicker:false,
                                            format:'H:i',
                                            allowTimes:['00:10', '00:20', '00:30', '00:40', '01:00', '01:30', '02:00', '03:00']
                                        });
                                    });
                                // ]]></script>                             
                            </td>
                        <tr>
                            <th align="left"><label class="label3">Выходные дни:</label></th>
                            <td>
                                <table><tr>
                                        <td class="padding_td">
                                            <sf:checkbox path="mon" id="monday"/>
                                            <label for="monday">ПН</label>
                                        </td>
                                        <td class="padding_td">
                                            <sf:checkbox path="tue" id="tueday"/>
                                            <label for="tueday">ВТ</label>                                            
                                        </td>
                                        <td class="padding_td">
                                            <sf:checkbox path="wed" id="wedday"/>
                                            <label for="wedday">СР</label>                                            
                                        </td>
                                        <td class="padding_td">
                                            <sf:checkbox path="thu" id="thuday"/>
                                            <label for="thuday">ЧТ</label>                                            
                                        </td>
                                        <td class="padding_td">
                                            <sf:checkbox path="fri" id="friday"/>
                                            <label for="friday">ПТ</label>                                            
                                        </td>
                                        <td class="padding_td">
                                            <sf:checkbox path="sat" id="satday"/>
                                            <label for="satday">СБ</label>                                            
                                        </td>
                                        <td class="padding_td">
                                            <sf:checkbox path="sun" id="sunday"/>
                                            <label for="sunday">ВС</label>                                            
                                        </td>
                                </tr></table>
                            </td>
                        </tr>
                    </table>    
                </td>
                <td valign="top" align="left" width="50%" height="200" class="large_padding_td">
                    <table>
                        <tr>
                            <img id="img-preview" style="max-height: 100%;" src="<spring:url value="/resources/avatars/${loggedUser.avatar}"/>"/>
                        </tr>
                        <tr>
                            <th align="left"><label for="image" class="label3">Выбор файла:</label></th>
                            <td align="left" class="padding_td">
                                <input type="file" name="image" id="image" style="color: transparent"/>
                            </td>
                        </tr>
                        <c:if test="${not empty imageException}">
                            <tr>
                                <td></td>
                                <td><label id="imageException" class="error">${imageException}</label></td>
                            </tr>            
                        </c:if>
                        <tr>
                            <td></td>
                            <td><sf:errors for="image" cssClass="error" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td align="left" class="padding_td">
                                <label>
                                <input type="checkbox" name="removeAvatar" id="removeAvatar"/>
                                <span style="display: inline-block; vertical-align: middle;">Удалить изображение?</span>
                                <label/>
                            </td>
                        </tr>
                        <tr>
                            <th align="left"><label for="password" class="label3" style="padding-top: 10px">Введите пароль:</label></th>
                            <td align="left" class="padding_td" style="padding-top: 10px">
                                <input size="30" type="password" name="password" id="password"/> 
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><sf:errors path="password" cssClass="error" /></td>
                        </tr>
                    </table>
                </td>                
            </tr>
            <tr>
                <td style="padding-top: 20px">
                    <input name="commit" type="submit"
                        value="Изменить" class="btn btn-large btn-primary submit" />
                </td>
                <td></td>
            </tr>
	</table>
    </sf:form>
    
    <script type = "text/javascript">
        $('#image').change(function () {
            var input = $(this)[0];
            if (input.files && input.files[0]) {
                if (input.files[0].type.match('image.*')) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $('#img-preview').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(input.files[0]);
                } else {
                    console.log('ошибка, не изображение');
                }
            } else {
                console.log('хьюстон у нас проблема');
            }
        });
        
        $(document).ready(function(){
            $('#allowBooking').bind('change', function () {
                if($('#allowBooking').prop('checked')){
                    $('#timepicker_beg').removeAttr('disabled');
                    $('#timepicker_end').removeAttr('disabled');
                    $('#timepicker_int').removeAttr('disabled');
                    $('#monday').removeAttr('disabled');
                    $('#tueday').removeAttr('disabled');
                    $('#wedday').removeAttr('disabled');
                    $('#thuday').removeAttr('disabled');
                    $('#friday').removeAttr('disabled');
                    $('#satday').removeAttr('disabled');
                    $('#sunday').removeAttr('disabled');
                } else {
                    $('#timepicker_beg').attr('disabled', "disabled");
                    $('#timepicker_end').attr('disabled', "disabled");
                    $('#timepicker_int').attr('disabled', "disabled");
                    $('#monday').attr('disabled', "disabled");
                    $('#tueday').attr('disabled', "disabled");
                    $('#wedday').attr('disabled', "disabled");
                    $('#thuday').attr('disabled', "disabled");
                    $('#friday').attr('disabled', "disabled");
                    $('#satday').attr('disabled', "disabled");
                    $('#sunday').attr('disabled', "disabled");
                }

            });
            $('#allowBooking').trigger('change');
        });        
    </script>    
</div>