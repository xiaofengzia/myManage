document.write("<script language=javascript src=\"" + process.env.H5_ROOT +  "/static/js/libs/aes.js\" charset=\"utf-8\"></script>");



/**
 * 加密（需要先加载lib/aes/aes.min.js文件）
 * @param word
 * @returns {*}
 */
function encrypt(word){
    var key = CryptoJS.enc.Utf8.parse("abcdefgabcdefg12");
    var srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
    return encrypted.toString();
}

/**
 * 解密
 * @param word
 * @returns {*}
 */
function decrypt(word){
    var key = CryptoJS.enc.Utf8.parse("abcdefgabcdefg12");
    var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}

/**
 * 银行账号隐藏
 */
function hideCrype(type, number) {
    let star= "";
    if(type=="YHK" && number.length>7) {
        for(let i=0 ;i<number.length-7;i++){
            star+="*";
        }
        number = number.substring(0, 3) + star + number.substring(number.length-4);
    }

    return number;

}

export {
    encrypt,decrypt,hideCrype
}