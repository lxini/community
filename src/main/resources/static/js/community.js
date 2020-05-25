function post() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:"application/json",
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            type:1
        }),
        success:function (data) {
            if (data.code == 200){
                $("#comment_section").hide();
            }else{
                if(data.code == 2003){
                    let isAccepted = confirm(data.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=3874868d0b7efd58df42&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(data.message);
                }
            }
        },
        dataType:"json"
    })
}