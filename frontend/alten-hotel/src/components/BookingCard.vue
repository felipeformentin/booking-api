<template>
  <div>
    <b-card tag="article" style="max-width: 30rem" class="mb-4">
      <b-card-sub-title>
        <p>
          CheckInDate:
          <v-date-picker v-model="checkInDate">
            <template v-slot="{ inputValue, inputEvents }">
              <input
                class="bg-white border px-2 py-1 rounded"
                :value="inputValue"
                v-on="inputEvents"
              />
            </template>
          </v-date-picker>
        </p>

        <p>
          CheckOutDate:
          <v-date-picker v-model="checkOutDate">
            <template v-slot="{ inputValue, inputEvents }">
              <input
                class="bg-white border px-2 py-1 rounded"
                :value="inputValue"
                v-on="inputEvents"
              />
            </template>
          </v-date-picker>
        </p>
      </b-card-sub-title>

      <b-button class="mr-2" variant="primary">Update Booking</b-button>
      <b-button class="ml-2" variant="danger">Delete Booking</b-button>
    </b-card>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
  name: "BookingCard",
  data() {
    return {
      checkInDate: this.parseDate(this.booking.checkInDate),
      checkOutDate: this.parseDate(this.booking.checkOutDate),
    };
  },
  created() {
    console.log(this.booking);
    this.parsedCheckInDate = this.readableDate(this.booking.checkInDate);
    this.parsedCheckOutDate = this.readableDate(this.booking.checkOutDate);
  },
  props: {
    booking: {},
  },
  methods: {
    parseDate(date) {
      return new Date(date[0], date[1] - 1, date[2]);
    },
    readableDate(date) {
      var parsedDate = moment(date);
      return `${parsedDate.date()}/${parsedDate.month()}/${parsedDate.year()}`;
    },
  },
};
</script>
