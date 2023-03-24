//import { createApp } from 'vue';
//import App from './App.vue';

import topMenuComponent 	from './components/topMenuComponent.js'; 	// 컴포넌트1 import 
import gChart00Component 	from './components/gChart00Component.js'; 	// 컴포넌트1 import 

//각 컴포넌트들을 변수에 할당
// const one_component = oneComponent;
// const gchart00_component = gChart00Component;

/*

*/
var app = Vue.createApp({
    data() {
        return {
            message: 'Hello~ Bryan~'
        }
    },
	components: {
		'topmenu-component': 	topMenuComponent,
		'gchart00-component': 	gChart00Component,
	}
    
}).mount('#app')

 
//뷰인스턴스 생성 및 컴포넌트들 저장

