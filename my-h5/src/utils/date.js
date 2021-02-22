/**
 * 返回指定时间
 * @param flag
 */
function getDate(flag){
  let date = new Date();
  if(!!!flag){
    return null;
  }
  if("0A" == flag){
    flag = "30D";
  }
  let num = parseInt(flag);
  if(flag.includes("D")){
    return new Date(date.getTime() - 60*60*24*1000*num);
  }else if(flag.includes("A")){
    let currentYear = date.getFullYear();
    let oldYear = currentYear - num;
    let currentMonth = date.getMonth();
    let currentDay = date.getDate();
    if(isLeapYear(oldYear) && currentMonth + 1 == 2 && currentDay == 29){
      return new Date(oldYear, currentMonth + 1, 1);
    }
    return new Date(oldYear, currentMonth, currentDay);
  }
}

/**
 * 返回指定时间
 * @param flag
 */
function getDateModi(flag){
  let date = new Date();
  if(!!!flag){
    return null;
  }
  if("0A" == flag){
    flag = "30D";
  }
  let num = parseInt(flag);
  if(flag.includes("D")){
    return new Date(date.getTime() - 60*60*24*1000*num);
  }else if(flag.includes("A")){
    let currentYear = date.getFullYear();
    let oldYear = currentYear - num;
    let currentMonth = date.getMonth();
    let currentDay = date.getDate()+2;
    if(isLeapYear(oldYear) && currentMonth + 1 == 2 && currentDay == 29){
      return new Date(oldYear, currentMonth + 1, 1);
    }
    return new Date(oldYear, currentMonth, currentDay);
  }
}

/**
 * 返回指定时间
 * @param flag
 */
function getDateForDate(date, flag){
  if(!!!flag){
    return null;
  }
  if("0A" == flag){
    flag = "30D";
  }
  let num = parseInt(flag);
  if(flag.includes("D")){
    return new Date(date.getTime() - 60*60*24*1000*num);
  }else if(flag.includes("A")){
    let currentYear = date.getFullYear();
    let oldYear = currentYear - num;
    let currentMonth = date.getMonth();
    let currentDay = date.getDate();
    if(isLeapYear(oldYear) && currentMonth + 1 == 2 && currentDay == 29){
      return new Date(oldYear, currentMonth + 1, 1);
    }
    return new Date(oldYear, currentMonth, currentDay);
  }
}

//判断是否是闰年
function isLeapYear(value){
  if((value % 4 == 0 && value % 100 != 0)||(value % 400 == 0)){
    return true;
  }
  return false;
}

function dateFormat(time, flag) {
  let date = new Date(time);
  let year = date.getFullYear();
  /* 在日期格式中，月份是从0开始的，因此要加0
   * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
   * */
  let month =
    date.getMonth() + 1 < 10
      ? "0" + (date.getMonth() + 1)
      : date.getMonth() + 1;
  let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
  let hours =
    date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
  let minutes =
    date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
  let seconds =
    date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
  // 拼接
  if(flag==true){
    return year + "年" + month + "月" + day+"日";
  }
  return year + "-" + month + "-" + day;
}

  /**
   * 计算N年后,YYYYMMDD
   * startdate：为开始时间，格式YYYY-MM-DD
   * nextYear：为间隔年月，如1表示一年后，2表示两年后
   */
  function getAfterNYear(startdate,nextYear){
    console.log(startdate)
    var expriedYear = parseInt(startdate.substring(0,4)) + nextYear;
    var expriedMonth = startdate.substring(5,7);
    var expriedDay = startdate.substring(8);
    //考虑二月份场景，若N年后的二月份日期大于该年的二月份的最后一天，则取该年二月份最后一天
    if(expriedMonth == '02' || expriedMonth == 2){
      var monthEndDate = new Date(expriedYear ,expriedMonth,0).getDate();
      if(parseInt(expriedDay) > monthEndDate){//为月底时间
        //取两年后的二月份最后一天
        expriedDay = monthEndDate;
      }
    }
    return expriedYear+"-"+expriedMonth+"-"+expriedDay;
  }

  function checkDate(date){
    var dateFormat =/^(\d{4})-(\d{2})-(\d{2})$/;
    if(dateFormat.test(date)){
      //true，是yyyy-MM-dd格式
      return true;
    }else{
      //false,不是yyyy-MM-dd格式
      return false;
    }
  }
  // 计算N个月之后的某一天 ,n个月后的日期,传入参数均为字符串
  function addmulMonth(dtstr, n){  
      var s = dtstr.split("-");
      var yy = parseInt(s[0]);
      var mm = parseInt(s[1]); 
      var dd = parseInt(s[2]); 
      var dt = new Date(yy, mm, dd); 
      
      var num=dt.getMonth() + parseInt(n);
      if(num/12>1){
         yy+=Math.floor(num/12) ;
         mm=num%12;
      }else{
          mm+=parseInt(n);
      }
   
      return yy + "-" + mm  + "-" + dd;
  } 

  function formatDate(date,type) {
    if(date instanceof Date){
        if(type ==1) {
            return date.format("yyyy-MM")
        }else{
            return date.format("yyyy-MM-dd")
        }  
    }else{
        return date;
    }
    
}

export {
  getDate,
  getDateForDate,
  dateFormat,
  getAfterNYear,
  getDateModi,
  checkDate,
  addmulMonth,
  formatDate
}
