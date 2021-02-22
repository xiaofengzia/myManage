/**
 * 计算年龄
 */
function getAgeByValue(value) {
  if(!!!value){
    return;
  }
  let today = new Date();
  let b = value.match(
    /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
  if (b == null) {
    return null;
  } else {
    let month = b[3] * 1;
    let day = b[4] * 1;
    let nowY = today.getFullYear();
    let age = nowY - b[1];
    if (today.getMonth() + 1 < month) {
        if(age==0){
            age=0;
        }else{
            age--;
        }
    } else if (today.getMonth() + 1 == month
      && today.getDate() < day) {
      //特殊处理，2月无29号时，2月29号过生日的，默认为2月28日过生日
      if(!isLeapYear(nowY)){
        if(month == 2 && day == 29 && today.getDate() == 28){
          return age;
        }
      }
        if(age==0){
            age=0;
        }else{
            age--;
        }
    } else if (age < 1) {
      let dayNum = getDateDiff(value, nowY
        + '-' + (today.getMonth() + 1) + '-'
        + today.getDate());
      if (dayNum <= 28) {
          if(age==0){
              age=0;
          }else{
              age--;
          }
      }
    }
    return age;
  }
}

function getDayByValue(value) {
  if(!!!value){
    return;
  }
  let today = new Date();
  let b = value.match(
    /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
  if (b == null) {
    return null;
  } else {
    let month = b[3] * 1;
    let day = b[4] * 1;
    let nowY = today.getFullYear();
    let age = nowY - b[1];
    if (today.getMonth() + 1 < month) {
      age--;
    } else if (today.getMonth() + 1 == month
      && today.getDate() < day) {
      //特殊处理，2月无29号时，2月29号过生日的，默认为2月28日过生日
      if(!isLeapYear(nowY)){
        if(month == 2 && day == 29 && today.getDate() == 28){
          return age;
        }
      }
      age--;
    } else if (age < 1) {
      let dayNum = getDateDiff(value, nowY
        + '-' + (today.getMonth() + 1) + '-'
        + today.getDate());
      if (dayNum <= 28) {
        age--;
      }
    }
    return age;
  }
}

/**
 * 字符串转日期
 */
function stringToDate(string) {
  if(!!string){
    return new Date(Date.parse(string.replace(/-/g, "/")));
  }
  return string;
}

/**
 * 取得两个日期的时间差
 */
function getDateDiff(date1, date2) {
  if(!!!date1 || !!!date2){
    return;
  }
  let dt1,dt2;
  if(date1 instanceof Date){
    dt1 = date1;
  }else{
    dt1 = stringToDate(date1);
  }
  if(date2 instanceof Date){
    dt2 = date2;
  }else{
    dt2 = stringToDate(date2);
  }
  return (dt2.getTime()-dt1.getTime())/(1000*3600*24);
}

//判断是否是闰年
function isLeapYear(value){
  if((value % 4 == 0 && value % 100 != 0)||(value % 400 == 0)){
    return true;
  }
  return false;
}


export {
  getAgeByValue,
  getDateDiff,
  stringToDate
}
