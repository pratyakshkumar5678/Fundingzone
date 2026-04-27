<?php
// Tentukan jalur absolut ke file config.php
$config_path = $_SERVER['DOCUMENT_ROOT'] . '/../config/Java/config.php';

// Include file config.php
include $config_path;

// Start the session
session_start();

// Database Configuration
$servername = DB_HOST;
$username = DB_USERNAME;
$password = DB_PASSWORD;
$dbname = DB_NAME;

// Create connection
$con = new mysqli($servername, $username, $password, $dbname);
// Set the timezone to Asia/Jakarta
date_default_timezone_set('Asia/Jakarta');

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

?>