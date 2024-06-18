import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import { App } from 'vue';

const setupMavonEditor = (app: App) => {
    app.use(mavonEditor);
}

export default setupMavonEditor;