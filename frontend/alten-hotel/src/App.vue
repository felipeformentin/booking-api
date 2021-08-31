<template>
  <div id="app">
    <Main/>
    <Calendar/>
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
}
</style>

