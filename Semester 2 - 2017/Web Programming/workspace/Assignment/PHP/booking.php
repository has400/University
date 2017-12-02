<?php
session_start();
$_SESSION['cart'][] = $_POST;
echo "<pre>";
   print_r($_SESSION['cart']);
   
  
echo "</pre>";
?>


