import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        // 保存登录信息
        userInfo: {},
        
        shareUrl: ''
    },
    actions: {
        signOut({commit}) {
            commit('SIGNOUT');
        },
        setUserInfo({commit}, userInfo) {
            commit('SETUSERINFO', userInfo);
        },
        
        setShareUrl({commit}, shareUrl) {
            commit('SETSHAREURL', shareUrl);
        },
       
        
    },
    mutations: {
        // 退出登录
        SIGNOUT(state) {
            state.userInfo = {};
            localStorage.removeItem(process.env.USERINFO_CHANNEL);
        },
        // 设置登录用户信息
        SETUSERINFO(state, data) {
            state.userInfo = data;
            localStorage.setItem(process.env.USERINFO_CHANNEL, JSON.stringify(data))
        },

      
        SETSHAREURL(state, data) {
            //如果是ios系统的，缓存第一次进来时的页面地址， 如果不是的话，每次都进行缓存
            let isIos = Vue.prototype.system.isIos();
            console.log(isIos?"苹果机":"安卓机");
            if(isIos && !!state.shareUrl){
                console.log("已缓存:", state.shareUrl)
                return;
            }
            state.shareUrl = data;
        },

    },
    getters: {
        getUserInfo(state) {
            if (Object.keys(state.userInfo).length == 0)
                var o = JSON.parse(localStorage.getItem(process.env.USERINFO_CHANNEL));
            if (!!o) {
                state.userInfo = o;
            }
            return state.userInfo;
        },
       
        getShareUrl(state) {
            return state.shareUrl;
        },
        
    }
})

export default store;
