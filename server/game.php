<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');


  $unit = isset($_POST['unit']) ? $_POST['unit'] : '';
  $number = isset($_POST['number']) ? $_POST['number'] : '';
  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';

  $android = strpos($_SERVER['HTTP_USER_AGENT'],"Android");

  if($unit != "" and $number !="" and $classname != ""){
    $sql = "select * from missions where classname = '$classname' && unit = '$unit' && number = '$number'";
    $sql_result = mysqli_query($connect,$sql); //sql문 실행
    $data = array();

    if($sql_result){
    while($info=mysqli_fetch_array($sql_result)){
      array_push($data, array('type'=>$info['type'],'mission'=>$info['mission'],'url'=>$info['url'],'ready'=>$info['ready'],'unitname'=>$info['unitname']));
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
         class 이름: <input type = "text" name = "classname" />
         unit: <input type = "number" name = "unit" />
         number: <input type = "number" name = "number" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
