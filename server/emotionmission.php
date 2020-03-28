<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $unit = isset($_POST['unit']) ? $_POST['unit'] : '';
  $number = isset($_POST['number']) ? $_POST['number'] : '';

  $android = strpos($_SERVER['HTTP_USER_AGENT'],"Android");

  if($classname != "" and $unit != "" and $number != ""){
    $sql = "select * from answers where classname = '$classname' && unit = '$unit' && number = '$number'";
    $sql_result = mysqli_query($connect,$sql); //sql문 실행
    $data = array();

    if($sql_result){
    while($info=mysqli_fetch_array($sql_result)){

      array_push($data, array('id'=>$info['id'],'answer'=>$info['answer'],'emotion'=>$info['emotion']));
    }
  }

  header('Content-Type: application/json; charset=utf8');
  $json = json_encode(array("users"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
  echo $json;
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
         classname : <input type = "text" name = "classname" />
         unit : <input type = "number" name = "unit" />
         number : <input type = "number" name = "number" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
