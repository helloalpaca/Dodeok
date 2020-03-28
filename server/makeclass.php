<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $userid = isset($_POST['id']) ? $_POST['id'] : '';
  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $grade = isset($_POST['grade']) ? $_POST['grade'] : '';
  $classcode = isset($_POST['classcode']) ? $_POST['classcode'] : '';

  echo $userid;
  echo $classname;

  if($userid !="" and $classname !="" and $grade !="" and $classcode !=""){
    $sql = "insert classroom values('$userid','$classname','$grade','$classcode')";
    $result = mysqli_query($connect,$sql);

    $sql2 = "insert into numbers(classname,unit,numbers) values('$classname',0,0)";
    $result2 = mysqli_query($connect,$sql2);

    if($result){
      echo "sql 성공";
    }
    else{
      echo "sql 실패";
      echo mysqli_error($connect);
    }
  }
  else{
    echo "데이터를 입력하세요";
  }

  mysqli_close($connect);
?>

<?php

  $android = strpos($_SERVER['HTTP_USER_AGENT'],"Android");
  if(!$android){
?>
  <html>
  <body>
    <form action="<?php $_PHP_SELF ?>" method="POST">
         user id: <input type = "text" name = "id" />
         classname: <input type = "text" name = "classname" />
         grade : <input type = "number" name = "grade" />
         classcode: <input type = "text" name = "classcode" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
