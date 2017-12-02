<?php session_start(); 
?>
<?php $_SESSION['cart'][] = $_POST; ?>
<!DOCTYPE html>

<html>
    
<head>
    <title>Silverado Cinema</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
        })
        window.location.assign("../a2/Cart.php");
    </script>
</head>

    
</html>        