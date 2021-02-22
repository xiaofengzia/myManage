 <template>
  <div id="app">
    <transition>
      <router-view></router-view>
    </transition>
  </div>
</template>
<script>
  export default {
    name: 'app',
    created() {  
        //第三方页面或初次登录添加加载动画，避免白屏，解决缓存后可删除div及其样式 -->
      document.body.removeChild(document.getElementById('Loading'))   
    }
  }
</script>

<style lang="less">
@import '~vux/src/styles/1px.less';
@import '~vux/src/styles/reset.less';
@import "./style/style.less";
@import "./style/allStyle.less";
@import "./style/org-tree.less";
@import "./style/organization.less";

// @import "./components/test/orgchart.css";
@import "./style/vendor/font-awesome.min.css";
</style>
