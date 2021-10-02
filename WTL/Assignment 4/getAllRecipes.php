<?php
    include 'databaseCon.php';
    $conn = connectionToDatabase();
    $query = "SELECT * FROM Recipes";
    $result = $conn->query($query);
    $conn->close();
    $data = array();
    if( mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_array($result)) {
          array_push($data, $row);
        }
    }
    echo json_encode($data);
    exit();
?>
