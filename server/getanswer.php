<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $userid = isset($_POST['id']) ? $_POST['id'] : '';
  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $android = strpos($_SERVER['HTTP_USER_AGENT'],"Android");

  if($userid != "" and $classname != ""){
    $sql = "select * from answers where classname = '$classname' && id='$userid'";
    $sql_result = mysqli_query($connect,$sql); //sql문 실행

    $data = array();

    if($sql_result){
    while($info=mysqli_fetch_array($sql_result)){
      //array_push($data, array('unit'=>$info['unit']));
      $nowid = $info['id'];
      $unit = $info['unit'];
      $number = $info['number'];

      $sql2 = "select * from class where classname = '$classname' && id = '$nowid'";
      $sql_result2 = mysqli_query($connect,$sql2);
      $info2 = mysqli_fetch_array($sql_result2);

      $sql3 = "select * from missions where classname = '$classname' && unit = '$unit' && number = '$number'";
      $sql_result3 = mysqli_query($connect,$sql3);
      $info3 = mysqli_fetch_array($sql_result3);

      array_push($data, array('nick'=>$info2['nick'],'unit'=>$info['unit'],'number'=>$info['number'],'mission'=>$info3['mission'],'answer'=>$info['answer']));

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
         user id: <input type = "text" name = "id" />
         classname: <input type = "text" name = "classname" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
