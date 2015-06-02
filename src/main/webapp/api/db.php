<?php
function getDB() {
	$dbhost="localhost";
	$dbuser="stw";
	$dbpass="stw";
	$dbname="slim";
	try {
	$dbConnection = new PDO("mysql:host=$dbhost;dbname=$dbname", $dbuser, $dbpass);	
	// echo "<h1>OK</h1>";
	$dbConnection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>";
    die();
	}
	return $dbConnection;
}
?>
