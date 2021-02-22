// 银行账号每4位添加空格
function numFormat(num) {
    var nums = num+"";
    var count = 0;
    var result = "";
    for(var i=0; i<=nums.length;i++) {
        if(count%4==0 && count!=0) {
            result =result+" "+nums.charAt(i);
        }else {
            result =result+nums.charAt(i);
        }
        count++;
    }

    return result;
}

function getBirthByNum(idCard) {
    var birthday = "";
    if(idCard != null && idCard != ""){
        if(idCard.length == 15){
            birthday = "19"+idCard.slice(6,12);
        } else if(idCard.length == 18){
            birthday = idCard.slice(6,14);
        }   
        birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");
    }   
    return birthday;
}

function getSexByNum(idCard) {
    var sexStr = '';
    if (parseInt(idCard.slice(-2, -1)) % 2 == 1) {
        sexStr = '0';
    }
    else {
        sexStr = '1';
    }
    return sexStr;
}

export {numFormat, getBirthByNum, getSexByNum};