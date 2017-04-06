<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
<#include "common-head.ftl">
</head>

<body>
<div class="container">
    <div class="panel text-center">
        <div class="panel-heading">
            <h2>${productDO.name}</h2>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
            <#--时钟图标-->
                <span class="glyphicon glyphicon-time"></span>
            <#--计时面板-->
                <span id="countdown-message"></span>
            </h2>
        </div>
    </div>
</div>
<#--模态窗口, 用于记录用户手机号-->
<div id="userPhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">
                    <span class="glyphicon glyphicon-phone"></span>
                    联系电话
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 clo-xs-offset-2">
                        <input type="text" name="userPhone" id="userPhone"
                               placeholder="手机号, 例如18810374312" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            <#--占位, 用于js返回结果-->
                <span class="glyphicon" id="callbackMsg"></span>
                <button type="button" id="userPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    Submit
                </button>
            </div>
        </div>
    </div>
</div>
</body>

<#include "common-script.ftl">
<#--引入额外的jquery-cookie和jquery-countdown插件-->
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="//cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<#--引入自定义的js-->
<script src="/js/seckill.js"></script>
<script type="text/javascript">
    // 使用jQuery立即执行
    $(function () {
        // 这里参数传入的是一个对象
        seckill.detail.init({
            productId: ${productDO.id?c},
            startTime: '${productDO.startTime?long?c}',
            endTime: '${productDO.endTime?long?c}'
        })
    });
</script>

</html>