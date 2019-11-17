<template>
  <div class="article_editor">
    <el-button type="text" @click="back">返回</el-button>

    {{form}}
    <el-form ref="form" :model="form" label-width="80px">
      <!-- <el-form-item label="所属栏目">
        <el-select v-model="form.categoryId" placeholder="请选择所属栏目">
          <el-option label="IT" value="3"></el-option>
          <el-option label="娱乐" value="4"></el-option>
        </el-select>
      </el-form-item> -->

      <el-form-item label="所属栏目" label-width="80px">
          <el-select clearable v-model="allCategorys" placeholder="请选择所属栏目">
            <el-option v-for="c in categorys" :key="c.id" :label="c.name" :value="c.id"></el-option>
          </el-select>
       </el-form-item>


      <el-form-item label="文章标题">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="正文">
        <el-input type="textarea" v-model="form.content" rows="10"></el-input>
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" @click="onSubmit">发布</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>
<script>
import request from '@/utils/request'
import qs from 'qs'
import { url } from 'inspector';
export default {
  data(){
    return {
      form:{},
      categorys:{}
    }
  },
  created(){
    //alert(JSON.stringify(this.$route.query));
    this.form = this.$route.query;  
  },
  methods:{
    back(){
      this.$router.go(-1);
    },
    onSubmit(){
      //alert(JSON.stringify(this.form));//后台要求的是字符串但是如果前台传json就不合理
      //alert(qs.stringify(this.form));
      //通过ajax将前端收集的数据this.form发送给服务接口
      
      request.request({
        method:"post",
        url:"http://127.0.0.1:8989/article/saveOrUpdate",
        // 借助qs库，将data转换为查询字符串
        data:qs.stringify(this.form),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded'
        }
      }).then(result=>{
          this.$message({
            message:result.message,
            type:"success"
          });
          //alert(result.message);
          this.back();
        })
    },


  }
}
</script>
