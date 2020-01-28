<template>
    <li class="list-group-item">
        {{ element.text }}
    </li>
</template>

<script>
  import 'bootstrap/dist/js/bootstrap.bundle.min'
  import {mapActions} from 'vuex'

  export default {
    name: 'siteTaskElement',

    props: ['element'],

    data() {
      return {
        changedElement: {
          text: '',
          color: ''
        }
      }
    },

    methods: {
      ...mapActions(['patchElement', 'deleteElement']),
      patch() {
        this.patchElement({
          taskElementId: this.element.id,
          text: this.changedElement.text,
          color: this.changedElement.color
        })
      },
      del() {
        this.deleteElement(this.element.id)
      }
    }
  }
</script>

<style scoped>

</style>