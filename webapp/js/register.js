var app = new Vue({
	// Vue绑定元素app
	el: '#app',
	// 这里写数据
	data: {
		// 登录表单数据
		regForm: {
			username: '',
			passwd: '',
			passwdConfirm: '',
			email: '',
			phone: '',
			vericode: '',
		},
		vericodeUrl: '/api/vericode',
	},
	computed: {
		// 表单验证,检查无效值
		formValidation() {
			if (this.regForm.username.length < 4) return '用户名至少为4位!';
			else if (this.regForm.passwd.length < 6)
				return '密码长度至少为6位!';
			else if (this.regForm.passwd != this.regForm.passwdConfirm)
				return '确认密码不一致!';
			else if (!/^(\w){6,20}$/.exec(this.regForm.passwd))
				return '密码只能输入6-20个字母、数字、下划线';
			else if (!/^[1][3,4,5,7,8][0-9]{9}$/.exec(this.regForm.phone))
				return '手机号格式错误!';
			else if (
				!/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/.exec(
					this.regForm.email
				)
			)
				return '邮箱格式错误!';
			else if (this.regForm.vericode.length < 3) return '验证码无效!';
			return 0;
		},
	},
	// 这里写方法
	methods: {
		// 跳转到后台管理
		gotoLogin() {
			window.location.href = '/';
		},
		// 切换验证码
		changeVericode() {
			this.vericodeUrl = '/api/vericode?num=' + Math.random(); // + Math.random();
			this.regForm.vericode = '';
		},
		// 比对验证码
		checkVericode(action) {
			var that = this;
			axios
				.post(
					'/api/vericode',
					Qs.stringify({
						code: that.regForm.vericode,
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
		onRegister() {
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
						'/api/user/register',
						Qs.stringify({
							username: that.regForm.username,
							passwd: that.regForm.passwd,
							email: that.regForm.email,
							phone: that.regForm.phone,
						})
					)
					.then(res => {
						console.log(res);
						if (res.data.code) {
							alert('注册失败!信息:' + res.data.msg);
							return;
						}
						alert('注册成功!');
						that.gotoLogin();
					})
					.catch(err => {
						console.log(err);
						if (err.data) {
							alert('注册失败!信息:' + err.data.msg);
							return;
						}
						alert('注册失败,内部错误!');
					});
			});
		},
	},
});
