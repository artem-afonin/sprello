<template>
    <li class="list-group-item px-1">
        <div class="row no-gutters">
            <div class="col">
                <h5>
                    {{ element.text }}
                </h5>
            </div>
            <div class="col-1 btn-group-vertical">
                <button class="btn btn-sm btn-outline-info" data-toggle="modal"
                        :data-target="`#changeElement${element.id}`">
                    &#9998;
                </button>
                <button class="btn btn-sm btn-outline-danger font-weight-bolder"
                        @click="del">
                    &times;
                </button>
            </div>
        </div>

        <!-- Модальное окно правки -->
        <div class="modal fade" :id="`changeElement${element.id}`" tabindex="-1" role="dialog"
             aria-labelledby="changeElementLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="changeElementLabel">Править задачу</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Закрыть">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <input class="form-control mt-3" v-model="changedElement.text" type="text" maxlength="48"
                                   placeholder="Введите новое описание подзадачи">
                        </div>
                    </div>
                    <div class="modal-footer text-center">
                        <button type="button" @click="patch"
                                data-dismiss="modal" class="btn btn-primary font-weight-bolder">
                            Сохранить изменения
                        </button>
                    </div>
                </div>
            </div>
        </div>
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
        this.changedElement.text = ''
      },
      del() {
        this.deleteElement(this.element.id)
      }
    }
  }
</script>

<style scoped>

</style>