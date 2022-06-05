let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        $("#btn-login").on("click", ()=>{
            this.login();
        });
    },

    save: function(){
        let data = {
            username: $("#username").val(),
            name: $("#name").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            phone: $("#phone").val()
        };

        $.ajax({
            type: "POST",
            url: "/auth/signup",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            location.href = "/";
            alert("회원가입이 완료되었습니다")
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    },

    login: function(){
            let data = {
                username: $("#username").val(),
                password: $("#password").val(),
            };

            $.ajax({
                type: "POST",
                url: "/auth/login",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function(resp){
                var token = localStorage.setItem("token",resp.data.token);
                alert(resp.data.token)
                location.href = "/";
                alert("로그인이 완료되었습니다")
            }).fail(function(error){
                alert(JSON.stringify(error));
            });

        },


}

index.init();
