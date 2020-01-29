<template>
  <div class="card text-center">
    <div class="card-header h4 text-center">
      {{ task.name }}
    </div>

    <ul class="list-group list-group-flush">
      <site-task-element
        v-for="el in task.elements"
        :key="el.id"
        :element="el"
      />
      <li class="list-group-item">
        <button
          class="btn btn-outline-success text-center font-weight-bolder"
          data-toggle="modal"
          :data-target="`#addModal${task.id}`"
        >
          +
        </button>
      </li>
    </ul>

    <!-- Модальное окно правки -->
    <div
      class="modal fade"
      :id="`addModal${task.id}`"
      tabindex="-1"
      role="dialog"
      aria-labelledby="changeModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addModalLabel">Править элемент</h5>
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
                class="form-control mt-3"
                v-model="createdElement.text"
                type="text"
                maxlength="48"
                placeholder="Введите новый текст подзадачи"
              />
            </div>
          </div>
          <div class="modal-footer text-center">
            <button
              type="button"
              @click="createElem"
              data-dismiss="modal"
              class="btn btn-primary font-weight-bolder"
            >
              Сохранить изменения
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Кнопки -->
    <div class="card-body">
      <button
        type="button"
        class="btn btn-info mr-3 font-weight-bolder"
        data-toggle="modal"
        :data-target="`#changeModal${task.id}`"
      >
        Править название
      </button>

      <!-- Модальное окно правки -->
      <div
        class="modal fade"
        :id="`changeModal${task.id}`"
        tabindex="-1"
        role="dialog"
        aria-labelledby="changeModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="changeModalLabel">Править задачу</h5>
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
                  class="form-control mt-3"
                  v-model="changedTask.name"
                  type="text"
                  maxlength="48"
                  placeholder="Введите новое имя задачи"
                />
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
      <button
        type="button"
        @click="del"
        class="btn btn-danger ml-3 font-weight-bolder"
      >
        Удалить
      </button>
    </div>
  </div>
</template>

<script>
import "bootstrap/dist/js/bootstrap.bundle.min";
import { mapActions, mapGetters } from "vuex";
import SiteTaskElement from "./siteTaskElement";

export default {
  name: "siteTask",
  components: { SiteTaskElement },
  props: ["task"],

  data() {
    return {
      changedTask: {
        name: ""
      },
      createdElement: {
        text: "",
        color: ""
      }
    };
  },

  methods: {
    ...mapActions(["patchTask", "deleteTask", "createElement"]),
    patch() {
      this.patchTask({
        taskId: this.task.id,
        name: this.changedTask.name
      });
      this.changedTask.name = "";
    },
    del() {
      this.deleteTask(this.task.id);
    },
    createElem() {
      this.createElement({
        taskId: this.task.id,
        text: this.createdElement.text,
        color: this.createdElement.color
      });
      this.createdElement.text = "";
    }
  },

  computed: {
    ...mapGetters(["getTasks"])
  }
};
</script>

<style scoped></style>
