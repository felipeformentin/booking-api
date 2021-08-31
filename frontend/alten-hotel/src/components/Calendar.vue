<template>
  <div class="text-center section">
    <v-date-picker
      v-model="user"
      is-range
      :min-date="minimumDate()"
      :max-date="maximumDate()"
      :disabled-dates="disabledDays"
    />
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Calendar",
  data() {
    return {
      disabledDays: [],
      user: {},
    };
  },
  created() {
    this.getDisabledDays();
  },
  methods: {
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
          res.data.daysBooked.forEach(day => {
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
