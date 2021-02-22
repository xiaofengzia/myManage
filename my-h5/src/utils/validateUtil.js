var validateUtils = {
	isPassword: function (element) { //6-10位字母数字密码
		var reg = /^(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{4,23}$/g;  // 修改6-10位数字字母组合 
		return reg.test(element);
	},
	isEmail: function (element) {
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		return reg.test(element);
	},
	isZip: function (element) { //邮编
		var reg = /[0-9]{6}$/;
		return reg.test(element);
	},
	isPhone: function (mobile) {
		var reg = /^1\d{10}$/;
		var reg2=/([\d])\1{10}/;
		return reg.test(mobile)&&!reg2.test(mobile);
	},
	isPhone2: function (mobile) {
		var reg = /^\d{1,}$/;
		var reg1 = /^1(3\d|4\d|5\d|6\d|7\d|8\d|9\d)\d{8}$/g;
		var reg2 = /^861(3\d|4\d|5\d|6\d|7\d|8\d|9\d)\d{8}$/g;
		var mobile1  = mobile.replace(/[ ]/g,'');
		if(!reg.test(mobile1)){
			return false;
		} 
		if(mobile.length == 11){
			return reg1.test(mobile);
		}
		if(mobile.length == 13){
			return reg2.test(mobile);
		}
	},
  isMoney: function(element){
	  var integer = /^\d+(.\d{2})?$/;
    var decimal = /^.\d{2}$/;
    return integer.test(element) || decimal.test(decimal);
  },
	trim: function (str) { 	//前后空格去除
		if(str) {
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
	},
	checkName_input: function (name) {
		var regx_cn =/^[\u4e00-\u9fa5]+([·]?[\u4e00-\u9fa5]+)+$/;
		var regx_en =/^[a-zA-Z]+[·|' ']?[a-zA-Z]+([·|' ']?[a-zA-Z]+)+$/;
		return (regx_cn.test(name) && name.length <= 20) ||
			(regx_en.test(name) && name.length <=20);
	},
	checkName_input_a: function (name) {
		var regx_cn =/^[\u4e00-\u9fa5]+([·]?[\u4e00-\u9fa5]+)+$/;
		var regx_en =/^[a-zA-Z]+[·|' ']?[a-zA-Z]+([·|' ']?[a-zA-Z]+)+$/;
		return (regx_cn.test(name) && getBLen(name) <= 20 && getBLen(name) >=4) ||
			(regx_en.test(name) && getBLen(name) <=20 && getBLen(name) >=4);
	},
	checkSpeName:function(name){//纳税人名
		var reg = /^[A-Za-z]+([·|.|·]?[A-Za-z]+)+$/;//只能支持汉字、拼音及英文
		var reg_cn = /^[\u4e00-\u9fa5]+([·|.|·]?[\u4e00-\u9fa5]+)+$/;
		var reg_mix = /^[A-Za-z\u4e00-\u9fa5]+([·|.|·]?[A-Za-z\u4e00-\u9fa5]+)+$/;
		return (reg_cn.test(name)&&name.length>=1)||(reg.test(name)&&name.length>=2)|| (reg_mix.test(name)&&name.length>=2);
	},
	checkSpeNameLast:function(name){//纳税人姓
		var reg = /^[A-Za-z]+([·|.|·]?[A-Za-z]+)+$/;//只能支持汉字、拼音及英文
		var reg_cn = /^[\u4e00-\u9fa5]+([·|.|·]?[\u4e00-\u9fa5]+)+$/;
		var reg_mix = /^[A-Za-z\u4e00-\u9fa5]+([·|.|·]?[A-Za-z\u4e00-\u9fa5]+)+$/;
		return (reg_cn.test(name)&&name.length>=1)||(reg.test(name)&&name.length>=2)|| (reg_mix.test(name)&&name.length>=2);
	},
	checkId:function (idType, idNo) {
		var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/; //只能由数字字母中文组成
		if (idType == "0" || idType == "4" || idType == "d") {
			return validateIdCard(idNo);
		} else if (idType == "9" || idType == "6" || idType =="b" || idType == "c") { // 港澳回乡证 警官证
				return reg.test(idNo);
		} else if (idType == "5") { // 外国人永久居留证
				return ( reg.test(idNo));
		} else if (idType == "7") { //出生证
				return ( reg.test(idNo));
		} else if (idType == "2") { //军人证
				return (reg.test(idNo));
		} else if (idType == "1") { //护照
			reg = /^[0-9a-zA-Z]*$/g; //只能由数字、字母组成
			return (reg.test(idNo));
		}
	},
	//校验第四级详细地址  
	//规则：包含至少一位数字（可为阿拉伯数字0、1、2、3、4、5、6、7、8、9，也可为0、一、二、三、四、五、六、七、八、九、十，也可为零、壹、贰、叁、肆、伍、陆、柒、捌、玖、拾等大写数字）；2.包含“组”或“号”或“室”等字。
	checkDetailAdress:function(str){
		//sq ==注释掉的
		// console.log("1");
		// var reg=/^(?=.*?[零|壹|贰|叁|肆|伍|陆|柒|捌|玖|拾|一|二|三|四|五|六|七|八|九|十|\d])(?=.*?[组|队|号|室])$/
		// return reg.test(name);
       let addmark="";
       let addmark_n="";
        var arr=['0','1','2','3','4','5','6','7','8','9','一','二','三','四','五','六','七','八','九','十','壹','贰','叁','肆','伍','陆','柒','捌','玖','拾'];
        var arr_n=['组','队','号','室'];
        for(var i=0;i<arr.length;i++){
            if(str.indexOf(arr[i])>-1){
                addmark="1";
            }
        }
        for(var i=0;i<arr_n.length;i++){
            if(str.indexOf(arr_n[i])>-1){
                addmark_n="1";
            }
        }
        if(addmark!="1"||addmark_n!="1"){
            return false;
        }else{
            return true;
        }
	},
	// 字符校验
     getByteLen:function(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null) {
            len += 2;
        }
        else {
            len += 1;
        }
    }
    return len;
},
checkNum: function (val) {
	var reg = /^[1-9]\d*$/;
	return reg.test(val);
}
}

	function validateIdCard(card_no) {
		var value = true;
		// // 15位和18位身份证号码的正则表达式
		// var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;

		// // 如果通过该验证，说明身份证格式正确，但准确性还需计算
		// if (regIdCard.test(idCard)) {
		// 	if (idCard.length == 18)
		// 		var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
		// 			5, 8, 4, 2); // 将前17位加权因子保存在数组里
		// 	var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); // 这是除以11后，可能产生的11位余数、验证码，也保存成数组
		// 	var idCardWiSum = 0; // 用来保存前17位各自乖以加权因子后的总和
		// 	for (var i = 0; i < 17; i++) {
		// 		idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
		// 	}

		// 	{
		// 		var idCardMod = idCardWiSum % 11; // 计算出校验码所在数组的位置
		// 		var idCardLast = idCard.substring(17); // 得到最后一位身份证号码

		// 		// 如果等于2，则说明校验码是10，身份证号码最后一位应该是X
		// 		if (idCardMod == 2) {
		// 			if (!(idCardLast == "X" || idCardLast == "x")) {
		// 				value = false;
		// 			}
		// 		} else {
		// 			// 用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
		// 			if (idCardLast != idCardY[idCardMod]) {
		// 				value = false;
		// 			}
		// 		}
		// 	}
		// } else {
		// 	value = false;
		// }
		var aCity = {
			11: "北京",
			12: "天津",
			13: "河北",
			14: "山西",
			15: "内蒙古",
			21: "辽宁",
			22: "吉林",
			23: "黑龙江 ",
			31: "上海",
			32: "江苏",
			33: "浙江",
			34: "安徽",
			35: "福建",
			36: "江西",
			37: "山东",
			41: "河南",
			42: "湖北 ",
			43: "湖南",
			44: "广东",
			45: "广西",
			46: "海南",
			50: "重庆",
			51: "四川",
			52: "贵州",
			53: "云南",
			54: "西藏 ",
			61: "陕西",
			62: "甘肃",
			63: "青海",
			64: "宁夏",
			65: "新疆",
			71: "台湾",
			81: "香港",
			82: "澳门",
			91: "国外 "
		};
	
		var iSum = 0;
		var reg = /^[1-9][0-7]\d{4}((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\d{3}(\d|X)?$/;
	
		if (reg.test(card_no) === false) {
			return false;
		}
		
		card_no = card_no.replace(/x$/i, "a");
		if (aCity[parseInt(card_no.substr(0, 2))] == null) {
			return false;
		}
		var sBirthday = card_no.substr(6, 4) + "-" + Number(card_no.substr(10, 2))
			+ "-" + Number(card_no.substr(12, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d
				.getDate())) {
				return false;
		}
		for (var i = 17; i >= 0; i--)
			iSum += (Math.pow(2, i) % 11) * parseInt(card_no.charAt(17 - i), 11);
		if (iSum % 11 != 1) {
				return false;
		}
		switch (card_no.length) {
			case 18:
				if (reg.test(card_no)) {
					var S = (parseInt(card_no[0]) + parseInt(card_no[10])) * 7 + (parseInt(card_no[1]) + parseInt(card_no[11])) * 9 + (parseInt(card_no[2]) + parseInt(card_no[12])) * 10 + (parseInt(card_no[3]) + parseInt(card_no[13])) * 5 + (parseInt(card_no[4]) + parseInt(card_no[14])) * 8 + (parseInt(card_no[5]) + parseInt(card_no[15])) * 4 + (parseInt(card_no[6]) + parseInt(card_no[16])) * 2 + parseInt(card_no[7]) * 1 + parseInt(card_no[8]) * 6 + parseInt(card_no[9]) * 3;
					var Y = S % 11;
					var M = "F";
					var JYM = "10X98765432";
					M = JYM.substr(Y, 1);
					/*判断校验位*/
					if (M != card_no[17].toUpperCase()) {
						return false;
				}
			}
		}
		return value;
	}

	function checkValidite(birthday,idType,idNo,sex){
		  let resultCode;
      let reg = /(\d{4})\-(\d{2})\-(\d{2})/;
      let holderbirthday = birthday.replace(reg, "$1$2$3");
      if (idType == "0"||idType == "d") {
        //身份证生日校验
        let number = idNo.substring(6, 14);
        if (number != holderbirthday) {
					resultCode=1;
          return resultCode;
        }
        //身份证性别校验
        if (idNo.charAt(16) >= "0" && idNo.charAt(16) <= "9") {
          if (
            (parseInt(idNo.charAt(16)) % 2 == 0 &&
              sex != 1) ||
            (parseInt(idNo.charAt(16)) % 2 != 0 &&
              sex != 0)
          ) {
						//女
						resultCode=2;
            return resultCode;
          }
        }
      }
      //户口本校验
      if (idType == "4") {
        let number = idNo;
        if (number.length != 8 && number.length != 18) {
					resultCode=3;
        } else if (number.length == 8 && number != holderbirthday) {
					resultCode=4;
        } else if (
          number.length == 18 &&
          number.substring(6, 14) != holderbirthday
        ) {
					resultCode=5;
        } else if (number.length == 18) {
          // TODO 户口本校验  18位时   校验方式
          if (number.charAt(16) >= "0" && number.charAt(16) <= "9") {
            if (
              (parseInt(number.charAt(16)) % 2 == 0 &&
                sex != 1) ||
              (parseInt(number.charAt(16)) % 2 != 0 &&
                sex != 0)
            ) {
							//女
							resultCode=6;
            }
          }
				}
				return resultCode;
      }
	}
	function compareDate(date1,date2,flag){
		//yyyy-dd-MM
		var data1s=date1.split("-");
		var data2s=date2.split("-");
    let result=false;
    if(Number(data1s[0])>Number(data2s[0])){
      return true;
    }else if(Number(data1s[0])==Number(data2s[0])){
      if(Number(data1s[1])>Number(data2s[1])){
        return true;
      }else if(Number(data1s[1])==Number(data2s[1])){
        if(Number(data1s[2])>Number(data2s[2])){
          return true;
        }
        //falg  判断是否支持日期相同
        if(flag==true){
          if(Number(data1s[2])==Number(data2s[2])){
          return true;
          }
        }
      }
    }
    return result;
}
//  获取字符串的长度
// 把双字节的替换成两个单字节的然后再获得长度 
function getBLen(str) {
	if(str) {
		var len = 0;
		for (var i=0; i<str.length; i++) { 
		 var c = str.charCodeAt(i); 
		//单字节加1 
		 if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
			 len++; 
		 } 
		 else { 
			len+=2; 
		 } 
		} 
		return len;
	}

};
	export {
		validateUtils,
		validateIdCard,
		checkValidite,
		compareDate,
		getBLen
	}
