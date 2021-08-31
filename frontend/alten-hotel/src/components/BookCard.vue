<template>
  <div class="text-center section">
    <b-card
      title="hashId124812!gh81271"
      tag="article"
      style="max-width: 30rem"
      class="mb-4"
    >
      <b-card-text>
        <p>CheckInDate: 08/20/2021</p>
        <p>CheckOutDate: 09/10/2021</p>
      </b-card-text>

      <b-button href="#"  class="mr-2" variant="success">Update Booking</b-button>
      <b-button href="#"   class="ml-2" variant="danger">Delete Booking</b-button>
    </b-card>

       {{ user }}
  </div>
</template>

<script>
  import axios from "axios";

  export default {
   name: "User",
   data() {
     return {
       user: {},
     };
   },
   created() {
     this.getUser();
   },
   methods: {
     getUser() {
       axios
         .get("http://localhost:8080/availability")
         .then((res) => {
           this.user = res.data;
         })
         .catch((error) => {
           console.log(error);
         });
     },
   },
  };
</script>
