<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <tr>
                                <td width="10">
                                    <div class="avatar-img">
                                        <img class="img-circle" src="${picture}" />
                                    </div>
                                </td>
                                <td class="align-middle">
                                    ${name}
                                </td>
                                <td class="align-middle">
                                    ${job}
                                </td>
                                <td  class="align-middle">
                                    <#if last_login_time??>
                                        Last Login:  ${last_login_time}
                                        <br>
                                        <small class="text-muted">${last_login_time_passed}</small>
                                    <#else>
                                        Has not logged yet
                                    </#if>
                                </td>
                            </tr>
                            <tr>
                                <td colspan=4>
                                    <div>
                                        <form action="" method="post">
                                            <input type="hidden" name="action" value="submit" />
                                            <input type="hidden" name="user_id" value="1" />
                                            <input type="submit" value="yes" name="submit" class="btn btn-success">
                                            <input type="submit" value="no" name="submit" class="btn btn-danger">
                                        </form>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
