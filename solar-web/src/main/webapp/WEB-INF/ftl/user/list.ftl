<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico" />

    <title></title>
</head>
<body>

<div>
    <form action="addUser" method="post">
        <div>
            <label>用户名</label>
            <input type="text" name="userName"  />
        </div>
        <div>
            <label>密码</label>
            <input type="password" name="passwd"  />
        </div>
        <div>
            <label>昵称</label>
            <input type="text" name="nickName"/>
        </div>
        <div>
            <label>年龄</label>
            <input type="text" name="age"/>
        </div>
        <div>
            <select name="sex">
                <option value="0">未知</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
        <div>
            <button  type="submit">添加</button>
        </div>
    </form>

</div>
<div>

    <table>
        <thead>
        <tr>
            <th>序号</th>
            <th>昵称</th>
            <th>年龄</th>
            <th>性别</th>
        </tr>
        </thead>
        <tbody>
        <#list list as userInfo>
        <tr>
            <td>${userInfo_index+1}</td>
            <td>${(userInfo.nickName)!''}</td>
            <td>${(userInfo.age)!''}</td>
            <td>
                <#switch userInfo.sex>
                    <#case 0>未知<#break >
                    <#case 1>男<#break >
                    <#case 2>女<#break >
                    <#default>
                </#switch>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>
</html>