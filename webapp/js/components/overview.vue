<template>
	<div>
		<v-chart
			style="width: 400px; height: 200px"
			:option="charts.cpuStatus"
		/>
	</div>
</template>
<script>
export default {
	mounted() {
		this.refreshData();
		setInterval(() => {
			this.refreshData();
		}, this.refreshRate);
	},
	methods: {
		refreshData() {
			let r = this.random(1, 100);
			this.charts.cpuStatus.series[0].data = [
				{ value: r, name: '已使用' },
				{ value: 100 - r, name: '空闲' },
			];
		},
		random(min, max) {
			return Math.floor(Math.random() * (max - min)) + min;
		},
	},
	data() {
		return {
			refreshRate: 2000,
			charts: {
				cpuStatus: {
					title: {
						text: '服务器运行状况',
						left: 'center',
					},
					series: [
						{
							name: '服务器运行状况',
							type: 'pie',
							radius: '55%',
							center: ['50%', '50%'],
							data: [],
						},
					],
				},
			},
		};
	},
};
</script>
<style></style>
