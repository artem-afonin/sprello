<template>
    <div id="wrapper">
        <div id="container">
            <header id="header">
                <div class="row">
                    <div class="col">
                        <h1>Sprello</h1>
                    </div>
                </div>
            </header>

            <div class="row" id="main">
                <div class="col" id="content">
                    <h1>A place for main content</h1>
                </div>

                <aside class="col-3" id="sidebar">
                    <ul>
                        <li>1 menu</li>
                        <li>2 menu</li>
                        <li>3 menu</li>
                        <li>4 menu</li>
                    </ul>
                </aside>
            </div>

            <footer id="footer" class="row">
                <div>
                    <h3>Footer</h3>
                </div>
            </footer>
        </div>
    </div>
</template>

<script>
  export default {
    name: "index",
    mounted: function () {
      const blocks = document.querySelectorAll('.row');
      blocks.forEach((el) => {
        el.classList.add('no-gutters');
      })
    }
  }
</script>

<style>
    #container {
        border: 1px solid blue;
    }

    #header {
        background-color: yellow;
    }

    #content {
        background-color: lightgreen;
    }

    #sidebar {
        background-color: bisque;
    }

    #footer {
        background-color: lightcoral;
    }
</style>