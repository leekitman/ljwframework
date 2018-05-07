<%--
  Created by IntelliJ IDEA.
  User: LeeKITMAN
  Date: 2018/5/5
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理-创建客户</title>
    <script src="${BASE}/asset/lib/jquery-3.3.1.js"></script>
    <script src="${BASE}/asset/lib/plugins/jquery.form.js"></script>
</head>
<body>
<h1>创建客户界面</h1>
<form id="customer_form" enctype="multipart/form-data">
    <table>
        <tr>
            <td>客户名称：</td>
            <td>
                <input type="text" name="name" value="${customer.name}">
            </td>
        </tr>
        <tr>
            <td>联系人：</td>
            <td>
                <input type="text" name="contact" value="${customer.contact}">
            </td>
        </tr>
        <tr>
            <td>电话号码：</td>
            <td>
                <input type="text" name="telephone" value="${customer.telephone}">
            </td>
        </tr>
        <tr>
            <td>邮箱地址</td>
            <td>
                <input type="text" name="email" value="${customer.email}">
            </td>
        </tr>
        <tr>
            <td>照片</td>
            <td>
                <input type="file" name="photo" value="${customer.photo}">
            </td>
        </tr>
        <
        <button type="submit">保存</button>
    </table>
</form>
<script>
    /*页面加载完成后执行*/
    $(function(){
        $('#customer_form').ajaxForm({
            type:'post',
            url:'${BASE}/customer_create',
            success:function (data) {
                if(data){
                    location.href = '${BASE}/customer';
                }
            }
        })
    });
</script>
</body>
</html>
