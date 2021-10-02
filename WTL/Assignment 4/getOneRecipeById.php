<?php
    include 'databaseCon.php';
    $conn = connectionToDatabase();
    $id = $_POST['id'];
    $query = "SELECT * FROM Recipes WHERE id = '$id'";
    $result = $conn->query($query);
    $conn->close();
    $data = array();
    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_array($result)) {
            $data[] = $row["recipe_name"];
            $data[] = $row["recipe_int"];
            $data[] = $row["recipe_proc"];
        }
    }
    echo json_encode($data);
    exit();
?>
