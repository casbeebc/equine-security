<?php

/**
 * Simple Android registration id storage for testing purposes.
 */


include_once("config.php");

$connectionString = sprintf("pgsql:dbname=%s;host=127.0.0.1", DATABASE_NAME);

try {

    $db = new PDO($connectionString, USERNAME, PASSWORD);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $registration_id = $_REQUEST['registration_id'];
    
    if(!empty($registration_id)) {
                
        $st = $db->prepare( 'INSERT INTO registration_ids ( registration_id ) VALUES ( :registrationid)');
        $st->execute( array( ':registrationid' => $registration_id ) );
        
    }
    
    $db = null;
}
catch(PDOException $e)
{
    echo $e->getMessage();
}


?>