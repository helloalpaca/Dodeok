<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $sql = "delete from missions where classname = '$classname'";
  $result = mysqli_query($connect,$sql);

  $sql2 = "delete from numbers where classname = '$classname'";
  $result2 = mysqli_query($connect,$sql2);
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
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
