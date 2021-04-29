var app = new Vue({
	el: '#app',
	data: {
		// 登录表单数据
		loginForm: {
			username: '',
			passwd: '',
			vericode: '',
		},
		vericodeUrl: '/api/vericode',
	},
	methods: {
		changeVericode() {
			this.vericodeUrl = '/api/vericode?num=' + Math.random(); // + Math.random();
		},
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
		// 处理登录按钮点击事件
		onLogin() {
			this.checkVericode(() => {
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
					})
					.catch(err => {
						console.log(err);
						if (err.data) {
							alert('登陆失败!信息:' + err.data.msg);
							return;
						}
						alert('登陆失败!');
					});
			});
		},
	},
});
