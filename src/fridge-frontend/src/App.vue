<template>
  <h1>Fridge Inventory</h1>
  <Table />
</template>

<script>
import Table from "./components/Table.vue";

export default {
  name: "App",
  components: {
    Table,
  },
  data() {
    return {
      showDetails: false,
    };
  },
  methods: {},
};
</script>

<style>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  height: 100vh;
  display: grid;
  justify-content: center;
  align-items: flex-start;
  background-color: #f9fbff;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

button {
  all: unset;
  cursor: pointer;
  border-radius: 0.2rem;
  padding: 0.3rem 1rem;
  text-align: center;
  font-weight: 600;
  background-color: #c8e6c9;
  margin-left: 0.75rem;
  margin-top: 0.75rem;
  margin-bottom: 1rem;
}

button:hover {
  background-color: rgba(181, 181, 181, 0.2);
}
</style>
