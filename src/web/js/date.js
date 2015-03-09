	isIE = (document.all ? true : false);
	function getIEPosX(elt) { return getIEPos(elt,"Left"); }
	function getIEPosY(elt) { return getIEPos(elt,"Top"); }
	function getIEPos(elt,which) {
	 iPos = 0
	 while (elt!=null) {
	  iPos += elt["offset" + which]
	  elt = elt.offsetParent
	 }
	 return iPos
	}
	function getXBrowserRef(eltname) {
	 return (isIE ? document.all[eltname].style : document.layers[eltname]);
	}

	function hideElement(eltname) { getXBrowserRef(eltname).visibility = 'hidden'; }

	// ????????????????????????????????
	function moveBy(elt,deltaX,deltaY) {
	 if (isIE) {
	  elt.left = elt.pixelLeft + deltaX;
	  elt.top = elt.pixelTop + deltaY;
	 } else {
	  elt.left += deltaX;
	  elt.top += deltaY;
	 }
	}

	function toggleVisible(eltname) {
	 elt = getXBrowserRef(eltname);
	 if (elt.visibility == 'visible' || elt.visibility == 'show') {
	   elt.visibility = 'hidden';
	 } else {
	   fixPosition(eltname);
	   elt.visibility = 'visible';
	 }
	}

	function setPosition(elt,positionername,isPlacedUnder) {
	 positioner = null;
	 if (isIE) {
	  positioner = document.all[positionername];
	  elt.left = getIEPosX(positioner);
	  elt.top = getIEPosY(positioner);
	 } else {
	  positioner = document.images[positionername];
	  elt.left = positioner.x;
	  elt.top = positioner.y;
	 }
	 if (isPlacedUnder) { moveBy(elt,0,positioner.height); }
	}
	//????????????????????????????????????????????????????????????????????????????

			 // ??????????
			 isIE = (document.all ? true : false);

			 // ????????????????????????
			 var months = new Array("??????", "??????", "??????", "??????", "??????", "??????", "??????","??????", "??????", "??????", "??????", "??????");
			 var daysInMonth = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
		 var displayMonth = new Date().getMonth();
		 var displayYear = new Date().getFullYear();
		 var displayDivName;
		 var displayElement;

			 function getDays(month, year) {
				//??????????????????????????
				if (1 == month)
				   return ((0 == year % 4) && (0 != (year % 100))) ||
					  (0 == year % 400) ? 29 : 28;
				else
				   return daysInMonth[month];
			 }

			 function getToday() {
				// ??????????????
				this.now = new Date();
				this.year = this.now.getFullYear();
				this.month = this.now.getMonth();
				this.day = this.now.getDate();
			 }

			 // ????????????????????????
			 today = new getToday();

			 function newCalendar(eltName,attachedElement) {
			if (attachedElement) {
			   if (displayDivName && displayDivName != eltName) hideElement(displayDivName);
			   displayElement = attachedElement;
			}
			displayDivName = eltName;
				today = new getToday();
				var parseYear = parseInt(displayYear + '');
				var newCal = new Date(parseYear,displayMonth,1);
				var day = -1;
				var startDayOfWeek = newCal.getDay();
				if ((today.year == newCal.getFullYear()) &&
					  (today.month == newCal.getMonth()))
			{
				   day = today.day;
				}
				var intDaysInMonth =
				   getDays(newCal.getMonth(), newCal.getFullYear());
				var daysGrid = makeDaysGrid(startDayOfWeek,day,intDaysInMonth,newCal,eltName)
			if (isIE) {
			   var elt = document.all[eltName];
			   elt.innerHTML = daysGrid;
			} else {
			   var elt = document.layers[eltName].document;
			   elt.open();
			   elt.write(daysGrid);
			   elt.close();
			}
		 }

		 function incMonth(delta,eltName) {
		   displayMonth += delta;
		   if (displayMonth >= 12) {
			 displayMonth = 0;
			 incYear(1,eltName);
		   } else if (displayMonth <= -1) {
			 displayMonth = 11;
			 incYear(-1,eltName);
		   } else {
			 newCalendar(eltName);
		   }
		 }

		 function incYear(delta,eltName) {
		   displayYear = parseInt(displayYear + '') + delta;
		   newCalendar(eltName);
		 }

		 function makeDaysGrid(startDay,day,intDaysInMonth,newCal,eltName) {
			var daysGrid;
			var month = newCal.getMonth();
			var year = newCal.getFullYear();
			var isThisYear = (year == new Date().getFullYear());
			var isThisMonth = (day > -1)
			daysGrid = '<table border=1 cellspacing=0 cellpadding=2><tr><td bgcolor=#ffffff nowrap>';
			daysGrid += '<font face="courier new, courier" size=1>';
			daysGrid += '<a href="javascript:hideElement(\'' + eltName + '\')">x</a>';
			daysGrid += '&nbsp;&nbsp;';
			daysGrid += '<a href="javascript:incMonth(-1,\'' + eltName + '\')">&laquo; </a>';

			daysGrid += '<b>';
			if (isThisMonth) { daysGrid += '<font color=red>' + months[month] + '</font>'; }
			else { daysGrid += months[month]; }
			daysGrid += '</b>';

			daysGrid += '<a href="javascript:incMonth(1,\'' + eltName + '\')"> &raquo;</a>';
			daysGrid += '&nbsp;&nbsp;&nbsp;';
			daysGrid += '<a href="javascript:incYear(-1,\'' + eltName + '\')">&laquo; </a>';

			daysGrid += '<b>';
			if (isThisYear) { daysGrid += '<font color=red>' + year + '</font>'; }
			else { daysGrid += ''+year; }
			daysGrid += '</b>';

			daysGrid += '<a href="javascript:incYear(1,\'' + eltName + '\')"> &raquo;</a><br>';
			daysGrid += '&nbsp;Su Mo Tu We Th Fr Sa&nbsp;<br>&nbsp;';
			var dayOfMonthOfFirstSunday = (7 - startDay + 1);
			for (var intWeek = 0; intWeek < 6; intWeek++) {
			   var dayOfMonth;
			   for (var intDay = 0; intDay < 7; intDay++) {
				 dayOfMonth = (intWeek * 7) + intDay + dayOfMonthOfFirstSunday - 7;
			 if (dayOfMonth <= 0) {
				   daysGrid += "&nbsp;&nbsp; ";
			 } else if (dayOfMonth <= intDaysInMonth) {
			   var color = "blue";
			   if (day > 0 && day == dayOfMonth) color="red";
			   daysGrid += '<a href="javascript:setDay(';
			   daysGrid += dayOfMonth + ',\'' + eltName + '\')" '
			   daysGrid += 'style="color:' + color + '">';
			   var dayString = dayOfMonth + "</a> ";
			   if (dayString.length == 6) dayString = '0' + dayString;
			   daysGrid += dayString;
			 }
			   }
			   if (dayOfMonth < intDaysInMonth) daysGrid += "<br>&nbsp;";
			}
			return daysGrid + "</td></tr></table>";
		 }

		 function setDay(day,eltName) {
			 var month_;
			 var day_;
			if((displayMonth+1)>0 && (displayMonth+1)<10){
				month_ = "0"+ (displayMonth+1);
			}else{
				month_ = displayMonth+1;
			}
			if(day>0 && day <10){
				day_ = "0"+ day;
			}else{
				day_ = day;
			}
		   displayElement.value = displayYear+"-"+month_ + "-" + day_; 
		   hideElement(eltName);
		 }


	//????????????????????????????????????????????????????????????????????????????


	// fixPosition() ????????????????????????????????
	//
	function fixPosition(eltname) {
	 elt = getXBrowserRef(eltname);
	 positionerImgName = eltname + 'Pos';
	 // hint: try setting isPlacedUnder to false
	 isPlacedUnder = false;
	 if (isPlacedUnder) {
	  setPosition(elt,positionerImgName,true);
	 } else {
	  setPosition(elt,positionerImgName)
	 }
	}
	function toggleDatePicker(eltName,formElt,dateField) {
	  var x = formElt.indexOf('.');
	  var formName = formElt.substring(0,x);
	  var formEltName = formElt.substring(x+1);
	  newCalendar(eltName,dateField);
	  toggleVisible(eltName);
	}


	/////////////////////////////////////////////////////////////
	//??????????
	/////////////////////////////////////////////////////////////
	 var dateYField;
	var dateYPicker;
 var dateObj = new Date();
 var dateYCode = '<table width="100" height="20" border="0" cellpadding="0" cellspacing="0" bgcolor="#E3E3E3">';
 dateYCode += '<tr> <td height="20"  class="tinttd"> <div align="left"><select name="select" ID="ID_Year">';
 for (i = dateObj.getFullYear()+100; i > 1900; i--)
 {
	 dateYCode += '<option value="'+i+'"';
	 if (i == dateObj.getFullYear())
		 dateYCode += ' selected';
	 dateYCode +='>'+i+'</option>';
 }
 dateYCode += '</select>??';
 dateYCode += '&nbsp;<img STYLE="cursor:hand" src="/BDApp/images/date2.gif" onclick="OnYPickDate()">';
 dateYCode += '</div></td> </tr></table>';

function OnYPickDate() {
	dateYField.value = dateYPicker.all.ID_Year.value;
	dateYPicker.style.display="none";
}

function YPickDate(pYDatePickerID, pYDateField) {
	dateYPicker = pYDatePickerID;
	dateYField = pYDateField;
	dateYPicker.innerHTML = dateYCode;
	dateYPicker.style.display="block";
 }

 //////////////////////////////////////////////////////////////////
 // ????????????
 //////////////////////////////////////////////////////////////////
  var dateYMField;
 var dateYMPicker;
 var dateObj = new Date();
 var dateYMCode = '<table width="160" height="20" border="0" cellpadding="0" cellspacing="0" bgcolor="#E3E3E3">';
 dateYMCode += '<tr> <td  class="tinttd"> <div align="left"><select name="select" ID="ID_Year">';
 for (i = dateObj.getFullYear()+100; i > 1900; i--)
 {
	 dateYMCode += '<option value="'+i+'"';
	 if (i == dateObj.getFullYear())
		 dateYMCode += ' selected';
	 dateYMCode +='>'+i+'</option>';
 }
 dateYMCode += '</select>?? <select name="select" ID="ID_Month">';
 for (i = 12; i  > 0; i--)
 {	
	var day = (i < 10 ? '0'+i:i);
	dateYMCode += '<option value="'+day+'"';
	if ( i  == dateObj.getMonth()+1)
		dateYMCode += ' selected ';
	dateYMCode += '>'+day+'</option>';
 }
 dateYMCode += '</select>??';
 dateYMCode += '&nbsp;<img STYLE="cursor:hand" src="/BDApp/images/date2.gif" onclick="OnYMPickDate()">';
 dateYMCode += '</div></td> </tr></table>';

function OnYMPickDate() {
	dateYMField.value = dateYMPicker.all.ID_Year.value+"-"+dateYMPicker.all.ID_Month.value;
	dateYMPicker.style.display="none";
}

function YMPickDate(pYMDatePickerID, pYMDateField) {
	dateYMPicker = pYMDatePickerID;
	dateYMField = pYMDateField;
	dateYMPicker.innerHTML = dateYMCode;
	dateYMPicker.style.display="block";
	dateYMPicker.style.width="10";
	dateYMPicker.style.height="50";
 }

  function viewCalendar(edit) {
          showx = event.screenX;
          showy = event.screenY;
          newWINwidth = 210 + 4 + 18;
          retval = window.showModalDialog("./js/CalendarDlg.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;");
          if( retval != null ) edit.value=retval;
  }