<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $userid = isset($_POST['id']) ? $_POST['id'] : '';
  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $feedid = isset($_POST['feedid']) ? $_POST['feedid'] : '';
  $feedback = isset($_POST['feedback']) ? $_POST['feedback'] : '';

  if($userid !="" and $classname !="" and $feedid !="" and $feedback !=""){
    $sql = "select * from class where id='$feedid' && classname='$classname'";
    $sql_result = mysqli_query($connect,$sql);
    $data = array();
    $info=mysqli_fetch_array($sql_result);

    $feedtime = $info['emotiontime'];
    echo $feedtime;

    $sql2 = "insert into feedback (id,feedid,classname,feedback,feedtime) values('$userid','$feedid','$classname','$feedback','$feedtime')";
    $sql2_result = mysqli_query($connect,$sql2);

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
         feed id: <input type = "text" name = "feedid" />
         feedback: <input type = "text" name = "feedback" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
