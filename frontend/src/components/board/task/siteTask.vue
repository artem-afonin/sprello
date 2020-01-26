<template>
    <div class="card text-center">
        <div class="card-header h4 text-center">
            {{ task.name }}
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Nunquam carpseris compater.</li>
            <li class="list-group-item">Ubi est brevis coordinatae?</li>
            <li class="list-group-item">Cur verpa observare?</li>
        </ul>
        <div class="card-body">
            <button type="button" @click="patch"
                    class="btn btn-info mr-3 font-weight-bolder">Править название</button>
            <!-- TODO: добавить функционал для кнопки ПРАВИТЬ -->
            <button type="button" @click="del"
                    class="btn btn-danger ml-3 font-weight-bolder">Удалить</button>
        </div>
    </div>
</template>

<script>
    import {mapActions} from 'vuex'

  export default {
    name: 'siteTask',

    props: ['task'],

    methods: {
      ...mapActions(['patchTask', 'deleteTask']),
      patch() {

      },
      del() {
        this.deleteTask(this.task.id)
      }
    }
  }
</script>

<style scoped>

</style>