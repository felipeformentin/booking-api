<template>
  <div class="text-center section">

    <v-calendar
    :min-date="minimumDate()"
    :max-date="maximumDate()"
    />
     
  </div>
</template>

<script>
export default {
  methods: {
    minimumDate() {
      var today = new Date();
      return today.setDate(today.getDate() + 1);
    },
    maximumDate() {
      var today = new Date();
      return today.setDate(today.getDate() + 30);
    }
  }
};

</script>
