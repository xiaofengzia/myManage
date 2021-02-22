let cutDown = 60;
function setTime(btn) {
    if (cutDown == 0) {
        btn.isDisabled = false;
        btn.butext = "获取验证码";
        cutDown = 60;
        return;
    } else {
        btn.isDisabled = true;
        btn.butext = "重新发送(" + cutDown + ")";
        cutDown--;
    }
    setTimeout(function () {
        setTime(btn)
    }, 1000)
}


export {
    setTime
  }