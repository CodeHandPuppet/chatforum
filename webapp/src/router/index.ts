import { RouteRecordRaw, createRouter, createWebHistory, createWebHashHistory } from 'vue-router';
import { constantRoutes, asyncRoutes } from './routes';

import { useUserStore } from "@/store/userStore";
import { pinia } from "@/plugins/pinia";
import { getToken } from '@/utils/token';

const userStore = useUserStore(pinia);

// const routes = userStore.type == 'admin' ? [...constantRoutes, ...asyncRoutes] : [...constantRoutes];

const router = createRouter({
    history: createWebHistory(),
    routes: [...constantRoutes, ...asyncRoutes]
    // routes
})

router.beforeEach(async (to, from, next) => {
    const token = getToken();
    // 用户登录   不能访问 login ,其他路由都可以访问
    if (token) {
        if (to.path == '/login') {
            next({ path: from.path })
        }

        else {
            if (userStore.username) {
                next()
            }
            else {
                await userStore.getUserInfo()
                next()
            }
        }
    }
    // 用户未登录  只能访问 login
    else {
        if (to.path == '/login') {
            next()
        }
        else {
            next({ path: '/login', query: { redirect: to.path } })
        }
    }
})

export default router