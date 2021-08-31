<template>
  <div class="main">
    
    <b-jumbotron id="cover" header="Alten Hotel" text-variant="dark"/>
    <h1>Check available dates for your reservation:</h1>
  </div>
</template>

<script>
export default {
  name: 'Main',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#cover {
  background-image: url("../assets/big_pine.jpeg");
  background-size: cover;
  background-repeat: no-repeat;
}
</style>
