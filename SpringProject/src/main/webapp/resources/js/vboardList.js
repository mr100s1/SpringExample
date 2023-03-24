//import { createApp } from 'vue';
//import App from './App.vue';

import topMenuComponent 	from './components/topMenuComponent.js'; 	// 상단 메뉴 import 
import oneComponent 		from './components/oneComponent.js'; 		// 컴포넌트1 import 
import vboardListComponent 	from './components/vboardListComponent.js'; // 게시글 목록 import 

//각 컴포넌트들을 변수에 할당

/*

*/
var app = Vue.createApp({
    data() {
        return {
            message: 'Hello~ Bryan~'
        }
    },
	components: {
		// 'one-component': oneComponent,
		// 'topmenu-component': topMenuComponent,
		'vboardlist-component': vboardListComponent
	}
    
}).mount('#app')

 
//뷰인스턴스 생성 및 컴포넌트들 저장

