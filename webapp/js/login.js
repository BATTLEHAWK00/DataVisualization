var app = new Vue({
	// Vue绑定元素app
	el: '#app',
	// 这里写数据
	data: {
		// 登录表单数据
		loginForm: {
			username: '',
			passwd: '',
			vericode: '',
		},
		vericodeUrl: '/api/vericode',
	},
	// 加载网页时处理逻辑
	beforeMount() {
		// 检查用户是否已登录,是则跳转到后台管理
		axios.get('/api/user/login').then(res => {
			if (res.data.code == 101) {
				this.gotoManage();
			}
		});
	},
	computed: {
		// 表单验证,检查无效值
		formValidation() {
			if (this.loginForm.username.length < 4) return '用户名至少为4位!';
			else if (this.loginForm.passwd.length < 6)
				return '密码长度至少为6位!';
			else if (this.loginForm.vericode.length < 3) return '验证码无效!';
			return 0;
		},
	},
	// 这里写方法
	methods: {
		// 跳转到后台管理
		gotoManage() {
			window.location.href = '/manage.html';
		},
		// 切换验证码
		changeVericode() {
			this.vericodeUrl = '/api/vericode?num=' + Math.random(); // + Math.random();
			this.loginForm.vericode = '';
		},
		// 比对验证码
		checkVericode(action) {
			var that = this;
			axios
				.post(
					'/api/vericode',
					Qs.stringify({
						code: that.loginForm.vericode,
					})
				)
				.then(res => {
					if (res.data.code == 0) {
						action();
					} else {
						alert('验证码错误!');
						this.changeVericode();
					}
				});
		},
		// 处理登录逻辑
		onLogin() {
			// 先检查表单
			if (this.formValidation != 0) {
				alert(this.formValidation);
				return;
			}
			// 再比对验证码
			this.checkVericode(() => {
				// 调用登录接口,处理登录逻辑
				var that = this;
				axios
					.post(
						'/api/user/login',
						Qs.stringify({
							username: that.loginForm.username,
							passwd: that.loginForm.passwd,
						})
					)
					.then(res => {
						console.log(res);
						if (res.data.code) {
							alert('登陆失败!信息:' + res.data.msg);
							return;
						}
						alert('登陆成功!');
						that.gotoManage();
					})
					.catch(err => {
						console.log(err);
						if (err.data) {
							alert('登陆失败!信息:' + err.data.msg);
							return;
						}
						alert('登陆失败,内部错误!');
					});
			});
		},
	},
});
