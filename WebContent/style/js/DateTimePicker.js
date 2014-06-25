/*  
	-------------------------	jQuery DateTimePicker v0.1.1	----------------------------
	
	https://github.com/CuriousSolutions/DateTimePicker
	
*/

(function(f,d,a,h){var e="DateTimePicker";var g={mode:"date",defaultDate:new Date(),dateSeparator:"-",timeSeparator:":",timeMeridiemSeparator:" ",dateTimeSeparator:" ",dateTimeFormat:"dd-MM-yyyy HH:mm:ss",dateFormat:"dd-MM-yyyy",timeFormat:"HH:mm",maxDate:null,minDate:null,maxTime:null,minTime:null,maxDateTime:null,minDateTime:null,shortDayNames:["Sun", "Mon", "Tues", "Wed", "Thu", "Fri", "Sat"],fullDayNames: ["Sunday", "Monday", "Tuesday", "Wedsday", "Thuesday", "Friday", "Saturday"],shortMonthNames: ["Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"],fullMonthNames: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December"],titleContentDate:"Choose date",titleContentTime:"Set Time",titleContentDateTime:"Set Date & Time",setButtonContent:"Choose",clearButtonContent:"Cancel",animationDuration:400,isPopup:true,addEventHandlers:null};var c={dCurrentDate:new Date(),iCurrentDay:0,iCurrentMonth:0,iCurrentYear:0,iCurrentHour:0,iCurrentMinutes:0,sCurrentMeridiem:"",iMaxNumberOfDays:0,sDateFormat:"",sTimeFormat:"",sDateTimeFormat:"",dMinValue:null,dMaxValue:null,sArrInputDateFormats:[],sArrInputTimeFormats:[],sArrInputDateTimeFormats:[],oInputElement:null,bIs12Hour:false};function b(j,i){this.element=j;this.settings=f.extend({},g,i);this.dataObject=c;this._defaults=g;this._name=e;this.init()}f.fn.DateTimePicker=function(i){return this.each(function(){if(!f.data(this,"plugin_"+e)){f.data(this,"plugin_"+e,new b(this,i))}})};b.prototype={init:function(){var i=this;i._setDateFormatArray();i._setTimeFormatArray();i._setDateTimeFormatArray();if(i.settings.isPopup){i._createPicker();f(i.element).addClass("dtpicker-mobile")}i._addEventHandlersForInput()},_setDateFormatArray:function(){var i=this;i.dataObject.sArrInputDateFormats=new Array();var j="";j="dd"+i.settings.dateSeparator+"MM"+i.settings.dateSeparator+"yyyy";i.dataObject.sArrInputDateFormats.push(j);j="MM"+i.settings.dateSeparator+"dd"+i.settings.dateSeparator+"yyyy";i.dataObject.sArrInputDateFormats.push(j);j="yyyy"+i.settings.dateSeparator+"MM"+i.settings.dateSeparator+"dd";i.dataObject.sArrInputDateFormats.push(j);j="dd"+i.settings.dateSeparator+"MMM"+i.settings.dateSeparator+"yyyy";i.dataObject.sArrInputDateFormats.push(j)},_setTimeFormatArray:function(){var j=this;j.dataObject.sArrInputTimeFormats=new Array();var i="";i="hh"+j.settings.timeSeparator+"mm"+j.settings.timeMeridiemSeparator+"AA";j.dataObject.sArrInputTimeFormats.push(i);i="HH"+j.settings.timeSeparator+"mm";j.dataObject.sArrInputTimeFormats.push(i)},_setDateTimeFormatArray:function(){var j=this;j.dataObject.sArrInputDateTimeFormats=new Array();var l="",i="",k="";l="dd"+j.settings.dateSeparator+"MM"+j.settings.dateSeparator+"yyyy";i="HH"+j.settings.timeSeparator+"mm"+j.settings.timeSeparator+"ss";k=l+j.settings.dateTimeSeparator+i;j.dataObject.sArrInputDateTimeFormats.push(k);l="dd"+j.settings.dateSeparator+"MM"+j.settings.dateSeparator+"yyyy";i="hh"+j.settings.timeSeparator+"mm"+j.settings.timeSeparator+"ss"+j.settings.timeMeridiemSeparator+"AA";k=l+j.settings.dateTimeSeparator+i;j.dataObject.sArrInputDateTimeFormats.push(k);l="MM"+j.settings.dateSeparator+"dd"+j.settings.dateSeparator+"yyyy";i="HH"+j.settings.timeSeparator+"mm"+j.settings.timeSeparator+"ss";k=l+j.settings.dateTimeSeparator+i;j.dataObject.sArrInputDateTimeFormats.push(k);l="MM"+j.settings.dateSeparator+"dd"+j.settings.dateSeparator+"yyyy";i="hh"+j.settings.timeSeparator+"mm"+j.settings.timeSeparator+"ss"+j.settings.timeMeridiemSeparator+"AA";k=l+j.settings.dateTimeSeparator+i;j.dataObject.sArrInputDateTimeFormats.push(k);l="yyyy"+j.settings.dateSeparator+"MM"+j.settings.dateSeparator+"dd";i="HH"+j.settings.timeSeparator+"mm"+j.settings.timeSeparator+"ss";k=l+j.settings.dateTimeSeparator+i;j.dataObject.sArrInputDateTimeFormats.push(k);l="yyyy"+j.settings.dateSeparator+"MM"+j.settings.dateSeparator+"dd";i="hh"+j.settings.timeSeparator+"mm"+j.settings.timeSeparator+"ss"+j.settings.timeMeridiemSeparator+"AA";k=l+j.settings.dateTimeSeparator+i;j.dataObject.sArrInputDateTimeFormats.push(k);l="dd"+j.settings.dateSeparator+"MMM"+j.settings.dateSeparator+"yyyy";i="hh"+j.settings.timeSeparator+"mm"+j.settings.timeSeparator+"ss";k=l+j.settings.dateTimeSeparator+i;j.dataObject.sArrInputDateTimeFormats.push(k);l="dd"+j.settings.dateSeparator+"MMM"+j.settings.dateSeparator+"yyyy";i="hh"+j.settings.timeSeparator+"mm"+j.settings.timeSeparator+"ss"+j.settings.timeMeridiemSeparator+"AA";k=l+j.settings.dateTimeSeparator+i;j.dataObject.sArrInputDateTimeFormats.push(k)},_createPicker:function(){var j=this;f(j.element).addClass("dtpicker-overlay");var i="";i+="<div class='dtpicker-bg'>";i+="<div class='dtpicker-cont'>";i+="<div class='dtpicker-content'>";i+="<div class='dtpicker-subcontent'>";i+="</div>";i+="</div>";i+="</div>";i+="</div>";f(j.element).html(i)},_addEventHandlersForInput:function(){var i=this;f("input[type='date'], input[type='time'], input[type='datetime']").each(function(){var j=f(this).attr("type");f(this).attr("type","text");f(this).attr("data-field",j)});f("[data-field='date'], [data-field='time'], [data-field='datetime']").unbind("focus",i._inputFieldFocus);f("[data-field='date'], [data-field='time'], [data-field='datetime']").on("focus",{obj:i},i._inputFieldFocus);f("[data-field='date'], [data-field='time'], [data-field='datetime']").not("input").click(function(){if(i.dataObject.oInputElement==null){i.showDateTimePicker(this)}});if(i.settings.addEventHandlers){i.settings.addEventHandlers.call(i)}},_inputFieldFocus:function(j){var i=j.data.obj;if(i.dataObject.oInputElement==null){i.showDateTimePicker(j.target)}},showDateTimePicker:function(n){var p=this;if(p.dataObject.oInputElement==null){p.dataObject.oInputElement=n;var t=f(n).data("field")||"";var j=f(n).data("min")||"";var u=f(n).data("max")||"";var l=f(n).data("format")||"";var i=f(n).data("view")||"";var q=f(n).data("startend")||"";var m=f(n).data("startendelem")||"";var o=p._getValueOfElement(n)||"";if(i!=""){if(p._compare(i,"Popup")){p.setIsPopup(true)}else{p.setIsPopup(false)}}if(!p.settings.isPopup){p._createPicker();var k=f(p.dataObject.oInputElement).offset().top+f(p.dataObject.oInputElement).outerHeight();var r=f(p.dataObject.oInputElement).offset().left;var s=f(p.dataObject.oInputElement).outerWidth();f(p.element).css({position:"absolute",top:k,left:r,width:s,height:"auto"})}p._showPicker(t,j,u,l,o,n,q,m)}},_setButtonAction:function(){var i=this;if(i.dataObject.oInputElement!=null){var j=i._setOutput();i._setValueOfElement(j);i._hidePicker()}},_setOutput:function(){var r=this;var u="";var m=r.dataObject.dCurrentDate.getDate();var p=r.dataObject.dCurrentDate.getMonth();var v=r.dataObject.dCurrentDate.getFullYear();var o=r.dataObject.dCurrentDate.getHours();var j=r.dataObject.dCurrentDate.getMinutes();if(r._compare(r.settings.mode,"date")){if(r._compare(r.dataObject.sDateFormat,r.dataObject.sArrInputDateFormats[0])){p++;var q=(m<10)?("0"+m):m;var s=(p<10)?("0"+p):p;u=q+r.settings.dateSeparator+s+r.settings.dateSeparator+v}else{if(r._compare(r.dataObject.sDateFormat,r.dataObject.sArrInputDateFormats[1])){p++;var q=(m<10)?("0"+m):m;var s=(p<10)?("0"+p):p;u=s+r.settings.dateSeparator+q+r.settings.dateSeparator+v}else{if(r._compare(r.dataObject.sDateFormat,r.dataObject.sArrInputDateFormats[2])){p++;var q=(m<10)?("0"+m):m;var s=(p<10)?("0"+p):p;u=v+r.settings.dateSeparator+s+r.settings.dateSeparator+q}else{if(r._compare(r.dataObject.sDateFormat,r.dataObject.sArrInputDateFormats[3])){var q=(m<10)?("0"+m):m;var s=r.settings.shortMonthNames[p];u=q+r.settings.dateSeparator+s+r.settings.dateSeparator+v}}}}}else{if(r._compare(r.settings.mode,"time")){if(r._compare(r.dataObject.sTimeFormat,r.dataObject.sArrInputTimeFormats[0])){var n="";if(o>12){o-=12;n="PM"}else{if(o==12&&j>0){n="PM"}else{n="AM"}}var t=(o<10)?("0"+o):o;var l=(j<10)?("0"+j):j;u=t+r.settings.timeSeparator+l+r.settings.timeMeridiemSeparator+n}else{if(r._compare(r.dataObject.sTimeFormat,r.dataObject.sArrInputTimeFormats[1])){var t=(o<10)?("0"+o):o;var l=(j<10)?("0"+j):j;u=t+r.settings.timeSeparator+l}}}else{if(r._compare(r.settings.mode,"datetime")){var k="";var i="";if(r._compare(r.dataObject.sDateTimeFormat,r.dataObject.sArrInputDateTimeFormats[0])||r._compare(r.dataObject.sDateTimeFormat,r.dataObject.sArrInputDateTimeFormats[1])){p++;var q=(m<10)?("0"+m):m;var s=(p<10)?("0"+p):p;k=q+r.settings.dateSeparator+s+r.settings.dateSeparator+v}else{if(r._compare(r.dataObject.sDateTimeFormat,r.dataObject.sArrInputDateTimeFormats[2])||r._compare(r.dataObject.sDateTimeFormat,r.dataObject.sArrInputDateTimeFormats[3])){p++;var q=(m<10)?("0"+m):m;var s=(p<10)?("0"+p):p;k=s+r.settings.dateSeparator+q+r.settings.dateSeparator+v}else{if(r._compare(r.dataObject.sDateTimeFormat,r.dataObject.sArrInputDateTimeFormats[4])||r._compare(r.dataObject.sDateTimeFormat,r.dataObject.sArrInputDateTimeFormats[5])){p++;var q=(m<10)?("0"+m):m;var s=(p<10)?("0"+p):p;k=v+r.settings.dateSeparator+s+r.settings.dateSeparator+q}else{if(r._compare(r.dataObject.sDateTimeFormat,r.dataObject.sArrInputDateTimeFormats[6])||r._compare(r.dataObject.sDateTimeFormat,r.dataObject.sArrInputDateTimeFormats[7])){var q=(m<10)?("0"+m):m;var s=r.settings.shortMonthNames[p];k=q+r.settings.dateSeparator+s+r.settings.dateSeparator+v}}}}if(r.dataObject.bIs12Hour){var n="";if(o>12){o-=12;n="PM"}else{if(o==12&&j>0){n="PM"}else{n="AM"}}var t=(o<10)?("0"+o):o;var l=(j<10)?("0"+j):j;i=t+r.settings.timeSeparator+l+r.settings.timeMeridiemSeparator+n}else{var t=(o<10)?("0"+o):o;var l=(j<10)?("0"+j):j;i=t+r.settings.timeSeparator+l}u=k+r.settings.dateTimeSeparator+i}}}return u},_clearButtonAction:function(){var i=this;if(i.dataObject.oInputElement!=null){i._setValueOfElement("")}i._hidePicker()},_showPicker:function(w,A,m,z,u,t,o,x){var B=this;if(w!=""){B.settings.mode=w}B.dataObject.dMinValue=null;B.dataObject.dMaxValue=null;B.dataObject.bIs12Hour=false;if(B._compare(B.settings.mode,"date")){var k=A||B.settings.minDate;var n=m||B.settings.maxDate;var l=z||B.settings.dateFormat;if(l!=""&&l!=null){B.dataObject.sDateFormat=l}if(k!=""&&k!=null){B.dataObject.dMinValue=B._parseDate(k)}if(n!=""&&n!=null){B.dataObject.dMaxValue=B._parseDate(n)}if(o!=""&&(B._compare(o,"start")||B._compare(o,"end"))&&x!=""){if(f(x).length>=1){var p=B._getValueOfElement(f(x));if(p!=""){var j=B._parseDate(p);if(B._compare(o,"start")){if(n!=""&&n!=null){if(B._compareDates(j,B.dataObject.dMaxValue)==2){B.dataObject.dMaxValue=new Date(j)}}else{B.dataObject.dMaxValue=new Date(j)}}else{if(B._compare(o,"end")){if(k!=""&&k!=null){if(B._compareDates(j,B.dataObject.dMinValue)==3){B.dataObject.dMinValue=new Date(j)}}else{B.dataObject.dMinValue=new Date(j)}}}}}}B.dataObject.dCurrentDate=B._parseDate(u);B.dataObject.dCurrentDate.setHours(0);B.dataObject.dCurrentDate.setMinutes(0);B.dataObject.dCurrentDate.setSeconds(0)}else{if(B._compare(B.settings.mode,"time")){var k=A||B.settings.minTime;var n=m||B.settings.maxTime;var s=z||B.settings.timeFormat;if(s!=""&&s!=null){B.dataObject.sTimeFormat=s}if(k!=""&&k!=null){B.dataObject.dMinValue=B._parseTime(k)}if(n!=""&&n!=null){B.dataObject.dMaxValue=B._parseTime(n)}if(o!=""&&(B._compare(o,"start")||B._compare(o,"end"))&&x!=""){if(f(x).length>=1){var y=B._getValueOfElement(f(x));if(y!=""){var r=B._parseTime(y);if(B._compare(o,"start")){r.setMinutes(r.getMinutes()-1);if(n!=""&&n!=null){if(B._compareTime(r,B.dataObject.dMaxValue)==2){B.dataObject.dMaxValue=new Date(r)}}else{B.dataObject.dMaxValue=new Date(r)}}else{if(B._compare(o,"end")){r.setMinutes(r.getMinutes()+1);if(k!=""&&k!=null){if(B._compareTime(r,B.dataObject.dMinValue)==3){B.dataObject.dMinValue=new Date(r)}}else{B.dataObject.dMinValue=new Date(r)}}}}}}B.dataObject.dCurrentDate=B._parseTime(u);B.dataObject.bIs12Hour=B._compare(B.dataObject.sTimeFormat,B.dataObject.sArrInputTimeFormats[0])}else{if(B._compare(B.settings.mode,"datetime")){var k=A||B.settings.minDateTime;var n=m||B.settings.maxDateTime;var q=z||B.settings.dateTimeFormat;if(q!=""&&q!=null){B.dataObject.sDateTimeFormat=q}if(k!=""&&k!=null){B.dataObject.dMinValue=B._parseDateTime(k)}if(n!=""&&n!=null){B.dataObject.dMaxValue=B._parseDateTime(n)}if(o!=""&&(B._compare(o,"start")||B._compare(o,"end"))&&x!=""){if(f(x).length>=1){var v=B._getValueOfElement(f(x));if(v!=""){var i=B._parseDateTime(v);if(B._compare(o,"start")){if(n!=""&&n!=null){if(B._compareDateTime(i,B.dataObject.dMaxValue)==2){B.dataObject.dMaxValue=new Date(i)}}else{B.dataObject.dMaxValue=new Date(i)}}else{if(B._compare(o,"end")){if(k!=""&&k!=null){if(B._compareDateTime(i,B.dataObject.dMinValue)==3){B.dataObject.dMinValue=new Date(i)}}else{B.dataObject.dMinValue=new Date(i)}}}}}}B.dataObject.dCurrentDate=B._parseDateTime(u);B.dataObject.bIs12Hour=B._compare(B.dataObject.sDateTimeFormat,B.dataObject.sArrInputDateTimeFormats[1])||B._compare(B.dataObject.sDateTimeFormat,B.dataObject.sArrInputDateTimeFormats[3])||B._compare(B.dataObject.sDateTimeFormat,B.dataObject.sArrInputDateTimeFormats[5])||B._compare(B.dataObject.sDateTimeFormat,B.dataObject.sArrInputDateTimeFormats[7])}}}B._setVariablesForDate();B._modifyPicker();f(B.element).fadeIn(B.settings.animationDuration)},_hidePicker:function(i){var j=this;if(j.dataObject.oInputElement!=null){f(j.dataObject.oInputElement).blur();j.dataObject.oInputElement=null}f(j.element).fadeOut(i||j.settings.animationDuration);setTimeout(function(){f(j.element).find(".dtpicker-subcontent").html("")},(i||j.settings.animationDuration))},_modifyPicker:function(){var o=this;var k,n;var l=new Array();if(o._compare(o.settings.mode,"date")){k=o.settings.titleContentDate;n=3;if(o._compare(o.dataObject.sDateFormat,o.dataObject.sArrInputDateFormats[0])){l=["day","month","year"]}else{if(o._compare(o.dataObject.sDateFormat,o.dataObject.sArrInputDateFormats[1])){l=["month","day","year"]}else{if(o._compare(o.dataObject.sDateFormat,o.dataObject.sArrInputDateFormats[2])){l=["year","month","day"]}else{if(o._compare(o.dataObject.sDateFormat,o.dataObject.sArrInputDateFormats[3])){l=["day","month","year"]}}}}}else{if(o._compare(o.settings.mode,"time")){k=o.settings.titleContentTime;if(o._compare(o.dataObject.sTimeFormat,o.dataObject.sArrInputTimeFormats[0])){n=3;l=["hour","minutes","meridiem"]}else{if(o._compare(o.dataObject.sTimeFormat,o.dataObject.sArrInputTimeFormats[1])){n=2;l=["hour","minutes"]}}}else{if(o._compare(o.settings.mode,"datetime")){k=o.settings.titleContentDateTime;if(o._compare(o.dataObject.sDateTimeFormat,o.dataObject.sArrInputDateTimeFormats[0])){n=5;l=["day","month","year","hour","minutes"]}else{if(o._compare(o.dataObject.sDateTimeFormat,o.dataObject.sArrInputDateTimeFormats[1])){n=6;l=["day","month","year","hour","minutes","meridiem"]}else{if(o._compare(o.dataObject.sDateTimeFormat,o.dataObject.sArrInputDateTimeFormats[2])){n=5;l=["month","day","year","hour","minutes"]}else{if(o._compare(o.dataObject.sDateTimeFormat,o.dataObject.sArrInputDateTimeFormats[3])){n=6;l=["month","day","year","hour","minutes","meridiem"]}else{if(o._compare(o.dataObject.sDateTimeFormat,o.dataObject.sArrInputDateTimeFormats[4])){n=5;l=["year","month","day","hour","minutes"]}else{if(o._compare(o.dataObject.sDateTimeFormat,o.dataObject.sArrInputDateTimeFormats[5])){n=6;l=["year","month","day","hour","minutes","meridiem"]}else{if(o._compare(o.dataObject.sDateTimeFormat,o.dataObject.sArrInputDateTimeFormats[6])){n=5;l=["day","month","year","hour","minutes"]}else{if(o._compare(o.dataObject.sDateTimeFormat,o.dataObject.sArrInputDateTimeFormats[7])){n=6;l=["day","month","year","hour","minutes","meridiem"]}}}}}}}}}}}var p="dtpicker-comp"+n;var r="";r+="<div class='dtpicker-header'>";r+="<div class='dtpicker-title'>"+k+"</div>";r+="<a class='dtpicker-close'>X</a>";r+="<div class='dtpicker-value'></div>";r+="</div>";var q="";q+="<div class='dtpicker-components'>";for(var j=0;j<n;j++){var i=l[j];q+="<div class='dtpicker-compOutline "+p+"'>";q+="<div class='dtpicker-comp "+i+"'>";q+="<a class='dtpicker-compButton increment'>+</a>";q+="<input type='text' class='dtpicker-compValue'></input>";q+="<a class='dtpicker-compButton decrement'>-</a>";q+="</div>";q+="</div>"}q+="</div>";var m="";m+="<div class='dtpicker-buttonCont'>";m+="<a class='dtpicker-button dtpicker-buttonSet'>"+o.settings.setButtonContent+"</a>";m+="<a class='dtpicker-button dtpicker-buttonClear'>"+o.settings.clearButtonContent+"</a>";m+="</div>";sTempStr=r+q+m;f(o.element).find(".dtpicker-subcontent").html(sTempStr);o._setCurrentDate();o._addEventHandlersForPicker()},_addEventHandlersForPicker:function(){var i=this;f(".dtpicker-compValue").not(".month .dtpicker-compValue, .meridiem .dtpicker-compValue").keyup(function(){this.value=this.value.replace(/[^0-9\.]/g,"")});f(".dtpicker-compValue").blur(function(){i._getValuesFromInputBoxes();i._setCurrentDate()});f(".dtpicker-comp .dtpicker-compValue").keyup(function(){var j=f(this);var m=j.val();var k=m.length;if(j.parent().hasClass("day")||j.parent().hasClass("hour")||j.parent().hasClass("minutes")||j.parent().hasClass("meridiem")){if(k>2){var l=m.slice(0,2);j.val(l)}}else{if(j.parent().hasClass("month")){if(k>3){var l=m.slice(0,3);j.val(l)}}else{if(j.parent().hasClass("year")){if(k>4){var l=m.slice(0,4);j.val(l)}}}}});f(i.element).find(".dtpicker-close").click(function(){i._hidePicker()});f(i.element).find(".dtpicker-buttonSet").click(function(){i._setButtonAction()});f(i.element).find(".dtpicker-buttonClear").click(function(){i._clearButtonAction()});f(i.element).find(".day .increment").click(function(){i.dataObject.iCurrentDay++;i._setCurrentDate()});f(i.element).find(".day .decrement").click(function(){i.dataObject.iCurrentDay--;i._setCurrentDate()});f(i.element).find(".month .increment").click(function(){i.dataObject.iCurrentMonth++;i._setCurrentDate()});f(i.element).find(".month .decrement").click(function(){i.dataObject.iCurrentMonth--;i._setCurrentDate()});f(i.element).find(".year .increment").click(function(){i.dataObject.iCurrentYear++;i._setCurrentDate()});f(i.element).find(".year .decrement").click(function(){i.dataObject.iCurrentYear--;i._setCurrentDate()});f(i.element).find(".hour .increment").click(function(){i.dataObject.iCurrentHour++;i._setCurrentDate()});f(i.element).find(".hour .decrement").click(function(){i.dataObject.iCurrentHour--;i._setCurrentDate()});f(i.element).find(".minutes .increment").click(function(){i.dataObject.iCurrentMinutes++;i._setCurrentDate()});f(i.element).find(".minutes .decrement").click(function(){i.dataObject.iCurrentMinutes--;i._setCurrentDate()});f(i.element).find(".meridiem .dtpicker-compButton").click(function(){if(i._compare(i.dataObject.sCurrentMeridiem,"AM")){i.dataObject.sCurrentMeridiem="PM";i.dataObject.iCurrentHour+=12}else{if(i._compare(i.dataObject.sCurrentMeridiem,"PM")){i.dataObject.sCurrentMeridiem="AM";i.dataObject.iCurrentHour-=12}}i._setCurrentDate()})},_getValueOfElement:function(k){var j=this;var i="";if(j._compare(f(k).prop("tagName"),"INPUT")){i=f(k).val()}else{i=f(k).html()}return i},_setValueOfElement:function(i){var j=this;var k=f(j.dataObject.oInputElement);if(j._compare(k.prop("tagName"),"INPUT")){k.val(i)}else{k.html(i)}k.change();return i},_parseDate:function(n){var l=this;var j=new Date(l.settings.defaultDate);var i=j.getDate();var o=j.getMonth();var k=j.getFullYear();if(n!=""&&n!=h&&n!=null){var m=n.split(l.settings.dateSeparator);if(l._compare(l.dataObject.sDateFormat,l.dataObject.sArrInputDateFormats[0])){i=parseInt(m[0]);o=parseInt(m[1]-1);k=parseInt(m[2])}else{if(l._compare(l.dataObject.sDateFormat,l.dataObject.sArrInputDateFormats[1])){o=parseInt(m[0]-1);i=parseInt(m[1]);k=parseInt(m[2])}else{if(l._compare(l.dataObject.sDateFormat,l.dataObject.sArrInputDateFormats[2])){k=parseInt(m[0]);o=parseInt(m[1]-1);i=parseInt(m[2])}else{if(l._compare(l.dataObject.sDateFormat,l.dataObject.sArrInputDateFormats[3])){i=parseInt(m[0]);o=l.getShortMonthIndex(m[1]);k=parseInt(m[2])}}}}}j=new Date(k,o,i,0,0,0,0);return j},_parseTime:function(r){var q=this;var k=new Date(q.settings.defaultDate);var m=k.getDate();var p=k.getMonth();var s=k.getFullYear();var o=k.getHours();var i=k.getMinutes();if(r!=""&&r!=h&&r!=null){if(q._compare(q.dataObject.sTimeFormat,q.dataObject.sArrInputTimeFormats[0])){var j=r.split(q.settings.timeMeridiemSeparator);var n=j[1];var l=j[0].split(q.settings.timeSeparator);o=parseInt(l[0]);i=parseInt(l[1]);if(q._compare(n,"PM")){o+=12}}else{if(q._compare(q.dataObject.sTimeFormat,q.dataObject.sArrInputTimeFormats[1])){var l=r.split(q.settings.timeSeparator);o=parseInt(l[0]);i=parseInt(l[1])}}}k=new Date(s,p,m,o,i,0,0);return k},_parseDateTime:function(t){var s=this;var l=new Date(s.settings.defaultDate);var n=l.getDate();var r=l.getMonth();var v=l.getFullYear();var p=l.getHours();var i=l.getMinutes();var o="";if(t!=""&&t!=h&&t!=null){var u=t.split(s.settings.dateTimeSeparator);var k=u[0].split(s.settings.dateSeparator);if(s._compare(s.dataObject.sDateTimeFormat,s.dataObject.sArrInputDateTimeFormats[0])||s._compare(s.dataObject.sDateTimeFormat,s.dataObject.sArrInputDateTimeFormats[1])){n=parseInt(k[0]);r=parseInt(k[1]-1);v=parseInt(k[2])}else{if(s._compare(s.dataObject.sDateTimeFormat,s.dataObject.sArrInputDateTimeFormats[2])||s._compare(s.dataObject.sDateTimeFormat,s.dataObject.sArrInputDateTimeFormats[3])){r=parseInt(k[0]-1);n=parseInt(k[1]);v=parseInt(k[2])}else{if(s._compare(s.dataObject.sDateTimeFormat,s.dataObject.sArrInputDateTimeFormats[4])||s._compare(s.dataObject.sDateTimeFormat,s.dataObject.sArrInputDateTimeFormats[5])){v=parseInt(k[0]);r=parseInt(k[1]-1);n=parseInt(k[2])}else{if(s._compare(s.dataObject.sDateTimeFormat,s.dataObject.sArrInputDateTimeFormats[6])||s._compare(s.dataObject.sDateTimeFormat,s.dataObject.sArrInputDateTimeFormats[7])){n=parseInt(k[0]);r=s.getShortMonthIndex(k[1]);v=parseInt(k[2])}}}}var q;if(s.dataObject.bIs12Hour){if(s._compare(s.settings.dateTimeSeparator,s.settings.timeMeridiemSeparator)&&(u.length==3)){o=u[2]}else{var m=u[1].split(s.settings.timeMeridiemSeparator);q=m[0];o=m[1]}if(!(!s._compare(o,"AM")||s._compare(o,"PM"))){o=""}}else{q=u[1]}var j=q.split(s.settings.timeSeparator);p=parseInt(j[0]);i=parseInt(j[1]);if(s._compare(o,"PM")){p+=12}}l=new Date(v,r,n,p,i,0,0);return l},getShortMonthIndex:function(k){var j=this;for(var i=0;i<j.settings.shortMonthNames.length;i++){if(j._compare(k,j.settings.shortMonthNames[i])){return i}}},_setVariablesForDate:function(){var i=this;i.dataObject.iCurrentDay=i.dataObject.dCurrentDate.getDate();i.dataObject.iCurrentMonth=i.dataObject.dCurrentDate.getMonth();i.dataObject.iCurrentYear=i.dataObject.dCurrentDate.getFullYear();if(i._compare(i.settings.mode,"time")){i.dataObject.iCurrentHour=i.dataObject.dCurrentDate.getHours();i.dataObject.iCurrentMinutes=i.dataObject.dCurrentDate.getMinutes();if(i._compare(i.dataObject.sTimeFormat,i.dataObject.sArrInputTimeFormats[0])){if(i.dataObject.iCurrentHour>12){i.dataObject.sCurrentMeridiem="PM"}else{if(i.dataObject.iCurrentHour==12&&i.dataObject.iCurrentMinutes>0){i.dataObject.sCurrentMeridiem="PM"}else{i.dataObject.sCurrentMeridiem="AM"}}}}else{if(i._compare(i.settings.mode,"datetime")){i.dataObject.iCurrentHour=i.dataObject.dCurrentDate.getHours();i.dataObject.iCurrentMinutes=i.dataObject.dCurrentDate.getMinutes();if(i._compare(i.dataObject.sDateTimeFormat,i.dataObject.sArrInputDateTimeFormats[1])||i._compare(i.dataObject.sDateTimeFormat,i.dataObject.sArrInputDateTimeFormats[3])||i._compare(i.dataObject.sDateTimeFormat,i.dataObject.sArrInputDateTimeFormats[5])||i._compare(i.dataObject.sDateTimeFormat,i.dataObject.sArrInputDateTimeFormats[7])){if(i.dataObject.iCurrentHour>12){i.dataObject.sCurrentMeridiem="PM"}else{if(i.dataObject.iCurrentHour==12&&i.dataObject.iCurrentMinutes>0){i.dataObject.sCurrentMeridiem="PM"}else{i.dataObject.sCurrentMeridiem="AM"}}}}}},_getValuesFromInputBoxes:function(){var l=this;if(l._compare(l.settings.mode,"date")||l._compare(l.settings.mode,"datetime")){var i=f(l.element).find(".month .dtpicker-compValue").val();if(i.length>1){i=i.charAt(0).toUpperCase()+i.slice(1)}var o=l.settings.shortMonthNames.indexOf(i);if(o!=-1){l.dataObject.iCurrentMonth=parseInt(o)}else{if(i.match("^[+|-]?[0-9]+$")){l.dataObject.iCurrentMonth=parseInt(i-1)}}l.dataObject.iCurrentDay=parseInt(f(l.element).find(".day .dtpicker-compValue").val())||l.dataObject.iCurrentDay;l.dataObject.iCurrentYear=parseInt(f(l.element).find(".year .dtpicker-compValue").val())||l.dataObject.iCurrentYear}if(l._compare(l.settings.mode,"time")||l._compare(l.settings.mode,"datetime")){l.dataObject.iCurrentHour=parseInt(f(l.element).find(".hour .dtpicker-compValue").val());l.dataObject.iCurrentMinutes=parseInt(f(l.element).find(".minutes .dtpicker-compValue").val());if(l._compare(l.settings.mode,"time")){if(l.dataObject.bIs12Hour){if(l.dataObject.iCurrentHour>12){l.dataObject.iCurrentHour=(l.dataObject.iCurrentHour%12)}if(l.dataObject.iCurrentMinutes>59){var n=l.dataObject.iCurrentMinutes/60;var m=l.dataObject.iCurrentMinutes%59;var j=l.dataObject.iCurrentHour+n;if(j>12){l.dataObject.iCurrentHour=(j%12)}l.dataObject.iCurrentMinutes=m}}else{if(l.dataObject.iCurrentHour>23){l.dataObject.iCurrentHour=(l.dataObject.iCurrentHour%23)}if(l.dataObject.iCurrentMinutes>59){var n=l.dataObject.iCurrentMinutes/60;var m=l.dataObject.iCurrentMinutes%59;var j=l.dataObject.iCurrentHour+n;if(j>23){l.dataObject.iCurrentHour=(j%23)}l.dataObject.iCurrentMinutes=m}}}if(l.dataObject.bIs12Hour){var k=f(l.element).find(".meridiem .dtpicker-compValue").val();if(l._compare(k,"AM")||l._compare(k,"PM")){l.dataObject.sCurrentMeridiem=k}if(l._compare(l.dataObject.sCurrentMeridiem,"PM")&&l.dataObject.iCurrentHour<13){l.dataObject.iCurrentHour+=12}if(l._compare(l.dataObject.sCurrentMeridiem,"AM")&&l.dataObject.iCurrentHour==12){l.dataObject.iCurrentHour=0}}}},_setCurrentDate:function(){var y=this;var l=new Date(y.dataObject.iCurrentYear,y.dataObject.iCurrentMonth,y.dataObject.iCurrentDay,y.dataObject.iCurrentHour,y.dataObject.iCurrentMinutes,0,0);var n=false,x=false;if(y.dataObject.dMaxValue!=null){n=(l.getTime()>y.dataObject.dMaxValue.getTime())}if(y.dataObject.dMinValue!=null){x=(l.getTime()<y.dataObject.dMinValue.getTime())}if(n||x){var t=false,o=false;if(y.dataObject.dMaxValue!=null){t=(y.dataObject.dCurrentDate.getTime()>y.dataObject.dMaxValue.getTime())}if(y.dataObject.dMinValue!=null){o=(y.dataObject.dCurrentDate.getTime()<y.dataObject.dMinValue.getTime())}if(!(t||o)){l=new Date(y.dataObject.dCurrentDate)}else{if(t){l=new Date(y.dataObject.dMaxValue)}if(o){l=new Date(y.dataObject.dMinValue)}}}y.dataObject.dCurrentDate=new Date(l);y._setVariablesForDate();if(y._compare(y.settings.mode,"date")){var m=y.dataObject.iCurrentDay;m=(m<10)?("0"+m):m;var i=y.dataObject.iCurrentMonth;var s=y.settings.shortMonthNames[i];var p=y.settings.fullMonthNames[i];var u=y.dataObject.iCurrentYear;var w=y.dataObject.dCurrentDate.getDay();var z=y.settings.shortDayNames[w];f(y.element).find(".day .dtpicker-compValue").val(m);f(y.element).find(".month .dtpicker-compValue").val(s);f(y.element).find(".year .dtpicker-compValue").val(u);var j=z+", "+p+" "+m+", "+u;f(y.element).find(".dtpicker-value").html(j)}else{if(y._compare(y.settings.mode,"time")){var q=y.dataObject.iCurrentHour;if(y.dataObject.bIs12Hour){if(q>12){q-=12}f(y.element).find(".meridiem .dtpicker-compValue").val(y.dataObject.sCurrentMeridiem)}q=(q<10)?("0"+q):q;if(y.dataObject.bIs12Hour&&q=="00"){q=12}var v=y.dataObject.iCurrentMinutes;v=(v<10)?("0"+v):v;f(y.element).find(".hour .dtpicker-compValue").val(q);f(y.element).find(".minutes .dtpicker-compValue").val(v);var r=q+y.settings.timeSeparator+v;if(y.dataObject.bIs12Hour){r+=y.settings.timeMeridiemSeparator+y.dataObject.sCurrentMeridiem}f(y.element).find(".dtpicker-value").html(r)}else{if(y._compare(y.settings.mode,"datetime")){var m=y.dataObject.iCurrentDay;m=(m<10)?("0"+m):m;var i=y.dataObject.iCurrentMonth;var s=y.settings.shortMonthNames[i];var p=y.settings.fullMonthNames[i];var u=y.dataObject.iCurrentYear;var w=y.dataObject.dCurrentDate.getDay();var z=y.settings.shortDayNames[w];f(y.element).find(".day .dtpicker-compValue").val(m);f(y.element).find(".month .dtpicker-compValue").val(s);f(y.element).find(".year .dtpicker-compValue").val(u);var j=z+", "+p+" "+m+", "+u;var q=y.dataObject.iCurrentHour;if(y.dataObject.bIs12Hour){if(q>12){q-=12}f(y.element).find(".meridiem .dtpicker-compValue").val(y.dataObject.sCurrentMeridiem)}q=(q<10)?("0"+q):q;if(y.dataObject.bIs12Hour&&q=="00"){q=12}var v=y.dataObject.iCurrentMinutes;v=(v<10)?("0"+v):v;f(y.element).find(".hour .dtpicker-compValue").val(q);f(y.element).find(".minutes .dtpicker-compValue").val(v);var r=q+y.settings.timeSeparator+v;if(y.dataObject.bIs12Hour){r+=y.settings.timeMeridiemSeparator+y.dataObject.sCurrentMeridiem}var k=j+y.settings.dateTimeSeparator+r;f(y.element).find(".dtpicker-value").html(k)}}}y._setButtons()},_setButtons:function(){var j=this;f(j.element).find(".dtpicker-compButton").removeClass("dtpicker-compButtonDisable").addClass("dtpicker-compButtonEnable");var i;if(j.dataObject.dMaxValue!=null){if(j._compare(j.settings.mode,"time")){if((j.dataObject.iCurrentHour+1)>j.dataObject.dMaxValue.getHours()||((j.dataObject.iCurrentHour+1)==j.dataObject.dMaxValue.getHours()&&j.dataObject.iCurrentMinutes>j.dataObject.dMaxValue.getMinutes())){f(j.element).find(".hour .increment").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}if(j.dataObject.iCurrentHour>=j.dataObject.dMaxValue.getHours()&&(j.dataObject.iCurrentMinutes+1)>j.dataObject.dMaxValue.getMinutes()){f(j.element).find(".minutes .increment").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}}else{i=new Date(j.dataObject.iCurrentYear,j.dataObject.iCurrentMonth,(j.dataObject.iCurrentDay+1),j.dataObject.iCurrentHour,j.dataObject.iCurrentMinutes,0,0);if(i.getTime()>j.dataObject.dMaxValue.getTime()){f(j.element).find(".day .increment").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}i=new Date(j.dataObject.iCurrentYear,(j.dataObject.iCurrentMonth+1),j.dataObject.iCurrentDay,j.dataObject.iCurrentHour,j.dataObject.iCurrentMinutes,0,0);if(i.getTime()>j.dataObject.dMaxValue.getTime()){f(j.element).find(".month .increment").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}i=new Date((j.dataObject.iCurrentYear+1),j.dataObject.iCurrentMonth,j.dataObject.iCurrentDay,j.dataObject.iCurrentHour,j.dataObject.iCurrentMinutes,0,0);if(i.getTime()>j.dataObject.dMaxValue.getTime()){f(j.element).find(".year .increment").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}i=new Date(j.dataObject.iCurrentYear,j.dataObject.iCurrentMonth,j.dataObject.iCurrentDay,(j.dataObject.iCurrentHour+1),j.dataObject.iCurrentMinutes,0,0);if(i.getTime()>j.dataObject.dMaxValue.getTime()){f(j.element).find(".hour .increment").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}i=new Date(j.dataObject.iCurrentYear,j.dataObject.iCurrentMonth,j.dataObject.iCurrentDay,j.dataObject.iCurrentHour,(j.dataObject.iCurrentMinutes+1),0,0);if(i.getTime()>j.dataObject.dMaxValue.getTime()){f(j.element).find(".minutes .increment").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}}}if(j.dataObject.dMinValue!=null){if(j._compare(j.settings.mode,"time")){if((j.dataObject.iCurrentHour-1)<j.dataObject.dMinValue.getHours()||((j.dataObject.iCurrentHour-1)==j.dataObject.dMinValue.getHours()&&j.dataObject.iCurrentMinutes<j.dataObject.dMinValue.getMinutes())){f(j.element).find(".hour .decrement").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}if(j.dataObject.iCurrentHour<=j.dataObject.dMinValue.getHours()&&(j.dataObject.iCurrentMinutes-1)<j.dataObject.dMinValue.getMinutes()){f(j.element).find(".minutes .decrement").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}}else{i=new Date(j.dataObject.iCurrentYear,j.dataObject.iCurrentMonth,(j.dataObject.iCurrentDay-1),j.dataObject.iCurrentHour,j.dataObject.iCurrentMinutes,0,0);if(i.getTime()<j.dataObject.dMinValue.getTime()){f(j.element).find(".day .decrement").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}i=new Date(j.dataObject.iCurrentYear,(j.dataObject.iCurrentMonth-1),j.dataObject.iCurrentDay,j.dataObject.iCurrentHour,j.dataObject.iCurrentMinutes,0,0);if(i.getTime()<j.dataObject.dMinValue.getTime()){f(j.element).find(".month .decrement").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}i=new Date((j.dataObject.iCurrentYear-1),j.dataObject.iCurrentMonth,j.dataObject.iCurrentDay,j.dataObject.iCurrentHour,j.dataObject.iCurrentMinutes,0,0);if(i.getTime()<j.dataObject.dMinValue.getTime()){f(j.element).find(".year .decrement").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}i=new Date(j.dataObject.iCurrentYear,j.dataObject.iCurrentMonth,j.dataObject.iCurrentDay,(j.dataObject.iCurrentHour-1),j.dataObject.iCurrentMinutes,0,0);if(i.getTime()<j.dataObject.dMinValue.getTime()){f(j.element).find(".hour .decrement").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}i=new Date(j.dataObject.iCurrentYear,j.dataObject.iCurrentMonth,j.dataObject.iCurrentDay,j.dataObject.iCurrentHour,(j.dataObject.iCurrentMinutes-1),0,0);if(i.getTime()<j.dataObject.dMinValue.getTime()){f(j.element).find(".minutes .decrement").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}}}if(j.dataObject.bIs12Hour){if(j.dataObject.dMaxValue!=null||j.dataObject.dMinValue!=null){var l=j.dataObject.iCurrentHour;if(j._compare(j.dataObject.sCurrentMeridiem,"AM")){l+=12}else{if(j._compare(j.dataObject.sCurrentMeridiem,"PM")){l-=12}}i=new Date(j.dataObject.iCurrentYear,j.dataObject.iCurrentMonth,j.dataObject.iCurrentDay,l,j.dataObject.iCurrentMinutes,0,0);if(j.dataObject.dMaxValue!=null){if(j._compare(j.settings.mode,"time")){var k=j.dataObject.iCurrentMinutes;if(l>j.dataObject.dMaxValue.getHours()||(l==j.dataObject.dMaxValue.getHours()&&k>j.dataObject.dMaxValue.getMinutes())){f(j.element).find(".meridiem .dtpicker-compButton").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}}else{if(i.getTime()>j.dataObject.dMaxValue.getTime()){f(j.element).find(".meridiem .dtpicker-compButton").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}}}if(j.dataObject.dMinValue!=null){if(j._compare(j.settings.mode,"time")){var k=j.dataObject.iCurrentMinutes;if(l<j.dataObject.dMinValue.getHours()||(l==j.dataObject.dMinValue.getHours()&&k<j.dataObject.dMinValue.getMinutes())){f(j.element).find(".meridiem .dtpicker-compButton").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}}else{if(i.getTime()<j.dataObject.dMinValue.getTime()){f(j.element).find(".meridiem .dtpicker-compButton").removeClass("dtpicker-compButtonEnable").addClass("dtpicker-compButtonDisable")}}}}}},_compare:function(j,i){if(j.toLowerCase()==i.toLowerCase()){return true}else{return false}},setIsPopup:function(k){var l=this;l.settings.isPopup=k;if(f(l.element).css("display")!="none"){l._hidePicker(1)}if(l.settings.isPopup){f(l.element).addClass("dtpicker-mobile");f(l.element).css({position:"fixed",top:0,left:0,width:"100%",height:"100%"})}else{f(l.element).removeClass("dtpicker-mobile");if(l.dataObject.oInputElement!=null){var j=f(l.dataObject.oInputElement).offset().top+f(l.dataObject.oInputElement).outerHeight();var m=f(l.dataObject.oInputElement).offset().left;var i=f(l.dataObject.oInputElement).outerWidth();f(l.element).css({position:"absolute",top:j,left:m,width:i,height:"auto"})}}},_compareDates:function(j,i){var k=0;if(j.getDate()==i.getDate()&&j.getMonth()==i.getMonth()&&j.getFullYear()==i.getFullYear()){k=1}else{if(j.getFullYear()<i.getFullYear()){k=2}else{if(j.getFullYear()>i.getFullYear()){k=3}else{if(j.getFullYear()==i.getFullYear()){if(j.getMonth()<i.getMonth()){k=2}else{if(j.getMonth()>i.getMonth()){k=3}else{if(j.getMonth()==i.getMonth()){if(j.getDate()<i.getDate()){k=2}else{if(j.getDate()>i.getDate()){k=3}}}}}}}}}return k},_compareTime:function(j,i){var k=0;if((j.getHours()==i.getHours())&&(j.getMinutes()==i.getMinutes())){k=1}else{if(j.getHours()<i.getHours()){k=2}else{if(j.getHours()>i.getHours()){k=3}else{if(j.getHours()==i.getHours()){if(j.getMinutes()<i.getMinutes()){k=2}else{if(j.getMinutes()>i.getMinutes()){k=3}}}}}}return k},_compareDateTime:function(k,j){var i=0;if((k.getDate()==j.getDate()&&k.getMonth()==j.getMonth()&&k.getFullYear()==j.getFullYear())&&(k.getHours()==j.getHours())&&(k.getMinutes()==j.getMinutes())){i=1}else{if(k.getFullYear()<j.getFullYear()){i=2}else{if(k.getFullYear()>j.getFullYear()){i=3}else{if(k.getFullYear()==j.getFullYear()){if(k.getMonth()<j.getMonth()){i=2}else{if(k.getMonth()>j.getMonth()){i=3}else{if(k.getMonth()==j.getMonth()){if(k.getDate()<j.getDate()){i=2}else{if(k.getDate()>j.getDate()){i=3}else{if(k.getDate()==j.getDate()){if(k.getHours()<j.getHours()){i=2}else{if(k.getHours()>j.getHours()){i=3}else{if(k.getHours()==j.getHours()){if(k.getMinutes()<j.getMinutes()){i=2}else{if(k.getMinutes()>j.getMinutes()){i=3}}}}}}}}}}}}}}}return i}}})(jQuery,window,document);