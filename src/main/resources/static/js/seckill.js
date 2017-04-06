/**
 * Created by <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a> on 17-4-5.
 */

// js模块化, 可以防止污染global环境, 还可以方便其他模块化
    // 在进行页面设计的时候先规划好整个流程是很有帮助的
var seckill = {
        detail: {
            init: function (params) {
                console.log("reach init");
                // 使用jQuery的cookie插件取出名为userPhone的值
                var userPhone = $.cookie('userPhone');
                var startTime = params.startTime;
                var endTime = params.endTime;
                var productId = params.productId;

                if (!seckill.validatePhone(userPhone)) {
                    // 钱字号的写法是jQuery的选择器, 也就是能够选择页面上的一个元素
                    var userPhoneModal = $('#userPhoneModal');
                    // 设置模态窗口的属性
                    userPhoneModal.modal({
                        show: true, // 显示弹出层
                        backdrop: 'static', // 禁止点击关闭事件, 也就是窗口没有'x'
                        keyboard: false // 关闭键盘事件, 防止从键盘esc等关闭窗口
                    });
                    $('#userPhoneBtn').click(function () {
                        // 获得输入框的值
                        var userPhone = $('#userPhone').val();
                        if (seckill.validatePhone(userPhone)) {
                            // 电话号码写入cookie, 设置过期时间, 适用范围等
                            $.cookie('userPhone', userPhone, {expires: 7, path: '/seckill'})
                            // 重新加载页面, 会顺带触发重新执行seckill.detail.init方法
                            window.location.reload();
                        } else {
                            // 先隐藏, 填好内容后, 再出现(show), 会有一个动态效果(依靠show中的毫秒延迟数实现)
                            $('#callbackMsg')
                                .hide()
                                .html('<label class="label label-danger">手机号填写错误!</label>')
                                .show(300);
                        }
                    });
                }
            }
        },

        // 验证手机号
        validatePhone: function (phone) {
            if (phone && phone.length == 11 && !isNaN(phone)) {
                return true;
            } else {
                return false;
            }
        }
    };