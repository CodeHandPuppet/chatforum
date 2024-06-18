import { createApp } from 'vue'
// 全局字体引用
import '@/assets/font/font.css'
// 全局样式引用
import './assets/reset/style.css'
import '@fortawesome/fontawesome-free/css/all.min.css';
// element 样式引用
import 'animate.css';

import App from './App.vue'
import setupPlugins from './plugins'
import router from './router'
import installGlobalComponents from './components'


const app = createApp(App)

setupPlugins(app)
app.use(router)


installGlobalComponents(app)


app.mount('#app')
