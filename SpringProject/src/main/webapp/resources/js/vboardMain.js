//import { createApp } from 'vue';
//import App from './App.vue';

import topMenuComponent 	from './components/topMenuComponent.js'; 	// 상단 메뉴 import 
import oneComponent 		from './components/oneComponent.js'; 		// 컴포넌트1 import 
import vboardListComponent	from './components/vboardListComponent.js'; // 게시글 목록 import 

//각 컴포넌트들을 변수에 할당

/*

*/

var myrouter = new VueRouter({
	routes: [
		{	path:		'./vboardList',
			component:	vboardListComponent
		}
	]
});

var app = Vue.createApp({
    data() {
        return {
            message: 	'Hello~ Bryan~',
			boardList:  {},  // 리스트 데이터
			router:		myrouter,
        }
    },
	mounted() {
		this.fnGetList()
	},
	components: {
		// 'one-component'		: oneComponent,
		'topmenu-component'		: topMenuComponent,
		'vboardlist-component'	: vboardListComponent
	},
	
	methods: {
		fnGetList() {
		
			alert("fnGetList() 시작.....")
		
			this.requestBody = { // 데이터 전송
				keyword: this.keyword,
				page: this.page,
				size: this.size
			}

			this.$axios.get(this.$serverUrl + "/vboard/list", {
				params: this.requestBody,
				headers: {}
			}).then((res) => {      
				//*******************************************************************
				//서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
				//*******************************************************************
				this.boardList = res.data  
			}).catch((err) => {
				if (err.message.indexOf('Network Error') > -1) {
					alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
				}
			})
		}
	}
	
    
}).mount('#app')

app.component('nGetList', {
	template: vboardListComponent 
})

 
//뷰인스턴스 생성 및 컴포넌트들 저장

