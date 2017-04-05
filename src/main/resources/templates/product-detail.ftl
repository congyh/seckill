<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <#include "common-head.ftl">
</head>

<body>
    <div class="container">
        <div class="panel">
            <div class="panel-heading">
                <h2>${product.name}</h2>
            </div>
            <div class="panel-body">

            </div>
        </div>
    </div>
</body>

<#include "common-script.ftl">
<#--引入额外的jquery-cookie和jquery-countdown插件-->
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="//cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<#--引入自定义的js-->
<script src="../static/js/seckill.js"></script>
<script type="javascript">
    // 使用jQuery立即执行
    $(function () {
        // 这里参数传入的是一个对象
        seckill.detail.init({
            productId: ${product.id},
            startTime: ${product.startTime?datetime},
            endTime: ${product.endTime?datetime}
        })
    });
</script>

</html>