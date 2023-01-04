<template>
  <div class="backdrop">
    <div class="modal">
      <div class="tabs">
        <div class="tabs_head">
          <span
            class="tabs_toggle"
            v-for="item in getManufacturers"
            v-bind:key="item"
            @click="setActive(item)"
            :class="{
              active: isActive(item),
            }"
            >{{ item }}</span
          >
        </div>
        <div class="tabs_body">
          <table>
            <thead>
              <th>ID</th>
              <th>Barcode</th>
              <th>Name</th>
              <th>Category</th>
              <th>Pcs</th>
              <th>Manufacturer</th>
              <th>Exp.</th>
            </thead>
            <tbody
              class="tabs_content"
              v-for="item in items"
              v-bind:key="item"
              v-show="isActive(item.manufacturerfk.manufacturer)">
              <td><input type="text" disabled v-model="item.id" /></td>
              <td><input type="text" disabled v-model="item.barcode" /></td>
              <td>
                <input type="text" disabled v-model="item.productfk.product" />
              </td>
              <td>
                <input type="text" disabled v-model="item.productfk.categoryfk.category" />
              </td>
              <td>
                <input class="dis" type="text" v-model="item.pcs" />
              </td>
              <td>
                <input type="text" disabled v-model="item.manufacturerfk.manufacturer" />
              </td>
              <td>
                <input class="dis" :class="{ exp: isExpired(item) }" type="date" v-model="item.expiration" />
              </td>
              <td class="buttons">
                <button class="edit-btn" @click="activateEdit($event, item)">Edit</button>
                <button class="delete-btn" @click="deleteProduct(item)">Delete</button>
                <button class="delete-btn change-btn" @click="editProduct(item)">Change</button>
              </td>
            </tbody>
          </table>
        </div>
      </div>
      <button @click="closeDetails">OK</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { ref } from "vue";

const apiURL = "/api/desiredproducts/";
const apiURLDelete = "/api/items/";

export default {
  name: "",
  props: ["clickedOn", "editBtnClicked"],
  data() {
    return {
      items: [],
      productName: "",
      category: "",
      previousPcs: 0,
      loaded: false,
      activeTab: "",
      date: new Date().toJSON().slice(0, 10),
      expired: false,
      numOfExpired: 0,
      disabled: 0,
      tempPcsSave: "",
    };
  },
  mounted() {
    this.getProducts();
  },
  computed: {
    getManufacturers() {
      var manufacturersForTabs = [];
      this.items.forEach((el) => {
        if (manufacturersForTabs.includes(el.manufacturerfk.manufacturer)) {
          return;
        } else {
          manufacturersForTabs.push(el.manufacturerfk.manufacturer);
        }
      });
      console.log(manufacturersForTabs);
      return manufacturersForTabs;
    },
  },
  methods: {
    getProducts() {
      axios.get(apiURL + this.clickedOn).then((response) => {
        this.items = response.data;
        console.log(response.data);
        this.isExpired(response.data);
        this.activeTab = response.data[0].manufacturerfk.manufacturer;
      });
      this.$parent.getProducts();
    },

    async deleteProduct(product) {
      var result = confirm("Are you sure you want to delete this item?");
      if (result) {
        await axios.delete(apiURLDelete + product.id);
        window.alert("You have deleted " + product.productfk.product + " " + product.manufacturerfk.manufacturer);
      } else {
        window.alert("Not deleted!");
        this.getProducts;
      }
      this.getProducts();
    },

    async editProduct(product) {
      this.pcs = product.pcs;
      if (this.pcs < 1) {
        var result = confirm("Are you sure you want to delete this item?");
        if (result) {
          axios.put(apiURLDelete + product.id, {
            pcs: product.pcs,
          });
          txt = "You have deteted" + product.productfk.product + "with ID" + product.id;
        } else {
          window.alert("Not deleted!");
          this.getProducts;
        }
      } else {
        axios.put(apiURLDelete + product.id, {
          pcs: product.pcs,
        });
        window.alert(
          "You have changed number of" +
            " " +
            product.productfk.product +
            " " +
            product.manufacturerfk.manufacturer +
            " " +
            "to" +
            " " +
            this.pcs +
            "pcs"
        );
      }
      this.getProducts();
    },
    setActive(tab) {
      this.activeTab = tab;
      console.log(tab);
    },
    isActive(tab) {
      return this.activeTab === tab;
      console.log(tab);
    },
    activate() {
      this.activeTab = this.items[0].id;
      console.log("Success");
    },
    closeDetails() {
      this.$emit("close");
    },
    test() {
      console.log(this.clickedOn);
    },

    passNumExp() {
      return this.numOfExpired;
    },

    isExpired(product) {
      if (product.expiration < this.date) {
        return true;
      } else {
        return false;
      }
    },
    activateEdit(event, product) {
      if (event.target.parentElement.parentElement.children[4].children[0].classList != "dis") {
        if (this.tempPcsSave != event.target.parentElement.parentElement.children[4].children[0].value) {
          event.target.parentElement.parentElement.children[4].children[0].value = this.tempPcsSave;
          this.tempPcsSave = 0;
        }
        event.target.parentElement.parentElement.children[4].children[0].classList.add("dis");
        event.target.nextElementSibling.style.visibility = "hidden";
        event.target.nextElementSibling.nextElementSibling.style.visibility = "hidden";
      } else {
        event.target.parentElement.parentElement.children[4].children[0].classList.remove("dis");
        event.target.nextElementSibling.style.visibility = "visible";
        event.target.nextElementSibling.nextElementSibling.style.visibility = "visible";
        this.tempPcsSave = product.pcs;
      }
      console.log(event.target.parentElement.parentElement.children[4].children[0].classList);
      console.log(this.tempPcsSave);
      event.target.parentElement.parentElement.children[4].children[0].value;
    },
  },
};
</script>

<style scoped>
.modal {
  font-family: "Mukta", sans-serif;
  padding: 20px;
  margin: 100px auto;
  background: white;
  border-radius: 10px;
  text-align: left;
  display: flex;
  flex-wrap: wrap;
  justify-content: right;
}

.modal button {
  margin: 10px 10px 0 10px;
  background: #009578;
  color: white;
  padding: 0 1rem;
}

.modal button:hover {
  background-color: #009577a5;
}

.tabs {
  max-width: 100%;
  display: flex;
  flex-direction: column;
  width: 100%;
}

.tabs_head {
  display: flex;
  align-items: stretch;
  font-weight: 700;
  font-size: 18px;
  color: #009578;
}

.tabs_toggle {
  display: flex;
  height: 50px;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  width: 50%;
  border-radius: 10px 10px 0 0;
}

.tabs_toggle:hover {
  background: rgba(0, 0, 0, 0.1);
}

.tabs_toggle.active {
  background: #009577;
  color: white;
  border-bottom: 3px solid #009578;
}

.tabs_toggle.active:hover {
  background: #009577ae;
}

.tabs_body {
  display: flex;
  justify-content: center;
  align-items: center;
  background: #f4f6fb;
  color: black;
  border-radius: 0 0 10px 10px;
}

thead {
  letter-spacing: 0.3rem;
  background: #f4f6fb;
  text-align: center;
  text-transform: uppercase;
}

th {
  padding: 10px;
}

.tabs_content {
  text-align: center;
  background: #f4f6fb;
}

.tabs_content input {
  background: rgb(255, 255, 255);
  text-align: center;
  font-size: 16px;
  border-collapse: collapse;
  max-width: 150px;
  padding: 5px;
  border: 0.1rem solid gray;
  border-radius: 0.3rem;
}

.tabs_content .dis,
input:disabled {
  text-align: center;
  font-size: 16px;
  border: none;
  background: #f4f6fb;
  pointer-events: none;
}

.backdrop {
  top: 0;
  left: 0;
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
}

table .delete-btn {
  background-color: #ffcdd2;
  color: #c62828;
  visibility: hidden;
}

table .edit-btn {
  background-color: #c8e6c9;
  color: #388e3c;
}
table .change-btn {
  background-color: #c4def3;
  color: #38598e;
}
.tabs_content td {
  justify-content: center;
  align-items: flex-end;
}

.tabs_content .exp {
  background-color: #ffcdd2;
}
</style>
