// 控制页面由第三方智能机器人问答页面返回时，重新跳转投保流程而非第三方页面,进入首页点击返回将关闭窗口
let listenerBackHandler = {
    param: {
        isRun: false, //防止微信返回立即执行popstate事件  
    },
    listenerBack: function(that) {
        window.history.pushState(null, null, "null"); 
        window.history.pushState(null, null, that.GLOBAL.H5_ROOT+"/product/"+that.orderDTO.productCode); 
        console.log(that.orderDTO.productCode)
        console.log(that.orderDTO.productCode.split("_")[1])
        console.log(that.GLOBAL.H5_ROOT)
        window.history.pushState(null, null, that.GLOBAL.H5_ROOT+"/product/DETAIL_"+that.orderDTO.productCode.split("_")[1]); 
        // window.history.pushState(null, null, that.GLOBAL.H5_ROOT+"/order/viewRight"); 
        window.history.pushState(null, null, that.GLOBAL.H5_ROOT+"/order/documentUpload"); 
        window.history.replaceState(null, null, that.GLOBAL.H5_ROOT+"/order/inform"); 
        window.history.pushState(null, null, that.GLOBAL.H5_ROOT+"/order/reminderLetter");             
    },

    //初始化返回事件  
    initBack: function(that) {
        window.addEventListener('pageshow', function(e) {
            listenerBackHandler.listenerBack(that);
        });
    }
}

export {
    listenerBackHandler
}