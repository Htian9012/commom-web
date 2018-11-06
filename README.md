# commom-web
 * 技术选型spring mvc ,spring security + mybatis +jwt
 * 前端采用vue / element，后台提供供前端绑数据的接口管理页面
 * 很久前做的一个demo，仅展示用，欢迎一起改进交流~~！
### 项目结构
 ![图片](https://raw.githubusercontent.com/HTian1992/commom-web/master/doc/A001.png)
  
### 效果展示
  * 项目后台接口地址：(http://120.78.82.33:8088/swagger-ui.html)
    * 步骤1,通过登录帐号名/密码（lizehao/123456,admin/admin），请求登录接口获得token,
     <Br/>  请求headers添加 Content-Type=application/json;X-Requested-With=XMLHttpRequest
    ![示例](https://raw.githubusercontent.com/HTian1992/commom-web/master/doc/B001.png)
    * 步骤2,请求附带 X-Authorization = Bearer + " " + token
    ![示例](https://raw.githubusercontent.com/HTian1992/commom-web/master/doc/B002.png)
    ![示例](https://raw.githubusercontent.com/HTian1992/commom-web/master/doc/B003.png)
  * 前端管理项目，完善中..
   ![示例](https://raw.githubusercontent.com/HTian1992/commom-vue/master/doc/O002.png)
   ![示例](https://raw.githubusercontent.com/HTian1992/commom-vue/master/doc/O001.png)



  
