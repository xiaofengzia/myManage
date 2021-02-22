<template>
  <div class="loginMian">
    <group class="mianGroup">
      <div style="width:250px;height:250px;margin:50px auto 20px;">
        <x-img
          :src="this.GLOBAL.H5_ROOT + '/static/image/head.jpg'"
          class="login logo_img"
        />
      </div>
      <div class="hrDiv login cellboxSpel">
        <hr />
      </div>
      <div>
        <cell-box class="login cellboxSpel">
          <flexbox>
            <flexbox-item :span="1">
              <x-img width="22" :src="this.GLOBAL.H5_ROOT + '/static/image/agent/login/user_.png'" />
            </flexbox-item>
            <flexbox-item :span="7">
              <x-input
                class="spelcellbox"
                placeholder="请输入工号"
                v-model="agentCommonReqDTO.accountCode"
              ></x-input>
            </flexbox-item>
          </flexbox>
        </cell-box>
        <div class="hrDiv login cellboxSpel">
          <hr />
        </div>
        <cell-box class="login cellboxSpel">
          <flexbox>
            <flexbox-item :span="1">
              <x-img
                width="22"
                :src="this.GLOBAL.H5_ROOT + '/static/image/agent/login/password_.png'"
              />
            </flexbox-item>
            <flexbox-item :span="7">
              <x-input
                class="spelcellbox"
                type="password"
                placeholder="请输入密码"
                v-model="agentCommonReqDTO.password"
              ></x-input>
            </flexbox-item>
          </flexbox>
        </cell-box>
        <div class="hrDiv login cellboxSpel">
          <hr />
        </div>
        <cell-box class="login cellboxSpel">
          <flexbox>
            <flexbox-item :span="1">
              <x-img
                width="22"
                :src="this.GLOBAL.H5_ROOT + '/static/image/agent/login/yanzheng_.png'"
              />
            </flexbox-item>
            <flexbox-item :span="7">
              <x-input class="spelcellbox" placeholder="验证码" v-model="agentCommonReqDTO.captcha"></x-input>
            </flexbox-item>
            <flexbox-item :span="4">
              <x-img
                class="login authCodeImg spelcellbox"
                :src="craphUrl"
                @click.native="craphValidateCode()"
              />
            </flexbox-item>
          </flexbox>
        </cell-box>
        <div class="hrDiv login cellboxSpel">
          <hr />
        </div>
      </div>
    </group>

    <group class="login_button login_submit" style="border-radius:5px;">
      <x-button type="primary" @click.native="submit">登 录</x-button>
    </group>
    <div class="login footer"></div>
  </div>
</template>
<script>
//利用QS包装data数据
import { md5 } from "vux";
import { validateIdCard } from "@/utils/validateUtil.js";
import qs from "qs";
export default {
  data() {
    return {
      agentCommonReqDTO: {},
      // store添加票证
      userInfo: {},
      //图形验证码地址
      craphUrl: "",
      showMsg: "",
      showMobile: false
    };
  },
  created() {
    let userInfo = this.$store.getters.getUserInfo;
    userInfo.officeFlag = undefined;
    this.$store.commit("SETUSERINFO", userInfo);
    this.craphValidateCode();
  },
  methods: {
    craphValidateCode() {
      var randomToken = this.generateRandom(10);
      this.agentCommonReqDTO.randomToken = randomToken;
      console.log("随机串",randomToken)
      this.craphUrl =
        this.GLOBAL.API_ROOT +
        this.GLOBAL.API_URLS.graphValidateCode +
        randomToken +
        "/" +
        Math.random();
    },
    submit() {
      if (!!!this.agentCommonReqDTO.accountCode) {
        this.$vux.alert.show("请输入工号");
        return;
      }

      if (!!!this.agentCommonReqDTO.password) {
        this.$vux.alert.show("请输入密码");
        return;
      }
      if (!!!this.agentCommonReqDTO.captcha) {
        this.$vux.alert.show("请输入验证码");
        return;
      }
      this.login();
        
    },
    generateRandom(len) {
      var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
      var res = "";
      for (var i = 0; i < len; i++) {
        var id = Math.ceil(Math.random() * 35);
        res += chars[id];
      }
      return res;
    },
    login() {
      
      this.postRequest(this.GLOBAL.API_URLS.login, this.agentCommonReqDTO).then(
        ResData => {
          console.log(ResData);
          
          if (ResData.data.resultCode == 1 || ResData.data.resultCode == 8) {
            // 处理成功后处理
            let userInfo = this.$store.getters.getUserInfo;
            userInfo.Authorization = ResData.data.object.accountCodeSM;
            userInfo.accountCode = this.agentCommonReqDTO.accountCode.replace(
              /\s+/g,
              ""
            );
            
            this.$store.commit("SIGNOUT");
            this.$store.commit("SETUSERINFO", userInfo);
            this.$router.push({ path: "/home" });
          } else {
            this.craphValidateCode();
            //数据有问题   在页面提示
            if (ResData.data.object == 5) {
              this.$vux.alert.show("录入的登陆信息错误，请重新输入！");
            } else if (ResData.data.object == 6) {
              this.$vux.alert.show("图形验证码输入有误，请重新输入！");
            } else {
              this.$vux.alert.show("号码或密码输入错误，请重新输入！");
            }
          }
        }
      );
    }
  }
};
</script>
<style>
html {
  height: 100%;
}
body {
  height: 100%;
}
.weui-cells:after {
  border: none !important;
}
.personnelType .weui-cell_access .weui-cell__ft:after {
  right: 100px;
}
</style>

