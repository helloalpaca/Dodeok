<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $userid = isset($_POST['id']) ? $_POST['id'] : '';
  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $emotion = isset($_POST['emotion']) ? $_POST['emotion'] : '';
  $emotiontime = isset($_POST['emotiontime']) ? $_POST['emotiontime'] : '';

  echo $userid;
  echo $classname;
  echo $unit;
  echo $number;
  echo $answer;
  echo $emotiontime;


  if($userid !="" and $classname !="" and $emotion !="" and $emotiontime !=""){
    $sql = "update class set emotion='$emotion', emotiontime='$emotiontime' where id='$userid' && classname='$classname' ";
    $result = mysqli_query($connect,$sql);
    $sql2 = "insert into emotions(id,classname,emotion,emotiontime) values('$userid','$classname','$emotion','$emotiontime')";
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
         emotion : <input type = "text" name = "emotion" />
         emotiontime : <input type = "text" name = "emotiontime" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
