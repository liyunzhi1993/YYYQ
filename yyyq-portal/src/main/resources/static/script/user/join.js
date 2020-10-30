new Vue({
    el: '#join',
    data: {
        loginUrl: 'auth/user/login',
        registerUrl:'auth/user/register',
        sendCodeUrl:'auth/message/sendRegisterCode'
    },
    mounted:function () {
        $("[name=nav-join]").click(function () {
            $("[role=tabpanel]").removeClass("active");
            $("#" + $(this).attr("data")).addClass("active");
            $("[name=nav-join]").show();
            $(this).hide();
        });
        if (window.location.href.toLowerCase().indexOf("#register") > -1) {
            $("[data='register']").click();
        }
    },
    methods: {
        login: function () {
            var vm = this;
            var data = $("#loginForm").serializeJSON();
            if (data.phone === "") {
                alert("手机号不能为空");
                return;
            }
            if (data.password === "") {
                alert("密码不能为空");
                return;
            }
            post(vm.loginUrl, JSON.stringify(data), function (result) {
                if (!result.hasError) {
                    $.removeCookie('YYYQUSERTOKEN', { path: '/' });
                    alert("登录成功");
                    var day=7;
                    if (data.rememberMe) {
                        day=30;
                    }
                    $.cookie('YYYQUSERTOKEN', result.data, { expires: day,path: '/'});
                    setTimeout(function () {
                        location.href = "/user/index";
                    },1000)
                }
            },false);
        },
        register:function () {
            var vm = this;
            var data = $("#registerForm").serializeJSON();
            if (data.phone === "") {
                alert("手机号不能为空");
                return;
            }
            if (data.regPassword !== data.regConfirmPassword) {
                alert("确认密码不一致");
                return;
            }
            post(vm.registerUrl, JSON.stringify(data), function (result) {
                if (!result.hasError) {
                    $.removeCookie('YYYQUSERTOKEN', { path: '/' });
                    alert("注册成功");
                    $.cookie('YYYQUSERTOKEN', result.data,  { expires: day,path: '/'});
                    setTimeout(function () {
                        location.href = "/user/index";
                    },1000)
                }
            },false);
        },
        sendCode:function () {
            var vm = this;
            var data = $("#registerForm").serializeJSON();
            if (data.phone === "") {
                alert("手机号不能为空");
                return;
            }
            post(vm.sendCodeUrl, data.phone, function (result) {
                if (!result.hasError) {
                    alert("成功发送验证码");
                }
            },false);
        }
    }
});