/**
 * Created by <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a> on 17-4-5.
 */

// js模块化, 可以防止污染global环境, 还可以方便其他模块化
    // 在进行页面设计的时候先规划好整个流程是很有帮助的
var seckill = {

        URL: {
            // 根据商品id拼接获取秒杀地址的URL
          seckillUrl: function (productId) {
              return '/seckill/products/' + productId + '/seckillUrl';
          },

          encryptedSeckillUrl: function (productId, md5) {
              return '/seckill/products/' + productId + '/' + md5;
          }
        },

        detail: {
            init: function (params) {
                console.log("reach init");
                // 使用jQuery的cookie插件取出名为userPhone的值
                var userPhone = $.cookie('userPhone');

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

                // 如果手机号已经填写, 则继续以下逻辑
                var gmtStart = params['gmtStart'];
                var gmtEnd = params['gmtEnd'];
                var seckillProductId = params['seckillProductId'];
                seckill.countdown(seckillProductId, new Date(), gmtStart, gmtEnd);
            }
        },

        // 验证手机号
        validatePhone: function (userPhone) {
            if (userPhone && userPhone.length == 11 && !isNaN(userPhone)) {
                return true;
            } else {
                return false;
            }
        },

        // 倒计时函数
        countdown: function (seckillProductId, gmtNow, gmtStart, gmtEnd) {
            var countdownMessage = $('#countdown-message');
            if (gmtNow.getTime() > gmtEnd) {
                countdownMessage.html("秒杀结束, 感谢关注!");
            } else if (gmtNow.getTime() < gmtStart) {
                // 这里实际上是对countdownMessage这个元素进行了事件绑定,
                // countdown函数接收一个时间, 然后每隔1s执行一次回调函数,
                // 回调函数接收的参数就是前面传入的时间.
                countdownMessage.countdown(gmtStart, function (event) {
                    var countdownTime = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                    countdownMessage.html(countdownTime);
                }).on('finish.countdown', function () {
                    seckill.executeSeckill(seckillProductId, countdownMessage, gmtNow, gmtStart, gmtEnd);
                });
            } else {
                seckill.executeSeckill(seckillProductId, countdownMessage, gmtNow, gmtStart, gmtEnd);
            }
        },

        executeSeckill: function (seckillProductId, domNode, gmtNow, gmtStart, gmtEnd) {
            // 拼接后先隐藏起来, 因为现在还不确定秒杀是否开启
            domNode.hide()
                .html('<button class="btn btn-primary btn-lg" id="seckillBtn">' +
                    '开始秒杀' +
                    '</button>');
            $.get(seckill.URL.seckillUrl(seckillProductId), function (result) {
                if (result) {
                    var seckillUrl = result['data'];
                    if (seckillUrl) {
                        var md5 = seckillUrl['md5'];
                        var encryptedUrl = seckill.URL.encryptedSeckillUrl(seckillProductId, md5);
                        console.log(encryptedUrl);
                        // 绑定事件仅触发一次, 且为click事件
                        $('#seckillBtn').one('click', function () {
                            // $(this)代表选择当前对象, 这句话的意思是点击后立即变为不可点击.
                            $(this).addClass('disabled');
                            // 下一步要完成的操作是用ajax异步发送秒杀请求. 并展示执行结果
                            $.get(encryptedUrl, function (result) {
                                domNode.html('<span class="label label-success">'
                                    + result['message']
                                    + '</span>');
                            });
                        })
                    } else {
                        // 注意: 走到这个分支是由于客户端和服务器的计时差异导致的
                        // var now = seckillUrl['now'];
                        // var start = seckillUrl['start'];
                        // var end = seckillUrl['end'];
                        seckill.countdown(seckillProductId, gmtNow, gmtStart, gmtEnd);
                    }
                }
            });
            domNode.show();
        }
    };