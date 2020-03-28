<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $userid = isset($_POST['id']) ? $_POST['id'] : '';
  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $nick = isset($_POST['nick']) ? $_POST['nick'] : '';

  if($userid !="" and $classname !="" and $nick !=""){
    $sql = "select * from class where id='$userid'&&classname='$classname'";
    $result = mysqli_query($connect,$sql);
    $data = array();

    while($info=mysqli_fetch_array($result)){
      array_push($data, array('id'=>$info['id'],'classname'=>$info['classname'],'nick'=>$info['nick']));
    }

    if(count($data)==0){
      echo "inside result else";
      $sql2 = "insert into class (id,classname,nick,emotion,nowUnit,nowNum,status) values('$userid','$classname','$nick','emotion_1',1,1,'승인대기')";
      $result2 = mysqli_query($connect,$sql2);

      if($result2){
        echo "sql 성공";
      }
      else{
        echo "sql 실패";
        echo mysqli_error($connect);
      }
    }

    header('Content-Type: application/json; charset=utf8');
    $json = json_encode(array("users"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
    echo $json;
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
         class name: <input type = "text" name = "classname" />
         nick: <input type = "text" name = "nick" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
