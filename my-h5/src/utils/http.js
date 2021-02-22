/**
 * http配置
 */
// 引入axios
import axios from 'axios'
import store from './../store'
import router from './../router'
import Vue from 'vue'

// 超时时间
axios.defaults.timeout = 3000000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

// http请求拦截器
axios.interceptors.request.use(
    config => {
        //TODO 请求拦截
        console.log("config:", config);
        Vue.$vux.loading.show({
            text: '加载中...'
        });

        return config
    }, error => {
        console.log("http请求进入拦截器,异常");
        Vue.$vux.loading.hide();
        return Promise.reject(error)
    })

// http响应拦截器
axios.interceptors.response.use(data => {//
    console.log("http响应拦截器", data);
    Vue.$vux.loading.hide();
    return data
}, error => {
    if (error.response) {
        Vue.$vux.loading.hide();
        //TODO 待处理
        switch (error.response.status) {
            case 401:
                /**token未授权或token授权失败，过期等等**/
                // 401 清除token信息并跳转到登录页面
                store.commit(types.LOGOUT);
                route.replace({
                    path: '/',
                    query: {redirect: routers.currentRoute.fullPath}
                });
                break;
            case 404:
                /**未找到页面**/
                // 404 跳转到404页面
                Vue.$vux.toast.show({
                    text: '请求异常',
                    type: 'warn'
                });
                break;
            case 403:
                /**未找到页面**/
                // 403 跳转到登录页面
                store.commit('SIGNOUT');
                router.push({
                    path: '/',
                    query: {redirect: router.currentRoute.fullPath}
                });
                break;
            case 504:
                Vue.$vux.toast.show({
                    text: '网络异常',
                    type: 'warn'
                });
                break;
            case 502:
                Vue.$vux.toast.show({
                    text: '网络异常',
                    type: 'warn'
                });
                break;
        }
    }
    Vue.$vux.loading.hide();
    return Promise.reject(error)
})

let base = '/api'

export const postCommonRequest = (url, params) => {
    // 在请求头添加票证
    return axios({
        method: 'post',
        url: `${url}`,
        data: params
    });
}

export const postRequest = (url, params) => {
    // 在请求头添加票证
    let _Authorization = store.getters.getUserInfo.Authorization;
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        headers: {"Authorization": _Authorization}
    });
}

export const getRequest = (url, params) => {
    // 在请求头添加票证
    let _Authorization = store.getters.getUserInfo.Authorization;
    return axios({
        method: 'get',
        url: `${base}${url}`,
        data: params,
        headers: {"Authorization": _Authorization}
    });
}

export const putRequest = (url, params) => {
    // 在请求头添加票证
    let _Authorization = store.getters.getUserInfo.Authorization;
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params,
        headers: {"Authorization": _Authorization}
    });
}

export const deleteRequest = (url, params) => {
    // 在请求头添加票证
    let _Authorization = store.getters.getUserInfo.Authorization;
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        data: params,
        headers: {"Authorization": _Authorization}
    });
}
