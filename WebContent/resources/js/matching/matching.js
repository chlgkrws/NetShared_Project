var temp = -1;



function matching_button_on(param) {
	var item = param.id;

	if (item == "button1") {
		document.getElementById("button1").style.backgroundColor = 'rgb(248, 248, 248)';
	} else {
		document.getElementById("button2").style.backgroundColor = 'rgb(248, 248, 248)';
	}

}

function matching_button_out(param) {
	var item = param.id;

	if (item == "button1") {
		document.getElementById("button1").style.backgroundColor = 'white';
	} else {
		document.getElementById("button2").style.backgroundColor = 'white';
	}
}

function matching_button_click(param) {
	var item = param.id;

	document.getElementById("button1").style.borderWidth = '1px';
	document.getElementById("button2").style.borderWidth = '1px';
	if (item == "button1") {
		document.getElementById("button1").style.borderWidth = '5px';
		document.getElementById("button1").style.borderColor = 'gray';
		document.getElementById("numberOfMember").style.display = "none";
		temp = 0;
	} else {
		document.getElementById("button2").style.borderWidth = '5px';
		document.getElementById("button2").style.borderColor = 'gray';
		document.getElementById("numberOfMember").style.display = "block";
		temp = 1;
	}

}
function hc_button_click(param) {
	var item = param.id;

	/*document.getElementById("HCButton1").style.borderWidth = '1px';*/
	document.getElementById("HCButton2").style.borderWidth = '1px';
	document.getElementById("HCButton3").style.borderWidth = '1px';
	document.getElementById("HCButton4").style.borderWidth = '1px';

	if (item == "HCButton1") {
		document.getElementById("HCButton1").style.borderWidth = '5px';
		document.getElementById("HCButton1").style.borderColor = 'gray';
		document.getElementById("headCount").value = "one";
	} else if (item == "HCButton2") {
		document.getElementById("HCButton2").style.borderWidth = '5px';
		document.getElementById("HCButton2").style.borderColor = 'gray';
		document.getElementById("headCount").value = "two";
	} else if (item == "HCButton3") {
		document.getElementById("HCButton3").style.borderWidth = '5px';
		document.getElementById("HCButton3").style.borderColor = 'gray';
		document.getElementById("headCount").value = "three";
	} else {
		document.getElementById("HCButton4").style.borderWidth = '5px';
		document.getElementById("HCButton4").style.borderColor = 'gray';
		document.getElementById("headCount").value = "four";
	}
}

function hc_button_on(param) {
	var item = param.id;

	if (item == "HCButton1") {
		document.getElementById("HCButton1").style.backgroundColor = 'rgb(248, 248, 248)';
	} else if (item == "HCButton2") {
		document.getElementById("HCButton2").style.backgroundColor = 'rgb(248, 248, 248)';
	} else if (item == "HCButton3") {
		document.getElementById("HCButton3").style.backgroundColor = 'rgb(248, 248, 248)';
	} else {
		document.getElementById("HCButton4").style.backgroundColor = 'rgb(248, 248, 248)';
	}
}

function hc_button_out(param) {
	var item = param.id;

	if (item == "HCButton1") {
		document.getElementById("HCButton1").style.backgroundColor = 'white';
	} else if (item == "HCButton2") {
		document.getElementById("HCButton2").style.backgroundColor = 'white';
	} else if (item == "HCButton3") {
		document.getElementById("HCButton3").style.backgroundColor = 'white';
	} else {
		document.getElementById("HCButton4").style.backgroundColor = 'white';
	}
}
function match_click() {

	var sessionId = document.getElementById("session_id").value;

	if (sessionId == "null" || sessionId == null || sessionId == undefined || sessionId == "") {
		alert("먼저 로그인을 해주세요.");
		return false;
	}

	if (temp == 0) {
		alert("파티원으로 매칭을 신청했습니다! \n매칭이 성공되면 알림 드릴게요:)");
		document.getElementById("ismember").value = "yes";
		return true;
	} else if (temp == 1) {
		alert("파티장으로 매칭을 신청했습니다! \n매칭이 성공되면 알림 드릴게요:)");
		document.getElementById("ismember").value = "no";
		return true;
	} else {
		alert("파티원과 파티장 중 1개를 선택해주세요.");
		return false;
	}
}