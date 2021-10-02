<?php
    include_once 'databaseCon.php';
    $conn = connectionToDatabase();
    $id = $_POST['id'];
    $sql = "DELETE FROM Recipes where id = '$id'";
     if ($conn->query($sql)) {
         $conn->close();
         return true;
     } else {
         $conn->close();
         return false;
     }
?>
