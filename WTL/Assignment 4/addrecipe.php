<?php
    include_once 'databaseCon.php';
    $conn = connectionToDatabase();
    $name = $_POST['name'];
    $int = $_POST['int'];
    $proc = $_POST['proc'];
    $sql = "INSERT INTO Recipes (recipe_name, recipe_int, recipe_proc)
	 VALUES ('$name','$int','$proc')";

    if ($conn->query($sql)) {
        $conn->close();
        return true;
    } else {
        $conn->close();
        return false;
    }
?>
