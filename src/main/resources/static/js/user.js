let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{
            this.save();
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
}

index.init();
