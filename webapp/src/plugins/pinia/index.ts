import { createPinia } from 'pinia'
import { App } from 'vue'
export const pinia = createPinia()

const setupPinia = (app: App) => {


    app.use(pinia)

}


export default setupPinia