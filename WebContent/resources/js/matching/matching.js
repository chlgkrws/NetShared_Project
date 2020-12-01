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
		temp = 0;

	} else {
		document.getElementById("button2").style.borderWidth = '5px';
		document.getElementById("button2").style.borderColor = 'gray';
		temp = 1;
	}

}

function match_click() {
	if (temp == 0) {
		alert("파티원으로 매칭을 신청했습니다! \n매칭이 성공되면 알림 드릴게요:)");
		location.href = '/index.jsp';
	} else if (temp == 1) {
		alert("파티장으로 매칭을 신청했습니다! \n매칭이 성공되면 알림 드릴게요:)");
		location.href = '/index.jsp';
	} else {
		alert("파티원과 파티장 중 1개를 선택해주세요.")
	}


}