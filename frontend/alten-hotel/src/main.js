import Vue from 'vue'
import App from './App.vue'
import VCalendar from 'v-calendar';
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.use(VCalendar)
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

new Vue({
  render: h => h(App),
}).$mount('#app')
