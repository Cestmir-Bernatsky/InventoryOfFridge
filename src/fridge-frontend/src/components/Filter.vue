<template>
  <div>
    <select class="filter" name="filter" v-model="filtered">
      <option disabled class="underlined">Categories</option>
      <option v-for="category in categories" :key="category">
        {{ category.category }}
      </option>
    </select>
    <button @click="getFiltered(filtered)">Filter!</button>
    <button class="reset-filter" @click="resetFilter">Reset filter</button>
  </div>
</template>

<script>
import axios from "axios";

const apiCategories = "/api/categories";
const apiManufacturers = "/api/manufacturers";

export default {
  props: ["getFiltered"],
  data() {
    return {
      categories: [],
      manufacturers: [],
      filtered: "",
    };
  },
  mounted() {
    this.getProducts();
    console.log(this.categories);
  },
  methods: {
    getProducts() {
      axios.get(apiCategories).then((response) => {
        this.categories = response.data;
      });
      axios.get(apiManufacturers).then((response) => {
        this.manufacturers = response.data;
      });
    },
    resetFilter() {
      this.filtered = "";
    },
  },
};
</script>

<style scoped>
.reset-filter {
  background-color: #ffcdd2;
  color: #c62828;
}
</style>
