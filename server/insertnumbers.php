<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $sql2 = "insert into numbers(classname,unit,numbers) values('$classname',1,10)";
  $result2 = mysqli_query($connect,$sql2);

  $sql3 = "insert into numbers(classname,unit,numbers) values('$classname',2,10)";
  $result3 = mysqli_query($connect,$sql3);

  $sql4 = "insert into numbers(classname,unit,numbers) values('$classname',3,10)";
  $result4 = mysqli_query($connect,$sql4);

  $sql5 = "insert into numbers(classname,unit,numbers) values('$classname',4,10)";
  $result5 = mysqli_query($connect,$sql5);

  $sql6 = "insert into numbers(classname,unit,numbers) values('$classname',5,10)";
  $result6 = mysqli_query($connect,$sql6);

  $sql7 = "insert into numbers(classname,unit,numbers) values('$classname',6,10)";
  $result7 = mysqli_query($connect,$sql7);

  mysqli_close($connect);
?>

<?php

  $android = strpos($_SERVER['HTTP_USER_AGENT'],"Android");
  if(!$android){
?>
  <html>
  <body>
    <form action="<?php $_PHP_SELF ?>" method="POST">
         classname: <input type = "text" name = "classname" />
         unit: <input type = "text" name = "unit" />
         number: <input type = "text" name = "number" />
         type: <input type = "text" name = "type" />
         mission: <input type = "text" name = "mission" />
         url: <input type = "text" name = "url" />
         ready: <input type = "text" name = "ready" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
