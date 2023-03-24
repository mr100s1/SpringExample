//import { createApp } from 'vue';
//import App from './App.vue';

import oneComponent 	from './components/oneComponent.js'; 	// 컴포넌트1 import 
import twoComponent 	from './components/twoComponent.js'; 	// 컴포넌트2 import
import threeComponent 	from './components/threeComponent.js';	// 컴포넌트3 import
import topMenuComponent from './components/topMenuComponent.js'; 	// 컴포넌트1 import 

//각 컴포넌트들을 변수에 할당
const one_component = oneComponent;
const two_component = twoComponent;
const three_component = threeComponent;

/*

*/
var app = Vue.createApp({
    data() {
        return {
            message: 'Hello~ Bryan~'
        }
    },
	components: {
		'one-component': 		oneComponent,
		'two-component': 		twoComponent,
		'three-component': 		threeComponent,
		'topmenu-component': 	topMenuComponent
	}
    
}).mount('#app')

 
//뷰인스턴스 생성 및 컴포넌트들 저장

