<template>
  <div id="app">
    <Main/>
    <Calendar/>
    <BookCard/>
  </div>
</template>

<script>
import Main from './components/Main.vue'
import Calendar from './components/Calendar.vue'
import BookCard from './components/BookCard.vue'


export default {
  name: 'App',
  components: {
    Main,
    Calendar,
    BookCard
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
  background-color: aliceblue;
  height: 100vh;
}
</style>
