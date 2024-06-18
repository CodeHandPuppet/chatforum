import { RouteRecordRaw } from "vue-router";


const constantRoutes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'home',
        component: () => import('@/layout/homePage/index.vue'),
        redirect: '/forum',
        children: [
            {
                path: '/messageList',
                name: 'MessageList',
                component: () => import('@/views/homePage/messageList.vue'),

            },
            {
                path: '/chatList',
                name: 'ChatList',
                component: () => import('@/views/homePage/chatList.vue')
            }, {
                path: '/forum',
                name: 'Forum',
                component: () => import('@/views/forum/index.vue')
            },
            {
                path: '/notification',
                name: 'Noticication',
                component: () => import('@/views/notification/index.vue')
            }, {
                path: '/genericPage',
                name: 'genericPage',
                component: () => import('@/views/index.vue')
            },
            {
                path: '/editArticle',
                name: 'EditArticle',
                component: () => import('@/views/article/addOrEdit.vue')
            }
        ]
    },
    // 参观文章
    {
        path: '/article',
        name: 'Article',
        component: () => import('@/views/article/index.vue')
    },
    // 登录界面
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/login/index.vue'),
    },
    // 用户的主页
    {
        path: '/userHome',
        name: 'UserHome',
        component: () => import('@/layout/userHome/index.vue'),
        redirect: '/userHome/myArticle',
        children: [
            {
                path: '/userHome/myArticle',
                name: 'MyArticle',
                component: () => import('@/views/userHome/myarticle.vue')
            },
            {
                path: '/userHome/myCollect',
                name: 'MyCollect',
                component: () => import('@/views/userHome/mycollect.vue')
            },
            {
                path: '/userHome/myLike',
                name: 'MyLike',
                component: () => import('@/views/userHome/mylike.vue')
            }
        ]
    }

]


const asyncRoutes: RouteRecordRaw[] = [
    {
        path: '/admin',
        name: 'Admin',
        component: () => import('@/layout/homePage/index.vue'),
        children: [
            {
                path: '/admin/user',
                name: 'UserAdmin',
                component: () => import('@/views/admin/userAdmin.vue')
            },
            {
                path: '/admin/sort',
                name: 'SortAdmin',
                component: () => import('@/views/admin/sortAdmin.vue')
            },
            {
                path: '/admin/address',
                name: 'AddressAdmin',
                component: () => import('@/views/admin/addressAdmin.vue')
            }
            , {
                path: '/admin/webInfo',
                name: 'WebInfoAdmin',
                component: () => import('@/views/admin/webInfoAdmin.vue')
            },
            {
                path: '/admin/notifications',
                name: 'NotificationsAdmin',
                component: () => import('@/views/admin/notificationAdmin.vue')
            }

        ]
    }
]


export { constantRoutes, asyncRoutes }