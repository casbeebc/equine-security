<?php
// java -cp .:`find libs/* -name "*.jar" | tr "\n" ":"` GCMserver
chdir("gcm");
exec('java -cp .:`find libs/* -name "*.jar" | tr "\n" ":"` GCMserver');

?>