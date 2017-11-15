<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <script src="http://localhost/test/static/js/jquery.min.js"></script>
    <script type="text/javascript">
        var _submit = function () {
            $.ajax({
                url:         "http://localhost/test/hello/del" ,
                type:        "DELETE",
                contentType: 'application/json',
                dataType:    "json",
                async:       true,
                timeout:     60000,
                data:        JSON.stringify({code:"123456"}),
                success: function (data) {

                    console.log("请求返回data："+data);

                },
                error: function(xhr, type){

                }
            })
        }
    </script>

</head>
<body>
    ${helloDubbo}

    <form action="http://localhost/test/hello/del"  method="post" >

        <input type="text" name="_method" value="DELETE">
        <input type="text" name="code" name="ssss">
        <input type="button" name="btn" value="提交" onclick="_submit()">
    </form>

</body>
</html>