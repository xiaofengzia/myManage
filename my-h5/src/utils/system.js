//地址相关js
let system = {
    isIos: function () {
        const u = navigator.userAgent;
        const flag = u.indexOf("iPhone") > -1 || u.indexOf("Mac OS") > -1;
        return flag;
    },
    isWeiXin: function() { // 微信？
        var ua = window.navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            return true; // 是微信端
        }
        return false;
    }
}


export {
    system
}