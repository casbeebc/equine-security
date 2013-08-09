<?php

/**
 * Simple Android registration id storage for testing purposes.
 */


include_once("config.php");

$connectionString = sprintf("pgsql:dbname=%s;host=127.0.0.1", DATABASE_NAME);

try {

    $db = new PDO($connectionString, USERNAME, PASSWORD);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $registration_id = "";
    $sql = "SELECT registration_id FROM registration_ids WHERE created = (SELECT MAX(created) FROM registration_ids)";
    foreach ($db->query($sql) as $row) {
        $registration_id = $row['registration_id'];
    }                        
    echo $registration_id;
    $db = null;
}
catch(PDOException $e)
{
    echo $e->getMessage();
}


?>