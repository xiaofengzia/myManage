function checkShareInfo(that) {
    that.checkInfoReqDTO.timeStemp = Date.parse(new Date());
    that.checkInfoReqDTO.saleChannel = that.GLOBAL.CHATYPE;
    that.postRequest(that.GLOBAL.API_URLS.checkShareInfo,that.checkInfoReqDTO).then(reps => {      
        if (reps.data.resultCode == 2 || (that.checkInfoReqDTO.shareFlag == 2 && reps.data.resultCode == 1 )) {
            let _orderDto=reps.data.object;
            if(that.checkInfoReqDTO.shareFlag == 1) {
                that.orderDTO=JSON.parse(_orderDto);
                if(!!!that.orderDTO.insuranceInnerList[0]) {
                    that.$vux.alert.show("该链接已失效"); 
                }else{
                    that.orderDTO.mainRiskCode = that.orderDTO.insuranceInnerList[0].code;
                    that.$store.commit("SETORDERDTO", that.orderDTO);
    //                if(that.isPreview) {
      //                  that.$nextTick(function(){ // dom更新调用方法
        //                    that.fillHtmlSign();
          //              })
            //        } 
                }   
            }
            // 处理成功后处理
            checkNoStatus(that);
        } else {
            //数据有问题   在页面提示
            that.$vux.alert.show("该链接已失效");   
            that.isSubmit = false;         
        }
    });
}

function checkNoStatus(that) {
    that.postRequest(that.GLOBAL.API_URLS.getPolicyStatus, that.checkInfoReqDTO).then(reps => {     
        if (reps.data.resultCode == 1) {
            // 判断保单是否已提交
            let obj = reps.data.object;
            if(obj && !!!that.GLOBAL.EDIT_POLICY_STATUS.includes(obj.status)) {
                that.$vux.alert.show("保单已经提交完核心，不允许重新编辑！");
            }else if(that.checkInfoReqDTO.shareFlag == 1) {                
                that.config();
            }else if(that.checkInfoReqDTO.shareFlag == 2) {
                that.gwolReqDTO.requestBodyJson.serialNumber=that.checkInfoReqDTO.serialNumber;
                that.gwolReqDTO.requestBodyJson.applicationNumber=that.checkInfoReqDTO.applicationNumber;      
                // end 添加请求数据
                that.showOrderInfo(); // 获取预览信息  
            }
            
        } else {
            //数据有问题   在页面提示reps.data.resultInfo
            that.$vux.alert.show("该链接已失效");
            that.isSubmit = false;
        }
    });
}

function getAgentInfo(that){
    that.getRequest(that.GLOBAL.API_URLS.shareAgentInfo+that.checkInfoReqDTO.agentCode).then(reps => {
        if (reps.data.resultCode == 1) {
            that.manageCom = reps.data.object.branchcode;
            that.saleChannel=reps.data.object.saleChannel;
            sessionStorage.setItem("empmobile",reps.data.object.mobile);
            sessionStorage.setItem("branchcode",that.manageCom);
            that.empphone=sessionStorage.getItem("empmobile");
        } else {
            that.$vux.alert.show(reps.data.resultInfo);
        }
    });
}

export {checkShareInfo, getAgentInfo};