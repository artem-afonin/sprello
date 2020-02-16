<template>
  <li class="list-group-item p-2">
    <div class="row no-gutters">
      <div :class="`col-11 bg-${colorName} rounded p-1`">
        <h5 class="font-weight-bolder">
          {{ element.text }}
        </h5>
      </div>
      <div class="col-1 btn-group-vertical">
        <button
          class="btn btn-sm btn-outline-info"
          data-toggle="modal"
          :data-target="`#changeElement${element.id}`"
        >
          &#9998;
        </button>
        <button
          class="btn btn-sm btn-outline-danger font-weight-bolder"
          @click="del"
        >
          &times;
        </button>
      </div>
    </div>

    <!-- Модальное окно правки -->
    <div
      class="modal fade"
      :id="`changeElement${element.id}`"
      tabindex="-1"
      role="dialog"
      aria-labelledby="changeElementLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="changeElementLabel">Править задачу</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Закрыть"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <input
                class="form-control my-3"
                v-model="changedElement.text"
                type="text"
                maxlength="48"
                placeholder="Введите новое описание подзадачи"
              />
              <button class="btn btn-outline-dark" @click="onChangeColor">
                Цвет: {{ colorTxt }}
              </button>
            </div>
          </div>
          <div class="modal-footer text-center">
            <button
              type="button"
              @click="patch"
              data-dismiss="modal"
              class="btn btn-primary font-weight-bolder"
            >
              Сохранить изменения
            </button>
          </div>
        </div>
      </div>
    </div>
  </li>
</template>

<script>
import "bootstrap/dist/js/bootstrap.bundle.min";
import { mapActions } from "vuex";

export default {
  name: "siteTaskElement",

  props: ["element"],

  data() {
    return {
      changedElement: {
        text: this.element.text,
        color: this.element.color
      }
    };
  },

  methods: {
    ...mapActions(["patchElement", "deleteElement"]),
    async patch() {
      if (this.changedElement.text.trim() !== "") {
        await this.patchElement({
          taskElementId: this.element.id,
          text: this.changedElement.text,
          color: this.changedElement.color
        });
        this.changedElement.text = this.element.text;
      }
    },
    del() {
      this.deleteElement(this.element.id);
    },
    onChangeColor({ target }) {
      const colors = ["DEFAULT", "RED", "GREEN", "BLUE"];
      const colorClasses = [
        "btn-outline-dark",
        "btn-outline-danger",
        "btn-outline-success",
        "btn-outline-info"
      ];
      for (let i = 0; i < colors.length; i++) {
        if (this.changedElement.color.toUpperCase() === colors[i]) {
          if (i === colors.length - 1) {
            this.changedElement.color = colors[0];
            target.classList.remove(colorClasses[colorClasses.length - 1]);
            target.classList.add(colorClasses[0]);
          } else {
            this.changedElement.color = colors[i + 1];
            target.classList.remove(colorClasses[i]);
            target.classList.add(colorClasses[i + 1]);
          }
          break;
        }
      }
    }
  },

  computed: {
    colorTxt() {
      let value = "";
      switch (this.changedElement.color.toUpperCase()) {
        case "RED": {
          value = "Красный";
          break;
        }
        case "GREEN": {
          value = "Зелёный";
          break;
        }
        case "BLUE": {
          value = "Синий";
          break;
        }
        default: {
          value = "Обычный";
        }
      }
      return value;
    },
    colorName() {
      let value = "";
      switch (this.element.color.toUpperCase()) {
        case "RED": {
          value = "secondary";
          break;
        }
        case "GREEN": {
          value = "success";
          break;
        }
        case "BLUE": {
          value = "info";
          break;
        }
        default: {
          value = "light";
        }
      }
      return value;
    }
  }
};
</script>

<style scoped></style>
