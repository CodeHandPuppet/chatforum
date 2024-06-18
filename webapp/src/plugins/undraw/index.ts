

import UndrawUi from 'undraw-ui'
import 'undraw-ui/dist/style.css'
import { App } from 'vue'
const setupUndraw = (app: App) => {
    app.use(UndrawUi)
}


export default setupUndraw;