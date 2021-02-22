/**
 * 在基本信息页面点击下一步发生阻断时，部分在点击该按钮时进行赋值的字段值会丢失，
 * 为解决此一问题，现将需要赋值的字段单独取出，在单击按钮时先进行统一赋值处理
 */
function getAllInsuredInfo(that) {
    if(that.orderDTO.policyList[0].relateInsuredCode !='00') {
        // 教育
        if((!!that.orderDTO.insuredList[0].degreeSelected) && (!!that.orderDTO.insuredList[0].degreeSelected[0])){
            that.orderDTO.insuredList[0].educationCode = that.orderDTO.insuredList[0].degreeSelected[0];
        }
        // 婚姻
        if((!!that.orderDTO.insuredList[0].marriageSelected) && (!!that.orderDTO.insuredList[0].marriageSelected[0])){
            that.orderDTO.insuredList[0].maritalStatusCode = that.orderDTO.insuredList[0].marriageSelected[0];
        }
        // 证件类型
        if((!!that.orderDTO.insuredList[0].idtypeSelected) && (!!that.orderDTO.insuredList[0].idtypeSelected[0])){
            that.orderDTO.insuredList[0].certificateTypeCode = that.orderDTO.insuredList[0].idtypeSelected[0];
        }

        // 证件类型名称
        if(that.orderDTO.insuredList[0].certificateTypeCode) {
            for (const key in that.GLOBAL.idTypeList) {
                if (that.GLOBAL.idTypeList.hasOwnProperty(key)) {
                    const element = that.GLOBAL.idTypeList[key];
                    if(element.value==that.orderDTO.insuredList[0].certificateTypeCode){
                    that.orderDTO.insuredList[0].certificateType=element.name;
                    }
                }
            }
        }
        // 证件有效期  如何处理？？？


        // 身高、体重、收入及收入来源
        that.orderDTO.insuredList[0].height=that.orderDTO.bbHeight;
        that.orderDTO.insuredList[0].weight=that.orderDTO.bbWeight;  
        if(that.orderDTO.policyList[0].relateInsuredCode !='00') {
            that.orderDTO.insuredList[0].money=that.orderDTO.bbIncome;
        }
        that.orderDTO.insuredList[0].sfIncome = that.orderDTO.bbIncomeList
        that.orderDTO.insuredList[0].incomeSourceCod = that.orderDTO.bbIncomeList;

        //教育名称
        if(that.orderDTO.insuredList[0].educationCode){
            for (const key in that.GLOBAL.degreeList) {
                if (that.GLOBAL.degreeList.hasOwnProperty(key)) {
                    const element = that.GLOBAL.degreeList[key];
                    if(element.value==that.orderDTO.insuredList[0].educationCode){
                        that.orderDTO.insuredList[0].education=element.name;
                    }
                }
            }
        }  
    }else{
        that.orderDTO.insuredList[0]=JSON.parse(JSON.stringify(that.orderDTO.policyList[0])); 
    }
}

function getAllAppntInfo(that){
    // 教育
    if (!!that.orderDTO.policyList[0].degreeSelected) {
        that.orderDTO.policyList[0].educationCode = that.orderDTO.policyList[0].degreeSelected[0];
    }
    // 婚姻
    if (!!that.orderDTO.policyList[0].marriageSelected) {
        that.orderDTO.policyList[0].maritalStatusCode = that.orderDTO.policyList[0].marriageSelected[0];
    }
    // 证件类型
    if (!!that.orderDTO.policyList[0].idtypeSelected) {
        that.orderDTO.policyList[0].certificateTypeCode = that.orderDTO.policyList[0].idtypeSelected[0];
    }

    if (that.orderDTO.policyList[0].certificateTypeCode) {
        for (const key in that.GLOBAL.idTypeList) {
            if (that.GLOBAL.idTypeList.hasOwnProperty(key)) {
              const element = that.GLOBAL.idTypeList[key];
                if (element.value == that.orderDTO.policyList[0].certificateTypeCode) {
                    that.orderDTO.policyList[0].certificateType = element.name;
                }
            }
        }
    }

    // 证件有效期  如何处理？？？

    // 婚姻
    if (that.orderDTO.policyList[0].maritalStatusCode) {
        for (const key in that.GLOBAL.marriageList) {
            if (that.GLOBAL.marriageList.hasOwnProperty(key)) {
              const element = that.GLOBAL.marriageList[key];
                if (element.value == that.orderDTO.policyList[0].maritalStatusCode) {
                    that.orderDTO.policyList[0].maritalStatus = element.name;
                }
            }
        }
    }

     if (that.orderDTO.policyList[0].educationCode) {
        for (const key in that.GLOBAL.degreeList) {
            if (that.GLOBAL.degreeList.hasOwnProperty(key)) {
              const element = that.GLOBAL.degreeList[key];
                if (element.value == that.orderDTO.policyList[0].educationCode) {
                    that.orderDTO.policyList[0].education = element.name;
                }
            }
        }
    }
}





export {getAllInsuredInfo, getAllAppntInfo};