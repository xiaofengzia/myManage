<template>
  <div>
    <van-row type="flex" justify="center" align="center" class="back">
      <van-col span="8" class="specTop specLeft">喜好疯子</van-col>
      <van-col span="8" class="specTop">我的相册</van-col>
      <van-col span="8" class="specTop specRight">退出登录</van-col>
    </van-row>
    <div >
    <van-row type="flex" justify="center" align="center" class="backBody" v-for="(item,index) in list" :key="index">
        <van-col span="8" class="bodyItem">
          <van-image  width="6rem"
            height="6rem"
            fit="contain" 
            :src="GLOBAL.H5_ROOT + '/static/image/home/created.png'">
          </van-image>
        </van-col>
        <van-col span="8" class="bodyItem">
            &#12288;相册名称:{{item.name}}<br/>
            <van-button round  plain hairline :icon="GLOBAL.H5_ROOT + '/static/image/home/modified.png'" type="primary" size="small">修改</van-button>
            <van-button round  plain hairline :icon="GLOBAL.H5_ROOT + '/static/image/home/delete.png'" type="danger" size="small">删除</van-button>
        </van-col>
    </van-row>
    <van-row type="flex" justify="center" align="center" class="backBody">
        <van-col span="8" class="bodyItem" style="">
          <van-image  width="6rem"
            height="6rem"
            fit="contain" 
            :src="GLOBAL.H5_ROOT + '/static/image/home/created.png'"
            @click="created()">
          </van-image>
        </van-col>
        <van-col span="8" class="bodyItem" style="">
          创建相册
        </van-col>
    </van-row>
    </div>
  </div>
</template>
<script>

export default {
  data() {
    return {
      list:[
       
      ]
    };
  },
  created() {
   
  },
  methods: {
    created(){
      this.$router.push({ path: "/created" });
    }
  }
};
</script>
<style>
.back{
  background-color:#11EEEE;
  height:50px;
}
.specTop{
  text-align: center;
}
.specLeft{
  padding-left:10px; 
  text-align: left;
  font-size: 10px;
}
.specRight{
  padding-right:15px; 
  text-align: right;
  font-size: 10px;
  color:#EF21C6;
}
.backBody{
  background-color:#FFCCD6;
}
.bodyItem{
  /* padding-top:20px;
  height:150px;
  padding-left:20px;  */
  
}
</style>

