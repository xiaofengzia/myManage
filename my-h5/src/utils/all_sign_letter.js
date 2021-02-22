
//document.write("<script language=javascript src=\"" + process.env.H5_ROOT +  "/static/js/anysignWebInterface.js\" charset=\"utf-8\"></script>");
// document.write("<link href=\""+process.env.H5_ROOT+"/static/css/canvas_css.css\" rel=\"stylesheet\">");
// document.write("<link href=\""+process.env.H5_ROOT+"/static/css/sp.css\" rel=\"stylesheet\">");

// document.write("<link media=\"screen and (max-width: 480px)\" href=\""+process.env.H5_ROOT+"/static/css/mw480Portrait.css\" rel=\"stylesheet\">");
// document.write("<link media=\"screen and (min-width: 480px) and (max-width: 1024px)\" href=\""+process.env.H5_ROOT+"/static/css/mw480Portrait.css\" rel=\"stylesheet\">");
// document.write("<link media=\"screen and (min-width: 1024px)\" href=\""+process.env.H5_ROOT+"/static/css/sw1024.css\" rel=\"stylesheet\">");

// document.write("<script language=javascript src=\""+process.env.H5_ROOT+"/static/js/libs/zlib/deflate.min.js\" charset=\"utf-8\"></script>");

// document.write("<script language=javascript src=\""+process.env.H5_ROOT+"/static/js/libs/CryptoJS v3.1.2/components/core-min.js\" charset=\"utf-8\"></script>");
// document.write("<script language=javascript src=\""+process.env.H5_ROOT+"/static/js/libs/CryptoJS v3.1.2/rollups/sha1.js\" charset=\"utf-8\"></script>");
// document.write("<script language=javascript src=\""+process.env.H5_ROOT+"/static/js/libs/CryptoJS v3.1.2/rollups/tripledes.js\" charset=\"utf-8\"></script>");
// document.write("<script language=javascript src=\""+process.env.H5_ROOT+"/static/js/libs/CryptoJS v3.1.2/components/mode-ecb.js\" charset=\"utf-8\"></script>");

// document.write("<script language=javascript src=\""+process.env.H5_ROOT+"/static/js/libs/anysign_all.min.js\" charset=\"utf-8\"></script>");

// document.write("<script language=javascript src=\""+process.env.H5_ROOT+"/static/js/libs/anysignCommentUI.js\" charset=\"utf-8\"></script>");
// document.write("<script language=javascript src=\""+process.env.H5_ROOT+"/static/js/libs/jquery-1.4.2.min.js\" charset=\"utf-8\"></script>");



let appnt_name = "测试姓名";
let appnt_id="test_0001";
let insured_name = "测试姓名";
let insured_id="test_0001";

let write_type; // 操作人？
         
let apiInstance;
let fileData;
let ocrCapture;

let DATA_CANNOT_PARSED = "10003"; //输入数据项无法解析
let SERVICE_SYSTEM_EXCEPTION = "10011"; //服务系统异常错误
let RECOGNITION_RESULT_EMPTY = "10100"; //识别结果为空
let CONNECTION_SERVICE_TIMEOUT = "10101"; //连接识别服务超时
let CONNECTION_RECOGNITION_EXCEPTION = "10102"; //连接识别服务异常
let SUCCESS = "0"; //识别成功
let RECOGNITION_FALSE = "-1";//识别错误
let RESULT_OK = 0; //操作成功
let CALLBACK_TYPE_SIGNATURE = 1; //签名框点击确认之后的回调，回调中包含签名快照
let CALLBACK_TYPE_COMMENTSIGN = 10; //批注签名框点击确认之后的回调，回调中包含签名快照

let RESULT_ERROR = -1; //操作失败
let EC_API_NOT_INITED = 1; //接口未初始化错误
let CALLBACK_TYPE_START_RECORDING = 14;
let CALLBACK_TYPE_STOP_RECORDING = 15;

let _this;
let _qname="";
let _qid="";
let _policy_code="";
let _sign_type ="";
let channel;
let reqUrl;

let imgmessage = "01308 、01110、11103、13301 、03316"; // 此险种需要签署批注


//配置模板数据
function testSetTemplateData() {
   //文件数据
    let formData = "<html></html>";
    //报案号+单证类型   报文单独传
    let businessId = "";
    if(_sign_type == 1){ // 代理人签名
        businessId =_policy_code+"-"+ _qid + "_OPS-NB-P00001";//集成信手书业务的唯一标识
    }
    if(_sign_type == 2){ //   投被保人签名
        businessId = _policy_code + "-" + _qid + "_OPS-NB-P00002";
    }
    if(_sign_type == "3"){ // 回执
        // console.log(123);
        businessId = _policy_code +"_OPS-NB-P00001";
    }
    if(_sign_type == "CCB"){ // 建行
        businessId = _policy_code +"_OPS-NB-P00005";
    }
    // let businessId="test_20180926_3001";
    console.log(businessId);
    let template_serial = "4000";//用于生成PDF的模板ID

    let res;

    //配置JSON格式签名原文
    /**
     * 设置表单数据，每次业务都需要set一次
     * @param template_type 签名所用的模板类型
     * @param contentUtf8Str 表单数据，类型为Utf8字符串
     * @param businessId 业务工单号
     * @param template_serial 模板序列号
     * @returns {*} 是否设置成功
     */
    res = apiInstance.setTemplate(TemplateType.PDF,formData,businessId,template_serial);

    if(!res){   
        _this.$vux.alert.show("模板配置数据失败");
        return res;
    }
}

  //添加单签签名框
function testAddSignatureObj(objId) {

    let context_id = objId;
    let signer = new Signer(_qname, _qid);

    let signerRule;

    if(_this.type == 'APPNT') { // 投保人
        signerRule = new SignRule_KeyWord("HSZT000",1,0,1,1);
    }else if(_this.type == 'INSURED') { // 被保人
        signerRule = new SignRule_KeyWord("HSZT001",1,0,1,1);
    } else if(_this.type == 'AGENT') { 
        signerRule = new SignRule_KeyWord("HSZTe000",1,0,1,1);
    } else if(_this.type == 'RECEIPT'){
        signerRule = new SignRule_KeyWord("HSZTe00001",1,0,1,1);
    } else if(_this.type =='COPY'){
        signerRule = new SignRule_KeyWord("投保人显示位置",1,0,1,1);
    } else if(_this.type =='CCB') {
        signerRule = new SignRule_KeyWord("甲方显示位置",1,0,1,1);
    }

	// if (type == "APPNT") {
	// 	signerRule = new SignRule_KeyWord("投保人显示位置", 3, 0, 1, 1);
	// }else if (type == "INSURED") {
	// 	signerRule = new SignRule_KeyWord("SZT001", 3, 0, 1, 1);
	// }else if (type == "COPY") {
	// 	signerRule = new SignRule_KeyWord("投保人显示位置", 3, 0, 1, 1);
	// }
    // let signerRule = new SignRule_KeyWordV2("签名算法",50,0,1,1);
    /**
     * 根据关键字定位签名位置
     * 
     * @param keyWord
     *            关键字字面值
     * @param xOffset
     *            X轴偏移量，适配关键字和规则
     * @param yOffset
     *            Y轴偏移量，适配关键字和规则
     * @param pageNo
     *            签名在PDF中的页码，第几页查找关键字，正数为正序，当是负数为倒序
     * @param KWIndex
     *            KWIndex 第几个关键字
     * @constructor
     */
    // let signerRule = new SignRule_KeyWord("签名算法",100,100,1,1);
    /**
     * 关键字定位方式，寻找PDF中的关键字，根据关键字位置放置签名图片
     * 
     * @param keyword
     *            关键字
     * @param keyWordAlignMethod
     *            签字图片和关键字位置关系：等于0时，签字图片和关键字矩形重心重合
     *            等于1时，签字图片位于关键字正下方，中心线对齐；等于2时，签字图片位于关键字正右方，中心线对齐；
     *            等于3时，签字图片左上角和关键字右下角重合，可能额外附加偏移量，详见构造函数的offset参数
     * @param keyWordOffset
     *            当keyWordAlignMethod非零时，额外附加的偏移量，单位pt
     * @param pageNo
     *            寻找关键字的PDF起始页码
     * @param KWIndex
     *            KWIndex 第几个关键字
     * @constructor
     */

    let signatureConfig = new SignatureConfig(signer,signerRule);
//                   1:时间在上、2：时间在下、3：时间在右
//                let timeTag = new TimeTag(1,"yyMMdd hh:mm;ss");
//                signatureConfig.timeTag = timeTag;
    signatureConfig.singleWidth = 100;
    signatureConfig.singleHeight = 100;
    if(_this.type == 'APPNT' || _this.type == 'COPY') {
        signatureConfig.title = "请投保人"+_qname+"手写签名";
    }else if(_this.type == 'INSURED') {
        signatureConfig.title = "请被保人"+_qname+"手写签名";
    } else if(_this.type == 'CCB') {
        signatureConfig.title = "请甲方"+_qname+"手写签名";
    }
    signatureConfig.penColor = "#000000";
    signatureConfig.isTSS = false;//是否开始时间戳服务
    signatureConfig.signatureImgRatio = 5.0;
    signatureConfig.nessesary = false;//是否为必须签名
    signatureConfig.isdistinguish = false;
    signatureConfig.ocrCapture = ocrCapture;
    let res = apiInstance.addSignatureObj(context_id,signatureConfig);
    if(res) {
        // _this.$vux.alert.show("签名组件ID"+context_id+"注册成功！");
        return res;
    }
    else {
        _this.$vux.alert.show("签名组件ID"+context_id+"注册失败！");
        return res;
    }
}

//添加批签签名框
function testAddCommentObj(objId) {

    let context_id = objId;
    let signer = new Signer(_qname, _qid);
    // let signerRule = new SignRule_KeyWord("抄录显示位置", 3, 0, 1, 1); // 手机

    let signerRule = new SignRule_KeyWord("柜面受理人员签字",1,0,1,1);

    let commentConfig = new CommentConfig(signer,signerRule);
    commentConfig.commitment = "本人已阅读保险条款、产品说明书和投保提示书，了解本产品的特点和保单利益的不确定性。";
    commentConfig.mass_word_height = 40;
    commentConfig.mass_word_width = 40;
    commentConfig.mass_words_in_single_line = 22;
    commentConfig.penColor = "#000";
    commentConfig.nessesary = false;
    commentConfig.isdistinguish = false;
    commentConfig.ocrCapture = ocrCapture;
    let res = apiInstance.addCommentObj(context_id,commentConfig);
    if(res) {
        // _this.$vux.alert.show("批注组件ID"+context_id+"注册成功！");
        return res;
    }
    else {
        _this.$vux.alert.show("批注组件ID"+context_id+"注册失败！");
        return res;
    }
}


//demo总入口
function anySign(dataLst) {
    
    _this = dataLst[0];  // this对象
    channel = dataLst[1]; // 信手书渠道唯一标识
    _qname = dataLst[2]; // 当前签名/批注人姓名
    _qid = dataLst[3];      // 当前签名/批注人ID
    _policy_code = dataLst[4]; // 发送给ca签章服务器的唯一标识
    _sign_type = dataLst[5]; // 签名类型

    let res;
    
    //识别回调接口
    let identify_callback = function(errCode){
        if(errCode == SUCCESS){
            return;
        }
          if(errCode == DATA_CANNOT_PARSED) {
            _this.$vux.alert.show("输入数据项无法解析！");

        } else if(errCode == SERVICE_SYSTEM_EXCEPTION) {
            _this.$vux.alert.show("服务系统异常错误！");

        } else if(errCode == RECOGNITION_RESULT_EMPTY) {
            _this.$vux.alert.show("识别结果为空！");

        } else if(errCode == CONNECTION_SERVICE_TIMEOUT) {
            _this.$vux.alert.show("连接识别服务超时！");

        } else if(errCode == CONNECTION_RECOGNITION_EXCEPTION) {
            _this.$vux.alert.show("连接识别服务异常！");

        } else if(errCode == RECOGNITION_FALSE) {
            _this.$vux.alert.show("书写错误！");

        }else{
            alert(errCode);
        }
    }
    // 成功回调函数
    let callback = function(context_id,context_type,val) {
        document.getElementById('result').value = ""; // 清空   
        // document.body.style.overflow = "auto";   //滚动条显示
        if(context_type == CALLBACK_TYPE_SIGNATURE) {
            // 签名回显
            // console.log(_this.type);
            if(_this.isCompleteSelect){
                // console.log("come on!")
                _this.$refs.transfer.setCom();
                _this.isCompleteSelect = false;
            } 
            if(_this.type=="APPNT") {
                // 签名完成后上传图片的方法。。。。。。
                _this.appnt_sign_result =  "data:image/png;base64," + val;

                testGenData(); // 生成并存储加密数据包
                _this.isAppntSign=true;
                _this.appntSignResult=document.getElementById('result').value;
                // console.log(document.getElementById('result').value);
                _this.sign_cpt = true; 
                _this.signUploadReqDTO.sign_ca = _this.appntSignResult;
                _this.signUploadReqDTO.sign1 =  val;

                uploadSign(_this);                     
      
              }else if (_this.type=="INSURED") {
                _this.insured_sign_result = "data:image/png;base64," + val;

                testGenData(); // 生成并存储加密数据包
                // console.log(document.getElementById('result').value);     
                _this.isInsuredSign=true; // 页面绑定标记
                _this.insuredSignResult=document.getElementById('result').value;
                _this.sign_cpt = true;
                _this.signUploadReqDTO.sign_ca = _this.insuredSignResult;
                _this.signUploadReqDTO.sign1 =  val;
                uploadSign(_this);


      
              }else if(_this.type =="AGENT"){
                    // 签名完成后上传图片的方法。。。。。。
                    _this.appnt_sign_result =  "data:image/png;base64," + val;

                    testGenData(); // 生成并存储加密数据包
                    _this.isAppntSign=true;
                    _this.appntSignResult=document.getElementById('result').value;
                    _this.sign_cpt = true;
                    _this.signUploadReqDTO.sign_ca = _this.appntSignResult;
                    _this.signUploadReqDTO.sign1 =  val;
                    uploadSign(_this);
                }else if(_this.type == "RECEIPT"){
                    // 签名完成后上传图片的方法。。。。。。
                    _this.appnt_sign_result =  "data:image/png;base64," + val;

                    testGenData(); // 生成并存储加密数据包
                    _this.isAppntSign=true;
                    _this.appntSignResult=document.getElementById('result').value;
                    // _this.sign_cpt = true;
                    _this.signUploadReqDTO.sign_ca = _this.appntSignResult;
                    _this.signUploadReqDTO.sign1 = val ;
                    uploadSign(_this); 
                   
              } else if( _this.type == "CCB") {
                  // 签名完成后上传图片的方法。。。。。。
                  _this.appnt_sign_result =  "data:image/png;base64," + val;

                  testGenData(); // 生成并存储加密数据包
                  _this.isAppntSign=true;
                  _this.appntSignResult=document.getElementById('result').value;
                  // _this.sign_cpt = true;
                  _this.signUploadReqDTO.sign_ca = _this.appntSignResult;
                  _this.signUploadReqDTO.sign1 = val ;
                //   uploadSign(_this);
              }
        } else if(context_type == CALLBACK_TYPE_COMMENTSIGN) {
            // 批注回显
            _this.appnt_commons_result =  "data:image/png;base64," + val; // 展示批注信息
            // testGenData(); // 生成并存储加密数据包
            // _this.appntCommentResult = document.getElementById('result').value; // 传值
            // console.log(document.getElementById('result').value); 
            _this.isCommons=true; // 展示批注结果的标记            
        }    
 
    };//测试回调，将回调数据显示
  
    //设置签名算法，默认为RSA，可以设置成SM2
    EncAlgType.EncAlg = "SM2";

    apiInstance = new AnySignApi();
    res = apiInstance.initAnySignApi(callback,channel);

    if(!res){
       _this.$vux.alert.show("信手书插件初始化失败， 请重试或联系管理员！");
    }else{

    }
    ////////////////////////////////////////////////

    setIdentifyCallBack(identify_callback);
    
    ///////////////////////////////////////////////

    //注册单字签字对象20
    res = testAddSignatureObj(20);
    if(!res){
        _this.$vux.alert.show("签名组件初始化失败， 请重试或联系管理员！");
        return;
    }else{

    }

    res = testAddCommentObj(30);
    if(!res){
        _this.$vux.alert.show("批注组件初始化失败， 请重试或联系管理员！");
        return;
    }else{

    }

    res = apiInstance.commitConfig();
    if(!res){
        _this.$vux.alert.show("Init ALL 提交配置失败");
    }

    ////////////////////////////////////////////////
    
    testSetTemplateData();
    isReadyToUpload();

}

function isReadyToUpload(){
    // console.log("testIsReadyToUpload :" + apiInstance.isReadyToUpload());
}

// 生成签名加密数据
function testGenData() {
    let res = document.getElementById('result');

    try{
        res.value = apiInstance.getUploadDataGram();
        // _this.$vux.alert.show("value"+res.value);
    }
    catch(err)
    {
        _this.$vux.alert.show(err);
    }
}

//弹出签名框签名
function popupDialog(context_id) {
    if(!apiInstance){
        _this.$vux.alert.show("信手书接口没有初始化");
        return;
    }
    switch (apiInstance.showSignatureDialog(context_id)) {
        case RESULT_OK:
            break;
        case EC_API_NOT_INITED:
            _this.$vux.alert.show("信手书接口没有初始化");
            break;
        case EC_WRONG_CONTEXT_ID:
            _this.$vux.alert.show("没有配置相应context_id的签字对象");
            break;
    }
}

function setIdentifyCallBack(callback){
    if(!apiInstance){
        alert("信手书接口没有初始化");
        return;
    }
    apiInstance.setIdentifyCallBack(callback);
}

//弹出批注签名框
function commentDialog(context_id) {

    if(!apiInstance){
        _this.$vux.alert.show("信手书接口没有初始化!");
        return;
    }
    switch (apiInstance.showCommentDialog(context_id)) {
        case RESULT_OK:
            break;
        case EC_API_NOT_INITED:
        _this.$vux.alert.show("信手书接口没有初始化!");
            break;
        case EC_WRONG_CONTEXT_ID:
        _this.$vux.alert.show("没有配置相应context_id的签字对象,请联系管理员!");
            break;
        case EC_COMMENT_ALREADY_SHOW:
        _this.$vux.alert.show("批注签名框已弹出，请勿重复操作！");
    }

}

//获取签名api版本信息
function testGetVersion() {
    _this.$vux.alert.show(apiInstance.getVersion());
}

//获取设备操作系统信息
function testGetOsInfo() {
    _this.$vux.alert.show(apiInstance.getOSInfo());
    _this.$vux.alert.show(navigator.userAgent);
    _this.$vux.alert.show(window.__wxjs_is_wkwebview);
}

//jane
function testAddEvidence(result) {
    _this.$vux.alert.show(apiInstance.addEvidence(20,result,DataFormat.IMAGE_JPEG,BioType.PHOTO_SIGNER_IDENTITY_CARD_BACK,0));
}

function uploadSign(that) {
     console.log("上传签名图片信息请求",that.signUploadReqDTO);
    that.postRequest(
        that.GLOBAL.API_URLS.shareUploadSign,
        that.signUploadReqDTO    
       ).then(reps => {
        // _this.$vux.loading.hide(); // 隐藏     
         console.log("签名上传返回reps", reps);
         if (reps.data.resultCode == 1) {
           // 处理成功后处理
           that.$vux.alert.show("签名成功!");
        //    console.log(reps);
         } else {
           //数据有问题   在页面提示
           that.$vux.alert.show("签名上传成功，请您重新操作签名！");
         }
         
       });

}

function echoSign(that) {
     console.log("查询签名图片信息请求",that.signEchoReqDTO);
    that.postRequest(
        that.GLOBAL.API_URLS.shareEchoSign,
        that.signEchoReqDTO
       ).then(reps => {     
         if (reps.data.resultCode == 1) {
           // 处理成功后处理
           that.signDataDTO = reps.data.object;

          console.log("查询签名图片信息结果",that.signDataDTO.list);
           var signs = that.signDataDTO.list;

           for(var i = 0;i<signs.length;i++) {
               let signResult = signs[i].sign1;
               if(!signResult) { // 非空校验
                 continue;
               }

               if(signResult.indexOf('data:image') == -1) {
                 //判断是否有这样的头部
                 signResult = 'data:image/png;base64,' +  signResult;
                }
               signResult = signResult.replace(/\r|\n/g, '').replace('data:image/jgp', 'data:image/jpeg'); // 替换ios与部分机型不兼容问题
               
               if(signs[i].sign_type == "_OPS-NB-P00002_1"){ // 回显投保人签名信息
                 that.isAppntSign = true;
                 that.appntSignResult = signs[i].sign_ca;
                 that.appnt_sign_result = signResult; 
                 that.opinionAndExplain = signs[i].content;
                 that.commonValue = [signs[i].choice];
                 that.sign_cpt = true;
               } else if(signs[i].sign_type == "_OPS-NB-P00002_2") {
                    that.isInsuredSign = true;
                    that.insuredSignResult = signs[i].sign_ca;
                    that.insured_sign_result = signResult; 
               } else if(signs[i].sign_type == "_OPS-NB-P00001_1") {
                    that.isAppntSign = true;
                    that.sign_cpt = true;
                    that.appntSignResult = signs[i].sign_ca;
                    that.appnt_sign_result = signResult; 
               }    
           }

         } else {
           //数据有问题   在页面提示
           that.$vux.alert.show("回显失败，请稍后重试！");
           that.showLoading = false;
         }  

       });

}

function echoImg(that) {
    that.imgEchoReqDTO.flag="33";
    that.imgEchoReqDTO.serialNumber=that.serialNumber;
    console.log("图片回显请求", that.imgEchoReqDTO);
    that.postRequest(
        that.GLOBAL.API_URLS.shareEchoImg,
        that.imgEchoReqDTO
    ).then(reps => {    
        console.log("图片回显", reps);
        if (reps.data.resultCode == 1) {
            // 处理成功后处理      
            let pictures = reps.data.object.pictures;
            for(let key in pictures) {
                let imgInfo={};
                imgInfo.name=pictures[key].filename.split(".")[0];
                imgInfo.base64="data:image/png;base64,"+pictures[key].remark;
                that.imgList.push(imgInfo);
            }
        } else {
        //数据有问题   在页面提示
        console.log("没有获取到图片信息，回显失败!");
        }
    });
}


export {

    anySign, isReadyToUpload, popupDialog, commentDialog, uploadSign, echoSign, echoImg

}
