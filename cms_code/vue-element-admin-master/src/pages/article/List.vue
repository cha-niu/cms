<template>
	<div class="article_list">
	<!-- 按钮 -->
	<div class="btns">
		<el-button type="primary" size="small" @click="toPublishArticle">发布文章</el-button>
	</div>
	<!-- 表格 -->
	<el-table :data="articles" style="width: 100%">
		<el-table-column prop="id" label="编号" width="180">
		</el-table-column>
		<el-table-column prop="title" label="标题" width="180">
		</el-table-column>
		<el-table-column prop="authorId" label="作者">
		</el-table-column>
		<el-table-column prop="category.name" label="所属栏目">
		</el-table-column>
		
		<el-table-column fixed="right" align="center" label="操作" width="150">
			<template slot-scope="scope">
				<el-button @click="toReview(scope.row)" type="text" size="small">查看</el-button>
				<el-button @click="toEdit(scope.row)" type="text" size="small">编辑</el-button>
				<el-button @click="toDelete(scope.row.id)" type="text" size="small">删除</el-button>
			</template>
		</el-table-column>

	</el-table>

	<!-- 分页 -->
	</div>
</template>

<script>
	import request from '@/utils/request'
	export default {
		//为模板提供数据
		data(){
			return {
				articles:[]
			}
		},
		//生命周期
		created(){
			this.reloadData();
		},
		//方法，时间处理函数
		methods:{
			reloadData(){
				//调用ajax查询所有的文章数据
				request.get("http://localhost:8989/article/cascadeFindAll")
					.then(result=>{this.articles = result.data;})
			},

			toPublishArticle(){
			// 跳转到编辑页面
			this.$router.push({path:'/article/editor'})
			},
			toReview(record){

			},
			toEdit(record){
				this.$router.push({path:'/article/editor', query:record})
			},
			toDelete(recordId){
				//alert(recordId)
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning', center: true })
					.then(
						//() => { this.$message({ type: 'success', message: '删除成功!' }); }
						()=>{
							let url = "http:localhost:8989/article/deleteById"
							request.get(url,{params:{id} })
								//异步做一个通知
								.then(response =>{
									this.$message({message:response.message,type:"success"}) 
									//重载数据
									this.reloadData();
								}) 
						}
					)
			}

		}
	}
</script>

<!-- scope 表示样式只对当前页面有效 -->
<style scoped>

</style>


