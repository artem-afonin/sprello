<template>
    <div class="border border-success rounded">
        <div class="row no-gutters">
            <div class="col-8">
                <div class="card pb-0">
                    <div class="card-header">
                        <div class="pt-0 mx-auto mb-0 text-black-50">
                            <i class="material-icons md-24 align-text-botto">note_add</i>
                            <span class="h5 ml-2">Создать новую доску</span>
                        </div>
                    </div>
                    <div class="form-group mb-1">
                        <input class="form-control" v-model="newPost.name" type="text" maxlength="48"
                               placeholder="Введите название доски">
                    </div>
                    <div class="form-group my-1 mx-auto">
                        <div class="custom-control custom-switch">
                            <input class="custom-control-input" v-model="newPost.isPrivate"
                                   type="checkbox" value="" id="newPostCheck">
                            <label class="custom-control-label" for="newPostCheck">Закрытая доска</label>
                        </div>
                    </div>
                    <button type="button" @click="postBoard" class="btn btn-outline-primary">Создать</button>
                </div>
            </div>
            <div class="col-4 border-left border-success">
                <div class="card pb-0">
                    <div class="card-header">
                        <div class="pt-0 mx-auto mb-0 text-black-50">
                            <i class="material-icons md-24 align-text-bottom">format_list_bulleted</i>
                            <span class="h5 ml-2">Параметры поиска</span>
                        </div>
                    </div>
                    <div class="form-group py-2 my-1 mx-auto">
                        <div class="custom-control custom-checkbox">
                            <input class="custom-control-input"
                                   v-model="searchParams.isTypeOwn"
                                   @click="$emit('updatePosts', !searchParams.isTypeOwn)"
                                   type="checkbox" value="" id="searchParamsCheck">
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
  import $ from 'jquery'
  import axios from 'axios'
  import {devMode, apiurl} from '../globalDefines'

  export default {
    name: 'siteBoardSidebar',

    // READONLY! Объект передаётся только для реактивного отображения добавленных данных в родителе
    props: ['boards'],

    data() {
      return {
        newPost: {
          name: '',
          isPrivate: false
        },
        searchParams: {
          isTypeOwn: false
        }
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
            name: this.newPost.name,
            isPrivate: this.newPost.isPrivate
          }).then(response => {
            if (response.statusText.toLowerCase() === 'ok')
              this.boards.push(response.data) //TODO будущем должно переходить на страничку с бордой
          }).catch(err => {
            if (devMode) console.error(err)
          })
          this.newPost.name = ''
          this.newPost.isPrivate = false
        }
      }
    }
  }
</script>

<style scoped>
    .border, .border-left {
        border-width: 2px !important;
    }
</style>