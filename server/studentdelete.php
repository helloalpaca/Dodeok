<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $nick= isset($_POST['nick']) ? $_POST['nick'] : '';
  $classname= isset($_POST['classname']) ? $_POST['classname'] : '';
  $android = strpos($_SERVER['HTTP_USER_AGENT'],"Android");

  if($classname != "" and $nick !=""){
    $sql = "delete from class where nick = '$nick' && classname = '$classname'";
    $sql_result = mysqli_query($connect,$sql); //sql문 실행

    if($sql_result){
      echo "sql 성공";
    }
    else{
      echo "sql 실패";
    }
  }

  mysqli_close($connect);
?>

<?php

  $android = strpos($_SERVER['HTTP_USER_AGENT'],"Android");
  if(!$android){
?>

  <!DOCTYPE html>
  <html>
  <body>
    <form action="<?php $_PHP_SELF ?>" method="POST">
         classname: <input type = "text" name = "classname" />
         nick: <input type = "text" name = "nick" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
