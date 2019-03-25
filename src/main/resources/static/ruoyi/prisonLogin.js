
$(function() {
    validateRule();
    $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green-login"});
	$('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
	
	//为小键盘的确定按钮绑定登录事件
	$("body").on("click", ".func.sure", function() {
		$("#btnSubmit").click();
    });
	//进入登录页显示小键盘
	$("#username").click();
});

$.validator.setDefaults({
    submitHandler: function() {
		login();
    }
});

function login() {
	$.modal.loading($("#btnSubmit").data("loading"));
	var username = $("input[name='username']").val().trim();
    var password = $("input[name='password']").val().trim();
//    var validateCode = $("input[name='validateCode']").val();
//    var rememberMe = $("input[name='rememberme']").is(':checked');
    $.ajax({
        type: "post",
        url: ctx + "prisonLogin",
        data: {
            "username": username,
            "password": password
//            "validateCode" : validateCode,
//            "rememberMe": rememberMe
        },
        success: function(r) {
            if (r.code == 0) {
                location.href = ctx + 'prisonIndex';
            } else {
            	$.modal.closeLoading();
//            	$('.imgcode').click();
            	$.modal.msg(r.msg);
            }
        }
    });
}

//校验规则-用户名密码非空校验
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}