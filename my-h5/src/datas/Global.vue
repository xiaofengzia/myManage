<script>
  //h5页面domain
  const H5_DOMAIN = process.env.H5_DOMAIN;
  //h5页面根路径
  const H5_ROOT = process.env.H5_ROOT;
  //api接口domain
  const API_DOMAIN = process.env.API_DOMAIN;
  //api接口根路径
  const API_ROOT = process.env.API_ROOT;

 
  //api接口地址汇总
  const API_URLS = {
    login: "/login",//登录
    graphValidateCode: "/xfz/graphValidateCode/", //图形验证码

  }

  

  export default {
    H5_DOMAIN,
    H5_ROOT,
    API_DOMAIN,
    API_ROOT,
    API_URLS,
    
  };
</script>

