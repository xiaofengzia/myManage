<template>
  <div>
    <van-row type="flex" justify="center" align="center" class="back">
      <van-col span="8" class="specTop specLeft" @click="back()">返回</van-col>
      <van-col span="8" class="specTop">{{photoParam.name}}</van-col>
      <van-col span="8" class="specTop specRight"></van-col>
    </van-row>
    <div style="height:635px">
    <van-grid :column-num="3">
        <van-grid-item  v-for="(item,index) in list" :key="index">
            <van-icon name="cross" color="red" 
            style="margin-left:22%;margin-top:-22%;position: fixed;z-index: 999; " 
            @click="deleted(item)"/>
             <van-image
            width="6rem"
            height="6rem"
            fit="contain"
            :src="item.url"
          >
          </van-image>
        </van-grid-item>
         <van-grid-item><van-uploader :after-read="afterRead" :max-size="500*1024" @oversize="onOversize" />
      </van-grid-item>
    </van-grid>
    </div>
    <div class="pageHolder">
      <van-pagination v-model="currentPage" :page-count="totoalPage" mode="simple" @change="changePage"/>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      list: [],
      userInfo:{},
      photoParam:{},
      currentPage:1,
      totoalPage:1,
    };
  },
  created() {
    this.photoParam = JSON.parse(sessionStorage.getItem("selectPhoto"));
    this.userInfo = this.$store.getters.getUserInfo;
    this.init();
  },
  methods: {
    onOversize(){
        this.$dialog.alert({message:  "文件大小不能超过5M"});
    },
    afterRead(file){
        console.log("文件",file)
        const param = new FormData();
        param.append("file", file.file);
        this.postRequest(this.GLOBAL.API_URLS.uploadImage, param).then(ResData => {
            console.log("上传",ResData)
            if(ResData.data.resultCode=='1'){
                var paramPhoto = {};
                paramPhoto.ossId=ResData.data.object.id;
                paramPhoto.type=this.photoParam.id;
                this.postRequest(this.GLOBAL.API_URLS.insertPhoto, paramPhoto).then(ResData => {
                  if(ResData.data.resultCode=='1'){
                    this.init();
                  }else{
                    this.$dialog.alert({message:  "系统异常，请稍后重试"});
                  }
                })
            }else{
                this.$dialog.alert({message:  "系统异常，请稍后重试"});
            }
        })
    },
    changePage(){
      var param = {};
      param.photoAlbumId=this.photoParam.id;
      param.start=this.currentPage;
      param.count=14;
      this.postRequest(this.GLOBAL.API_URLS.selectPhoto,param).then(ResData => {
            console.log("上传",ResData)
            if(ResData.data.resultCode=='1'){
                this.list = ResData.data.object.list;
                if(Number(ResData.data.object.total)<=14){
                   this.totoalPage=1;
                }else if(Number(ResData.data.object.total)%14 != 0){
                  this.totoalPage = Number((Number(ResData.data.object.total)/14).toFixed(0))+1;
                }else{
                   this.totoalPage = (Number(ResData.data.object.total)/14);
                }
            }else{
                this.$dialog.alert({message:  "系统异常，请稍后重试"});
            }
        })
    },
    init(){
      var param = {};
      param.photoAlbumId=this.photoParam.id;
      param.start=0;
      param.count=14;
      this.postRequest(this.GLOBAL.API_URLS.selectPhoto,param).then(ResData => {
            console.log("上传",ResData)
            if(ResData.data.resultCode=='1'){
                this.list = ResData.data.object.list;
                if(Number(ResData.data.object.total)<=14){
                   this.totoalPage=1;
                }else if(Number(ResData.data.object.total)%14 != 0){
                  this.totoalPage = Number((Number(ResData.data.object.total)/14).toFixed(0))+1;
                }else{
                   this.totoalPage = (Number(ResData.data.object.total)/14);
                }
            }else{
                this.$dialog.alert({message:  "系统异常，请稍后重试"});
            }
        })
    },
    back(){
      this.$router.push({ path: "/home" });
    },
    created() {
      this.$router.push({ path: "/created" });
    },
    deleted(item){
      this.$dialog.confirm({
        title: '提示',
        message: '确认删除这张图片吗？',
      })
        .then(() => {
          this.postRequest(this.GLOBAL.API_URLS.deletePhoto,item).then(ResData => {
            console.log("上传",ResData)
            if(ResData.data.resultCode=='1'){
                this.init();
            }else{
                this.$dialog.alert({message:  "系统异常，请稍后重试"});
            }
        })
        })
        .catch(() => {
          // on cancel
        });
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
.pageHolder{
    bottom: 0; 
    z-index: 999; 
}
</style>

