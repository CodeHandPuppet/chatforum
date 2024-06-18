import { App } from "vue";



const componentName = ['chatScreen', 'chatSearch']
const installGlobalComponents = (app: App) => {
    const result = import.meta.glob("./components/*.vue", { eager: true });


    componentName.forEach(name => {
        Object.keys(result).forEach(key => {
            if (key.includes(name)) {
                app.component(name, (result[key] as any).default)
            }
        })
    })


}

export default installGlobalComponents