<template>
    <div class="category_list">
        <!-- 按钮 -->
        <div class="btns">
            <el-button type="primary" size="small" @click="toAddCategory">添加栏目</el-button>
            <el-button type="danger" size="small" @click="toBatchDelete(ids)">批量删除</el-button>
        </div>

        {{ids}}
        <!-- 表格 -->
        <el-table :data="categorys" style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="id" label="栏目编号" width="180">
            </el-table-column>
            <el-table-column prop="name" label="栏目名称" width="180">
            </el-table-column>
            <el-table-column prop="description" label="栏目描述">
            </el-table-column>
            <el-table-column prop="no" label="栏目序号">
            </el-table-column>
            <el-table-column prop="parentId" label="父级栏目">
            </el-table-column>
            
            <el-table-column fixed="right" align="center" label="操作" width="150">
                <template slot-scope="scope">
                    <el-button @click="toReview(scope.row)" type="text" size="small">查看</el-button>
                    <el-button @click="toEdit(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="toDelete(scope.row.id)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 模态框 -->
        <el-dialog title="新增栏目信息" :visible.sync="dialogFormVisible">
            {{form}}
            <el-form :model="form">
                <el-form-item label="栏目名称" label-width="80px">
                    <el-input v-model="form.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="栏目描述" label-width="80px">
                    <el-input type="textarea" v-model="form.description" auto-complete="off"></el-input>
                </el-form-item>
                
                <el-form-item label="所属栏目" label-width="80px">
                    <el-select clearable v-model="form.parentId" placeholder="请选择所属栏目">
                        <el-option v-for="c in categorys" :key="c.id" :label="c.name" :value="c.id"></el-option>
                    </el-select>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="submitHandler">确 定</el-button>
            </div>
        </el-dialog>


    </div>
</template>

<script>
    import request from '@/utils/request'
    import qs from 'querystring'
	export default {
		//为模板提供数据
		data(){ 
            return { 
                dialogFormVisible:false,
                form:{},
                categorys:[], 
                ids:[]
            } 
        },
        
        //生命周期
        created(){ this.reloadData(); },
        
		//方法，时间处理函数
		methods:{
			reloadData(){
				//调用ajax查询所有的文章数据
				request.get("http://localhost:8989/category/findAll")
					.then(result=>{this.categorys = result.data;})
			},

			toAddCategory(){
                //置空
                this.form= {}
			    // 跳转到编辑页面
			    this.dialogFormVisible = true;
			},
			toReview(record){

			},
			toEdit(record){
                this.dialogFormVisible = true;
                this.form = record;
				//this.$router.push({path:'/category/editor', query:record})
            },

            toBatchDelete(ids){
                let url = "http://127.0.0.1:8989/category/batchDelete" 
                request.post(url,this.ids )
                .then(response=>{
                    //提示信息
                    this.$message({
                        message:response.message,
                        type:"success"      
                    })
                    this.reloadData();
                })
            },

			toDelete(id){
				//alert(recordId)
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning', center: true })
					.then(
						//() => { this.$message({ type: 'success', message: '删除成功!' }); }
						()=>{
							let url = "http://127.0.0.1:8989/category/deleteById"
							request.get(url,{params:{id} })
								//异步做一个通知
								.then(response =>{
									this.$message({message:response.message, type:"success"}) 
									//重载数据
									this.reloadData();
								}) 
						}
					)
            },
            
            submitHandler(){
                //alert(qs.stringify(this.form) )
                request.request({
                    url:'http://127.0.0.1:8989/category/saveOrUpdate',
                    method:'post',
                    header:{
                        'Content-Type':'application/x-wwww-form-urlencoded'
                    },
                    data:qs.stringify(this.form)
                })
                .then(response=>{
                    //提示信息
                    this.$message({
                        message:response.message,
                        type:"success"
                    })
                    //关闭模态框
                    this.dialogFormVisible = false;
                    //重载数据
                    this.reloadData();
                })
            },

            handleSelectionChange(val){
                //val 就是所有选中了的栏目对象的集合,我们只要获得这个对象的id 
                this.ids = val.map(item => item.id);
            }
		}
	}
</script>

<style scoped>

</style>