import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'


const Login = () => import('@/components/agent/login')
const Home = () => import('@/components/home/index')


Vue.use(VueRouter)
/**
 *  路由配置
 */
const router = new VueRouter({
    likActiveClass: 'link-active',
    mode: 'history',
    base: process.env.H5_ROOT + "/",
    routes: [
        {
            path: '/',
            name: 'login',
            component: Login,
            meta: {
                title: '登录',
            }
        },
        {
            path: '/home',
            name: 'home',
            component: Home,
            meta: {
                title: '首页',
                requireAuth: true
            }
        },
       
        
    ]
})

router.beforeEach((to, from, next) => {
    
    if (to.meta.requireAuth && !!!store.getters.getUserInfo.Authorization ) {
        next({
            path: '/',
            query: { redirect: to.fullPath }
        });
        return;
    }
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title
    }

    var metaList = document.getElementsByTagName("meta");

    //缓存当前页面路径
    let shareUrl = Vue.prototype.GLOBAL.H5_DOMAIN + Vue.prototype.GLOBAL.H5_ROOT + to.fullPath;
    console.log("当前路由", shareUrl)
    store.commit("SETSHAREURL", shareUrl);

    next();
})

/**
 *  路由出口
 */
export default router
