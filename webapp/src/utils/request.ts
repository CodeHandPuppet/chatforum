import { pinia } from "@/plugins/pinia";
import { useUserStore } from "@/store/userStore";
import axios from "axios";
import { ElMessage } from "element-plus";

const request = axios.create({
    baseURL: '/api',
    timeout: 5000,

});

request.defaults.headers.post["Content-Type"] = "application/json";


request.interceptors.request.use((config) => {
    // 传递出token
    const userStore = useUserStore(pinia);
    if (userStore.token) {
        config.headers.token = userStore.token
    }

    return config;
})

// 
request.interceptors.response.use((res) => {
    // 成功回调数据
    return res.data;
}, (err) => {
    // 失败回调
    let msg = '';
    let status = err.response.status;
    switch (status) {
        case 401:
            msg = "token过期";
            break;
        case 403:
            msg = '无权访问';
            break;
        case 404:
            msg = "请求地址错误";
            break;
        case 500:
            msg = "不允许此操作";
            break;
        default:
            msg = "无网络";
    }
    ElMessage({
        type: 'error',
        message: msg
    })
    return Promise.reject(err);
})

export default request;