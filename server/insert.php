<?php

  $mysql_hostname = 'localhost';
  $mysql_username = 'root';
  $mysql_password = '123456';
  $mysql_database = 'dodeok';

  $connect = mysqli_connect($mysql_hostname,$mysql_username,$mysql_password,$mysql_database);

  mysqli_select_db($connect,$mysql_database) or die('DB선택 실패');

  $userid = isset($_POST['id']) ? $_POST['id'] : '';
  $classname = isset($_POST['classname']) ? $_POST['classname'] : '';
  $unit = isset($_POST['unit']) ? $_POST['unit'] : '';
  $number = isset($_POST['number']) ? $_POST['number'] : '';
  $answer = isset($_POST['answer']) ? $_POST['answer'] : '';
  $emotion = isset($_POST['emotion']) ? $_POST['emotion'] : '';
  $solved = isset($_POST['solved']) ? $_POST['solved'] : '';

  echo $userid;
  echo $classname;
  echo $unit;
  echo $number;
  echo $answer;

  //if($userid !="" and $classname !="" and $unit !="" and $number !="" and $answer !="" and $emotion !=""){

    $sql_before = "select * from answers where id = '$userid' && classname = '$classname' && unit = '$unit' && number = '$number'";
    $result_before = mysqli_query($connect,$sql_before);
    $num = $result_before->num_rows;
    echo $num;
    echo "\n";

    if($num > 0){
      $sqls = "update answers set answer='$answer', solved='$solved', emotion='$emotion'
      where id='$userid' && classname = '$classname' && unit = '$unit' && number = '$number'";
      $results = mysqli_query($connect,$sqls);
      if($results){
        echo "sql update 성공";
      } else{
        echo "sql update 실패";
      }

    }
    else{
      if($solved!=""){
        $sql = "insert into answers(id,classname,unit,number,answer,emotion,solved) values('$userid','$classname',
        '$unit','$number','$answer','$emotion','$solved')";
      }
      else{
        $sql = "insert into answers(id,classname,unit,number,answer,emotion) values('$userid','$classname',
        '$unit','$number','$answer','$emotion')";
      }
      $result = mysqli_query($connect,$sql);

      if($result){
        echo "sql 성공";
        $sql2 = "select * from numbers where classname = '$classname' && unit='$unit'";
        $result2 = mysqli_query($connect, $sql2);
        $row = mysqli_fetch_assoc($result2);
        echo $row;
        $maxNum = $row["numbers"];
        echo $maxNum;

        if($maxNum == $number){
          $sql3 = "update class set nowNum = 1, nowUnit = nowUnit+1 where id = '$userid' && classname = '$classname'";
          $result3 = mysqli_query($connect,$sql3);
        }
        if($number < $maxNum){
          $sql3 = "update class set nowNum = nowNum+1 where id = '$userid' && classname = '$classname'";
          $result3 = mysqli_query($connect,$sql3);
        }
      }
      else{
        echo "sql 실패";
        echo mysqli_error($connect);
      }
    }
  //}
  //else{
    //echo "데이터를 입력하세요";
  //}

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
         unit: <input type = "text" name = "unit" />
         number: <input type = "text" name = "number" />
         answer: <input type = "text" name = "answer" />
         emotion: <input type = "text" name = "emotion" />
         solved: <input type = "text" name = "solved" />
         <input type = "submit" />
    </form>
  </body>
  </html>
  <?php
  }
?>
