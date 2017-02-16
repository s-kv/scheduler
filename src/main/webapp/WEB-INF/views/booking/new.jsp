<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="span10">
    <h3>Запись на приём к специалисту</h3>
    
    <script src="<spring:url value="/resources/js/dateTime/jquery.js"/>"></script>
    <script src="<spring:url value="/resources/js/dateTime/jquery.datetimepicker.full.js"/>"></script>
    
    <sf:form method="POST" modelAttribute="booking">
        <sf:errors path="*" class="error"/>
        <table id="innerTable">
            <tr>
                <td>
                    <p><sf:input path="startDate" id="datetimepicker" type="text"/></p>
                    <script type="text/javascript">// <![CDATA[
                        jQuery(function(){
                            var logic = function( currentDateTime ){
                                var currentDate = new Date(currentDateTime);
                                var allowDate;
                                currentDate.setHours(0,0,0,0);
                            
                                <c:forEach items="${allowTimes}" var="allowTime">
                                    allowDate = new Date(Date.parse('${allowTime.key}'));
                                    allowDate.setHours(0,0,0,0);
                                    
                                    if( currentDate.valueOf() === allowDate.valueOf() ){
                                        this.setOptions({
                                            allowTimes:[${allowTime.value}]
                                        });                                
                                    }
                                </c:forEach>
                            };
                        
                        jQuery.datetimepicker.setLocale('ru');
                        
                        jQuery(function(){
                            jQuery('#datetimepicker').datetimepicker({
                                beforeShowDay: function(date) {
                                    var currentDate = new Date(date);
                                    var allowDate;
                                    currentDate.setHours(0,0,0,0);
                                    
                                    <c:forEach items="${allowTimes}" var="allowTime">
                                        allowDate = new Date(Date.parse('${allowTime.key}'));
                                        allowDate.setHours(0,0,0,0);

                                        if( currentDate.valueOf() === allowDate.valueOf() ){
                                            return [true, ""];                            
                                        }
                                    </c:forEach>                                    

                                    return [false, ""]
                                },
                                value:new Date(),
                                dayOfWeekStart:1,
                                format:'d.m.Y H:i',
                                inline:true,
                                minDate:'-1970/01/01',
                                maxDate:'+1970/01/14',
                                allowTimes:[${curDayAllowTimes}],
                                
                                onChangeDateTime:logic,
                                onShow:logic
                            });
                        });
                        });
                    // ]]></script>                     
                </td>
            </tr>            
            <tr>
                <th align="left"><label for="booking_notes" class="label3">Комментарии: </label></th>
            </tr>
            <tr>
                <td align="left">
                    <sf:textarea path="note" id="booking_notes" style="width:295px"/> 
                </td>                
            </tr>    
            <tr>
                <td align="left">
                    <input name="commit" type="submit" class="btn btn-primary"
                        value="Записаться на приём" class="submit" />
                </td>
            </tr>
        </table>
    </sf:form>    
</div>
    
    
