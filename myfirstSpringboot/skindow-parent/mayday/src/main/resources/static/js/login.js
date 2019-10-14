$('#login-button').click(function (event) {
    var userName = document.getElementById("userName").value;
    var pwd = document.getElementById("pwd").value;
    //修改密码请改此处
    if (userName === "xiaojuzi" && pwd === "930920") {
        event.preventDefault();
        $('form').fadeOut(500);
        $('.wrapper').addClass('form-success');
        requestFullScreen();
        setTimeout(function () {
            location.href = "BirthdayCake.html";
        }, 2000);
    }
    else {
        alert("输错了呢QAQ，请重新输入哦");
    }
});

function requestFullScreen() {
    var element = document.documentElement;
    var requestMethod = element.requestFullScreen || //W3C
        element.webkitRequestFullScreen || //Chrome等
        element.mozRequestFullScreen || //FireFox
        element.msRequestFullScreen; //IE11
    if (requestMethod) {
        requestMethod.call(element);
    }
    else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript !== null) {
            wscript.SendKeys("{F11}");
        }
    }
}
