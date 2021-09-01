<template>
  <div class="text-center section">
    <div>
      <b-alert v-model="showDismissibleSuccess" variant="success" dismissible>
        {{successMessage}}
      </b-alert>

      <v-date-picker
        v-model="range"
        is-range
        :min-date="minimumDate()"
        :max-date="maximumDate()"
        :disabled-dates="disabledDays"
        :key="disabledDays"
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

export default {
  name: "Calendar",
  data() {
    return {
      successMessage: null,
      showDismissibleSuccess: false,
      isMakeBookingDisabled: true,
      disabledDays: [],
      range: {},
      calendarKey: 0,
    };
  },
  created() {
    this.getDisabledDays();
  },
  watch: {
    range: function (val) {
      if (this.range != null) this.isMakeBookingDisabled = false;
    },
  },
  methods: {
    makeBooking() {
console.log(this.range.start);

var month = this.range.start.getUTCMonth();
var day = this.range.start.getDay();
var year = this.range.start.getUTCFullYear();
console.log(new Date(year, month, day));

      // var postRequest = {
      //   checkInDate: this.range.start.toISOString(),
      //   checkOutDate: this.range.end.toISOString(),
      // };

      // axios
      //   .post("http://localhost:8080/booking", postRequest)
      //   .then((res) => {
      //     this.getDisabledDays();
      //     this.range = null;
      //     this.successMessage = "Booking created with success!";
      //     this.showDismissibleSuccess = true;
      //   })
      //   .catch((error) => {
      //     console.log(error);
      //   });
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
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
