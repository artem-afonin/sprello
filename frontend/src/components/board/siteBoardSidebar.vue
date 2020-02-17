<template>
  <div class="border border-success rounded">
    <div class="row no-gutters">
      <div class="col-8">
        <div class="card pb-0 text-center">
          <div class="card-header">
            <div class="pt-0 mx-auto mb-0 text-black-50">
              <span class="h5 ml-2">Создать новую доску</span>
            </div>
          </div>
          <div class="form-group mb-1">
            <input
              class="form-control"
              v-model="newPost.name"
              type="text"
              maxlength="48"
              placeholder="Введите название доски"
            />
          </div>
          <button type="button" @click="post" class="btn btn-outline-primary">
            Создать
          </button>
        </div>
      </div>
      <div class="col-4 border-left border-success">
        <div class="card pb-0 text-center">
          <div class="card-header">
            <div class="pt-0 mx-auto mb-0 text-black-50">
              <span class="h5 ml-2">Параметры поиска</span>
            </div>
          </div>
          <div class="form-group py-2 my-1 mx-auto">
            <div class="custom-control custom-checkbox">
              <input
                class="custom-control-input"
                v-model="searchParams.own"
                @click="$emit('updatePosts', !searchParams.own)"
                type="checkbox"
                value=""
                id="searchParamsCheck"
              />
              <label class="custom-control-label" for="searchParamsCheck">
                Показывать только свои доски
              </label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "siteBoardSidebar",

  // READONLY! Объект передаётся только для реактивного отображения добавленных данных в родителе
  props: ["boards"],

  data() {
    return {
      newPost: {
        name: ""
      },
      searchParams: {
        own: false
      }
    };
  },

  methods: {
    ...mapActions(["postBoard"]),
    post() {
      if (this.newPost.name !== "")
        this.postBoard({
          name: this.newPost.name
        });
    }
  }
};
</script>

<style scoped>
.border,
.border-left {
  border-width: 2px !important;
}
</style>
