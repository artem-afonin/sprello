<template>
    <div>
        <div class="card pb-0">
            <div class="card-header">
                <div class="pt-0 mx-auto mb-0 text-black-50">
                    <i class="material-icons">note_add</i>
                    <span class="h6">Создать новую доску</span>
                </div>
            </div>
            <div class="form-group mb-1">
                <input class="form-control" v-model="name" type="text" maxlength="48"
                       placeholder="Введите название доски">
            </div>
            <div class="form-group my-1 mx-auto">
                <div class="custom-control custom-switch">
                    <input class="custom-control-input" v-model="isPrivate" type="checkbox" value="" id="check"
                           style="width: 18px; height: 18px">
                    <label class="custom-control-label" for="check">Закрытая доска</label>
                </div>
            </div>
            <button type="button" @click="postBoard" class="btn btn-outline-primary">Создать</button>
        </div>
    </div>
</template>

<script>
  import $ from 'jquery'
  import axios from 'axios'
  import {devMode, apiurl} from '../globalDefines'

  export default {
    name: "siteBoardSidebar",

    // READONLY! Объект передаётся только для реактивного отображения добавленных данных в родителе
    props: ['boards'],

    data() {
      return {
        name: '',
        isPrivate: false
      }
    },

    mounted() {
      this.addAnimation()
    },

    methods: {
      addAnimation() {
        const elem = $('span.h6')
        elem.on('mouseenter', function () {
          $(this).addClass('font-weight-bolder').css('cursor', 'default')
        })
        elem.on('mouseleave', function () {
          $(this).removeClass('font-weight-bolder')
        })
      },
      postBoard() {
        if (this.name !== '') {
          axios.post(`${apiurl}/board`, {
            name: this.name,
            isPrivate: this.isPrivate
          }).then(response => {
            if (response.statusText.toLowerCase() === 'ok')
              this.boards.push(response.data) //TODO будущем должно переходить на страничку с бордой
          }).catch(err => {
            if (devMode) console.error(err)
          })
          this.name = ''
          this.isPrivate = false
        }
      }
    }
  }
</script>

<style scoped>
</style>