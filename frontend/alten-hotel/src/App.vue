<template>
  <div id="app">
    <Main />
  
      <div>
      <b-alert
            class="mt-5"
        :show="dismissCountDown"
        v-bind:variant="alertClass"
        dismissible
        fade
        @dismiss-count-down="countDownChanged"
      >
        {{ message }}
      </b-alert>
    </div>
      <Calendar :key="bookingChangeCounter"/>
    <b-container
      v-if="isBookingLoaded"
      class="mt-5"
      :key="bookingChangeCounter"
    >
      <h1 v-if="bookings.length > 0">Bookings</h1>
      <b-row>
        <b-col sm="4" v-for="booking in bookings" :key="booking.id">
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
      alertClass: "success",
      dismissSecs: 5,
      dismissCountDown: 0,
      bookingChangeCounter: 0,
      isBookingLoaded: false,
    };
  },
  mounted: function () {
    this.$root.$on("newBooking", () => {
      this.getAllBookings();
    });
    this.$root.$on("updateBooking", (text, success) => {
      this.message = text;
      if (success) this.alertClass = "success";
      else this.alertClass = "danger";
      this.showAlert();
      this.getAllBookings();
    });
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
          this.bookingChangeCounter += 1;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
    showAlert() {
      this.dismissCountDown = this.dismissSecs;
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
  height: 100vh;
}
</style>
