<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $unit = isset($_POST['unit']) ? $_POST['unit'] : '';
  $unitname = isset($_POST['unitname']) ? $_POST['unitname'] : '';
  $number = isset($_POST['number']) ? $_POST['number'] : '';
  $mission = isset($_POST['mission']) ? $_POST['mission'] : '';
  $type = isset($_POST['type']) ? $_POST['type'] : '';
  $url = isset($_POST['url']) ? $_POST['url'] : '';
  $ready = isset($_POST['ready']) ? $_POST['ready'] : '';

  echo $url;

  if($classname !="" and $unit !="" and $number !="" and $mission !="" and $type !="" and $ready !=""){
    if($url=="") {
      $sql = "insert into missions(classname,unit,unitname,number,mission,type,ready) values('$classname',
      '$unit','$unitname','$number','$mission','$type','$ready')";
    }
    else {
      $sql = "insert into missions(classname,unit,unitname,number,mission,type,url,ready) values('$classname',
      '$unit','$unitname','$number','$mission','$type','$url','$ready')";
    }
    $result = mysqli_query($connect,$sql);

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
         classname: <input type = "text" name = "classname" />
         unit: <input type = "text" name = "unit" />
         unitname: <input type = "text" name = "unitname" />
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
