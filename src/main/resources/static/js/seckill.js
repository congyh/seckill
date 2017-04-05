/**
 * Created by <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a> on 17-4-5.
 */

// js模块化, 可以防止污染global环境, 还可以方便其他模块调用
var seckill = {
    detail: {
        init: function(params) {
            var userPhone = $.cookie('userPhone');
            var startTime = params.startTime;
            var endTime = params.endTime;
            var productId = params.productId;

            if (!seckill.validatePhone(userPhone)) {
                // 钱字号的写法是jQuery的选择器, 也就是能够选择页面上的一个元素
                var userPhoneModal = $('#userPhoneModal');
                userPhoneModal.modal({
                    show: true, // 显示弹出层
                    backdrop: 'static', // 禁止关闭
                    keyboard: false // 关闭键盘事件
                });
                $('userPhoneBtn').click(function () {
                    var userPhone = $('#userPhone').val();
                    if (seckill.validatePhone(userPhone)) {
                        // TODO 电话需要写入cookie
                        // 刷新页面
                        window.location.reload();
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