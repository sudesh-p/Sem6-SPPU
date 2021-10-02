<?php
    include_once 'databaseCon.php';
    $conn = connectionToDatabase();
    $id = $_POST['id'];
    $name = $_POST['name'];
    $int = $_POST['int'];
    $proc = $_POST['proc'];
    $sql = "UPDATE Recipes SET recipe_name = '$name', recipe_int = '$int', recipe_proc = '$proc' WHERE id = '$id'";
    if ($conn->query($sql)) {
        $conn->close();
        return true;
    } else {
        $conn->close();
        return false;
    }
?>
