<template>
  <div>
    <van-row type="flex" justify="center" align="center" class="back">
      <van-col span="8" class="specTop specLeft">喜好疯子</van-col>
      <van-col span="8" class="specTop">我的相册</van-col>
      <van-col span="8" class="specTop specRight">退出登录</van-col>
    </van-row>
    <div>
      <van-row
        type="flex"
        justify="center"
        align="center"
        class="backBody"
        v-for="(item,index) in list"
        :key="index"
      >
        <van-col span="8" class="bodyItem">
          <van-image
            width="6rem"
            height="6rem"
            fit="contain"
            :src="item.url"
          ></van-image>
        </van-col>
        <van-col span="8" class="bodyItem">
          &#12288;相册名称:{{item.name}}
          <br />
          <van-button
            round
            plain
            hairline
            :icon="GLOBAL.H5_ROOT + '/static/image/home/modified.png'"
            type="primary"
            size="small"
            @click="modified(item)"
          >修改</van-button>
          <van-button
            round
            plain
            hairline
            :icon="GLOBAL.H5_ROOT + '/static/image/home/delete.png'"
            type="danger"
            size="small"
            @click="deleted(item)"
          >删除</van-button>
        </van-col>
      </van-row>
      <van-row type="flex" justify="center" align="center" class="backBody">
        <van-col span="8" class="bodyItem" style>
          <van-image
            width="6rem"
            height="6rem"
            fit="contain"
            :src="GLOBAL.H5_ROOT + '/static/image/home/created.png'"
            @click="created()"
          ></van-image>
        </van-col>
        <van-col span="8" class="bodyItem" style>创建相册</van-col>
      </van-row>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      list: []
    };
  },
  created() {
    this.init();
  },
  methods: {
    init(){
      let userInfo = this.$store.getters.getUserInfo;
      this.postRequest(this.GLOBAL.API_URLS.selectPhotoAlbum+"/"+userInfo.accountCode).then(ResData => {
            console.log("上传",ResData)
            if(ResData.data.resultCode=='1'){
                this.list = ResData.data.object;
            }else{
                this.$dialog.alert({message:  "系统异常，请稍后重试"});
            }
        })
    },
    created() {
      this.$router.push({ path: "/created" });
    },
    modified(item){

    },
    deleted(item){

    }
  }
};
</script>
<style>
.back {
  background-color: #11eeee;
  height: 50px;
}
.specTop {
  text-align: center;
}
.specLeft {
  padding-left: 10px;
  text-align: left;
  font-size: 10px;
}
.specRight {
  padding-right: 15px;
  text-align: right;
  font-size: 10px;
  color: #ef21c6;
}
.backBody {
  background-color: #ffccd6;
}
.bodyItem {
  /* padding-top:20px;
  height:150px;
  padding-left:20px;  */
}
</style>

