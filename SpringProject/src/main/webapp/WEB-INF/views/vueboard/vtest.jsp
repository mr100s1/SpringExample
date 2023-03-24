<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://unpkg.com/vue@next"></script>
</head>
<body>

  <div id="root">
    <button v-on:click="currentTab = 'home'">Home</button>
    <button v-on:click="currentTab = 'blog'">Blog</button>
    <component v-bind:is="currentTabComponent"></component>
  </div>

  <script>
    const app = Vue.createApp({
      data() {
        return {
          currentTab: 'home'
        }
      },
      computed: {
        currentTabComponent() {
          return this.currentTab
        }
      }
    })

    app.component('home', {
      template: `<div>Home component</div>`
    })
    app.component('blog', {
      template: `<div>Blog component</div>`
    })

    app.mount('#root')
  </script>


</body>
</html>