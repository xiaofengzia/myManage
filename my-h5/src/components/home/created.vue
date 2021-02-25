<template>
  <div>
      <van-uploader :after-read="afterRead" />
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
    afterRead(file){
        console.log("文件",file)
        const param = new FormData();
        param.append("file", file.file);
        this.postRequest(this.GLOBAL.API_URLS.uploadImage, param).then(ResData => {
            console.log("上传",ResData)
        })
    }
  }
};
</script>
<style>

</style>

