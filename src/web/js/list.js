function onMultiCheck0() {
        var checkValue;
        if (IDlistTable.all.IDmultiCheck0.checked)
                checkValue = true;
        else 
                checkValue = false;
        for (i = 0; i < IDlistTable.all.length; i++) {
                try {
                   eval("IDlistTable.all.PK"+i+".checked="+checkValue);
              } catch(e) {}
        }
}

function isContain(source, finder) {
	if (source.indexOf(finder) != -1)
		return true;
	return false;
}

function getCheckedValues(){
	var ids = "";
	for (i = 0; i < IDlistTable.all.length; i++) {
		try {
			if (eval("IDlistTable.all.PK"+i+".checked")) {
				ids += eval("IDlistTable.all.PK"+i+".value")+",";
			}
		} catch(e) {}
	}
	return ids;
}

function getCheckedValuesWithSplit(split){
	var ids = "";
	for (i = 0; i < IDlistTable.all.length; i++) {
		try {
			if (eval("IDlistTable.all.PK"+i+".checked")) {
				ids += eval("IDlistTable.all.PK"+i+".value")+split;
			}
		} catch(e) {}
	}
	return ids;
}

function getCheckedValuesByAtt(attName){
	var ids = "";
	for (i = 0; i < IDlistTable.all.length; i++) {
		try {
			if (eval("IDlistTable.all.PK"+i+".checked")) {
				ids += eval("IDlistTable.all.PK"+i+"."+attName)+",";
			}
		} catch(e) {}
	}
	return ids;
}

function getCheckedCount(){
	var iCount = 0;
	for (i = 0; i < IDlistTable.all.length; i++) {
		try {
			if (eval("IDlistTable.all.PK"+i+".checked"))
				iCount++;
		} catch(e) {}
	}
	return iCount;
}

function OnGoto(pageId) {
	if (pageURL.value.indexOf("?") == -1)	{
		pageURL.value += "?";
	} else {
		pageURL.value += "&";
	}
	window.location = pageURL.value+params.value+"&pageId="+pageId;
}