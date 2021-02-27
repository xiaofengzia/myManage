<template>
  <div>
        <van-row type="flex" justify="center" align="center" style="height:300px">
            <van-col span="8" style="padding-left:20px">
                相册封面
            </van-col>
            <van-col span="8" >
                <van-image  width="6rem" v-if="uploadFlag"
                    height="6rem"
                    fit="contain" 
                    :src="photoParam.url">
                </van-image>
                <van-image  width="6rem" v-if="!uploadFlag"
                    height="6rem"
                    fit="contain" 
                    :src="GLOBAL.H5_ROOT + '/static/image/home/created.png'">
                </van-image>
            </van-col>
            <van-col span="8" style="padding-left:20px">
                <van-uploader :after-read="afterRead" :max-size="500*1024" @oversize="onOversize" />
            </van-col>
        </van-row>
        <van-cell-group>
            <van-field v-model="photoParam.name" label="相册名称：" placeholder="请输入相册名" />
        </van-cell-group>
        <div style="height:160px"></div>
        <van-row type="flex" justify="center" align="center">
            <van-button round  plain hairline 
                @click="save()"
                style="width:80%;"
                type="primary" 
                size="large" 
                :icon="GLOBAL.H5_ROOT + '/static/image/home/save.png'">
                保存
            </van-button>
        </van-row>
  </div>
</template>
<script>

export default {
  data() {
    return {
      uploadFlag:false,
      photoParam:{},
    };
  },
  created() {
      if(sessionStorage.getItem("modifyPhoto")){
          this.uploadFlag = true;
          this.photoParam = JSON.parse(sessionStorage.getItem("modifyPhoto"));
      }
  },
  methods: {
    afterRead(file){
        console.log("文件",file)
        const param = new FormData();
        param.append("file", file.file);
        this.postRequest(this.GLOBAL.API_URLS.uploadImage, param).then(ResData => {
            console.log("上传",ResData)
            if(ResData.data.resultCode=='1'){
                this.photoParam.ossId=ResData.data.object.id;
                this.photoParam.url = ResData.data.object.url;
                this.uploadFlag=true;
                this.$forceUpdate()
            }else{
                this.$dialog.alert({message:  "系统异常，请稍后重试"});
            }
        })
    },
    onOversize(){
        this.$dialog.alert({message:  "文件大小不能超过5M"});
    },
    save(){
        if(!this.photoParam.ossId){
            this.$dialog.alert({message:  "请上传相册封面"});
            return;
        }
        if(!this.photoParam.name){
            this.$dialog.alert({message:  "请填写相册名称"});
            return;
        }
        let userInfo = this.$store.getters.getUserInfo;
        this.photoParam.accountNum=userInfo.accountCode;
        this.postRequest(this.GLOBAL.API_URLS.insertOrUpdatePhotoAlbum, this.photoParam).then(ResData => {
            console.log("上传",ResData)
            if(ResData.data.resultCode=='1'){
                this.$router.push({ path: "/home" });
            }else{
                this.$dialog.alert({message:  "系统异常，请稍后重试"});
            }
        })
    }
  }
};
</script>
<style>

</style>

