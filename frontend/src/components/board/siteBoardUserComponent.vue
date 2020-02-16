<template>
  <div>
    <router-link
      :to="{
        name: 'userpage',
        params: {
          userid: user.id
        }
      }"
    >
      <div class="row p-3 border border-primary">
        <div class="col-4">
          <img :src="user.userpic" class="w-75 rounded-circle" />
        </div>
        <div class="col-8 py-3">
          <h4 class="w-100">
            {{ user.name }}
          </h4>
        </div>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "siteBoardUserComponent",

  props: ["user"]
};
</script>

<style scoped></style>
