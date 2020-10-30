function getZuulUrl() {
    if (window.location.href.toLowerCase().indexOf("localhost") > -1)
        return "http://localhost:1013/api/"; //本地
    else
        return "http://45.32.121.86:1013/api/";//线上
}

window.alert = function (str) {
    layer.msg(str);
};

// ajax封装
function ajax(url, data, success,isAuth, async, type, dataType,isLoading) {
    type = type || 'post';//请求类型
    async = async || true;//异步请求
    if (isAuth === undefined) {
        isAuth=true
    }
    if (isLoading == undefined) {
        isLoading=true;
    }
    success = success || function (result) {
        if (result.hasError && result.errorMessage != null) {
            alert(result.errorMessage);
        }
    };
    var complete = complete || function (XMLHttpRequest, textStatus) {
        layer.closeAll('loading');
        var result = $.parseJSON(XMLHttpRequest.responseText);
        if (result.hasError && result.errorMessage != null) {
            alert(result.errorMessage);
        }
    };
    var error = function (result) {
        setTimeout(function () {
            if (result.status === 401) {
                location.href = "/user/join";
            }
            else if (result.status === 404) {
                alert('请求失败，请求未找到');
            } else if (result.status === 503) {
                alert('请求失败，服务器内部错误');
            } else {
                alert('请求失败,网络连接超时');
            }
        }, 500);
    };
    $.ajax({
        url: getZuulUrl() + url,
        data: data,
        type: type,
        dataType: dataType,
        async: async,
        success: success,
        contentType:"application/json; charset=utf-8",
        error: error,
        jsonpCallback: 'jsonp' + (new Date()).valueOf().toString().substr(-4),
        beforeSend: function (request) {
            if (isLoading) {
                layer.msg('加载中', {
                    icon: 16,
                    shade: 0.01
                });
            }
            if (isAuth) {
                var token = $.cookie('YYYQUSERTOKEN');
                if (token ==null) {
                    request.setRequestHeader("YYYQUSERTOKEN", "");
                } else {
                    request.setRequestHeader("YYYQUSERTOKEN", token);
                }
            }
        },
        complete:complete
    });
}

// ajax提交(post方式提交)
function post(url, data, success,isAuth,isLoading) {
    ajax(url, data, success, isAuth,false, 'post', 'json',isLoading);
}

// ajax提交(get方式提交)
function get(url, success) {
    ajax(url, {}, success, true,false, 'get', 'json');
}

// jsonp跨域请求(get方式提交)
function jsonp(url, success) {
    ajax(url, {}, success, true,false, 'get', 'jsonp');
}