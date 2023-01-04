<template>
  <div>
    <Filter :getFiltered="filter" />
  </div>
  <div v-if="showDetails">
    <Modal2 @close="toggleModal" :clickedOn="clicked" :editBtnClicked="editBtnClicked" />
  </div>
  <table class="table">
    <thead>
      <th>Name</th>
      <th>Category</th>
      <th>Pcs</th>
      <th>Added</th>
      <th>Manufacturers</th>
      <th>Exp.</th>
      <th>Actions</th>
    </thead>
    <tbody>
      <tr v-for="item in filteredCategories" :key="item">
        <td class="productSelect" @click="toggleModal($event, item[0].productfk.product)">
          {{ item[0].productfk.product }}
          <img :src="logo" />
        </td>
        <td>{{ item[0].productfk.categoryfk.category }}</td>
        <td>{{ totalRequest(item) }}</td>
        <td>{{ item[0].added }}</td>
        <td>{{ numOfManu(item) }}</td>
        <td :class="{ exp: isExpired(item) }">{{ totalExp(item) }}/{{ totalRequest(item) }}</td>
        <td>
          <div class="actions">
            <button class="edit-btn" @click="toggleModal($event, item[0].productfk.product)">Show</button>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
  <input type="file" ref="uploadTxt" @change="onTxtUpload()" />
  <button @click="uploadFile(), getProducts(), refreshPage()">Upload!</button>
</template>

<script>
import axios from "axios";
import Modal2 from "./Modal.vue";
import Filter from "./Filter.vue";

const apiURL = "/api/mainTable";
const apiUpload = "/api/upload";

export default {
  components: {
    Modal2,
    Filter,
  },
  name: "",
  data() {
    return {
      items: [],
      productName: "",
      pcs: "",
      showDetails: false,
      clicked: "",
      editBtnClicked: "",
      filtered: "",
      logo: require("../assets/details.png"),
      expired: "",
      formData: null,
    };
  },
  mounted() {
    this.getProducts();
  },
  methods: {
    getProducts() {
      axios.get(apiURL).then((response) => {
        this.items = Object.values(this.groupBy(response.data));
      });
    },
    addProduct(event) {
      this.$emit(
        "test",
        axios
          .post(apiURL, {
            product: this.productName,
            category: this.category,
            pcs: this.pcs,
          })
          .then((savedProduct) => {
            this.items.push(savedProduct.data);
            this.productName = "";
            this.category = "";
            this.pcs = "";
          })
      );
    },
    onTxtUpload() {
      let file = this.$refs.uploadTxt.files[0];
      this.formData = new FormData();
      this.formData.append("file", file);
    },
    uploadFile() {
      axios.post(apiUpload, this.formData).then((response) => {
        console.log(JSON.stringify(response.data));
      });
    },
    refreshPage() {
      if (this.$refs.uploadTxt.value != "") {
        location.reload();
      }
    },
    editProduct(product) {
      this.productName = product.id;
      this.category = product.category;
      this.pcs = product.pcs;
    },
    async deleteProduct(product) {
      await axios.delete(apiURL + product.id);
      this.getProducts();
    },
    toggleModal(event, item) {
      this.showDetails = !this.showDetails;
      this.clicked = item;
      console.log(this.clicked);
    },
    filter(toBeFiltered) {
      this.filtered = toBeFiltered;
    },

    groupBy(arr) {
      var groupedData = {};

      arr.forEach((item) => {
        if (!groupedData[item.productfk.product]) {
          groupedData[item.productfk.product] = [];
        }
        groupedData[item.productfk.product].push(item);
      });
      return groupedData;
    },
    checkOutput() {
      console.log();
    },
    totalRequest(item) {
      return item.reduce((acc, val) => acc + val.pcs, 0);
    },
    totalExp(item) {
      return item.reduce((acc, val) => acc + val.exp, 0);
    },
    isExpired(item) {
      if (this.totalExp(item) > 0) {
        return true;
      } else {
        return false;
      }
    },
    numOfManu(arr) {
      var manu = {};
      var num = 0;

      arr.forEach((item) => {
        if (!manu[item.manufacturerfk.manufacturer]) {
          manu[item.manufacturerfk.manufacturer] = [];
          num++;
        }
        manu[item.manufacturerfk.manufacturer].push(item);
      });
      return num;
    },
  },
  computed: {
    filteredCategories() {
      return this.items.filter((item) => {
        return item[0].productfk.categoryfk.category.match(this.filtered);
      });
    },
  },
};

export function addProduct() {
  return document.cookie !== "";
}
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Mukta:wght@300&display=swap");

.table {
  font-family: "Mukta", sans-serif;
  border-collapse: collapse;
  box-shadow: 0 5px 10px #e1e5ee;
  background-color: white;
  text-align: left;
  overflow: hidden;
  padding: 1000px;
  border-radius: 15px;
}

.table thead {
  box-shadow: 0 5px 10px #e1e5ee;
}

.table th {
  padding: 1rem 2rem;
  text-transform: uppercase;
  letter-spacing: 0.3rem;
  font-size: 0.9rem;
  font-weight: 900;
  text-align: center;
}

.table td {
  padding: 1rem 2rem;
  font-size: 1.05rem;
  text-align: center;
}

.edit-btn {
  background-color: #c8e6c9;
  color: #388e3c;
}

.delete-btn {
  background-color: #ffcdd2;
  color: #c62828;
}

.delete-btn,
.edit-btn {
  margin: 0 0 0 0.75rem;
}

.table tbody tr:nth-child(even) {
  background-color: #f4f6fb;
}

.underlined {
  font-weight: 800;
}

.productSelect:hover {
  cursor: pointer;
  background: #ffcdd2;
}

.exp {
  background-color: #ffcdd2;
}

input[type="file"]::-webkit-file-upload-button {
  background-color: #d1d1d1;
  color: black;
  border: none;
  border-radius: 0.3rem;
  padding: 0.3rem;
}

input[type="file"]::-webkit-file-upload-button:hover {
  background-color: #a2a2a2;
}
</style>
