<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">

        <title>Hello, world!</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var max_fields = 10; //maximum input boxes allowed
                var wrapper = $(".input_fields_wrap"); //Fields wrapper
                var add_button = $(".add_field_button"); //Add button ID

                var x = 1; //initlal text box count
                $(add_button).click(function (e) { //on add input button click
                    e.preventDefault();
                    if (x < max_fields) { //max input box allowed
                        x++; //text box increment
                        $(wrapper).append('<div><input type="text" name="number[]" placeholder="number">\n' +
                            '            <input type="text" name="company[]" placeholder="company"><a href="#" class="remove_field">Remove</a></div>'); //add input box
                    }
                });

                $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                    e.preventDefault();
                    $(this).parent('div').remove();
                    x--;
                })
            });
        </script>
    </head>
    <body>


    <div class="container">
        <div class="row justify-content-md-center">
            <div style="margin-top:15px; border-right: 1px dashed #333;" class="col col-md-4">

                <h6 style="color:  crimson">[<@security.authentication property="principal.username" />
                    : <@security.authentication property="authorities" /> ]</h6>
                <form method="GET" action="/personalinfo" style="border-bottom: 1px dashed #333;padding: 10px">
                    <input class="btn btn-primary" type="submit" value="Personal information"/>
                </form>
                <@security.authorize  access="hasRole('BOOKING_MANAGER')">
                    <form method="POST" action="/uploadFile" enctype="multipart/form-data"
                          style="border-bottom: 1px dashed #333; padding: 10px">
                        <input type="file" name="file"/>
                        <input class="btn btn-primary" type="submit" value="Submit"/>
                    </form>
                    <form method="POST" action="/pdf" style="border-bottom: 1px dashed #333;padding: 10px">
                        <input class="btn btn-primary" type="submit" value="generate pdf for all users">
                    </form>
                    <form method="GET" action="/getallusers" style="border-bottom: 1px dashed #333;padding: 10px">
                        <input class="btn btn-primary" type="submit" value="Get all users"/>
                    </form>
                    <form method="GET" action="/getuserbyid" style="border-bottom: 1px dashed #333;padding: 10px">
                        <input class="btn btn-primary" type="submit" value="Get user by id"/>
                        <input type="text" name="id" placeholder="id"/>
                    </form>
                    <form method="GET" action="/add" style="border-bottom: 1px dashed #333;padding: 10px">
                        <input class="btn btn-primary" type="submit" value="Add user" disabled/>
                    </form>
                </@security.authorize>
                <form method="GET" action="/logout" style="border-bottom: 1px dashed #333;padding: 10px">
                    <input class="btn btn-primary" type="submit" value="Logout"/>
                </form>
            </div>

            <div class="col col-md-8">
                <#nested>
            </div>
            <div>
                <p>MTS      numberPrefix:099 | cost of transferring:100</p>
                <p>KYIVSTAR numberPrefix:068 | cost of transferring:200</p>
                <p>LIFECELL numberPrefix:066 | cost of transferring:300</p>
                <p>Other cost of transferring:400</p>
            </div>
        </div>
    </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>

