var allDays = new Array();
var fullDays;
var msg;

function updateVarFull(roomId) {
	fullDays = allDays[roomId];
}

function unavailableDates(date) {
	var e = document.getElementById("roomform:room_input");
	updateVarFull(e.options[e.selectedIndex].value);
	if(fullDays!="" &&  fullDays.length!=0){
		for (var j = 0; j < fullDays.length; j++) {
			if (date.getMonth() == fullDays[j][1] - 1 && date.getDate() == fullDays[j][0] && date.getFullYear() == fullDays[j][2]) {
				return [true, 'full', msg];
			}
		}
	}
	return [true, 'empty'];
}