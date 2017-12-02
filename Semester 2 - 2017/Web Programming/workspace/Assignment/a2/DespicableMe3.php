<?php session_start(); ?>
<!DOCTYPE html>
<html>
    
<head>
<title>Despicable Me 3</title>
 <link rel='stylesheet' href='stylesheet.css' type='text/css' />
<link href="https://fonts.googleapis.com/css?family=Roboto:400,900" rel="stylesheet">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
</head>

<body>

<header>
<!-- Header -->
<div class="container">
        
</div>

<div class="item">
<center><img src="../img/cinemalogo.png" alt="Logo Of Silverado Cinema"></center>
</div>

</header>

<nav>
<!-- Navigation -->
<div class="container2">
    
<div class="table">
<ul>
  <li><a class="active" href="index.php">Home</a></li>
  <li><a href="showing.php">Now Showing</a></li>
  <li><a href="Contact.php">Contact Us</a></li>
</ul>
</div>

</div>
</nav>

<div class="left">
</div>

<div class="right">
</div>
 
<main>
<div class="container4">
                <center>

                    <div class="floatLeft">
                        <!-- This image was sourced from http://www.hoyts.com.au/movies/2017/despicable_me_3.aspx-->
                        <img src="../img/despicableme3.jpg" height="300px" width="300px" border="1px">
                        <br>

                </center>

                <center>
                    <h3>DESPICABLE ME 3</h3>
                    <br>
                </center>
             
                Despicable Me 3 is now being shown at the Silverado cinemas, it's a family friend movie perfect to distract the kids on the weekend or on school holidays!
                </br>
                <!-- sourced from http://www.shopatwestgatemall.com/trends/at-the-movies-june-30/11852/ -->
                Despicable Me 3 is about how minions wish that Gru will once again become a criminal after being fired from his job. However, Gru instead decides to travel to Freedonia to reunite with his twin brother for the first time ever. The twins plot a plan to take down the child star "Balthazar Bratt".
                </br>
                </br>
                </br>
                </br>
                </br>
                <center>
                <?php include_once("../PHP/button.php"); ?>
                </center>
            </div>
        </div>
         </div>
    <br>
    </main>

    <footer
        <!-- Footer -->
        <div class="container2">
           <?php include_once("../PHP/footer.php"); ?>
            </div>
<?php include_once("/home/eh1/e54061/public_html/wp/debug.php"); ?>
</body>
</html>
