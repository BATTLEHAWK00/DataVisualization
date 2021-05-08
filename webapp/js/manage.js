const options = {
	moduleCache: {
		vue: Vue,
	},

	async getFile(url) {
		const res = await fetch(url);
		if (!res.ok)
			throw Object.assign(new Error(url + ' ' + res.statusText), { res });
		return await res.text();
	},

	addStyle(textContent) {
		const style = Object.assign(document.createElement('style'), {
			textContent,
		});
		const ref = document.head.getElementsByTagName('style')[0] || null;
		document.head.insertBefore(style, ref);
	},
};

const { loadModule } = window['vue2-sfc-loader'];
var app = new Vue({
	// Vue绑定元素app
	el: '#app',
	// 这里写数据
	data: {
		componentView: this.overview,
	},
	// 加载网页时处理逻辑
	beforeMount() {
		// 检查用户是否已登录,否则跳转到登录页面
		axios.get('/api/user/login').then(res => {
			if (res.data.code != 101) {
				this.gotoLogin();
			}
		});
	},
	mounted() {},
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
	components: {
		myCom: () => loadModule('./js/components/mycom.vue', options),
		overview: () => loadModule('./js/components/overview.vue', options),
	},
});
