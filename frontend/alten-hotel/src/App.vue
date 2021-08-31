<template>
  <div id="app">
    <img id="logo" alt="Vue logo" src="./assets/logo.png">
    <Main msg="Welcome to Alten Hotel"/>
    <Calendar msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import Main from './components/Main.vue'
import Calendar from './components/Calendar.vue'

export default {
  name: 'App',
  components: {
    Main,
    Calendar
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
