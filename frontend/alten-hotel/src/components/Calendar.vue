<template>
  <div class="text-center section">
    <div>
      <b-alert
        :show="dismissCountDown"
        v-bind:variant="alertClass"
        dismissible
        fade
        @dismiss-count-down="countDownChanged"

      >
        {{ message }}
      </b-alert>

      <v-date-picker
        v-model="range"
        is-range
        :min-date="minimumDate()"
        :max-date="maximumDate()"
        :disabled-dates="disabledDays"
        :attributes="calendarAttributes"
        :key="disabledDays.index"
      />
    </div>
    <b-button
      class="mt-2"
      variant="success"
      :disabled="isMakeBookingDisabled"
      @click="makeBooking"
    >
      Make Booking
    </b-button>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
  name: "Calendar",
  data() {
    return {
      alertClass: "success",
      dismissSecs: 5,
      dismissCountDown: 0,
      message: null,
      isMakeBookingDisabled: true,
      calendarAttributes: [],
      disabledDays: [],
      range: {},
      calendarKey: 0,
    };
  },
  created() {
    this.getDisabledDays();
  },
  watch: {
    range: function () {
      if (this.range != null) this.isMakeBookingDisabled = false;
    },
  },
  methods: {
    makeBooking() {
      var postRequest = {
        checkInDate: this.parseDate(this.range.start),
        checkOutDate: this.parseDate(this.range.end),
      };

      axios
        .post("http://localhost:8080/booking", postRequest)
        .then(() => {
          this.getDisabledDays();
          this.range = null;
          this.message = "Booking created with success!";
          this.alertClass = "success";
          this.showAlert();
        })
        .catch((error) => {
          this.message = error.response.data.message;
          this.alertClass = "danger";
          this.showAlert();
        });
    },

    minimumDate() {
      var today = new Date();
      return today.setDate(today.getDate() + 1);
    },
    maximumDate() {
      var today = new Date();
      return today.setDate(today.getDate() + 30);
    },
    getDisabledDays() {
      axios
        .get("http://localhost:8080/availability")
        .then((res) => {
          res.data.daysBooked.forEach((day) => {
            this.disabledDays.push(new Date(day[0], day[1] - 1, day[2]));
            this.calendarAttributes.push({
              highlight: "red",
              dates: this.disabledDays,
            });
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    parseDate(date) {
      var parsedDate = moment(date);
      return new Date(parsedDate.year(), parsedDate.month(), parsedDate.date());
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
