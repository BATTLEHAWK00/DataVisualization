<template>
	<div class="wrap">
		<div class="header">数据可视化</div>
		<div class="nav">
			<ul>
				<li class="list-item">
					<h2 @click="onMenuClick()"><i></i>概览</h2>
					<div class="hide">
						<h5>1</h5>
						<h5>2</h5>
						<h5>3</h5>
						<h5>4</h5>
						<h5>5</h5>
						<h5>6</h5>
					</div>
				</li>
				<li class="list-item">
					<h2><i></i>图表管理</h2>
					<div class="hide">
						<h5>1</h5>
						<h5>2</h5>
						<h5>3</h5>
						<h5>4</h5>
						<h5>5</h5>
						<h5>6</h5>
					</div>
				</li>
				<li class="list-item">
					<h2><i></i>数据管理</h2>
					<div class="hide">
						<h5>1</h5>
						<h5>2</h5>
						<h5>3</h5>
						<h5>4</h5>
						<h5>5</h5>
						<h5>6</h5>
					</div>
				</li>
				<li class="list-item">
					<h2><i></i>账号管理</h2>
					<div class="hide">
						<h5>1</h5>
						<h5>2</h5>
						<h5>3</h5>
						<h5>4</h5>
						<h5>5</h5>
						<h5>6</h5>
					</div>
				</li>
			</ul>
		</div>
	</div>
</template>
<script>
export default {
	data() {
		return {};
	},
	beforeMount() {
		console.log(1);
	},
};
</script>
<style>
.wrap {
	width: 240px;
	height: 100%;
	background-color: #a0b1df;
}
.header {
	width: 100%;
	height: 65px;
	background-color: #b3bbd4;
	font-size: 24px;
	color: rgb(10, 9, 9);
	text-align: center;
	line-height: 65px;
}
.header-right {
	white-space: nowrap;
	/* 自动向右对齐 */
	margin-left: auto;
	font-size: 16px;
	float: none;
}
.nav {
	width: 220px;
	margin: 10px 5px 0;
}
.list-item {
	margin-bottom: 5px;
}
.list-item h2 {
	position: relative;
	padding: 20px 0;
	background-color: #486886;
	text-align: center;
	font-size: 16px;
	color: rgb(252, 248, 248);
	transition: 0.5s;
}
.list-item h2.on {
	background-color: #9099c4;
}
.list-item i {
	position: absolute;
	right: 10px;
	top: 16px;
	border: 8px solid transparent;
	border-left-color: rgb(15, 13, 13); /*triangle*/
	transform: rotate(0deg);
	transform-origin: 1px 8px;
	transition: 0.5s;
}
.list-item i.on {
	transform: rotate(90deg);
}
.hide {
	overflow: hidden;
	height: 0;
	transition: 0.5s;
}
.hide h5 {
	padding: 10px 0;
	background-color: #39365e;
	text-align: center;
	color: #fff;
	border-bottom: #6087e7;
}
</style>
