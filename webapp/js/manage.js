var app = new Vue({
	// Vue绑定元素app
	el: '#app',
	// 这里写数据
	data: {},
	// 加载网页时处理逻辑
	beforeMount() {
		// 检查用户是否已登录,否则跳转到登录页面
		axios.get('/api/user/login').then(res => {
			if (res.data.code != 101) {
				this.gotoLogin();
			}
		});
	},
	// 这里写方法
	methods: {
        // 跳转到登录页面
		gotoLogin() {
			window.location.href = '/';
		},
        // 用户注销事件
		onLogout() {
			axios.delete('/api/user/login').then(res => {
				this.gotoLogin();
			});
		},
	},
});
