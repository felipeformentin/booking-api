<template>
  <div id="app">
    <Main />
    <Calendar />
    <b-container v-if="isBookingLoaded" class="mt-5">
      <h1 v-if="bookings.length > 0">Bookings</h1>
      <b-row>
        <b-col v-for="booking in bookings" :key="booking.id">
          <BookingCard :booking="booking" />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import Main from "./components/Main.vue";
import Calendar from "./components/Calendar.vue";
import BookingCard from "./components/BookingCard.vue";
import axios from "axios";

export default {
  name: "App",
  components: {
    Main,
    Calendar,
    BookingCard,
  },
  data() {
    return {
      isBookingLoaded: false,
    };
  },
  created() {
    this.getAllBookings();
  },
  methods: {
    getAllBookings() {
      axios
        .get("http://localhost:8080/booking")
        .then((res) => {
          this.bookings = res.data;
          this.isBookingLoaded = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
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
