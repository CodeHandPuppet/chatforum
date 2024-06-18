import { App } from "vue"
import { setupElementPlus } from "./elementPlus"
import setupPinia from "./pinia"
import setupMavonEditor from "./markdown"
import setupUndraw from "./undraw"
import setupAntDesign from "./antDesign"

const setupPlugins = (app: App) => {
    setupUndraw(app);
    setupElementPlus(app);
    setupAntDesign(app);
    setupPinia(app);
    setupMavonEditor(app);
}
export default setupPlugins