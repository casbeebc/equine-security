<?php
// java -cp .:`find libs/* -name "*.jar" | tr "\n" ":"` GCMserver

exec('java -cp .:`find libs/* -name "*.jar" | tr "\n" ":"` gcm/GCMserver');

?>