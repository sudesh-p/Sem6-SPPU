<?php
    function connectionToDatabase() {
        $servername = "localhost";
        $username = "web";
        $password = "tech";
        $database = "assignment4";
        $port = 3306;
        $conn = mysqli_connect($servername, $username, $password, $database, $port);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        return $conn;
    }
?>
